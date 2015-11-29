package org.daubin.pistorms;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws Exception {
		//PiStorms pi = new PiStorms();
		PiStormsCom com = new PiStormsCom();
		
		System.out.println("Vendor: " + com.getVendorName());
		System.out.println("Device ID: " + com.getDeviceId());
		System.out.println("Firmware Version: " + com.getFirmwareVersion());
		System.out.println("Battery voltage: " + com.getBatteryVoltage());
		
		PSSensor sensor = com.getSensor(PSSensorPort.BBS1);
		sensor.resetTouches();
		
		for (int i = 0; i < 10000; i++) {
			sensor.setType(SensorType.EV3_SWITCH);

			sensor.retrieve();
			
			System.err.println("Touched: " + sensor.isByteOne());
			
			Thread.sleep(TimeUnit.SECONDS.toMillis(1));
		}
	}

}
