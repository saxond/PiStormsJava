package org.daubin.mindstorms;

import java.io.IOException;
import java.util.Map;

import org.daubin.mindstorms.motors.Motor;
import org.daubin.mindstorms.motors.MotorPort;
import org.daubin.mindstorms.sensors.Sensor;
import org.daubin.mindstorms.sensors.SensorPort;

public interface MindstormsCom {

    float getBatteryVoltage() throws IOException;

    String getFirmwareVersion() throws IOException;

    String getVendorName() throws IOException;

    String getDeviceId() throws IOException;

    Map<SensorPort, Sensor> getSensors();
    
    Map<MotorPort, Motor> getMotors();

    String getDiagnostics() throws IOException;

}
