package com.loloof64.chess_lib_java.rules;

public class MoveResult {
    public final String moveSan;
    public final Position position;

    public MoveResult(Position position, String moveSan) {
        this.moveSan = moveSan;
        this.position = position;
    }
}
