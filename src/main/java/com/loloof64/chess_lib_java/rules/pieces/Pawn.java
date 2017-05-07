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

public class Pawn extends Piece {

    public Pawn(boolean whitePlayer){
        this.whitePlayer = whitePlayer;
    }

    @Override
    public boolean canMove(BoardCell from, BoardCell to, Position position) {
        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final Piece pieceAtEndCell = position.getPieceAt(to);

        final boolean tryingToMoveOneCellStraightAhead = deltaX == 0 && deltaY == (whitePlayer ? 1 : -1);
        final boolean tryingToMoveTwoCellsStraightAhead = deltaX == 0 && deltaY == (whitePlayer ? 2 : -2);
        final boolean tryingToCapture = Math.abs(deltaX) == 1 && deltaY == (whitePlayer ? 1 : -1);

        if (tryingToMoveOneCellStraightAhead){
            return pieceAtEndCell == null;
        }
        else if (tryingToMoveTwoCellsStraightAhead){
            BoardCell cellAlongPath = new BoardCell(from.rank + (whitePlayer ? 1 : -1), from.file);
            boolean isAtItsOriginRank = from.rank == (whitePlayer ? BoardRank.RANK_2.ordinal() : BoardRank.RANK_7.ordinal());
            boolean noPieceAlongPath = position.getPieceAt(cellAlongPath) == null;

            return isAtItsOriginRank && noPieceAlongPath && pieceAtEndCell == null;
        }
        else if (tryingToCapture){
            final boolean tryingToCaptureEnPassant = pieceAtEndCell == null;
            if (tryingToCaptureEnPassant){
                final boolean enPassantFileSameAsDestinationFile = position._info.enPassantFile != null &&
                        position._info.enPassantFile.ordinal() == to.file;
                final boolean endRankIsAStandardEnPassantEndRank = to.rank == (whitePlayer ? BoardRank.RANK_6.ordinal() :
                        BoardRank.RANK_3.ordinal());
                return enPassantFileSameAsDestinationFile && endRankIsAStandardEnPassantEndRank;
            }
            else {
                // piece at end cell is enemy piece
                return pieceAtEndCell.isWhitePiece() != whitePlayer;
            }
        }
        return false;
    }

    @Override
    public Maybe<Position> move(BoardCell from, BoardCell to, Position position, Class<? extends PromotablePiece> promotionPiece) {
        final int deltaX = to.file - from.file;
        final int deltaY = to.rank - from.rank;
        final boolean isTwoCellsJump = (whitePlayer && deltaY == 2 && deltaX == 0
                && from.rank == BoardRank.RANK_2.ordinal()) ||
                (!whitePlayer && deltaY == -2 && deltaX == 0 && from.rank == BoardRank.RANK_7.ordinal());
        final BoardFile newEnPassantFile = isTwoCellsJump ? BoardFile.values()[from.file] : null;
        final boolean isPromotion = whitePlayer ?
                to.rank == BoardRank.RANK_8.ordinal() : to.rank == BoardRank.RANK_1.ordinal();

        Board newPositionBoard = Board.fromFEN(position._board.toFEN()); // A simple way to get a copy.
        final Piece pieceAtEndSquare = position.getPieceAt(to);
        Piece replacingPieceForEndSquare;
        try {
            replacingPieceForEndSquare = isPromotion ? promotionPiece.getDeclaredConstructor(boolean.class).
                    newInstance(whitePlayer): position.getPieceAt(from);

            final BoardFile enPassantFile = position._info.enPassantFile;

            final boolean isEnPassantMove =
                    ((whitePlayer && deltaY == 1) || (!whitePlayer && deltaY == -1)) && Math.abs(deltaX) == 1 &&
                    pieceAtEndSquare == null && enPassantFile != null && enPassantFile.ordinal() == to.file;

            newPositionBoard = newPositionBoard.copy(from, null);
            newPositionBoard = newPositionBoard.copy(to, replacingPieceForEndSquare);
            if (isEnPassantMove) {
                int capturedPieceRank = whitePlayer ? BoardRank.RANK_5.ordinal() : BoardRank.RANK_4.ordinal();
                BoardCell capturedPieceCell = new BoardCell(capturedPieceRank, position._info.enPassantFile.ordinal());
                newPositionBoard = newPositionBoard.copy(capturedPieceCell, null);
            }

            GameInfo newPositionInfo = position._info;
            newPositionInfo = newPositionInfo.copyWithTurnReversedAndMoveNumberUpdated();
            newPositionInfo = newPositionInfo.copyWithThisEnPassantFile(newEnPassantFile);
            newPositionInfo = newPositionInfo.copyWithThisNullityHalfMovesCount(0);

            return new Just<>(new Position(newPositionBoard, newPositionInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new Nothing<>();
        }
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "whitePlayer=" + whitePlayer +
                '}';
    }

    @Override
    public char toFEN() {
        return whitePlayer? 'P' : 'p';
    }
}
