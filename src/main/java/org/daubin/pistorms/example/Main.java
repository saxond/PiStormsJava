package org.daubin.pistorms.example;

import java.util.concurrent.TimeUnit;

import org.daubin.mindstorms.MindstormsCom;
import org.daubin.mindstorms.motors.Motor;
import org.daubin.mindstorms.motors.MotorPort;
import org.daubin.mindstorms.sensors.Color;
import org.daubin.mindstorms.sensors.EV3ColorSensor;
import org.daubin.mindstorms.sensors.SensorPort;
import org.daubin.mindstorms.sensors.TouchSensor;
import org.daubin.pistorms.PiStormsCom;

public class Main {

    /**
     * Example usage of the PiStormsJava library.
     */
    public static void main(String[] args) throws Exception {
        MindstormsCom com = new PiStormsCom();

        System.out.println(com.getDiagnostics());
        
        Motor motor = com.getMotors().get(MotorPort.BAM1);
        motor.setSpeed(50);
        
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        
        motor.setSpeed(100);
        
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        
        motor.brake();

        TouchSensor touchSensor = com.getSensors().get(SensorPort.BBS1).getTouchSensor();

        EV3ColorSensor colorSensor = com.getSensors().get(SensorPort.BBS2).getEV3ColorSensor();

        for (int i = 0; i < 10000; i++) {
            Color color = colorSensor.getColor();
            System.err.println("Color: " + color);

            System.err.println("Touched: " + touchSensor.isSet());

            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
    }

}
