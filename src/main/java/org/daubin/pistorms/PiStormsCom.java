package org.daubin.pistorms;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class PiStormsCom implements Constants {

    final Map<Bank, I2CDevice> banks;
    /*
     * static final int bankA = mindsensors_i2c(PS_A_ADDRESS >> 1); static final
     * int bankB = mindsensors_i2c(PS_B_ADDRESS >> 1);
     * 
     * static final PSMotor BAM1 = PSMotor(bankA,1); static final PSMotor BAM2 =
     * PSMotor(bankA,2); static final PSMotor BBM1 = PSMotor(bankB,1); static
     * final PSMotor BBM2 = PSMotor(bankB,2);
     * 
     * static final PSSensor BAS1 = PSSensor(bankA,1); static final PSSensor
     * BAS2 = PSSensor(bankA,2); static final PSSensor BBS1 = PSSensor(bankB,1);
     * static final PSSensor BBS2 = PSSensor(bankB,2);
     */

    private final Map<PSSensorPort, PSSensor> sensors;

    public PiStormsCom() throws IOException {
        final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);

        banks = ImmutableMap.of(Bank.A, bus.getDevice(PS_A_ADDRESS >> 1), Bank.B, bus.getDevice(PS_B_ADDRESS >> 1));

        // sanity check - this will throw if things aren't configured properly
        banks.get(Bank.A).read(PS_BattV);

        for (I2CDevice dev : banks.values()) {
            dev.write(PS_Command, R);
        }

        Map<PSSensorPort, PSSensor> sensors = Maps.newHashMap();
        for (PSSensorPort port : PSSensorPort.values()) {
            sensors.put(port, new PSSensor(port, banks.get(port.getBank())));
        }
        this.sensors = Collections.unmodifiableMap(sensors);
    }

    public float getBatteryVoltage() throws IOException {
        return 0.04f * banks.get(Bank.A).read(PS_BattV);
    }

    public String readBankAString(int address, int length) throws IOException {
        byte[] buffer = new byte[length];
        banks.get(Bank.A).read(address, buffer, 0, buffer.length);

        return new String(buffer);
    }

    /**
     * Read the firmware version of the i2c device
     * 
     * @return
     * @throws IOException
     */
    public String getFirmwareVersion() throws IOException {
        return readBankAString(0x00, 8);
    }

    /**
     * Read the vendor name of the i2c device
     * 
     * @return
     * @throws IOException
     */
    public String getVendorName() throws IOException {
        return readBankAString(0x08, 8);
    }

    /**
     * Read the i2c device id
     * 
     * @return
     * @throws IOException
     */
    public String getDeviceId() throws IOException {
        return readBankAString(0x10, 8);
    }

    public void executeCommand(Bank bank, byte command) throws IOException {
        banks.get(bank).write(command);
    }

    public PSSensor getSensor(PSSensorPort port) {
        return sensors.get(port);
    }

    public Map<PSSensorPort, PSSensor> getSensors() {
        return sensors;
    }

    // static void write(I2CDevice device, byte... bytes) throws IOException {
    // device.write(bytes, 0, bytes.length);
    // }
}
