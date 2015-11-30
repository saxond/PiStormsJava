package org.daubin.mindstorms.sensors;

import java.io.IOException;

public interface Sensor {

    byte[] retrieve(int length) throws IOException;

    byte[] retrieve() throws IOException;

    void resetTouches() throws IOException;

    boolean isFirstByteEnabled();
    
    EV3ColorSensor getEV3ColorSensor() throws IOException;
    
    TouchSensor getTouchSensor() throws IOException;

}
