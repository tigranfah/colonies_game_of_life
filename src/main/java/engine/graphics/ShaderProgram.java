package engine.graphics;

import engine.exceptions.ShaderCompilationException;
import engine.utils.ShaderLoader;

import static org.lwjgl.opengl.GL30.*;

public class ShaderProgram {

    public static final String BASE_PATH = "resources/";

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
        try {
            vertexShader.compile().attachToProgram(programRef);
            fragmentShader.compile().attachToProgram(programRef);
        } catch (ShaderCompilationException e) {
            e.printStackTrace();
        }

        glLinkProgram(programRef);
        glValidateProgram(programRef);

        final int status = glGetProgrami(programRef, GL_LINK_STATUS);
        final int validationStatus = glGetProgrami(programRef, GL_VALIDATE_STATUS);
        System.out.println(status);
        System.out.println(validationStatus);
        System.out.println(glGetProgramInfoLog(programRef));

        vertexShader.cleanup();
        fragmentShader.cleanup();
    }

    /**
     * Enable program and attach shaders
     */
    public void enable(){
        enabled = true;
        glUseProgram(programRef);
    }

    /**
     * Release program
     */
    public void disable(){
        enabled = false;
        glUseProgram(0);
    }
}
