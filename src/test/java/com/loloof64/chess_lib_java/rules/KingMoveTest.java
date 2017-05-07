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

    @Test
    public void kingCanDoKingSideCastleIfFeatureAvailableAndPathFree(){
        Position pos1 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4");
        assertEquals(true, pos1.canMove(BoardCell.E1, BoardCell.G1));

        Position pos2 = Position.fromFEN("r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3");
        assertEquals(false, pos2.canMove(BoardCell.E1, BoardCell.G1));

        Position pos3 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/2b1p3/2B1P3/8/PPPP1PPP/RNBQK1NR w KQkq - 2 3");
        assertEquals(false, pos3.canMove(BoardCell.E1, BoardCell.G1));

        Position pos4 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPPN1PPP/R1BQKB1R b KQkq - 2 4");
        assertEquals(true, pos4.canMove(BoardCell.E8, BoardCell.G8));

        Position pos5 = Position.fromFEN("rnbqkb1r/pppp1ppp/5n2/4p3/4P3/3P1N2/PPP2PPP/RNBQKB1R b KQkq - 0 3");
        assertEquals(false, pos5.canMove(BoardCell.E8, BoardCell.G8));

        Position pos6 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/2b1p3/4P3/3P1N2/PPP2PPP/RNBQKB1R b KQkq - 0 3");
        assertEquals(false, pos6.canMove(BoardCell.E8, BoardCell.G8));

        Position pos7 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w Qkq - 4 4");
        assertEquals(false, pos7.canMove(BoardCell.E1, BoardCell.G1));

        Position pos8 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPPN1PPP/R1BQKB1R b KQq - 2 4");
        assertEquals(false, pos8.canMove(BoardCell.E8, BoardCell.G8));
    }

    @Test
    public void kingCanDoQueenSideCastleIfFeatureAvailableAndPathFree(){
        Position pos1 = Position.fromFEN("rnbq1rk1/ppp1bppp/3p1n2/4p1B1/4P3/2NP4/PPPQ1PPP/R3KBNR w KQ - 4 6");
        assertEquals(true, pos1.canMove(BoardCell.E1, BoardCell.C1));

        Position pos2 = Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p1B1/4P3/2NP4/PPP2PPP/R2QKBNR w KQ - 5 5");
        assertEquals(false, pos2.canMove(BoardCell.E1, BoardCell.C1));

        Position pos3 = Position.fromFEN("rnbqk2r/ppp1bppp/3p1n2/4p1B1/4P3/3P4/PPPQ1PPP/RN2KBNR w KQkq - 2 5");
        assertEquals(false, pos3.canMove(BoardCell.E1, BoardCell.C1));

        Position pos4 = Position.fromFEN("rnbq1rk1/ppppbppp/5n2/4p3/4P3/2NP4/PPPQ1PPP/R1B1KBNR w KQ - 5 5");
        assertEquals(false, pos4.canMove(BoardCell.E1, BoardCell.C1));

        Position pos5 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p3/2B1P1b1/P1N2N2/1PPP1PPP/R1BQ1RK1 b kq - 2 6");
        assertEquals(true, pos5.canMove(BoardCell.E8, BoardCell.C8));

        Position pos6 = Position.fromFEN("r2qkbnr/ppp2ppp/2np4/4p3/2B1P1b1/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5");
        assertEquals(false, pos6.canMove(BoardCell.E8, BoardCell.C8));

        Position pos7 = Position.fromFEN("rn2kbnr/pppq1ppp/3p4/4p3/2B1P1b1/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5");
        assertEquals(false, pos7.canMove(BoardCell.E8, BoardCell.C8));

        Position pos8 = Position.fromFEN("r1b1kbnr/pppq1ppp/2np4/4p3/2B1P3/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5");
        assertEquals(false, pos8.canMove(BoardCell.E8, BoardCell.C8));

        Position pos9 = Position.fromFEN("rnbq1rk1/ppp1bppp/3p1n2/4p1B1/4P3/2NP4/PPPQ1PPP/R3KBNR w K - 4 6");
        assertEquals(false, pos9.canMove(BoardCell.E1, BoardCell.C1));

        Position pos10 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p3/2B1P1b1/P1N2N2/1PPP1PPP/R1BQ1RK1 b k - 2 6");
        assertEquals(false, pos10.canMove(BoardCell.E8, BoardCell.C8));
    }

}
