package com.loloof64.chess_lib_java.rules.pieces;

public class Bishop extends Piece {

    public Bishop(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'B' : 'b';
    }
}
