package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;

public class Knight extends Piece {

    public Knight(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(BoardCell from, BoardCell to, Position position) {
        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);
        final Piece pieceAtEndCell = position.getPieceAt(to);

        return ((absDeltaX == 1 && absDeltaY == 2) || (absDeltaX == 2 && absDeltaY == 1))
                && (pieceAtEndCell == null || pieceAtEndCell.isWhitePiece() != whitePlayer);
    }

    @Override
    public String toString() {
        return "Knight{" +
                "whitePlayer=" + whitePlayer +
                '}';
    }

    @Override
    public char toFEN() {
        return whitePlayer ? 'N' : 'n';
    }
}
