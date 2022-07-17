package com.loloof64.chess_lib_java.rules;

import java.util.Objects;

public class MoveResult {
    public final String moveSan;
    public final Position position;
    public final Move generatorMove;

    public MoveResult(Position position, String moveSan, Move generatorMove) {
        this.moveSan = moveSan;
        this.position = position;
        this.generatorMove = generatorMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveResult that = (MoveResult) o;
        return moveSan.equals(that.moveSan) && position.equals(that.position) && generatorMove.equals(that.generatorMove);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveSan, position, generatorMove);
    }

    public String getMoveSan() {
        return moveSan;
    }

    public Position getPosition() {
        return position;
    }

    public Move getGeneratorMove() {
        return generatorMove;
    }
}
