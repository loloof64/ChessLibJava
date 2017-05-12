package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Board;
import com.loloof64.chess_lib_java.rules.GameInfo;
import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.coords.BoardFile;
import com.loloof64.chess_lib_java.rules.coords.BoardRank;
import com.loloof64.functional.monad.Either;

public class King extends Piece {

    public King(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(Move moveToDo, Position position) {
        final BoardCell from = moveToDo.from();
        final BoardCell to = moveToDo.to();

        Either<Exception, Boolean> kingIsInChess = position.kingIsInChess(position.info.whiteTurn);

        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);
        final boolean standardMove = (absDeltaX > 0 || absDeltaY > 0) && absDeltaX < 2 && absDeltaY < 2;
        final int playerBackRank = whitePlayer ? BoardRank.RANK_1.ordinal() : BoardRank.RANK_8.ordinal();

        final boolean noPieceInKingSidePath = position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_F.ordinal())) == null;
        final boolean hasRightForKingSideCastle = whitePlayer ? position.info.castlesRights.whiteKingSide :
                position.info.castlesRights.blackKingSide;
        final boolean kingSideCastleMove = hasRightForKingSideCastle && deltaX == 2 && deltaY == 0 && noPieceInKingSidePath;

        final boolean hasRightForQueenSideCastle = whitePlayer ? position.info.castlesRights.whiteQueenSide :
                position.info.castlesRights.blackQueenSide;
        final boolean noPieceInQueenSidePath = position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_D.ordinal())) == null
                && position.getPieceAt(new BoardCell(playerBackRank, BoardFile.FILE_B.ordinal())) == null;
        final boolean queenSideCastleMove = hasRightForQueenSideCastle && deltaX == -2 && deltaY == 0 && noPieceInQueenSidePath;

        final Piece pieceAtEndCell = position.getPieceAt(to);
        final boolean targetCellHasNoFriendPiece = pieceAtEndCell == null || pieceAtEndCell.isWhitePiece() != whitePlayer;

        GameInfo positionIfKingCrossF1OrF8_GameInfo = GameInfo.fromFEN(position.toFEN()); // Simple copy
        Board positionIfKingCrossF1OrF8_Board = Board.fromFEN(position.toFEN()); // Simple copy
        positionIfKingCrossF1OrF8_Board = positionIfKingCrossF1OrF8_Board.copy(from, null);
        positionIfKingCrossF1OrF8_Board = positionIfKingCrossF1OrF8_Board.copy(position.info.whiteTurn ? BoardCell.F1 : BoardCell.F8,
                new King(position.info.whiteTurn));
        Position positionIfKingCrossF1OrF8 = new Position(positionIfKingCrossF1OrF8_Board,
                positionIfKingCrossF1OrF8_GameInfo);
        Either<Exception, Boolean> positionIfKingCrossF1OrF8HasCurrentPlayerTurnKingInChess =
                positionIfKingCrossF1OrF8.kingIsInChess(position.info.whiteTurn);

        GameInfo positionIfKingCrossD1OrD8_GameInfo = GameInfo.fromFEN(position.toFEN()); // Simple copy
        Board positionIfKingCrossD1OrD8_Board = Board.fromFEN(position.toFEN()); // Simple copy
        positionIfKingCrossD1OrD8_Board = positionIfKingCrossD1OrD8_Board.copy(from, null);
        positionIfKingCrossD1OrD8_Board = positionIfKingCrossD1OrD8_Board.copy(position.info.whiteTurn ? BoardCell.D1 : BoardCell.D8,
                new King(position.info.whiteTurn));
        Position positionIfKingCrossD1OrD8 = new Position(positionIfKingCrossD1OrD8_Board,
                positionIfKingCrossD1OrD8_GameInfo);
        Either<Exception, Boolean> positionIfKingCrossD1OrD8HasCurrentPlayerTurnKingInChess =
                positionIfKingCrossD1OrD8.kingIsInChess(position.info.whiteTurn);

        GameInfo positionIfKingReachEndCell_GameInfo = GameInfo.fromFEN(position.toFEN()); // Simple copy
        Board positionIfKingReachEndCell_Board = Board.fromFEN(position.toFEN()); // Simple copy
        positionIfKingReachEndCell_Board = positionIfKingReachEndCell_Board.copy(from, null);
        positionIfKingReachEndCell_Board = positionIfKingReachEndCell_Board.copy(to,  new King(position.info.whiteTurn));
        Position positionIfKingReachEndCell = new Position(positionIfKingReachEndCell_Board,
                positionIfKingReachEndCell_GameInfo);
        Either<Exception, Boolean> positionIfKingReachEndCellHasCurrentPlayerTurnKingInChess =
                positionIfKingReachEndCell.kingIsInChess(position.info.whiteTurn);

        final boolean canDoKingSideCastleMove = kingSideCastleMove &&
                kingIsInChess.isRight() && !kingIsInChess.right() &&
                positionIfKingCrossF1OrF8HasCurrentPlayerTurnKingInChess.isRight() &&
                !positionIfKingCrossF1OrF8HasCurrentPlayerTurnKingInChess.right() &&
                positionIfKingReachEndCellHasCurrentPlayerTurnKingInChess.isRight() &&
                !positionIfKingReachEndCellHasCurrentPlayerTurnKingInChess.right();
        final boolean canDoQueenSideCastleMove = queenSideCastleMove &&
                kingIsInChess.isRight() && !kingIsInChess.right() &&
                positionIfKingCrossD1OrD8HasCurrentPlayerTurnKingInChess.isRight() &&
                !positionIfKingCrossD1OrD8HasCurrentPlayerTurnKingInChess.right() &&
                positionIfKingReachEndCellHasCurrentPlayerTurnKingInChess.isRight() &&
                !positionIfKingReachEndCellHasCurrentPlayerTurnKingInChess.right();

        return (standardMove || canDoKingSideCastleMove || canDoQueenSideCastleMove)
                && targetCellHasNoFriendPiece;
    }

    @Override
    public Either<Exception, Position> move(Move moveToDo, Position position, Class<? extends PromotablePiece> promotionPiece) {
        final BoardCell from = moveToDo.from();
        final BoardCell to = moveToDo.to();

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

        return Either.right(new Position(newPositionBoard, newPositionInfo));
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
