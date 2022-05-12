package core;

import utils.Matrix;
import utils.Pair;
import utils.Pattern;

import java.util.ArrayList;

public class BoardManager {

    private Board board;
    private GameSetting setting;

    public enum GameType {
        STANDARD, COLONIES
    }

    public BoardManager(GameSetting setting, int width, int height) {
        this.board = new Board(width, height);
        this.setting = setting;
        if (setting.getType() == GameType.COLONIES) {
            for (int i = 1; i <= setting.getNumberOfColonies(); ++i) {
                Position pos = this.setting.getColony(i).getKingPosition();
                this.board.setCellAt(new King(i), pos);
            }
        }
    }

    public GameSetting getSetting() { return this.setting; }

    public void destroyColony(int indexOfColony) {
        Colony col = this.setting.getColony(indexOfColony);
        if (col == null) {
            System.err.printf("No colony with index %d, cannot destory it.", indexOfColony);
            return;
        }
        this.board.setCellAt(new ColonyCell(0), col.getKingPosition());
        this.setting.removeColony(col);
    }

    public void step() {
        if (this.setting.getType() == GameType.STANDARD)
            this.standardGameStep();
        else if (this.setting.getType() == GameType.COLONIES)
            this.coloniesGameStep();
    }

    private void coloniesGameStep() {

        ArrayList<Pair<ColonyCell, Position>> toSetAlivePairs = new ArrayList<>();
        ArrayList<Position> toSetDeadPositions = new ArrayList<>();

        for (int i = 0; i < this.board.getHeight(); ++i) {
            for (int j = 0; j < this.board.getWidth(); ++j) {
                Position currentPosition = new Position(j, i);
                if (this.board.isKingAt(currentPosition)) continue;

                if (this.board.isWorkerAt(currentPosition)) {
                    Worker worker = (Worker) this.board.getCellAt(currentPosition);
                    Colony colony = this.setting.getColony(worker.getColonyIndex());
                    if (colony != null)
                        colony.performWorkerIncrement();
                }

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

        for (Integer i : this.checkKingAttack())
            this.destroyColony(i);

        for (Colony colony : this.setting.getColonies()) {
            colony.performIterationIncrement();
            if (colony.getCoins() > 1.0f) {
                Pattern pat = colony.makePattern();
                Matrix<Worker> workers = pat.toWorkerMatrix(colony.getColonyIndex());
                this.board.setBoardSubpart(workers, colony.getKingPosition().clone(), true);
            }
        }

//        int isOver = game();
//        if () {
    }

    private ArrayList<Integer> checkKingAttack() {
        ArrayList<Integer> colonyiesToDestroy = new ArrayList<>();
        for (Colony col : this.setting.getColonies()) {
            for (Position pos : this.getAliveNeighbours(this.getNeighbourPositions(col.getKingPosition()))) {
                if (this.board.getCellAt(pos).getColonyIndex() != col.getColonyIndex()) {
                    colonyiesToDestroy.add(col.getColonyIndex());
                    break;
                }
            }
        }
        return colonyiesToDestroy;
    }

    public int isGameOver() {
        if (this.setting.getNumberOfColonies() == 1)
            return this.setting.getColonies().get(0).getColonyIndex();
        return -1;
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
            if (this.isCellAlive(curPos)) {
                if (this.board.isKingAt(curPos)) continue;
                aliveNeighbours.add(curPos);
            }
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
