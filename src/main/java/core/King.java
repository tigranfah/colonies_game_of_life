package core;

public final class King extends ColonyCell {

    public King(int colonyIndex) {
        super(colonyIndex);
    }

    @Override
    public void makeAlive(int colonyIndex) { }

    @Override
    public char getCharacter() {
        return this.toString().charAt(0);
    }

    public String toString() {
        return Integer.toString(this.getColonyIndex());
    }

}
