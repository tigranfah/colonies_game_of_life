package core;

public class Fighter extends ColonyCell {

    public Fighter(int colonyIndex) {
        super(colonyIndex);
    }

    @Override
    public void makeAlive(int colonyIndex) {
        super.makeAlive(colonyIndex);
        this.setCharacter(Utils.getCharPairByIndex(colonyIndex).second);
    }

    public String toString() { return Character.toString(this.getCharacter()); }

}
