package engine.exceptions;

public class ShaderCompilationException extends Exception{
    public ShaderCompilationException(String shaderPath) {
        super("Failed to compile the shader at " + shaderPath);
    }
}
