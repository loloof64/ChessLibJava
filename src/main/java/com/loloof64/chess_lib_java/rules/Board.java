package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.pieces.Piece;

/**
 * Game board representation.
 */
public class Board {

    /**
     * Constructor from pieces values : null value indicates that there is no piece.
     * @param values - Array of array of Piece
     */
    public Board(Piece[][] values){
        this._values = values;
    }

    /**
     * Gets a board from its FEN representation. (Forsyth-Edwards Notation).
     * @param fenStr - String - FEN notation of the board we want.
     * @return Board
     */
    public static Board fromFEN(String fenStr){
        Piece[][] boardToBuild = new Piece[8][8];

        final String boardFEN = fenStr.split("\\s+")[0];
        final String lines [] = boardFEN.split("/");

        int rankIndex = 7;
        for (String currentLine : lines){
            char cellValues [] = currentLine.toCharArray();
            int fileIndex = 0;
            for (char cell : cellValues){
                if (Character.isDigit(cell)){
                    int holesNumber = (int) cell - (int) '0';
                    for (int times = 0; times < holesNumber; times++) fileIndex++;
                }
                else {
                    boardToBuild[rankIndex][fileIndex++] = Piece.fromFEN(cell);
                }
            }
            rankIndex--;
        }

        return new Board(boardToBuild);
    }

    /**
     * Gets the values of the board.
     * First dimension is rank (careful ! index 0 is for line 8).
     * @return Array of Array of Piece
     */
    Piece[][] values(){
        return this._values;
    }

    /**
     * Gets the Forsyth-Edwards Notation of this board (so, only the board part).
     * @return String - the FEN representation of this board.
     */
    public String toFEN(){
        final StringBuilder strBuilder = new StringBuilder();

        for (int rankIndex = 7; rankIndex >= 0; rankIndex--){
            int holesNumber = 0;

            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                Piece cellValue = _values[rankIndex][fileIndex];
                if (cellValue == null) holesNumber++;
                else {
                    if (holesNumber > 0) strBuilder.append(holesNumber);
                    holesNumber = 0;
                    strBuilder.append(cellValue.toFEN());
                }
            }
            if (holesNumber > 0) strBuilder.append(holesNumber);
            if (rankIndex > 0) strBuilder.append('/');
        }

        return strBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Board && ((Board) obj).toFEN().equals(toFEN());
    }

    @Override
    public int hashCode() {
        return toFEN().hashCode();
    }

    @Override
    public String toString() {
        return toFEN();
    }

    private final Piece[][] _values;

}
