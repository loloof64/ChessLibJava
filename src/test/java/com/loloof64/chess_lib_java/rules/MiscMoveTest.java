package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MiscMoveTest {

    @Test
    public void cannotMoveFromAnEmptyCell(){
        Position pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.E6, BoardCell.F7));

        Position pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.G4, BoardCell.E5));
    }

    @Test
    public void cannotMoveAnEnemyPiece(){
        Position pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.H8, BoardCell.H7));
        assertEquals(false, pos1.canMove(BoardCell.C7, BoardCell.C2));
        assertEquals(false, pos1.canMove(BoardCell.F6, BoardCell.G4));

        Position pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.B3, BoardCell.E6));
        assertEquals(false, pos2.canMove(BoardCell.G2, BoardCell.G4));
        assertEquals(false, pos2.canMove(BoardCell.H3, BoardCell.C8));
    }
}
