package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightMoveTest {

    Position pos1, pos2;

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

        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.A3));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.B2));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.D2));
        assertEquals(true, pos2.canMove(BoardCell.C4, BoardCell.B6));
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
}
