package org.daubin.pistorms;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
        // PiStorms pi = new PiStorms();
        PiStormsCom com = new PiStormsCom();

        System.out.println("Vendor: " + com.getVendorName());
        System.out.println("Device ID: " + com.getDeviceId());
        System.out.println("Firmware Version: " + com.getFirmwareVersion());
        System.out.println("Battery voltage: " + com.getBatteryVoltage());

        PSSensor touchSensor = com.getSensor(PSSensorPort.BBS1);
        touchSensor.resetTouches();
        touchSensor.setType(SensorType.EV3_SWITCH);

        PSSensor colorSensor = com.getSensor(PSSensorPort.BBS2);

        for (int i = 0; i < 10000; i++) {
            Color color = colorSensor.getColorSensorEV3();
            System.err.println("Color: " + color);

            touchSensor.retrieve(1);

            System.err.println("Touched: " + touchSensor.isFirstByteEnabled());

            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
    }

}
