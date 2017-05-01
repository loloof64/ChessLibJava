package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.coords.BoardRank;

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
