package engine;

import engine.exceptions.WindowCreationException;
import engine.rendering.Square;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final int width, height;
    private final String title;

    private long windowRef;

    public Window(int width, int height, String title) throws WindowCreationException {
        this.width = width;
        this.height = height;

        this.title = title;
        windowRef = -1;

        prepare();
        attachCallbacks();
    }

    private void prepare() throws WindowCreationException {
        if(!glfwInit()){
            throw new WindowCreationException("Failed to initialize GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

        windowRef = glfwCreateWindow(width, height, title, NULL, NULL);
        if(windowRef == NULL){
            throw new WindowCreationException("Failed to create window object.");
        }

        // Print errors to the console.
        GLFWErrorCallback.createPrint(System.err).set();
    }

    private void attachCallbacks(){

    }

    private GLFWVidMode getPrimaryMonitor(){
        return glfwGetVideoMode(glfwGetPrimaryMonitor());
    }

    private void loop(){
        if(windowRef <= NULL) return;

        glClearColor(1.0f, 1.0f, 1.0f, 1f);
        glEnable(GL_DEPTH_TEST);

        Square sq = new Square();

        while (!glfwWindowShouldClose(windowRef)){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            sq.render();
            glfwSwapBuffers(windowRef); // Swap buffers to reflect all changes

            glfwPollEvents();
        }
    }

    private void destroy(){
        GLFWErrorCallback.createPrint(null).set();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowRef);
        glfwDestroyWindow(windowRef);

        // Terminate GLFW and free the error callback
        glfwTerminate();
    }

    /**
     * Puts the window in the center of the screen.
     */
    public Window centerOnScreen(){
        GLFWVidMode primaryMonitor = getPrimaryMonitor();

        setPosition((primaryMonitor.width() - width) / 2, (primaryMonitor.height() - height) / 2);

        return this;
    }

    /**
     * Sets the position of the window on the screen
     * @param x - horizontal position in pixels
     * @param y - vertical position in pixels
     */
    public Window setPosition(int x, int y) {
        glfwSetWindowPos(windowRef, x, y);

        return this;
    }

    /**
     * Use for smoother FPS
     */
    public Window enableVSync() {
        glfwSwapInterval(1);

        return this;
    }

    /**
     * Show the window and run the update loop
     */
    public void run(){
        glfwMakeContextCurrent(windowRef);
        GL.createCapabilities();
        glfwShowWindow(windowRef);

        loop();
        destroy();
    }

    public static void main(String[] args) {
        try {
            Window window = new Window(500, 500, "TEST");
            window.centerOnScreen().run();
        } catch (WindowCreationException e) {
            e.printStackTrace();
        }
    }

}
