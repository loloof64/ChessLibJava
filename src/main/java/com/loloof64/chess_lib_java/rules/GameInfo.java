package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardFile;

/**
 * Holds position info : player turn, move number, castles rights ...
 */
public class GameInfo {

    /**
     * Game info constructor with all parameters.
     * @param whiteTurn - boolean - is it white turn ?
     * @param castlesRights - CastlesRights - castle rights, can't be null
     * @param enPassantFile - BoardFile, null if you don't want an en passant file
     * @param nullityHalfMovesCount - int - half moves number since last capture or pawn move.
     * @param moveNumber - int - move number
     */
    GameInfo(boolean whiteTurn, CastlesRights castlesRights, BoardFile enPassantFile,
                    int nullityHalfMovesCount, int moveNumber){
        if (castlesRights == null) throw new IllegalArgumentException("Castle rights can't be null");
        if (nullityHalfMovesCount < 0) throw new IllegalArgumentException("Nullity half moves count must be 0 or greater !");
        if (moveNumber < 1) throw new IllegalArgumentException("Move number must be 1 or greater !");

        this.whiteTurn = whiteTurn;
        this.castlesRights = castlesRights;
        this.enPassantFile = enPassantFile;
        this.nullityHalfMovesCount = nullityHalfMovesCount;
        this.moveNumber = moveNumber;
    }

    /**
     * Generates a game info from a Forsyth-Edwards Notation string.
     * @param fenStr - String - FEN to convert: gives the full FEN str, not just the game info part !!!
     * @return GameInfo - generated GameInfo from the FEN parameter.
     */
    public static GameInfo fromFEN(String fenStr){
        final String [] parts = fenStr.split("\\s+");
        final boolean whiteTurn = !parts[1].equals("b");
        final CastlesRights castlesRights = CastlesRights.fromFEN(fenStr);
        final BoardFile enPassantFile;
        if (parts[3].equals("-")){
            enPassantFile = null;
        }
        else {
            final int fileIndex = (int) parts[3].charAt(0) - (int) 'a';
            enPassantFile = BoardFile.values()[fileIndex];
        }
        final int nullityHalfMovesCount = Integer.parseInt(parts[4]);
        final int moveNumber = Integer.parseInt(parts[5]);

        return new GameInfo(whiteTurn, castlesRights, enPassantFile, nullityHalfMovesCount, moveNumber);
    }

    /**
     * Generated a Forsyth-Edwards Notation representation of this game info.
     * @return String - FEN string.
     */
    public String toFEN(){
        final char playerTurnChar = whiteTurn ? 'w' : 'b';
        final String enPassantFileStr;

        if (enPassantFile == null){
            enPassantFileStr = "-";
        }
        else {
            final char enPassantFileChar = (char) ('a' + enPassantFile.ordinal());
            final char enPassantRankChar = whiteTurn ? '6' : '3';
            enPassantFileStr = String.format("%c%c", enPassantFileChar, enPassantRankChar);
        }

        return String.format("%c %s %s %d %d", playerTurnChar, castlesRights.toFEN(), enPassantFileStr, nullityHalfMovesCount, moveNumber);
    }

    /**
     * Gets a copy of this game info but with the player turn reversed.
     * Caution ! This will also update the move number if we switch from black to white.
     * @return GameInfo - the copy with player turn inverse, and eventually move number incremented.
     */
    public GameInfo copyWithTurnReversedAndMoveNumberUpdated() {
        return new GameInfo(!whiteTurn, castlesRights, enPassantFile, nullityHalfMovesCount,
                whiteTurn ? moveNumber : moveNumber+1);
    }

    /**
     * Gets a copy of this game info but with the given en passant file.
     * @param newEnPassantFile - BoardFile - the en passant file.
     * @return GameInfo - the copy with the given en passant file.
     */
    public GameInfo copyWithThisEnPassantFile(BoardFile newEnPassantFile) {
        return new GameInfo(whiteTurn, castlesRights, newEnPassantFile, nullityHalfMovesCount, moveNumber);
    }

    /**
     * Gets a copy of this game info but with the given nullity half moves count.
     * @param newCount - int - the new value for nullity half moves count.
     * @return GameInfo - the copy with the given nullity half moves count.
     */
    public GameInfo copyWithThisNullityHalfMovesCount(int newCount){
        return new GameInfo(whiteTurn, castlesRights, enPassantFile, newCount, moveNumber);
    }

    /**
     * Gets a copy with a modified state for white king side castle availability.
     * @param newState - boolean - new state for white king side castle.
     * @return GameInfo - a copy with white king side castle availability modified
     */
    public GameInfo copyWithThisWhiteKingSideCastleState(boolean newState){
        return new GameInfo(whiteTurn, castlesRights.copyWithThisWhiteKingSideCastleRight(newState), enPassantFile,
                nullityHalfMovesCount, moveNumber);
    }

    /**
     * Gets a copy with a modified state for white queen side castle availability.
     * @param newState - boolean - new state for white queen side castle.
     * @return GameInfo - a copy with white queen side castle availability modified
     */
    public GameInfo copyWithThisWhiteQueenSideCastleState(boolean newState){
        return new GameInfo(whiteTurn, castlesRights.copyWithThisWhiteQueenSideCastleRight(newState), enPassantFile,
                nullityHalfMovesCount, moveNumber);
    }

    /**
     * Gets a copy with a modified state for black king side castle availability.
     * @param newState - boolean - new state for black king side castle.
     * @return GameInfo - a copy with white black side castle availability modified
     */
    public GameInfo copyWithThisBlackKingSideCastleState(boolean newState){
        return new GameInfo(whiteTurn, castlesRights.copyWithThisBlackKingSideCastleRight(newState), enPassantFile,
                nullityHalfMovesCount, moveNumber);
    }

    /**
     * Gets a copy with a modified state for black queen side castle availability.
     * @param newState - boolean - new state for black queen side castle.
     * @return GameInfo - a copy with black queen side castle availability modified
     */
    public GameInfo copyWithThisBlackQueenSideCastleState(boolean newState){
        return new GameInfo(whiteTurn, castlesRights.copyWithThisBlackQueenSideCastleRight(newState), enPassantFile,
                nullityHalfMovesCount, moveNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInfo gameInfo = (GameInfo) o;

        if (whiteTurn != gameInfo.whiteTurn) return false;
        if (nullityHalfMovesCount != gameInfo.nullityHalfMovesCount) return false;
        if (moveNumber != gameInfo.moveNumber) return false;
        if (!castlesRights.equals(gameInfo.castlesRights)) return false;
        return enPassantFile == gameInfo.enPassantFile;
    }

    @Override
    public int hashCode() {
        int result = (whiteTurn ? 1 : 0);
        result = 31 * result + castlesRights.hashCode();
        result = 31 * result + (enPassantFile != null ? enPassantFile.hashCode() : 0);
        result = 31 * result + nullityHalfMovesCount;
        result = 31 * result + moveNumber;
        return result;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "whiteTurn=" + whiteTurn +
                ", castlesRights=" + castlesRights +
                ", enPassantFile=" + enPassantFile +
                ", nullityHalfMovesCount=" + nullityHalfMovesCount +
                ", moveNumber=" + moveNumber +
                '}';
    }

    public final boolean whiteTurn;
    public final CastlesRights castlesRights;
    public final BoardFile enPassantFile;
    public final int nullityHalfMovesCount;
    public final int moveNumber;
}
