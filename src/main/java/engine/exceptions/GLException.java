package engine.exceptions;

public class GLException extends Exception{

    public GLException(int errorCode){
        super("OpenGL error has occurred with code: " + errorCode);
    }

}
