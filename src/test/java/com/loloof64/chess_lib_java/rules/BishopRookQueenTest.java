package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BishopRookQueenTest {

    @Test
    public void bishopCanMoveInDiagonalAndAwayIfNoObstacle(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/3B4/8/8/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.B3));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.E6));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.B4));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.G4));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D7));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D3));
        assertEquals(true, pos1.canMove(BoardCell.D4,BoardCell.B2));
        assertEquals(true, pos1.canMove(BoardCell.D4,BoardCell.H8));
        assertEquals(true, pos1.canMove(BoardCell.D4,BoardCell.E3));
        assertEquals(true, pos1.canMove(BoardCell.D4,BoardCell.A7));

        Position pos2 = Position.fromFEN("4k3/8/8/8/4b3/8/8/4K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.G5));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.C3));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.E6));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.E3));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.A4));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.G4));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.G6));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.B1));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.A8));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.F3));
    }

    @Test
    public void bishopCanCaptureEnemyPieceIfOnItsPathButNotFriendPiece(){
        Position pos1 = Position.fromFEN("N3k3/8/6n1/8/4B3/8/6P1/1b2K3 w - - 0 1");
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.G6));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.B1));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.A8));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.G2));

        Position pos2 = Position.fromFEN("n3k3/8/6N1/8/4b3/8/6p1/1B2K3 b - - 0 1");
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.G6));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.B1));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.A8));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.G2));
    }

    @Test
    public void bishopCannotJumpOverObstacles(){
        Position pos1 = Position.fromFEN("n3k3/8/2p5/5P2/4B3/5N2/2b3p1/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.A8));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.G2));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.B1));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.G6));

        Position pos2 = Position.fromFEN("7r/4k3/1R3n2/8/3b4/2r5/5N2/b3K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.A1));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.H8));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.A7));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.G1));
    }

    @Test
    public void rookCanMoveHorizontallyOrVerticallyIfNoObstacle(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/3R4/8/8/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.C6));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.F3));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.G7));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.A7));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.G1));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.H4));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.D2));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.D7));

        Position pos2 = Position.fromFEN("4k3/8/8/8/3r4/8/8/4K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.C6));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.F3));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.G7));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.C3));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.A7));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.G1));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.C4));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.H4));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.D2));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.D7));
    }

    @Test
    public void rookCanCaptureEnemyPieceIfOnItsTargetSquareButNotFriendPiece(){
        Position pos1 = Position.fromFEN("3bk3/8/8/8/n2R2B1/8/3N4/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D2));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.G4));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.A4));
        assertEquals(true, pos1.canMove(BoardCell.D4, BoardCell.D8));

        Position pos2 = Position.fromFEN("3bk3/8/8/8/n2r2B1/8/3N4/4K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.A4));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D8));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.D2));
        assertEquals(true, pos2.canMove(BoardCell.D4, BoardCell.G4));
    }

    @Test
    public void rookCannotJumpOverObstacles(){
        Position pos1 = Position.fromFEN("3bk3/3R4/8/8/nN1R1n2/3P4/8/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.A4));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.G4));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D8));
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D2));

        Position pos2 = Position.fromFEN("3Bk3/3R4/8/8/BN1r1n2/3P4/8/4K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.A4));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.G4));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D8));
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D2));
    }

    @Test
    public void queenCanMoveEitherAsRookOrBishop(){
        Position pos1 = Position.fromFEN("4k3/1n2B3/2n5/8/bP2Q1P1/4p3/6N1/4K3 w - - 0 1");
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.E3));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.C2));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.C4));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.F3));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.F4));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.C6));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.E5));
        assertEquals(true, pos1.canMove(BoardCell.E4, BoardCell.H7));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.A4));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.B7));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.E7));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.H1));
        assertEquals(false, pos1.canMove(BoardCell.E4, BoardCell.G4));

        Position pos2 = Position.fromFEN("4k3/1n6/4B3/8/1N2q1nN/4p3/2P5/4K3 b - - 0 1");
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.D3));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.H7));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.B4));
        assertEquals(true, pos2.canMove(BoardCell.E4, BoardCell.E6));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.E3));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.E2));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.H4));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.B7));
        assertEquals(false, pos2.canMove(BoardCell.E4, BoardCell.A8));
    }

}
