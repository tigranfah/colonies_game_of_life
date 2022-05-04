package engine.graphics;

import engine.exceptions.ShaderCompilationException;
import engine.utils.ShaderLoader;

import static org.lwjgl.opengl.GL30.*;

public class ShaderProgram {

    public static final String BASE_PATH = "resources/shaders/";

    private final ShaderLoader vertexShader, fragmentShader;

    private final int programRef;

    private boolean enabled;

    public ShaderProgram(String vertexPath, String fragmentPath){
        vertexShader = new ShaderLoader(BASE_PATH + vertexPath, GL_VERTEX_SHADER);
        fragmentShader = new ShaderLoader(BASE_PATH + fragmentPath, GL_FRAGMENT_SHADER);

        programRef = glCreateProgram();

        init();
        enabled = false;
    }

    public void init(){
        try{
            vertexShader.compile().attachToProgram(programRef);
            fragmentShader.compile().attachToProgram(programRef);
        }catch (ShaderCompilationException e){
            e.printStackTrace();
        }

        glLinkProgram(programRef);
        glValidateProgram(programRef);

        vertexShader.cleanup();
        fragmentShader.cleanup();
    }

    public void enable(){
        enabled = true;
        glUseProgram(programRef);
    }

    public void disable(){
        enabled = false;
        glUseProgram(0);
    }
}
