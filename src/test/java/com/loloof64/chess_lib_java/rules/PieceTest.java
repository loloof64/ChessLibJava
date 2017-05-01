package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.pieces.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PieceTest {

    @Test
    public void pieceIsConvertedFromFENCorrectly(){
        assertEquals(new Pawn(true), Piece.fromFEN('P'));
        assertEquals(new Pawn(false), Piece.fromFEN('p'));
        assertEquals(new Knight(true), Piece.fromFEN('N'));
        assertEquals(new Knight(false), Piece.fromFEN('n'));
        assertEquals(new Bishop(true), Piece.fromFEN('B'));
        assertEquals(new Bishop(false), Piece.fromFEN('b'));
        assertEquals(new Rook(true), Piece.fromFEN('R'));
        assertEquals(new Rook(false), Piece.fromFEN('r'));
        assertEquals(new Queen(true), Piece.fromFEN('Q'));
        assertEquals(new Queen(false), Piece.fromFEN('q'));
        assertEquals(new King(true), Piece.fromFEN('K'));
        assertEquals(new King(false), Piece.fromFEN('k'));
    }

    @Test
    public void pawnGenerateCorrectFEN(){
        assertEquals('P', new Pawn(true).toFEN());
        assertEquals('p', new Pawn(false).toFEN());
    }

    @Test
    public void knightGenerateCorrectFEN(){
        assertEquals('N', new Knight(true).toFEN());
        assertEquals('n', new Knight(false).toFEN());
    }

    @Test
    public void bishopGenerateCorrectFEN(){
        assertEquals('B', new Bishop(true).toFEN());
        assertEquals('b', new Bishop(false).toFEN());
    }

    @Test
    public void rookGenerateCorrectFEN(){
        assertEquals('R', new Rook(true).toFEN());
        assertEquals('r', new Rook(false).toFEN());
    }

    @Test
    public void queenGenerateCorrectFEN(){
        assertEquals('Q', new Queen(true).toFEN());
        assertEquals('q', new Queen(false).toFEN());
    }

    @Test
    public void kingGenerateCorrectFEN(){
        assertEquals('K', new King(true).toFEN());
        assertEquals('k', new King(false).toFEN());
    }
}
