package core;

public class BoardManager {

    private Board board;
    private float renderSpeed = 60.0f;

    public BoardManager(Board board, float renderSpeed) {
        this.board = board;
        this.renderSpeed = renderSpeed;
    }

    public Board getBoard() {
        return this.board;
    }

    public Board getBoardCopy() {
        return this.board.clone();
    }

}
