package engine.utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * A static class to easily convert arrays to buffers
 */
public class BufferUtils {

    private BufferUtils(){

    }

    public static FloatBuffer toFloatBuffer(float[] array){
        // Each float takes 4 bytes
        FloatBuffer bufferedArray = org.lwjgl.BufferUtils.createFloatBuffer(array.length * 4);
        bufferedArray.put(array);

        return bufferedArray;
    };

    public static ByteBuffer toByteBuffer(byte[] array){
        ByteBuffer bufferedArray = org.lwjgl.BufferUtils.createByteBuffer(array.length);
        bufferedArray.put(array);

        return bufferedArray;
    };

}
