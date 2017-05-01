package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;

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
        Piece movingPiece = _board.values()[from.rank][from.file];
        boolean noPieceAtStartCell = movingPiece == null;
        boolean pieceIsNotOurs = movingPiece != null && movingPiece.isWhitePiece() != _info.whiteTurn;

        if (noPieceAtStartCell || pieceIsNotOurs) return false;
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
