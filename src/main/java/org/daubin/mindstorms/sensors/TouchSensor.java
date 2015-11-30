package org.daubin.mindstorms.sensors;

import java.io.IOException;

public interface TouchSensor {
    
    /**
     * Returns true if the touch sensor is depressed.
     */
    boolean isSet() throws IOException;
}
