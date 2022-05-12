package core;

import java.util.ArrayList;

public class GameSetting {

    private BoardManager.GameType type;
    private ArrayList<Colony> colonies;

    public GameSetting(BoardManager.GameType type) {
        if (type == null)
            throw new IllegalArgumentException("Game type cannot be null.");
        this.type = type;
    }

    public void setKingPositions(Position[] kingPositions) {
        if (this.type == BoardManager.GameType.STANDARD) {
            System.err.println("Cannot have king position in a standard game setting.");
            return;
        }
        this.colonies = new ArrayList<>();
        this.verifyKingPositions(kingPositions);
        for (int i = 0; i < kingPositions.length; ++i)
            this.colonies.add(new Colony(i + 1, kingPositions[i]));
    }

    private void verifyKingPositions(Position[] kingPositions) {
        if (kingPositions != null) {
            for (Position pos : kingPositions)
                if (pos == null) throw new IllegalArgumentException("Elements of the positions cannot be null.");

            for (int i = 0; i < kingPositions.length; ++i) {
                for (int j = 0 ; j < kingPositions.length; ++j) {
                    if (i == j) continue;
                    if (kingPositions[i].equals(kingPositions[j])) {
                        throw new IllegalArgumentException("Elements of the positions must be unique.");
                    }
                }
            }
        }
    }

    public Colony getColony(int index) {
        for (Colony col : this.colonies) {
            if (col.getColonyIndex() == index)
                return col;
        }
        return null;
    }

    public BoardManager.GameType getType() { return this.type; }

    public int getNumberOfColonies() { return this.colonies.size(); }

    public ArrayList<Colony> getColonies() { return this.colonies; }

}
