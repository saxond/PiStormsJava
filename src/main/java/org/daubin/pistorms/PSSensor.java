package org.daubin.pistorms;

import java.io.IOException;

import com.pi4j.io.i2c.I2CDevice;

public class PSSensor {

	private final PSSensorPort port;
	private final I2CDevice i2cDevice;
	private SensorType type;
	private byte[] data = new byte[32];

	public PSSensor(PSSensorPort port, I2CDevice i2cDevice) {
		this.port = port;
		this.i2cDevice = i2cDevice;
		this.type = SensorType.NONE;
	}
	
	public void retrieve() throws IOException {
		i2cDevice.read(PiStormsCom.PS_EV_Data + port.getMode(), data, 0, data.length);
		
		/*
		int ready = i2cDevice.read(PiStormsCom.PS_EV_Ready + port.getMode());
		byte[] id = new byte[16];
		i2cDevice.read(PiStormsCom.PS_EV_SensorID + port.getMode(), id, 0, id.length);
		
		int mode = i2cDevice.read(PiStormsCom.PS_EV_Mode + port.getMode());
		int length = i2cDevice.read(PiStormsCom.PS_EV_Length + port.getMode());
		
		byte[] data = new byte[32];
		i2cDevice.read(PiStormsCom.PS_EV_Data + port.getMode(), data, 0, data.length);
				//PiStormsCom.PS_EV_Data + port.getMode(), data, 0, data.length);
		
		System.err.println(port + " Ready : " + ready + " Id: " + new String(id) + " Length: " + length + " Data: " + new String(data));
		System.err.println("touch: " + data[0]);
		*/
		
        //self.EV3Cache[0] = self.bank.readByte(PiStormsCom.PS_S1EV_Ready)
        //self.EV3Cache[1] = self.bank.readArray(PiStormsCom.PS_S1EV_SensorID,16)
        //self.EV3Cache[2] = self.bank.readByte(PiStormsCom.PS_S1EV_Mode)
        //self.EV3Cache[3] = self.bank.readByte(PiStormsCom.PS_S1EV_Length)
        //self.EV3Cache[4] = self.bank.readArray(PiStormsCom.PS_S1EV_Data,32)
	}
	
	public byte[] getData() {
		return data;
	}
	
	public boolean isByteOne() {
		return data[0] == 1;
	}

	public void resetTouches() throws IOException {
		setType(SensorType.EV3_SWITCH);
		i2cDevice.write(PiStormsCom.PS_EV_Data + port.getMode() + 1, (byte)0);
	}
	
	public void setType(SensorType type) throws IOException {
		if (!type.equals(this.type)) {
			i2cDevice.write(port.getMode(), type.getTypeId());
		}
		/*
        if(self.type != self.PS_SENSOR_TYPE_CUSTOM):
            time.sleep(1)
            */
	}

}
