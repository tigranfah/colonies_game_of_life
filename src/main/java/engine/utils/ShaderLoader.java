package engine.utils;

import engine.exceptions.ShaderCompilationException;

import static org.lwjgl.opengl.GL30.*;
import java.io.IOException;

/**
 * Utility class to easily load add attach shaders
 */
public class ShaderLoader {

    private int shaderRef;
    private final String shaderPath;

    /**
     *
     * @param shaderPath - relative path to GLSL shader
     * @param shaderType - type of the shader, GL_VERTEX_ARRAY or GL_FRAGMENT_ARRAY
     */
    public ShaderLoader(String shaderPath, int shaderType){
        this.shaderPath = shaderPath;

        String shaderSource = "";
        try{
            shaderSource = FileLoader.extractContentAsString(shaderPath);
        }catch (IOException e){
            System.err.println("Could not extract shader content.");
            e.printStackTrace();
        }

        shaderRef = glCreateShader(shaderType);
        glShaderSource(shaderRef, shaderSource);
    }

    /**
     * Compile shader to machine code readable by GPU
     *
     * @return current instance for chaining
     */
    public ShaderLoader compile() throws ShaderCompilationException{
        glCompileShader(shaderRef);

        if(glGetShaderi(shaderRef, GL_COMPILE_STATUS) == GL_FALSE){

//            throw new ShaderCompilationException(toString(), glGetShaderInfoLog());
            throw new ShaderCompilationException(toString());
        }

        return this;
    }

    /**
     * Create and link a new GL program
     * Attach compiled shader to GL program
     *
     * @return current instance for chaining
     */
    public ShaderLoader attachToProgram(){
        int programRef = glCreateProgram();
        attachToProgram(programRef);

        glLinkProgram(programRef);

        return this;
    }

    /**
     * Attach compiled shader to specified GL program
     *
     * @param programRef - reference to program object
     * @return current instance for chaining
     */
    public ShaderLoader attachToProgram(int programRef){
        glAttachShader(programRef, shaderRef);

        return this;
    }

    /**
     * Delete the compiled shader as it is already attached
     *
     */
    public void cleanup(){
        shaderRef = -1;
        glDeleteShader(shaderRef);
    }

    public String toString(){
        return "A shader at path " + shaderPath;
    }

}
