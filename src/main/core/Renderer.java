public class Renderer {

    private float renderingSpeed = 0.5f;

    public Renderer(float renderingSpeed) {
        this.renderingSpeed = renderingSpeed;
    }

    private void renderCell(Cell cell) {
        System.out.println(cell.toString());
    }

    public void render(Board board) {
        for (int i = 0; i < board.getHeight(); ++i) {
            for (int j = 0; j < board.getWidth(); ++j) {
                this.renderCell(this.board.getCellAt(j, i));
            }
        }
    }

}