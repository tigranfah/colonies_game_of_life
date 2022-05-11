package engine.graphics;

import engine.exceptions.GLException;
import engine.utils.BufferUtils;

import static org.lwjgl.opengl.GL30.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * A class to manage a single vertex array and its parameters
 */
public class VertexArray {

    /**
     * Buffered positions of each vertex of the primitive object
     * and the order in which those should be rendered
     */
    private final FloatBuffer vertices;
    private final ByteBuffer indices;

    private final byte numberOfVertices;

    private final int vertexArrayRef;

    public VertexArray(float[] v, byte[] i) throws GLException {
        vertices = BufferUtils.toFloatBuffer(v);
        indices = BufferUtils.toByteBuffer(i);
        numberOfVertices = (byte) i.length;

        vertexArrayRef = glGenVertexArrays();
        init();
    }

    public void render(){
        glBindVertexArray(vertexArrayRef);
        glDrawArrays(GL_TRIANGLES, 0, numberOfVertices);

        cleanup();
    }

    private void init() throws GLException {
        glBindVertexArray(vertexArrayRef);
        prepareVertices();
        prepareIndices();

        final int err = glGetError();
        if(err != GL_NO_ERROR){
            throw new GLException(err);
        }

        cleanup();
    }

    private void prepareVertices(){
        final int vertexBufferRef = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferRef);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        // For each vertex we specify n float coordinates
        glVertexAttribPointer(0, numberOfVertices, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
    }

    private void prepareIndices(){
        final int indicesBufferRef = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferRef);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
    }

    private void cleanup(){
        glBindVertexArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
