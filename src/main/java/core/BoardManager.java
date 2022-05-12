package core;

import utils.Pair;

import java.awt.*;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class BoardManager {

    private Board board;
    private GameType gameType;

    public enum GameType {
        STANDARD, COLONIES
    }

    public BoardManager(GameType type, int width, int height) {
        this.board = new Board(width, height);
        this.gameType = type;
    }

    public void step() {
        if (gameType == GameType.STANDARD)
            this.standardGameStep();
        else if (gameType == GameType.COLONIES)
            this.coloniesGameStep();
    }

    private void coloniesGameStep() {

        ArrayList<Pair<ColonyCell, Position>> toSetAlivePairs = new ArrayList<>();
        ArrayList<Position> toSetDeadPositions = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); ++i) {
            for (int j = 0; j < this.board.getWidth(); ++j) {
                Position currentPosition = new Position(j, i);

                ArrayList<Position> neighbourPositions = this.getNeighbourPositions(currentPosition);
                ArrayList<Position> aliveNeighbours = this.getAliveNeighbours(neighbourPositions);
                int numberOfNeighbours = aliveNeighbours.size();

                if (this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours < 2 || numberOfNeighbours > 3)
                        toSetDeadPositions.add(currentPosition);
                }
                else if (!this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours == 3) {
                        ColonyCell cell = this.getTheDominantCell(aliveNeighbours).clone();
//                        System.out.printf("%s, from %s at %s\n", cell.getClass(), pos.toString(), currentPosition.toString());
//                        int rand = (int) (Math.random() * 4 + 1);
//                        ColonyCell cell = rand > 3 ? new Fighter(rand) : new Worker(rand);
//                        System.out.printf("%b %b ", cell instanceof Worker, cell instanceof Fighter);
//                        System.out.printf("%s %d \n", cell.getClass(), cell.getColonyIndex());
                        toSetAlivePairs.add(new Pair<>(cell, currentPosition));
                    }
                }
            }
        }

        for (Pair<ColonyCell, Position> pair : toSetAlivePairs)
            this.makeAlive(pair.first, pair.second);

        for (Position pos : toSetDeadPositions)
            this.makeDead(pos);
    }

    private void standardGameStep() {

        ArrayList<Position> toSetAlivePositions = new ArrayList<>();
        ArrayList<Position> toSetDeadPositions = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); ++i) {
            for (int j = 0; j < this.board.getWidth(); ++j) {
                Position currentPosition = new Position(j, i);
                int numberOfNeighbours = this.getAliveNeighbours(this.getNeighbourPositions(currentPosition)).size();
                if (this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours < 2 || numberOfNeighbours > 3)
                        toSetDeadPositions.add(currentPosition);
                }
                else if (!this.isCellAlive(currentPosition)) {
                    if (numberOfNeighbours == 3)
                        toSetAlivePositions.add(currentPosition);
                }
            }
        }

        for (Position pos : toSetAlivePositions)
            this.makeAlive(pos);

        for (Position pos : toSetDeadPositions)
            this.makeDead(pos);
    }

    public boolean isCellAlive(Position pos) {
        return this.board.getCellAt(pos).isAlive();
    }

    private ArrayList<Position> getNeighbourPositions(Position pos) {
        int[][] posInds = {
                // up left
                {pos.getX() - 1, pos.getY() + 1},
                // up
                {pos.getX(), pos.getY() + 1},
                // up right
                {pos.getX() + 1, pos.getY() + 1},
                // right
                {pos.getX() + 1, pos.getY()},
                // right down
                {pos.getX() + 1, pos.getY() - 1},
                // down
                {pos.getX(), pos.getY() - 1},
                // left down
                {pos.getX() - 1, pos.getY() - 1},
                // left
                {pos.getX() - 1, pos.getY()},
        };

        ArrayList<Position> positions = new ArrayList<>(posInds.length);

        for (int[] inds : posInds) {
            Position currentPos = new Position(inds[0], inds[1]);
            if (this.board.isValidBoardPosition(currentPos)) {
                positions.add(currentPos);
            }
        }
        return positions;
    }

    private ArrayList<Position> getAliveNeighbours(ArrayList<Position> positions) {
        ArrayList<Position> aliveNeighbours = new ArrayList<>();

        for (Position curPos : positions) {
            if (this.isCellAlive(curPos))
                aliveNeighbours.add(curPos);
        }
        return aliveNeighbours;
    }

    private ColonyCell getTheDominantCell(ArrayList<Position> positions) {
        ArrayList<ColonyCell> neighbours = new ArrayList<>(positions.size());
        for (Position p : positions)
            neighbours.add(this.board.getCellAt(p));

        if (neighbours.get(0).equals(neighbours.get(0)))
            return neighbours.get(0);
        if (neighbours.get(0).equals(neighbours.get(2)))
            return neighbours.get(0);
        if (neighbours.get(1).equals(neighbours.get(2)))
            return neighbours.get(1);

        int rand = (int) (Math.random() * 2);
        return neighbours.get(rand);
    }

    public void makeAlive(ColonyCell cell, Position pos) {
        this.board.setCellAt(cell, pos);
    }

    public void makeAlive(Position pos) {
        this.board.getCellAt(pos).makeAlive();
    }

    public void makeDead(Position pos) {
        this.board.getCellAt(pos).makeDead();
    }

    public Board getBoard() {
        return this.board;
    }

}
