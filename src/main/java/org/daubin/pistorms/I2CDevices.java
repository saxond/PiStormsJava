package org.daubin.pistorms;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.pi4j.io.i2c.I2CDevice;

/**
 * Helper methods for reading data from an I2CDevice.
 * @author sdaubin
 *
 */
public class I2CDevices {
    private I2CDevices() {
    }
    
    /**
     * Read a long from the given address.
     */
    public static long readLong(I2CDevice device, int address) throws IOException {
        byte[] bytes = new byte[Long.BYTES];
        
        device.read(address, bytes, 0, bytes.length);
        
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip(); 
        return buffer.getLong();
    }
    
    /**
     * Read a string of the given length from the given address.
     */
    public static String readString(I2CDevice device, int address, int length) throws IOException {
        byte[] buffer = new byte[length];
        device.read(address, buffer, 0, buffer.length);

        return new String(buffer);
    }

}
