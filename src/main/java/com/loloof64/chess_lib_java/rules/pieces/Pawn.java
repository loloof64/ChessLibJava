package com.loloof64.chess_lib_java.rules.pieces;

public class Pawn extends Piece {

    public Pawn(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'P' : 'p';
    }
}
