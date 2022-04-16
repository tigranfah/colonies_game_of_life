package core;

public class Renderer {

    private float renderingSpeed = 0.5f;

    public Renderer() { }

    public Renderer(float renderingSpeed) {
        this.renderingSpeed = renderingSpeed;
    }

    private void renderCell(Cell cell) {
        System.out.printf("%s ", cell.toString());
    }

    public void render(Board board) {
        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                this.renderCell(board.getCellAt(new Position(j, i)));
            }
            System.out.printf("\n");
        }
    }

}