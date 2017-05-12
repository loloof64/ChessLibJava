package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.Pair;

/**
 * Simply wraps a move start and end cells.
 */
public class Move {

    /**
     * Constructor with the start and target cell.
     * @param from - {@link BoardCell} - start cell. Can't be null.
     * @param to - {@link BoardCell} - target cell. Can't be null.
     */
    public Move(BoardCell from, BoardCell to){
        if (from == null || to == null) throw new IllegalArgumentException("Both from cell and to cell must" +
                " be provided (cannot be null) !");
        this._coordinates = new Pair<>(from, to);
    }

    /**
     * Gets the start cell.
     * @return BoardCell - the start cell.
     */
    public BoardCell from(){
        return this._coordinates.first;
    }

    /**
     * Gets the target cell.
     * @return BoardCell - the target cell.
     */
    public BoardCell to(){
        return this._coordinates.second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return _coordinates.equals(move._coordinates);
    }

    @Override
    public int hashCode() {
        return _coordinates.hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s => %s)", _coordinates.first, _coordinates.second);
    }

    private Pair<BoardCell, BoardCell> _coordinates;
}
