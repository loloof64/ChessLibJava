package com.loloof64.chess_lib_java.rules.pieces;

public class Queen extends Piece {

    public Queen(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'Q' : 'q';
    }
}
