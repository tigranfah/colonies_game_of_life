package core;

public final class King extends ColonyCell {

    public King(int colonyIndex) {
        super(colonyIndex);
    }

    @Override
    public void makeAlive(int colonyIndex) { }

    public String toString() { return Character.toString(this.getColonyIndex()); }

}
