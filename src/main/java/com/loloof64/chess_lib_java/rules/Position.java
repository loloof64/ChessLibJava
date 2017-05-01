package com.loloof64.chess_lib_java.rules;

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
