package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.functional.monad.Either;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class KnightMoveTest {

    private Position pos1, pos2;

    @Before
    public void setupBeforeEach(){
        pos1 = Position.fromFEN("6k1/8/3N4/BpPpq3/1PNP4/3Pb3/8/4K3 w - - 0 1").right();
        pos2 = Position.fromFEN("6K1/8/3n4/bPpPQ3/1pnp4/3pB3/8/4k3 b - - 0 1").right();
    }

    @Test
    public void knightHasParticularMoveAndCanJumpOverPieces(){
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.A3)));
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.B2)));
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.D2)));
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.B6)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.F4)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.A4)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.C2)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.C8)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.F7)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.A2)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.B5)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.E2)));

        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.A3)));
        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.B2)));
        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.D2)));
        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.B6)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.F4)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.A4)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.C2)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.C8)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.F7)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.A2)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.B5)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.E2)));

        // ---

        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.A3)).isRight());
        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.B2)).isRight());
        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.D2)).isRight());
        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.B6)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.F4)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.A4)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.C2)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.C8)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.F7)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.A2)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.B5)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.E2)).isRight());

        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.A3)).isRight());
        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.B2)).isRight());
        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.D2)).isRight());
        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.B6)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.F4)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.A4)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.C2)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.C8)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.F7)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.A2)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.B5)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.E2)).isRight());
    }

    @Test
    public void knightCannotGoOnCellOccupiedByFriendPiece(){
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.A5)));
        assertFalse(pos1.canMove(new Move(BoardCell.C4, BoardCell.D6)));

        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.A5)));
        assertFalse(pos2.canMove(new Move(BoardCell.C4, BoardCell.D6)));

        /// ---

        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.A5)).isRight());
        assertFalse(pos1.move(new Move(BoardCell.C4, BoardCell.D6)).isRight());

        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.A5)).isRight());
        assertFalse(pos2.move(new Move(BoardCell.C4, BoardCell.D6)).isRight());
    }

    @Test
    public void knightCanCaptureEnemyPieceIfOnItsTargetCell(){
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.E3)));
        assertTrue(pos1.canMove(new Move(BoardCell.C4, BoardCell.E5)));

        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.E3)));
        assertTrue(pos2.canMove(new Move(BoardCell.C4, BoardCell.E5)));

        // ---

        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.E3)).isRight());
        assertTrue(pos1.move(new Move(BoardCell.C4, BoardCell.E5)).isRight());

        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.E3)).isRight());
        assertTrue(pos2.move(new Move(BoardCell.C4, BoardCell.E5)).isRight());
    }

    @Test
    public void knightMoveGeneratesPositionCorrectly(){
        Either<Exception, MoveResult> wrapPos3 = pos1.move(new Move(BoardCell.C4, BoardCell.B6));
        Position pos3 = wrapPos3.right().position;
        assertEquals(Position.fromFEN("6k1/8/1N1N4/BpPpq3/1P1P4/3Pb3/8/4K3 b - - 1 1").right(), pos3);
        assertEquals("Nb6", wrapPos3.right().moveSan );

        Either<Exception, MoveResult> wrapPos4 = pos1.move(new Move(BoardCell.C4, BoardCell.E5));
        Position pos4 = wrapPos4.right().position;
        assertEquals(Position.fromFEN("6k1/8/3N4/BpPpN3/1P1P4/3Pb3/8/4K3 b - - 0 1").right(), pos4);
        assertEquals( "Nxe5", wrapPos4.right().moveSan);

        Either<Exception, MoveResult> wrapPos5 = pos2.move(new Move(BoardCell.C4, BoardCell.D2));
        Position pos5 = wrapPos5.right().position;
        assertEquals(Position.fromFEN("6K1/8/3n4/bPpPQ3/1p1p4/3pB3/3n4/4k3 w - - 1 2").right(), pos5);
        assertEquals("Nd2", wrapPos5.right().moveSan);

        Either<Exception, MoveResult> wrapPos6 = pos2.move(new Move(BoardCell.C4, BoardCell.E3));
        Position pos6 = wrapPos6.right().position;
        assertEquals(Position.fromFEN("6K1/8/3n4/bPpPQ3/1p1p4/3pn3/8/4k3 w - - 0 2").right(), pos6);
        assertEquals("Nxe3", wrapPos6.right().moveSan);
    }

    @Test
    public void knightMoveClearsEnPassantFile(){
        Position pos3 = Position.fromFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").right();
        Either<Exception, MoveResult> wrapPos4 = pos3.move(new Move(BoardCell.G8, BoardCell.F6));
        assertEquals(Position.fromFEN("rnbqkb1r/pppppppp/5n2/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2").right(), wrapPos4.right().position);
        assertEquals("Nf6", wrapPos4.right().moveSan);

        Position pos5 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, MoveResult> wrapPos6 = pos5.move(new Move(BoardCell.G1, BoardCell.F3));
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2").right(), wrapPos6.right().position);
        assertEquals("Nf3", wrapPos6.right().moveSan);
    }

    @Test
    public void knightAttackCellsTheSameWayHeMoves(){
        Position pos3 = Position.fromFEN("4k3/8/8/1P3b2/3N4/5K2/2B5/8 w - - 0 1").right();
        Piece pos3WhiteKnight = pos3.getPieceAt(BoardCell.D4);
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.C2, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.B3, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.B5, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.C6, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E6, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.F5, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.F3, pos3));
        assertTrue(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E2, pos3));
        assertFalse(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.D6, pos3));
        assertFalse(pos3WhiteKnight.isAttackingCell(BoardCell.D4, BoardCell.E4, pos3));

        Position pos4 = Position.fromFEN("8/8/3k4/P7/2n5/1B1p4/8/4K3 b - - 0 1").right();
        Piece pos4BlackKnight = pos4.getPieceAt(BoardCell.C4);
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.B2, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.A3, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.A5, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.B6, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.D6, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E5, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E3, pos4));
        assertTrue(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.D2, pos4));
        assertFalse(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.E4, pos4));
        assertFalse(pos4BlackKnight.isAttackingCell(BoardCell.C4, BoardCell.C5, pos4));
    }
}
