package com.loloof64.chess_lib_java.rules.pieces;

public class King extends Piece {

    public King(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'K' : 'k';
    }
}
