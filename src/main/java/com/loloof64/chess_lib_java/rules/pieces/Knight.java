package com.loloof64.chess_lib_java.rules.pieces;

public class Knight extends Piece {

    public Knight(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer ? 'N' : 'n';
    }
}
