package com.loloof64.chess_lib_java.rules;

/**
 * Holds the castles rights of a given position.
 */
public class CastlesRights {

    /**
     * Constructor setting all rights.
     * @param whiteKingSide - boolean - set if white can castle king side.
     * @param whiteQueenSide - boolean - set if white can castle queen side.
     * @param blackKingSide - boolean - set if black can castle king side.
     * @param blackQueenSide - boolean - set if black can castle queen side.
     */
    CastlesRights(boolean whiteKingSide, boolean whiteQueenSide,
                         boolean blackKingSide, boolean blackQueenSide){
        this.whiteKingSide = whiteKingSide;
        this.whiteQueenSide = whiteQueenSide;
        this.blackKingSide = blackKingSide;
        this.blackQueenSide = blackQueenSide;
    }

    /**
     * Gets the castles part of the Forsyth-Edwards Notation.
     * @return String - the FEN notation (castles part).
     */
    public String toFEN(){
        final StringBuilder stringBuilder = new StringBuilder();

        if (whiteKingSide) stringBuilder.append('K');
        if (whiteQueenSide) stringBuilder.append('Q');
        if (blackKingSide) stringBuilder.append('k');
        if (blackQueenSide) stringBuilder.append('q');

        return stringBuilder.length() > 0 ? stringBuilder.toString() : "-";
    }

    /**
     * Returns a copy of these rights with the modified white king side castle right
     * @param newState - boolean - new state for white king side castle.
     * @return CastlesRights - a copy with the white king side castle modified.
     */
    public CastlesRights copyWithThisWhiteKingSideCastleRight(boolean newState){
        return new CastlesRights(newState, whiteQueenSide, blackKingSide, blackQueenSide);
    }

    /**
     * Returns a copy of these rights with the modified white queen side castle right
     * @param newState - boolean - new state for white queen side castle.
     * @return CastlesRights - a copy with the white queen side castle modified.
     */
    public CastlesRights copyWithThisWhiteQueenSideCastleRight(boolean newState){
        return new CastlesRights(whiteKingSide, newState, blackKingSide, blackQueenSide);
    }

    /**
     * Returns a copy of these rights with the modified black king side castle right
     * @param newState - boolean - new state for black king side castle.
     * @return CastlesRights - a copy with the black king side castle modified.
     */
    public CastlesRights copyWithThisBlackKingSideCastleRight(boolean newState){
        return new CastlesRights(whiteKingSide, whiteQueenSide, newState, blackQueenSide);
    }

    /**
     * Returns a copy of these rights with the modified white queen side castle right
     * @param newState - boolean - new state for black queen side castle.
     * @return CastlesRights - a copy with the black queen side castle modified.
     */
    public CastlesRights copyWithThisBlackQueenSideCastleRight(boolean newState){
        return new CastlesRights(whiteKingSide, whiteQueenSide, blackKingSide, newState);
    }

    /**
     * Gets CastlesRights instance from Forsyth-Edwards Notation.
     * @param fenStr - String - position FEN.
     * @return CastlesRights - rights generated from the FEN.
     */
    static CastlesRights fromFEN(String fenStr){
        final String castlesPart = fenStr.split("\\s+")[2];
        return new CastlesRights(castlesPart.contains("K"), castlesPart.contains("Q"),
                castlesPart.contains("k"), castlesPart.contains("q"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CastlesRights that = (CastlesRights) o;

        if (whiteKingSide != that.whiteKingSide) return false;
        if (whiteQueenSide != that.whiteQueenSide) return false;
        if (blackKingSide != that.blackKingSide) return false;
        return blackQueenSide == that.blackQueenSide;
    }

    @Override
    public int hashCode() {
        int result = (whiteKingSide ? 1 : 0);
        result = 31 * result + (whiteQueenSide ? 1 : 0);
        result = 31 * result + (blackKingSide ? 1 : 0);
        result = 31 * result + (blackQueenSide ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CastlesRights{" +
                "whiteKingSide=" + whiteKingSide +
                ", whiteQueenSide=" + whiteQueenSide +
                ", blackKingSide=" + blackKingSide +
                ", blackQueenSide=" + blackQueenSide +
                '}';
    }

    public final boolean whiteKingSide;
    public final boolean whiteQueenSide;
    public final boolean blackKingSide;
    public final boolean blackQueenSide;

}
