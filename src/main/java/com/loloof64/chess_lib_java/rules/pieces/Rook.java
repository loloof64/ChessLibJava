package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;

public class Rook extends Piece {

    public Rook(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(BoardCell from, BoardCell to, Position position) {
        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);
        final Piece pieceAtEndCell = position.getPieceAt(to);
        final boolean targetCellHasNoFriendPiece = pieceAtEndCell == null || pieceAtEndCell.isWhitePiece() != whitePlayer;
        final boolean noObstacleBefore = !position.obstacleBetween(to, from);
        final boolean isAGoodPath = absDeltaX == 0 || absDeltaY == 0;

        return isAGoodPath && targetCellHasNoFriendPiece && noObstacleBefore;
    }

    @Override
    public String toString() {
        return "Rook{" +
                "whitePlayer=" + whitePlayer +
                '}';
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'R' : 'r';
    }
}
