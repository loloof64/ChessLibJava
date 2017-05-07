package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;

public class Queen extends PromotablePiece {

    public Queen(boolean whitePlayer){
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
        final boolean isAGoodPath = (absDeltaX == absDeltaY) || (absDeltaX == 0 || absDeltaY == 0);

        return isAGoodPath && targetCellHasNoFriendPiece && noObstacleBefore;
    }

    @Override
    public Maybe<Position> move(BoardCell from, BoardCell to, Position position, Class<? extends PromotablePiece> promotionPiece) {
        return new Nothing<>();
    }

    @Override
    public String toString() {
        return "Queen{" +
                "whitePlayer=" + whitePlayer +
                '}';
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'Q' : 'q';
    }
}
