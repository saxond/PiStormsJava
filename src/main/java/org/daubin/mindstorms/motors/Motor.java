package org.daubin.mindstorms.motors;

import java.io.IOException;

public interface Motor {

    /**
     * Reads the encoder position of the motor.
     * @return
     * @throws IOException
     */
    long getPosition() throws IOException;

    /**
     * Stop the motor with abruptly with brake.
     * @throws IOException
     */
    void brake() throws IOException;

    /**
     * Stop the motor smoothly with float.
     * @throws IOException
     */
    void floatMotor() throws IOException;
    
    /**
     * Stop the motor abruptly and hold the current position.
     * @throws IOException
     */
    void hold() throws IOException;
    
    /**
     * Run the motor(s) at a set speed for an unlimited duration.
     * @param speed
     * @throws IOException
     */
    void setSpeed(int speed) throws IOException;
    
    /**
     * Returns true if the motor is stalled.
     */
    boolean isStalled() throws IOException;
    
    /**
     * Returns true if the motor is overloaded.
     */
    boolean isOverloaded() throws IOException;

    /**
     * Run the motor at the given speed for a given time in seconds.
     */
    void runForXSeconds(int durationInSeconds, int speed, boolean brakeOnCompletion) throws IOException;
}
