package org.daubin.pistorms;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.daubin.mindstorms.MindstormsCom;
import org.daubin.mindstorms.motors.Motor;
import org.daubin.mindstorms.motors.MotorPort;
import org.daubin.mindstorms.sensors.Sensor;
import org.daubin.mindstorms.sensors.SensorPort;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

public class PiStormsCom implements Constants, MindstormsCom {

    private final Map<Bank, I2CDevice> banks;

    private final Map<SensorPort, Sensor> sensors;
    private final Map<MotorPort, Motor> motors;

    public PiStormsCom() throws IOException {
        final I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);

        banks = ImmutableMap.of(Bank.A, bus.getDevice(PS_A_ADDRESS >> 1), Bank.B, bus.getDevice(PS_B_ADDRESS >> 1));

        // sanity check - this will throw if things aren't configured properly
        banks.get(Bank.A).read(PS_BattV);

        for (I2CDevice dev : banks.values()) {
            dev.write(PS_Command, R);
        }

        Map<SensorPort, Sensor> sensors = Maps.newHashMap();
        for (PSSensorPort port : PSSensorPort.values()) {
            sensors.put(port.getSensorPort(), new PSSensor(port, banks.get(port.getBank())));
        }
        this.sensors = Collections.unmodifiableMap(sensors);
        
        Map<MotorPort, Motor> motors = Maps.newHashMap();
        for (PSMotorPort port : PSMotorPort.values()) {
            motors.put(port.getMotorPort(), new PSMotor(port, banks.get(port.getBank())));
        }
        this.motors = Collections.unmodifiableMap(motors);
    }

    @Override
    public float getBatteryVoltage() throws IOException {
        return 0.04f * banks.get(Bank.A).read(PS_BattV);
    }

    public String readBankAString(int address, int length) throws IOException {
        return I2CDevices.readString(banks.get(Bank.A), address, length);
    }

    /**
     * Read the firmware version of the i2c device
     */
    @Override
    public String getFirmwareVersion() throws IOException {
        return readBankAString(0x00, 8);
    }

    /**
     * Read the vendor name of the i2c device
     */
    @Override
    public String getVendorName() throws IOException {
        return readBankAString(0x08, 8);
    }

    /**
     * Read the i2c device id
     */
    @Override
    public String getDeviceId() throws IOException {
        return readBankAString(0x10, 8);
    }

    public void executeCommand(Bank bank, byte command) throws IOException {
        banks.get(bank).write(command);
    }

    @Override
    public Map<SensorPort, Sensor> getSensors() {
        return sensors;
    }

    public Map<MotorPort, Motor> getMotors() {
        return motors;
    }

    @Override
    public String getDiagnostics() throws IOException {
        StringBuilder builder = new StringBuilder().
                append("Vendor: ").append(getVendorName()).
                append("\nDevice ID: ").append(getDeviceId()).
                append("\nFirmware Version: ").append(getFirmwareVersion()).
                append("\nBattery voltage: ").append(getBatteryVoltage()).append('\n');
        return builder.toString();
    }

    // static void write(I2CDevice device, byte... bytes) throws IOException {
    // device.write(bytes, 0, bytes.length);
    // }
}
