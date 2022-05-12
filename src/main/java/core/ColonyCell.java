package core;

public class ColonyCell extends Cell {

    private int colonyIndex;

    public ColonyCell(int colonyIndex) {
        this.colonyIndex = colonyIndex;
        if (colonyIndex > 0)
            this.makeAlive(colonyIndex);
        else
            this.makeDead();
    }

    public ColonyCell clone() {
        return (ColonyCell) super.clone();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other.getClass() != getClass()) return false;

        ColonyCell cell = (ColonyCell) other;
        return cell.getCharacter() == this.getCharacter();
    }

    public void makeAlive(int colonyIndex) {
        super.makeAlive();
        this.colonyIndex = colonyIndex;
    }

    public int getColonyIndex() { return this.colonyIndex; }

}
