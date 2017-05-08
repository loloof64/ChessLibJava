package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Board;
import com.loloof64.chess_lib_java.rules.GameInfo;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.coords.BoardFile;
import com.loloof64.chess_lib_java.rules.coords.BoardRank;
import com.loloof64.functional.monad.Just;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;

public class King extends Piece {

    public King(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(BoardCell from, BoardCell to, Position position) {
        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);
        final boolean standardMove = (absDeltaX > 0 || absDeltaY > 0) && absDeltaX < 2 && absDeltaY < 2;
        final int playerBackRank = whitePlayer ? BoardRank.RANK_1.ordinal() : BoardRank.RANK_8.ordinal();

        final boolean noPieceInKingSidePath = position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_F.ordinal())) == null;
        final boolean hasRightForKingSideCastle = whitePlayer ? position._info.castlesRights.whiteKingSide :
                position._info.castlesRights.blackKingSide;
        final boolean kingSideCastleMove = hasRightForKingSideCastle && deltaX == 2 && deltaY == 0 && noPieceInKingSidePath;

        final boolean hasRightForQueenSideCastle = whitePlayer ? position._info.castlesRights.whiteQueenSide :
                position._info.castlesRights.blackQueenSide;
        final boolean noPieceInQueenSidePath = position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_D.ordinal())) == null
                && position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_B.ordinal())) == null;
        final boolean queenSideCastleMove = hasRightForQueenSideCastle && deltaX == -2 && deltaY == 0 && noPieceInQueenSidePath;

        final Piece pieceAtEndCell = position.getPieceAt(to);
        final boolean targetCellHasNoFriendPiece = pieceAtEndCell == null || pieceAtEndCell.isWhitePiece() != whitePlayer;

        return (standardMove || kingSideCastleMove || queenSideCastleMove)
                && targetCellHasNoFriendPiece;
    }

    @Override
    public Maybe<Position> move(BoardCell from, BoardCell to, Position position, Class<? extends PromotablePiece> promotionPiece) {
        final boolean isCaptureMove = position.getPieceAt(to) != null;
        final int deltaX = to.file - from.file;
        final boolean isKingSideCastleMove = (whitePlayer ? from == BoardCell.E1 : from == BoardCell.E8) && deltaX == 2;
        final boolean isQueenSideCastleMove = (whitePlayer ? from == BoardCell.E1 : from == BoardCell.E8) && deltaX == -2;

        Board newPositionBoard = Board.fromFEN(position.toFEN()); // A simple way to get a copy.
        final Piece pieceAtStartCell = position.getPieceAt(from);
        newPositionBoard = newPositionBoard.copy(from, null);
        newPositionBoard = newPositionBoard.copy(to, pieceAtStartCell);
        if (isKingSideCastleMove){
            final BoardCell movedRookCell = new BoardCell(from.rank, BoardFile.FILE_H.ordinal());
            final BoardCell movedRookEndCell = new BoardCell(from.rank, BoardFile.FILE_F.ordinal());
            final Piece movedRook = position.getPieceAt(movedRookCell);
            newPositionBoard = newPositionBoard.copy(movedRookCell, null);
            newPositionBoard = newPositionBoard.copy(movedRookEndCell, movedRook);
        }
        else if (isQueenSideCastleMove){
            final BoardCell movedRookCell = new BoardCell(from.rank, BoardFile.FILE_A.ordinal());
            final BoardCell movedRookEndCell = new BoardCell(from.rank, BoardFile.FILE_D.ordinal());
            final Piece movedRook = position.getPieceAt(movedRookCell);
            newPositionBoard = newPositionBoard.copy(movedRookCell, null);
            newPositionBoard = newPositionBoard.copy(movedRookEndCell, movedRook);
        }

        GameInfo newPositionInfo = GameInfo.fromFEN(position.toFEN());  // A simple way to get a copy.
        newPositionInfo = newPositionInfo.copyWithTurnReversedAndMoveNumberUpdated();
        newPositionInfo = newPositionInfo.copyWithThisEnPassantFile(null);
        int  newNullityHalfMovesCount = isCaptureMove ? 0 : newPositionInfo.nullityHalfMovesCount + 1;
        if (whitePlayer) {
            newPositionInfo = newPositionInfo.copyWithThisWhiteKingSideCastleState(false);
            newPositionInfo = newPositionInfo.copyWithThisWhiteQueenSideCastleState(false);
        }
        else {
            newPositionInfo = newPositionInfo.copyWithThisBlackKingSideCastleState(false);
            newPositionInfo = newPositionInfo.copyWithThisBlackQueenSideCastleState(false);
        }
        newPositionInfo = newPositionInfo.copyWithThisNullityHalfMovesCount(newNullityHalfMovesCount);

        return new Just<>(new Position(newPositionBoard, newPositionInfo));
    }

    @Override
    public boolean isAttackingCell(BoardCell pieceCell, BoardCell testedCell, Position position) {
        final int deltaX = testedCell.file - pieceCell.file;
        final int deltaY = testedCell.rank - pieceCell.rank;

        return (Math.abs(deltaX) > 0 || Math.abs(deltaY) > 0) &&
                (Math.abs(deltaX) < 2 && Math.abs(deltaY) < 2);
    }


    @Override
    public String toString() {
        return "King{" +
                "whitePlayer=" + whitePlayer +
                '}';
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'K' : 'k';
    }
}
