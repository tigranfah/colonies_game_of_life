package core;

public class Worker extends ColonyCell {

    public Worker(int colonyIndex) {
        super(colonyIndex);
    }

    @Override
    public void makeAlive(int colonyIndex) {
        super.makeAlive(colonyIndex);
        this.setCharacter(Utils.getCharPairByIndex(colonyIndex).first);
    }

    public String toString() { return Character.toString(this.getCharacter()); }

}
