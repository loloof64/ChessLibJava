package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Board;
import com.loloof64.chess_lib_java.rules.GameInfo;
import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;

public class Rook extends PromotablePiece {

    public Rook(boolean whitePlayer){
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
        final boolean noObstacleBefore = !position.obstacleBetween(to, from);
        final boolean isAGoodPath = absDeltaX == 0 || absDeltaY == 0;

        return isAGoodPath && targetCellHasNoFriendPiece && noObstacleBefore;
    }

    @Override
    public Either<Exception, Position> move(Move moveToDo, Position position, Class<? extends PromotablePiece> promotionPiece) {
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
        if (from == BoardCell.A1) newPositionInfo = newPositionInfo.copyWithThisWhiteQueenSideCastleState(false);
        else if (from == BoardCell.H1) newPositionInfo = newPositionInfo.copyWithThisWhiteKingSideCastleState(false);
        else if (from == BoardCell.A8) newPositionInfo = newPositionInfo.copyWithThisBlackQueenSideCastleState(false);
        else if (from == BoardCell.H8) newPositionInfo = newPositionInfo.copyWithThisBlackKingSideCastleState(false);

        return Either.right(new Position(newPositionBoard, newPositionInfo));
    }

    @Override
    public boolean isAttackingCell(BoardCell pieceCell, BoardCell testedCell, Position position) {
        final int deltaX = testedCell.file - pieceCell.file;
        final int deltaY = testedCell.rank - pieceCell.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);
        final boolean noObstacleBefore = !position.obstacleBetween(pieceCell, testedCell);
        final boolean isAGoodPath = (absDeltaX == 0 || absDeltaY == 0) && (absDeltaX > 0 || absDeltaY > 0);

        return isAGoodPath && noObstacleBefore;
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
