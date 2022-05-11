package engine.rendering;

import engine.exceptions.GLException;
import engine.graphics.ShaderProgram;
import engine.graphics.VertexArray;

public class Square implements Renderable{

    private ShaderProgram program;
    private VertexArray vertices;

    public Square() {
        try {
            program = new ShaderProgram("cell.vert", "cell.frag");
            vertices = new VertexArray(
                    new float[]{
                            -0.5f, -0.5f * (float) Math.sqrt(3) / 3, 0f,
                            0.5f, -0.5f * (float) Math.sqrt(3) / 3, 0f,
                            0f, 0.5f * (float) Math.sqrt(3) * 2 / 3, 0f,
                    },
                    new byte[]{0, 1, 2}
            );
        }catch (GLException e){
            e.printStackTrace();
        }
    }

    public void render() {
        if(vertices == null || program == null)
            return;

        program.enable();
        vertices.render();

        program.disable();

    }
}
