package core;// I am creating a class for abstraction over the x, y arguments in our game,
// morover we might want to extend the idea of position in the future (ege 3rd dimentsion in the game)

public class Position {

    private int x = 0;
    private int y = 0;

    // public Position() {
    //     this.x = 0;
    //     this.y = 0;
    // }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }


//    public static boolean isValueInValidRange(int value) {
//    }

}