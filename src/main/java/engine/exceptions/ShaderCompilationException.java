package engine.exceptions;

public class ShaderCompilationException extends Exception{
    public ShaderCompilationException(String shaderPath) {
        super("Failed to compile the shader at " + shaderPath);
    }

    public ShaderCompilationException(String shaderPath, String log) {
        super("Failed to compile the shader at " + shaderPath + ". " + log);
    }
}
