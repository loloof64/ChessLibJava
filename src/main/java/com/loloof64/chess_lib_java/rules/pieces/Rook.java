package com.loloof64.chess_lib_java.rules.pieces;

public class Rook extends Piece {

    public Rook(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'R' : 'r';
    }
}
