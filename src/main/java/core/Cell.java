package core;

import utils.Copyable;

//*
// This class operates with this simple rules.
// if colonyIndex variable is -1 or < -1 => cell is dead.
// if colonyIndex is any other integer, it is a representative of that particular colony member.
// the aim for this class is to achieve minimalistic computation to effectively
// process large number of cells.
// /
public class Cell implements Copyable {

    private boolean isAlive;
    private int colonyIndex;

    public Cell() {
        this(0);
    }

    public Cell(int colonyIndex) {
//        if (colonyIndex <= 0)
//            this.isAlive = false;
//        else
//            this.isAlive = true;
        this.setColonyIndex(colonyIndex);
    }

    public Cell(Cell cell) {
        this.isAlive = cell.isAlive();
        this.colonyIndex = cell.colonyIndex;
    }

    @Override
    public Cell copy() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setColonyIndex(int colonyIndex) {
        this.isAlive = (colonyIndex > 0);
        this.colonyIndex = colonyIndex;
    }

    public void setAlive(int colonyIndex) {
        this.isAlive = true;
        this.colonyIndex = colonyIndex;
    }

    public void setDead() {
        this.isAlive = false;
        this.colonyIndex = 0;
    }

    public int getColonyIndex() {
        return this.colonyIndex;
    }

    public boolean isAlive() { return this.isAlive; }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;
        Cell cell = (Cell) other;
        return (this.isAlive == cell.isAlive && this.colonyIndex == cell.colonyIndex);
    }
    
    public String toString() {
        return Integer.toString(this.colonyIndex);
    }

}