package core;

import utils.Pattern;

public class Colony {

    private int colonyIndex;
    private double coins = 1.0f;
    private double increment = 0.5E-2;
    private double workerIncrement = 1E-4;
    private Position kingPosition;
    Strategy strategy = new Strategy();

    public Colony(int colonyIndex, Position kingPosition) {
        this.colonyIndex = colonyIndex;
        this.kingPosition = kingPosition;
    }

    public Colony(int colonyIndex, Position kingPosition, Strategy strategy) {
        this.colonyIndex = colonyIndex;
        this.kingPosition = kingPosition;
        this.strategy = strategy;
    }

    public void performWorkerIncrement() { this.coins += this.workerIncrement; }

    public void performIterationIncrement() { this.coins += this.increment; }

    public int getColonyIndex() {
        return colonyIndex;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setIncrement(float increment) {
        this.increment = increment;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public double getCoins() {
        return coins;
    }

    public Position getKingPosition() {
        return kingPosition;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public Pattern makePattern() {
        if (coins < 1) return null;
        coins -= 1.0f;
        return this.strategy.generatePattern();
    }

}