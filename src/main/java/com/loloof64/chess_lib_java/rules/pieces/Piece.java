package com.loloof64.chess_lib_java.rules.pieces;

/**
 * Representation of a chess Piece.
 */
public abstract class Piece {

    /**
     * Gets the Forsyth-Edwards Notation of this piece.
     * @return char - the FEN value of this piece.
     */
    public abstract char toFEN();

    /**
     * Translate a Forsyth-Edwards Notation to a Piece instance.
     * @param fenChar - char - the FEN we want to convert.
     * @return Piece - piece equivalent to the fenChar
     */
    public static Piece fromFEN(char fenChar){
        switch (fenChar){
            case 'P' : return new Pawn(true);
            case 'p': return new Pawn(false);
            case 'N': return new Knight(true);
            case 'n': return new Knight(false);
            case 'B': return new Bishop(true);
            case 'b': return new Bishop(false);
            case 'R': return new Rook(true);
            case 'r': return new Rook(false);
            case 'Q': return new Queen(true);
            case 'q': return new Queen(false);
            case 'K': return new King(true);
            case 'k': return new King(false);
            default: return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        return whitePlayer == piece.whitePlayer;
    }

    @Override
    public int hashCode() {
        return (whitePlayer ? 1 : 0);
    }

    protected boolean whitePlayer;
}
