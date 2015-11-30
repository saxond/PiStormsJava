package org.daubin.mindstorms.sensors;

import java.io.IOException;

public interface EV3ColorSensor {
    /**
     * Reads the color value from an EV3 color sensor.
     */
    Color getColor() throws IOException;

    byte getAmbientLightValue() throws IOException;
}
