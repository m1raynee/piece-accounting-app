package com.m1raynee.pieceaccountingapp.loans;

public enum StockMovementType {
    INCOMING(1),
    RETURN(1),
    LOAN(-1),
    LOSS(-1),
    ADJUSTMENT_INCREASE(1),
    ADJUSTMENT_DECREASE(-1);

    private final int direction;

    StockMovementType(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}