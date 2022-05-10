package engine.rendering;

import engine.graphics.ShaderProgram;
import engine.graphics.VertexArray;

public class Square implements Renderable{

    private final ShaderProgram program;
    private final VertexArray vertices;

    public Square() {
        program = new ShaderProgram("cell.vert", "cell.frag");
        vertices = new VertexArray(
                new float[]{
                        -0.5f, -0.5f * (float) Math.sqrt(3) / 3, 0f,
                        0.5f, -0.5f * (float) Math.sqrt(3) / 3, 0f,
                        0f, 0.5f * (float) Math.sqrt(3) * 2 / 3, 0f,
                },
                new byte[]{0, 1, 2}
        );
    }

    public void render() {
        program.enable();
        vertices.render();

        program.disable();

    }
}
