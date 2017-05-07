package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Board;
import com.loloof64.chess_lib_java.rules.GameInfo;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Just;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;

public class Knight extends PromotablePiece {

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

        final boolean targetCellHasNoFriendPiece = pieceAtEndCell == null || pieceAtEndCell.isWhitePiece() != whitePlayer;

        return ((absDeltaX == 1 && absDeltaY == 2) || (absDeltaX == 2 && absDeltaY == 1))
                && targetCellHasNoFriendPiece;
    }

    @Override
    public Maybe<Position> move(BoardCell from, BoardCell to, Position position, Class<? extends PromotablePiece> promotionPiece) {
        final boolean isCaptureMove = position.getPieceAt(to) != null;

        Board newPositionBoard = Board.fromFEN(position.toFEN()); // A simple way to get a copy.
        final Piece pieceAtStartCell = position.getPieceAt(from);
        newPositionBoard = newPositionBoard.copy(from, null);
        newPositionBoard = newPositionBoard.copy(to, pieceAtStartCell);

        GameInfo newPositionInfo = GameInfo.fromFEN(position.toFEN());  // A simple way to get a copy.
        newPositionInfo = newPositionInfo.copyWithTurnReversedAndMoveNumberUpdated();
        newPositionInfo = newPositionInfo.copyWithThisEnPassantFile(null);
        int  newNullityHalfMovesCount = isCaptureMove ? 0 : newPositionInfo.nullityHalfMovesCount + 1;
        newPositionInfo = newPositionInfo.copyWithThisNullityHalfMovesCount(newNullityHalfMovesCount);

        return new Just<>(new Position(newPositionBoard, newPositionInfo));
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
