package com.loloof64.chess_lib_java.rules.pieces;

import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;
import com.loloof64.functional.monad.Maybe;

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
     * Says if the piece belongs to white army.
     * @return boolean - does the piece belong to white army ?
     */
    public boolean isWhitePiece(){
        return whitePlayer;
    }

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

    /**
     * Says if this piece can do the given move in the given position.
     * @param moveToDo - {@link Move} - the move to do
     * @param position - {@link Position} - the position which this piece belongs to.
     * @return is this move correct ?
     */
    abstract public boolean canMove(Move moveToDo, Position position);

    /**
     * Executes the given move in the given position.
     * @param moveToDo - {@link Move} - the move to do
     * @param position - Position - the position which this piece belongs to.
     * @param promotionPiece - class of PromotablePiece - promotion piece if the move leads to pawn promotion.
     * @return Either of Exception and Position - Left of Exception if the move cannot be done, otherwise Right of
     * Position, wrapping the resulting position.
     */
    abstract public Either<Exception, Position> move(Move moveToDo,
                                                     Position position, Class<? extends PromotablePiece> promotionPiece);

    /**
     * Says if the piece is attacking the given cell, with no consideration about the current player turn
     * not the color of the piece on tested cell, nor the fact that it could be taken on this square.
     * @param pieceCell - BoardCell - the cell where is this piece on the board.
     * @param testedCell - BoardCell - tested cell.
     * @param position - Position - the position where all happens.
     * @return boolean - true if the tested cell is attacked, false otherwise.
     */
    public abstract boolean isAttackingCell(BoardCell pieceCell, BoardCell testedCell, Position position);

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

    boolean whitePlayer;
}
