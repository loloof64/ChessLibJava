package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.*;
import com.loloof64.functional.monad.Either;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PawnMoveTest {

    private Position pos1, pos2, pos3, pos4;

    @Before
    public void beforeEach(){
        pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1").right();
        pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1").right();
        pos3 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K w - - 0 1").right();
        pos4 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K b - - 0 1").right();
    }

    @Test
    public void pawnCanGoOneCellStraightAheadIfNoObstacle(){
        assertTrue(pos1.canMove(new Move(BoardCell.A3, BoardCell.A4)));
        assertFalse(pos1.canMove(new Move(BoardCell.A3, BoardCell.A5)));
        assertFalse(pos1.canMove(new Move(BoardCell.A3, BoardCell.A7)));
        assertFalse(pos1.canMove(new Move(BoardCell.A3, BoardCell.A2)));
        assertFalse(pos1.canMove(new Move(BoardCell.B2, BoardCell.B3)));

        assertTrue(pos2.canMove(new Move(BoardCell.D6, BoardCell.D5)));
        assertFalse(pos2.canMove(new Move(BoardCell.D6, BoardCell.D7)));
        assertFalse(pos2.canMove(new Move(BoardCell.D6, BoardCell.D3)));
        assertFalse(pos2.canMove(new Move(BoardCell.A6, BoardCell.A5)));

        assertTrue(pos3.canMove(new Move(BoardCell.C6, BoardCell.C7)));

        assertTrue(pos4.canMove(new Move(BoardCell.F5, BoardCell.F4)));
    }

    @Test
    public void pawnCanGoTwoCellsStraightAheadIfNoObstacleAndOnItsStartCell(){
        assertTrue(pos1.canMove(new Move(BoardCell.C2, BoardCell.C4)));
        assertTrue(pos1.canMove(new Move(BoardCell.G2, BoardCell.G4)));
        assertFalse(pos1.canMove(new Move(BoardCell.A3, BoardCell.A5)));
        assertFalse(pos1.canMove(new Move(BoardCell.B2, BoardCell.B4)));

        Position pos5 = Position.fromFEN("r6k/pp2N1pp/1bp5/6N1/1P6/P6P/5PP1/6K1 b - - 0 1").right();
        assertTrue(pos5.canMove(new Move(BoardCell.H7, BoardCell.H5)));
        assertFalse(pos5.canMove(new Move(BoardCell.G7, BoardCell.G5)));
    }

    @Test
    public void pawnCannotCaptureFriendPiece(){
        assertFalse(pos1.canMove(new Move(BoardCell.B2, BoardCell.A3)));
        assertFalse(pos3.canMove(new Move(BoardCell.G2, BoardCell.H3)));
        assertFalse(pos3.canMove(new Move(BoardCell.A5, BoardCell.B6)));

        assertFalse(pos2.canMove(new Move(BoardCell.A6, BoardCell.B5)));
        assertFalse(pos4.canMove(new Move(BoardCell.H7, BoardCell.G6)));
        assertFalse(pos4.canMove(new Move(BoardCell.G6, BoardCell.F5)));
    }

    @Test
    public void pawnCanCaptureEnemyPieceIfWellPlaced(){
        Position pos5 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 w - - 0 1").right();
        assertTrue(pos5.canMove(new Move(BoardCell.G3, BoardCell.F4)));
        assertTrue(pos5.canMove(new Move(BoardCell.G3, BoardCell.H4)));
        assertFalse(pos5.canMove(new Move(BoardCell.G3, BoardCell.E4)));
        assertFalse(pos5.canMove(new Move(BoardCell.C4, BoardCell.D5)));

        Position pos6 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 b - - 0 1").right();
        assertTrue(pos6.canMove(new Move(BoardCell.C5, BoardCell.B4)));
        assertTrue(pos6.canMove(new Move(BoardCell.C5, BoardCell.D4)));
        assertFalse(pos6.canMove(new Move(BoardCell.C5, BoardCell.E4)));
        assertFalse(pos6.canMove(new Move(BoardCell.G4, BoardCell.F3)));
    }

    @Test
    public void pawnCanCaptureEnPassantIfFeatureAvailable(){
        Position pos5 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3").right();
        assertTrue(pos5.canMove(new Move(BoardCell.E5, BoardCell.D6)));
        assertFalse(pos5.canMove(new Move(BoardCell.E5, BoardCell.F6)));

        Position pos6 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq e3 0 3").right();
        assertTrue(pos6.canMove(new Move(BoardCell.D4, BoardCell.E3)));
        assertFalse(pos6.canMove(new Move(BoardCell.D4, BoardCell.C3)));

        Position pos7 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq - 0 3").right();
        assertFalse(pos7.canMove(new Move(BoardCell.E5, BoardCell.D6)));
        assertFalse(pos7.canMove(new Move(BoardCell.E5, BoardCell.F6)));

        Position pos8 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq - 0 3").right();
        assertFalse(pos8.canMove(new Move(BoardCell.D4, BoardCell.E3)));
        assertFalse(pos8.canMove(new Move(BoardCell.D4, BoardCell.C3)));

        Position pos9 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/3Pp3/8/3P4/PPP2PPP/RNBQKBNR w KQkq e6 0 1").right();
        assertTrue(pos9.canMove(new Move(BoardCell.D5, BoardCell.E6)));
        assertFalse(pos9.canMove(new Move(BoardCell.D3, BoardCell.E4)));

        Position pos10 = Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/3pP3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").right();
        assertTrue(pos10.canMove(new Move(BoardCell.D4, BoardCell.E3)));
        assertFalse(pos10.canMove(new Move(BoardCell.D6, BoardCell.E5)));
    }

    @Test
    public void pawnCannotDoArbitraryMove(){
        assertFalse(pos1.canMove(new Move(BoardCell.C2, BoardCell.F5)));
        assertFalse(pos1.canMove(new Move(BoardCell.C2, BoardCell.E3)));
        assertFalse(pos1.canMove(new Move(BoardCell.C2, BoardCell.F3)));

        assertFalse(pos2.canMove(new Move(BoardCell.D6, BoardCell.A3)));
        assertFalse(pos2.canMove(new Move(BoardCell.G7, BoardCell.E6)));
        assertFalse(pos2.canMove(new Move(BoardCell.G7, BoardCell.C6)));
    }

    @Test
    public void legalStandardPawnMoveGeneratesCorrectPosition(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1").right();
        Either<Exception, Position> wrapPos2 = pos1.move(new Move(BoardCell.E2, BoardCell.E4));
        Position pos2 = wrapPos2.right();
        assertEquals(Position.fromFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").right(), pos2);

        Either<Exception, Position> wrapPos3 = pos2.move(new Move(BoardCell.D7, BoardCell.D6));
        Position pos3 = wrapPos3.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/3p4/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2").right(), pos3);

        Either<Exception, Position> wrapPos4 = pos3.move(new Move(BoardCell.D2, BoardCell.D4));
        Position pos4 = wrapPos4.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/3p4/8/3PP3/8/PPP2PPP/RNBQKBNR b KQkq d3 0 2").right(), pos4);

        Either<Exception, Position> wrapPos5 = pos4.move(new Move(BoardCell.E7, BoardCell.E5));
        Position pos5 = wrapPos5.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/4p3/3PP3/8/PPP2PPP/RNBQKBNR w KQkq e6 0 3").right(), pos5);

        Either<Exception, Position> wrapPos6 = pos5.move(new Move(BoardCell.D4, BoardCell.E5));
        Position pos6 = wrapPos6.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/4P3/4P3/8/PPP2PPP/RNBQKBNR b KQkq - 0 3").right(), pos6);

        Position pos7 = Position.fromFEN("8/2P4k/8/8/8/8/8/4K3 w - - 0 1").right();
        Either<Exception, Position> wrapPos8 = pos7.move(new Move(BoardCell.C7, BoardCell.C8), Queen.class);
        Position pos8 = wrapPos8.right();
        assertEquals(Position.fromFEN("2Q5/7k/8/8/8/8/8/4K3 b - - 0 1").right(), pos8);
        Either<Exception, Position> wrapPos9 = pos7.move(new Move(BoardCell.C7, BoardCell.C8), Bishop.class);
        Position pos9 = wrapPos9.right();
        assertEquals(Position.fromFEN("2B5/7k/8/8/8/8/8/4K3 b - - 0 1").right(), pos9);

        Position pos10 = Position.fromFEN("4k3/8/8/8/8/8/3p3K/2N5 b - - 0 1").right();
        Either<Exception, Position> wrapPos11 = pos10.move(new Move(BoardCell.D2, BoardCell.C1), Rook.class);
        Position pos11 = wrapPos11.right();
        assertEquals(Position.fromFEN("4k3/8/8/8/8/8/7K/2r5 w - - 0 2").right(), pos11);
        Either<Exception, Position> wrapPos12 = pos10.move(new Move(BoardCell.D2, BoardCell.C1), Knight.class);
        Position pos12 = wrapPos12.right();
        assertEquals(Position.fromFEN("4k3/8/8/8/8/8/7K/2n5 w - - 0 2").right(), pos12);
    }

    @Test
    public void enPassantPawnMoveGeneratesCorrectPosition(){
        Position pos1 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3").right();
        Either<Exception, Position> wrapPos2 = pos1.move(new Move(BoardCell.E5, BoardCell.D6));
        Position pos2 = wrapPos2.right();
        assertEquals(Position.fromFEN("rnbqkb1r/ppp1pppp/3P3n/8/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 3").right(), pos2);

        Position pos3 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq e3 0 3").right();
        Either<Exception, Position> wrapPos4 = pos3.move(new Move(BoardCell.D4, BoardCell.E3));
        Position pos4 = wrapPos4.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/8/4p3/PPPP1PPP/RNBQKB1R w KQkq - 0 4").right(), pos4);

        Position pos5 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/3Pp3/8/3P4/PPP2PPP/RNBQKBNR w KQkq e6 0 1").right();
        Either<Exception, Position> wrapPos6 = pos5.move(new Move(BoardCell.D5, BoardCell.E6));
        Position pos6 = wrapPos6.right();
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/4P3/8/8/3P4/PPP2PPP/RNBQKBNR b KQkq - 0 1").right(), pos6);

        Position pos7 = Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/3pP3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").right();
        Either<Exception, Position> wrapPos8 = pos7.move(new Move(BoardCell.D4, BoardCell.E3));
        Position pos8 = wrapPos8.right();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/8/4p3/PPPP1PPP/RNBQKBNR w KQkq - 0 2").right(), pos8);
    }

    @Test
    public void pawnAttackCellsDirectlyInFrontOfHimAndInDiagonal(){
        Position pos1 = Position.fromFEN("4kr1R/6P1/8/2p5/2P5/8/1p6/N1n1K3 w - - 0 1").right();

        Pawn pos1Pawn1 = (Pawn) pos1.getPieceAt(BoardCell.C4);
        assertTrue(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B5, pos1));
        assertTrue(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.D5, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B4, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.C5, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.A6, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.F7, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B3, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.C3, pos1));
        assertFalse(pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.D3, pos1));
        Pawn pos1Pawn2 = (Pawn) pos1.getPieceAt(BoardCell.G7);
        assertTrue(pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.F8, pos1));
        assertTrue(pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.H8, pos1));
        assertFalse(pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.G8, pos1));
        assertFalse(pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.H7, pos1));
        assertFalse(pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.F6, pos1));

        Position pos2 = Position.fromFEN("4kr1R/6P1/8/2p5/2P5/8/1p6/N1n1K3 b - - 0 1").right();
        Pawn pos2Pawn1 = (Pawn) pos2.getPieceAt(BoardCell.C5);
        assertTrue(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.B4, pos2));
        assertTrue(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D4, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.C4, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D5, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.F2, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.A3, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.B6, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.C6, pos2));
        assertFalse(pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D6, pos2));
        Pawn pos2Pawn2 = (Pawn) pos2.getPieceAt(BoardCell.B2);
        assertTrue(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A1, pos2));
        assertTrue(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C1, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.B1, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.E5, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A2, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C2, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A3, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.B3, pos2));
        assertFalse(pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C3, pos2));
    }

}
