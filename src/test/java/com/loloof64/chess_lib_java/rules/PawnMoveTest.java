package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PawnMoveTest {

    Position pos1, pos2, pos3, pos4;

    @Before
    public void beforeEach(){
        pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1");
        pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1");
        pos3 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K w - - 0 1");
        pos4 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K b - - 0 1");
    }

    @Test
    public void pawnCanGoOneCellStraightAheadIfNoObstacle(){
        assertEquals(true, pos1.canMove(BoardCell.A3, BoardCell.A4));
        assertEquals(false, pos1.canMove(BoardCell.A3, BoardCell.A5));
        assertEquals(false, pos1.canMove(BoardCell.A3, BoardCell.A7));
        assertEquals(false, pos1.canMove(BoardCell.A3, BoardCell.A2));
        assertEquals(false, pos1.canMove(BoardCell.B2, BoardCell.B3));

        assertEquals(true, pos2.canMove(BoardCell.D6, BoardCell.D5));
        assertEquals(false, pos2.canMove(BoardCell.D6, BoardCell.D7));
        assertEquals(false, pos2.canMove(BoardCell.D6, BoardCell.D3));
        assertEquals(false, pos2.canMove(BoardCell.A6, BoardCell.A5));

        assertEquals(true, pos3.canMove(BoardCell.C6, BoardCell.C7));

        assertEquals(true, pos4.canMove(BoardCell.F5, BoardCell.F4));
    }

    @Test
    public void pawnCanGoTwoCellsStraightAheadIfNoObstacleAndOnItsStartCell(){
        assertEquals(true, pos1.canMove(BoardCell.C2, BoardCell.C4));
        assertEquals(true, pos1.canMove(BoardCell.G2, BoardCell.G4));
        assertEquals(false, pos1.canMove(BoardCell.A3, BoardCell.A5));
        assertEquals(false, pos1.canMove(BoardCell.B2, BoardCell.B4));

        Position pos5 = Position.fromFEN("r6k/pp2N1pp/1bp5/6N1/1P6/P6P/5PP1/6K1 b - - 0 1");
        assertEquals(true, pos5.canMove(BoardCell.H7, BoardCell.H5));
        assertEquals(false, pos5.canMove(BoardCell.G7, BoardCell.G5));
    }

    @Test
    public void pawnCannotCaptureFriendPiece(){
        assertEquals(false, pos1.canMove(BoardCell.B2, BoardCell.A3));
        assertEquals(false, pos3.canMove(BoardCell.G2, BoardCell.H3));
        assertEquals(false, pos3.canMove(BoardCell.A5, BoardCell.B6));

        assertEquals(false, pos2.canMove(BoardCell.A6, BoardCell.B5));
        assertEquals(false, pos4.canMove(BoardCell.H7, BoardCell.G6));
        assertEquals(false, pos4.canMove(BoardCell.G6, BoardCell.F5));
    }

    @Test
    public void pawnCanCaptureEnemyPieceIfWellPlaced(){
        Position pos5 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 w - - 0 1");
        assertEquals(true, pos5.canMove(BoardCell.G3, BoardCell.F4));
        assertEquals(true, pos5.canMove(BoardCell.G3, BoardCell.H4));
        assertEquals(false, pos5.canMove(BoardCell.G3, BoardCell.E4));
        assertEquals(false, pos5.canMove(BoardCell.C4, BoardCell.D5));

        Position pos6 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 b - - 0 1");
        assertEquals(true, pos6.canMove(BoardCell.C5, BoardCell.B4));
        assertEquals(true, pos6.canMove(BoardCell.C5, BoardCell.D4));
        assertEquals(false, pos6.canMove(BoardCell.C5, BoardCell.E4));
        assertEquals(false, pos6.canMove(BoardCell.G4, BoardCell.F3));
    }

    @Test
    public void pawnCanCaptureEnPassantIfFeatureAvailable(){
        Position pos5 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3");
        assertEquals(true, pos5.canMove(BoardCell.E5, BoardCell.D6));
        assertEquals(false, pos5.canMove(BoardCell.E5, BoardCell.F6));

        Position pos6 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq e3 0 3");
        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.C3));

        Position pos7 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq - 0 3");
        assertEquals(false, pos7.canMove(BoardCell.E5, BoardCell.D6));
        assertEquals(false, pos7.canMove(BoardCell.E5, BoardCell.F6));

        Position pos8 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq - 0 3");
        assertEquals(false, pos8.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos8.canMove(BoardCell.D4, BoardCell.C3));

        Position pos9 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/3Pp3/8/3P4/PPP2PPP/RNBQKBNR w KQkq e6 0 1");
        assertEquals(true, pos9.canMove(BoardCell.D5, BoardCell.E6));
        assertEquals(false, pos9.canMove(BoardCell.D3, BoardCell.E4));

        Position pos10 = Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/3pP3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1");
        assertEquals(true, pos10.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos10.canMove(BoardCell.D6, BoardCell.E5));
    }

    @Test
    public void pawnCannotDoArbitraryMove(){
        assertEquals(false, pos1.canMove(BoardCell.C2, BoardCell.F5));
        assertEquals(false, pos1.canMove(BoardCell.C2, BoardCell.E3));
        assertEquals(false, pos1.canMove(BoardCell.C2, BoardCell.F3));

        assertEquals(false, pos2.canMove(BoardCell.D6, BoardCell.A3));
        assertEquals(false, pos2.canMove(BoardCell.G7, BoardCell.E6));
        assertEquals(false, pos2.canMove(BoardCell.G7, BoardCell.C6));
    }

}
