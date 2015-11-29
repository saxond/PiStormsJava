package org.daubin.pistorms;

import java.io.IOException;

import com.pi4j.io.i2c.I2CDevice;

public class PSSensor implements Constants {

    private final PSSensorPort port;
    private final I2CDevice i2cDevice;
    private SensorType type;
    private byte[] data = new byte[32];

    public PSSensor(PSSensorPort port, I2CDevice i2cDevice) {
        this.port = port;
        this.i2cDevice = i2cDevice;
        this.type = SensorType.NONE;
    }

    /**
     * Retrieves all 32 bytes of data.
     * 
     * @throws IOException
     */
    public void retrieve() throws IOException {
        retrieve(32);
    }

    public int readReadyValue() throws IOException {
        return i2cDevice.read(PS_EV_Ready + port.getMode());
    }

    /**
     * Reads the specified amount of data into the {@link #data} array. length
     * cannot be greater than 32.
     */
    public void retrieve(int length) throws IOException {
        if (length > data.length) {
            throw new IOException("Requested length " + length + " exceeds data buffer size of " + data.length);
        }
        i2cDevice.read(PS_EV_Data + port.getMode(), data, 0, length);

        /*
         * int ready = i2cDevice.read(PS_EV_Ready + port.getMode()); byte[] id =
         * new byte[16]; i2cDevice.read(PS_EV_SensorID + port.getMode(), id, 0,
         * id.length);
         * 
         * int mode = i2cDevice.read(PS_EV_Mode + port.getMode()); int length =
         * i2cDevice.read(PS_EV_Length + port.getMode());
         * 
         * byte[] data = new byte[32]; i2cDevice.read(PS_EV_Data +
         * port.getMode(), data, 0, data.length); //PS_EV_Data + port.getMode(),
         * data, 0, data.length);
         * 
         * System.err.println(port + " Ready : " + ready + " Id: " + new
         * String(id) + " Length: " + length + " Data: " + new String(data));
         * System.err.println("touch: " + data[0]);
         */

        // self.EV3Cache[0] = self.bank.readByte(PS_S1EV_Ready)
        // self.EV3Cache[1] = self.bank.readArray(PS_S1EV_SensorID,16)
        // self.EV3Cache[2] = self.bank.readByte(PS_S1EV_Mode)
        // self.EV3Cache[3] = self.bank.readByte(PS_S1EV_Length)
        // self.EV3Cache[4] = self.bank.readArray(PS_S1EV_Data,32)
    }

    /**
     * Returns the 32 byte wide data array.
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Returns true if the first data byte equals 1.
     */
    public boolean isFirstByteEnabled() {
        return data[0] == 1;
    }

    public void setModeEV3(byte mode) throws IOException {
        i2cDevice.write(PS_EV_Mode + port.getMode(), mode);
    }

    public void resetTouches() throws IOException {
        setType(SensorType.EV3_SWITCH);
        i2cDevice.write(PS_EV_Data + port.getMode() + 1, (byte) 0);
    }

    public void setType(SensorType type) throws IOException {
        if (!type.equals(this.type)) {
            i2cDevice.write(port.getMode(), type.get());
        }
        /*
         * if(self.type != self.PS_SENSOR_TYPE_CUSTOM): time.sleep(1)
         */
    }

    public byte getAmbientLightSensorEV3() throws IOException {
        setType(SensorType.EV3);
        setModeEV3((byte) 1);
        retrieve(1);

        return data[0];
    }

    /**
     * Reads the color from an EV3 color sensor.
     */
    public Color getColorSensorEV3() throws IOException {
        setType(SensorType.EV3);
        setModeEV3((byte) 2);
        retrieve(1);

        return Color.get(data[0]);
    }
}
