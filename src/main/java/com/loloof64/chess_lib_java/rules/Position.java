package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.PromotablePiece;
import com.loloof64.chess_lib_java.rules.pieces.Queen;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;

/**
 * Immutable chess position.
 */
public class Position {

    /**
     * Constructor with a board and a game info.
     * @param board - Board - board part.
     * @param gameInfo - GameInfo - game info part.
     */
    public Position(Board board, GameInfo gameInfo){
        if (board == null) throw new IllegalArgumentException("Board can't be null !");
        if (gameInfo == null) throw new IllegalArgumentException("Game info can't be null !");

        this._board = board;
        this._info = gameInfo;
    }

    /**
     * Generates a position from a Forsyth-Edwards Notation.
     * @param fenStr - String - the FEN to convert.
     * @return Position - the converted position.
     */
    public static Position fromFEN(String fenStr){
        return new Position(Board.fromFEN(fenStr), GameInfo.fromFEN(fenStr));
    }

    /**
     * Gets the Forsyth-Edwards Notation of the position.
     * @return String - the converted FEN.
     */
    public String toFEN(){
        return _board.toFEN() + " " + _info.toFEN();
    }

    /**
     * Says if a move can be done on this position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the end cell
     * @return boolean - can we move as described ?
     */
    public boolean canMove(BoardCell from, BoardCell to){
        final Piece movingPiece = _board.values()[from.rank][from.file];
        final boolean noPieceAtStartCell = movingPiece == null;
        final boolean pieceIsNotOurs = movingPiece != null && movingPiece.isWhitePiece() != _info.whiteTurn;
        final boolean originSquareEqualsToTarget = from == to;

        if (noPieceAtStartCell || pieceIsNotOurs || originSquareEqualsToTarget) return false;
        return movingPiece.canMove(from, to,this);
    }

    /**
     * Gets the piece (can be null) at the given cell.
     * @param cell - BoardCell - the cell where we want to get the piece.
     * @return Piece - the piece in the given cell.
     */
    public Piece getPieceAt(BoardCell cell){
        return _board.values()[cell.rank][cell.file];
    }

    /**
     * Says if there is any piece between those two cells. Notice that if the two cells does not define
     * a straight line, the result will be false (no obstacle).
     * @param cell1 - BoardCell
     * @param cell2 - BoardCell
     * @return true if and only if there is an obstacle between the two cells (both not included, of course).
     */
    public boolean obstacleBetween(BoardCell cell1, BoardCell cell2){
        final int deltaX = cell2.file - cell1.file;
        final int deltaY = cell2.rank - cell1.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);

        final boolean notAStraightLine = (absDeltaX > 0 && absDeltaY > 0) && (absDeltaX != absDeltaY);
        final boolean sameCell = absDeltaX == 0 && absDeltaY == 0;

        if (notAStraightLine || sameCell) return false;

        final int xSide = deltaX == 0 ? 0 : deltaX > 0 ? 1 : -1;
        final int ySide = deltaY == 0 ? 0 : deltaY > 0 ? 1 : -1;

        final int loopLimit = absDeltaX > 0 ? absDeltaX : absDeltaY;

        for (int i = 1; i < loopLimit; i++){ // Caution ! The start cell and target cell are not included !
            final int dxi = i * xSide;
            final int dyi = i * ySide;
            final BoardCell computedCell = new BoardCell(cell1.rank + dyi, cell1.file + dxi);

            if (_board.values()[computedCell.rank][computedCell.file] != null) return true;
        }

        return false;
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the target cell
     * @return Maybe of Position - Nothing on failure, otherwise Just of Position : wrapping the result.
     */
    public Maybe<Position> move(BoardCell from, BoardCell to){
        return move(from, to, Queen.class);
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the target cell
     * @param promotionPiece - Class of PromotablePiece - promotion piece if the move leads to pawn promotion.
     * @return Maybe of Position - Nothing on failure, otherwise Just of Position : wrapping the result.
     */
    public Maybe<Position> move(BoardCell from, BoardCell to, Class<? extends PromotablePiece> promotionPiece){
        final Piece movingPiece = _board.values()[from.rank][from.file];
        boolean noPieceAtStartCell = movingPiece == null;

        if (noPieceAtStartCell || !canMove(from, to)) return new Nothing<>();
        return movingPiece.move(from, to, this, promotionPiece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return _info.equals(position._info) && _board.equals(position._board);
    }

    @Override
    public int hashCode() {
        int result = _board.hashCode();
        result = 31 * result + _info.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "_board=" + _board +
                ", _info=" + _info +
                '}';
    }

    public final Board _board;
    public final GameInfo _info;
}
