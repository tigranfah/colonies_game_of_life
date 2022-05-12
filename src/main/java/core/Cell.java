package core;

//*
// This class operates with this simple rules.
// if colonyIndex variable is -1 or < -1 => cell is dead.
// if colonyIndex is any other integer, it is a representative of that particular colony member.
// the aim for this class is to achieve minimalistic computation to effectively
// process large number of cells.
// /
public abstract class Cell implements Cloneable {

    private boolean isAlive;
    private char character;

    public Cell() {
        this(false);
    }

    public Cell(boolean isAlive) {
        if (isAlive)
            makeAlive();
        else
            makeDead();
    }

    public Cell(Cell cell) {
        this.isAlive = cell.isAlive;
        this.character = cell.character;
    }

    @Override
    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public char getCharacter() { return this.character; }

    protected void setCharacter(char c) { this.character = c; }

    public void makeAlive() {
        this.isAlive = true;
        this.character = Utils.DEFAULT_ALIVE_CELL_CHARACTER;
    }

    public void makeDead() {
        this.isAlive = false;
        this.character = Utils.DEFAULT_DEAD_CELL_CHARACTER;
    }

    public boolean isAlive() { return this.isAlive; }

    protected void setAlive(boolean isAlive) { this.isAlive = isAlive; }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;
        Cell cell = (Cell) other;
        return this.isAlive == cell.isAlive;
    }
    
    public String toString() {
        return Character.toString(this.character);
    }

}