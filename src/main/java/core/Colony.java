package core;

import utils.Pattern;

public class Colony {

    private int colonyIndex;
    private float coins = 0;
    private float increment = 0.05f;
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

    public void performIncrement() { this.coins += this.increment; }

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

    public float getCoins() {
        return coins;
    }

    public float getIncrement() {
        return increment;
    }

    public Position getKingPosition() {
        return kingPosition;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public Pattern makePattern() {
        if (coins <= 0) return null;
        --coins;
        return this.strategy.generatePattern();
    }

}