package core;

public class Cell {

    private boolean isAlive;
    private char character;

    public Cell() {
        this.isAlive = false;
        this.character = Utils.DEFAULT_DEAD_CELL_CHARACTER;
    }

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
        if (isAlive)
            this.character = Utils.DEFAULT_ALIVE_CELL_CHARACTER;
        else
            this.character = Utils.DEFAULT_DEAD_CELL_CHARACTER;
    }

    public Cell(Cell cell) {
        this.isAlive = cell.isAlive();
        this.character = cell.getCharacter();
    }

    public void setAlive() {
        this.isAlive = true;
        this.character = Utils.DEFAULT_ALIVE_CELL_CHARACTER;
    }

    public void setDead() {
        this.isAlive = false;
        this.character = Utils.DEFAULT_DEAD_CELL_CHARACTER;
    }

    public char getCharacter() {
        return this.character;
    }

    public boolean isAlive() { return this.isAlive; }
    
    public String toString() {
        return Character.toString(this.character);
    }

}