package core;// I am creating a class for abstraction over the x, y arguments in our game,
// morover we might want to extend the idea of position in the future (ege 3rd dimentsion in the game)

public class Position implements Cloneable {

    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public Position clone() {
        try {
            return (Position) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        Position pos = (Position) other;
        return (this.x == pos.x) && (this.y == pos.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    public static Position[] copyFrom(Position[] positions) {
        Position[] newPos = new Position[positions.length];
        for (int i = 0; i < newPos.length; ++i)
            newPos[i] = positions[i].clone();

        return newPos;
    }

}