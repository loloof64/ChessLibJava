package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingMoveTest {

    Position pos1, pos2, pos3, pos4, pos5, pos6;

    @Before
    public void setupBeforeEach(){
        pos1 = Position.fromFEN("6k1/8/8/2nNn3/2NKN3/2nNn3/8/8 w - - 0 1");
        pos2 = Position.fromFEN("4K3/8/8/2NnN3/2nkn3/2NnN3/8/8 b - - 0 1");
        pos3 = Position.fromFEN("4k3/8/8/8/3K4/8/8/8 w - - 0 1");
        pos4 = Position.fromFEN("4K3/8/8/8/3k4/8/8/8 b - - 0 1");
        pos5 = Position.fromFEN("6k1/8/8/2NnN3/2nKn3/2NnN3/8/8 w - - 0 1");
        pos6 = Position.fromFEN("6K1/8/8/2nNn3/2NkN3/2nNn3/8/8 b - - 0 1");
    }

    @Test
    public void kingCanMoveOneCellTowardsAllDirections(){
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.C6));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.F3));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.D6));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.D1));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.B4));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.H4));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.H8));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.B2));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.B6));
        assertEquals(false, pos3.canMove(BoardCell.D4, BoardCell.G1));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.E4));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(true, pos3.canMove(BoardCell.D4, BoardCell.E3));

        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.C6));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.F3));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.D6));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.D1));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.B4));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.H4));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.H8));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.B2));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.B6));
        assertEquals(false, pos4.canMove(BoardCell.D4, BoardCell.G1));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.E4));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(true, pos4.canMove(BoardCell.D4, BoardCell.E3));
    }

    @Test
    public void kingCaptureEnemyPieceIfOnItsTargetCellButNotHisFriendPiece(){
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.E4));

        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.E4));

        assertEquals(true, pos5.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(true, pos5.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(true, pos5.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos5.canMove(BoardCell.D4, BoardCell.E4));
        assertEquals(false, pos5.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(false, pos5.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(false, pos5.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(false, pos5.canMove(BoardCell.D4, BoardCell.E3));

        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.D5));
        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.E4));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.C5));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.E5));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.E3));
    }

}
