package org.daubin.pistorms;

import java.io.IOException;

import org.daubin.mindstorms.motors.Motor;

import com.pi4j.io.i2c.I2CDevice;

class PSMotor implements Motor, Constants {

    private final PSMotorPort port;
    private final I2CDevice i2cDevice;

    protected PSMotor(PSMotorPort port, I2CDevice i2cDevice) {
        this.port = port;
        this.i2cDevice = i2cDevice;
    }
    
    @Override
    public long getPosition() throws IOException {
        return I2CDevices.readLong(i2cDevice, port.getPositionAddress());
    }
        
    @Override
    public void brake() throws IOException {
        i2cDevice.write(PS_Command, port.getBrakeAddress());
    }
    
    @Override
    public void floatMotor() throws IOException {
        i2cDevice.write(PS_Command, port.getFloatAddress());
    }
    
    @Override
    public void hold() throws IOException {
        int ctrl =  PS_CONTROL_BRK |
                    PS_CONTROL_ON |
                    PS_CONTROL_RELATIVE |
                    PS_CONTROL_TACHO | 
                    PS_CONTROL_GO;
        
        byte[] bytes = new byte[] {0,0,0,0};
        i2cDevice.write(port.getSetPointAddress(), bytes, 0, bytes.length);
        i2cDevice.write(port.getCDMAAddress(), (byte) ctrl);

    }
    
    @Override
    public void setSpeed(int speed) throws IOException {
        if (0 == speed) {
            floatMotor();
            return;
        }
        
        int ctrl = PS_CONTROL_SPEED | PS_CONTROL_GO;
        
        byte[] buffer = new byte[] {(byte)speed, 0, 0, (byte)ctrl};
        i2cDevice.write(port.getSpeedAddress(), buffer, 0, buffer.length);
    }
    
    @Override
    public void runForXSeconds(int durationInSeconds, int speed, boolean brakeOnCompletion) throws IOException {
        int ctrl = PS_CONTROL_SPEED | PS_CONTROL_TIME | PS_CONTROL_GO;
        if (brakeOnCompletion) {
            ctrl |= PS_CONTROL_BRK;
        }
        byte[] buffer = new byte[] {(byte)speed, (byte)durationInSeconds, 0, (byte)ctrl};
        i2cDevice.write(port.getSpeedAddress(), buffer, 0, buffer.length);
    }
    
    private int getStatus() throws IOException {
        return i2cDevice.read(port.getStatusAddress());
    }
    
    private boolean isSet(int bitNumber) throws IOException {
        return 1 == ((getStatus() >> bitNumber) & 1);
    }

    @Override
    public boolean isStalled() throws IOException {
        return isSet(7);
    }

    @Override
    public boolean isOverloaded() throws IOException {
        return isSet(5);
    }
    
}

