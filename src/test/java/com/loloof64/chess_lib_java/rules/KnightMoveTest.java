package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.functional.monad.Maybe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightMoveTest {

    private Position pos1, pos2;

    @Before
    public void setupBeforeEach(){
        pos1 = Position.fromFEN("6k1/8/3N4/BpPpq3/1PNP4/3Pb3/8/4K3 w - - 0 1");
        pos2 = Position.fromFEN("6K1/8/3n4/bPpPQ3/1pnp4/3pB3/8/4k3 b - - 0 1");
    }

    @Test
    public void knightHasParticularMoveAndCanJumpOverPieces(){
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.A3));
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.B2));
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.D2));
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.B6));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.F4));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.A4));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.C2));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.C8));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.F7));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.A2));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.B5));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.E2));

        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.A3));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.B2));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.D2));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.B6));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.F4));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.A4));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.C2));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.C8));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.F7));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.A2));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.B5));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.E2));
    }

    @Test
    public void knightCannotGoOnCellOccupiedByFriendPiece(){
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.A5));
        assertEquals(false, pos1.canMove(BoardCell.C4, BoardCell.D6));

        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.A5));
        assertEquals(false, pos2.canMove(BoardCell.C4, BoardCell.D6));
    }

    @Test
    public void knightCanCaptureEnemyPieceIfOnItsTargetCell(){
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.E3));
        assertEquals(true, pos1.canMove(BoardCell.C4, BoardCell.E5));

        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.E3));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.E5));
    }

    @Test
    public void knightMoveGeneratesPositionCorrectly(){
        Maybe<Position> wrapPos3 = pos1.move(BoardCell.C4, BoardCell.B6);
        Position pos3 = wrapPos3.fromJust();
        assertEquals(Position.fromFEN("6k1/8/1N1N4/BpPpq3/1P1P4/3Pb3/8/4K3 b - - 1 1"), pos3);

        Maybe<Position> wrapPos4 = pos1.move(BoardCell.C4, BoardCell.E5);
        Position pos4 = wrapPos4.fromJust();
        assertEquals(Position.fromFEN("6k1/8/3N4/BpPpN3/1P1P4/3Pb3/8/4K3 b - - 0 1"), pos4);

        Maybe<Position> wrapPos5 = pos2.move(BoardCell.C4, BoardCell.D2);
        Position pos5 = wrapPos5.fromJust();
        assertEquals(Position.fromFEN("6K1/8/3n4/bPpPQ3/1p1p4/3pB3/3n4/4k3 w - - 1 2"), pos5);

        Maybe<Position> wrapPos6 = pos2.move(BoardCell.C4, BoardCell.E3);
        Position pos6 = wrapPos6.fromJust();
        assertEquals(Position.fromFEN("6K1/8/3n4/bPpPQ3/1p1p4/3pn3/8/4k3 w - - 0 2"), pos6);
    }

    @Test
    public void knightMoveClearsEnPassantFile(){
        Position pos3 = Position.fromFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.G8, BoardCell.F6);
        assertEquals(Position.fromFEN("rnbqkb1r/pppppppp/5n2/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2"), wrapPos4.fromJust());

        Position pos5 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.G1, BoardCell.F3);
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2"), wrapPos6.fromJust());
    }

    @Test
    public void knightAttackCellsTheSameWayHeMoves(){
        Position pos3 = Position.fromFEN("4k3/8/8/1P3b2/3N4/5K2/2B5/8 w - - 0 1");
        Piece pos3WhiteKnight = pos3.getPieceAt(BoardCell.D4);
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.C2, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.B3, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.B5, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.C6, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E6, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.F5, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.F3, pos3));
        assertEquals(true, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E2, pos3));
        assertEquals(false, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.D6, pos3));
        assertEquals(false, pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E4, pos3));

        Position pos4 = Position.fromFEN("8/8/3k4/P7/2n5/1B1p4/8/4K3 b - - 0 1");
        Piece pos4BlackKnight = pos4.getPieceAt(BoardCell.C4);
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.B2, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.A3, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.A5, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.B6, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.D6, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E5, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E3, pos4));
        assertEquals(true, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.D2, pos4));
        assertEquals(false, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E4, pos4));
        assertEquals(false, pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.C5, pos4));
    }
}
