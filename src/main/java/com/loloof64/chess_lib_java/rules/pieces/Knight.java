package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.*;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;

public class Knight extends Piece implements Promotable {

    public Knight(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(Move moveToDo, Position position) {
        final BoardCell from = moveToDo.from();
        final BoardCell to = moveToDo.to();

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
    public Either<Exception, MoveResult> move(Move moveToDo, Position position, Promotable promotionPiece) {
        final BoardCell from = moveToDo.from();
        final BoardCell to = moveToDo.to();

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

        Position resultPosition = new Position(newPositionBoard, newPositionInfo);
        boolean opponentKingInCheck = resultPosition.kingIsInChess(resultPosition.info.whiteTurn).right();

        String moveSan = String.format("N%s", to);
        if (isCaptureMove) moveSan = String.format("Nx%s", to);
        if (opponentKingInCheck) moveSan = String.format("%s+", moveSan);
        return Either.right(new MoveResult(resultPosition, moveSan, moveToDo));
    }

    @Override
    public boolean isAttackingCell(BoardCell pieceCell, BoardCell testedCell, Position position) {
        final int deltaX = testedCell.file - pieceCell.file;
        final int deltaY = testedCell.rank - pieceCell.rank;

        return (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1)
                || (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2);
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

    @Override
    public char pieceLetter() {
        return 'N';
    }
}
