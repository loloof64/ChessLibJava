package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.functional.monad.Either;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class KingMoveTest {

    private Position pos1, pos2, pos3, pos4, pos5, pos6;

    @Before
    public void setupBeforeEach(){
        pos1 = Position.fromFEN("6k1/8/8/2nNn3/2NKN3/2nNn3/8/8 w - - 0 1").right();
        pos2 = Position.fromFEN("4K3/8/8/2NnN3/2nkn3/2NnN3/8/8 b - - 0 1").right();
        pos3 = Position.fromFEN("4k3/8/8/8/3K4/8/8/8 w - - 0 1").right();
        pos4 = Position.fromFEN("4K3/8/8/8/3k4/8/8/8 b - - 0 1").right();
        pos5 = Position.fromFEN("6k1/8/8/2NnN3/2nKn3/2NnN3/8/8 w - - 0 1").right();
        pos6 = Position.fromFEN("6K1/8/8/2nNn3/2NkN3/2nNn3/8/8 b - - 0 1").right();
    }

    @Test
    public void kingCanMoveOneCellTowardsAllDirections(){
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.C6));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.F3));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.D6));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.D1));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.B4));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.H4));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.H8));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.B2));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.B6));
        assertFalse(pos3.canMove(BoardCell.D4, BoardCell.G1));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.C5));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.D5));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.E5));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.C4));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.E4));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.C3));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.D3));
        assertTrue(pos3.canMove(BoardCell.D4, BoardCell.E3));

        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.C6));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.F3));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.D6));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.D1));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.B4));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.H4));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.H8));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.B2));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.B6));
        assertFalse(pos4.canMove(BoardCell.D4, BoardCell.G1));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.C5));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.D5));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.E5));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.C4));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.E4));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.C3));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.D3));
        assertTrue(pos4.canMove(BoardCell.D4, BoardCell.E3));
    }

    @Test
    public void kingCaptureEnemyPieceIfOnItsTargetCellButNotHisFriendPiece(){
        assertTrue(pos1.canMove(BoardCell.D4, BoardCell.C5));
        assertTrue(pos1.canMove(BoardCell.D4, BoardCell.E5));
        assertTrue(pos1.canMove(BoardCell.D4, BoardCell.C3));
        assertTrue(pos1.canMove(BoardCell.D4, BoardCell.E3));
        assertFalse(pos1.canMove(BoardCell.D4, BoardCell.D5));
        assertFalse(pos1.canMove(BoardCell.D4, BoardCell.D3));
        assertFalse(pos1.canMove(BoardCell.D4, BoardCell.C4));
        assertFalse(pos1.canMove(BoardCell.D4, BoardCell.E4));

        assertTrue(pos2.canMove(BoardCell.D4, BoardCell.C5));
        assertTrue(pos2.canMove(BoardCell.D4, BoardCell.E5));
        assertTrue(pos2.canMove(BoardCell.D4, BoardCell.C3));
        assertTrue(pos2.canMove(BoardCell.D4, BoardCell.E3));
        assertFalse(pos2.canMove(BoardCell.D4, BoardCell.D5));
        assertFalse(pos2.canMove(BoardCell.D4, BoardCell.D3));
        assertFalse(pos2.canMove(BoardCell.D4, BoardCell.C4));
        assertFalse(pos2.canMove(BoardCell.D4, BoardCell.E4));

        assertTrue(pos5.canMove(BoardCell.D4, BoardCell.D5));
        assertTrue(pos5.canMove(BoardCell.D4, BoardCell.D3));
        assertTrue(pos5.canMove(BoardCell.D4, BoardCell.C4));
        assertTrue(pos5.canMove(BoardCell.D4, BoardCell.E4));
        assertFalse(pos5.canMove(BoardCell.D4, BoardCell.C5));
        assertFalse(pos5.canMove(BoardCell.D4, BoardCell.E5));
        assertFalse(pos5.canMove(BoardCell.D4, BoardCell.C3));
        assertFalse(pos5.canMove(BoardCell.D4, BoardCell.E3));

        assertTrue(pos6.canMove(BoardCell.D4, BoardCell.D5));
        assertTrue(pos6.canMove(BoardCell.D4, BoardCell.D3));
        assertTrue(pos6.canMove(BoardCell.D4, BoardCell.C4));
        assertTrue(pos6.canMove(BoardCell.D4, BoardCell.E4));
        assertFalse(pos6.canMove(BoardCell.D4, BoardCell.C5));
        assertFalse(pos6.canMove(BoardCell.D4, BoardCell.E5));
        assertFalse(pos6.canMove(BoardCell.D4, BoardCell.C3));
        assertFalse(pos6.canMove(BoardCell.D4, BoardCell.E3));
    }

    @Test
    public void kingCanDoKingSideCastleIfFeatureAvailableAndPathFree(){
        Position pos1 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4").right();
        assertTrue(pos1.canMove(BoardCell.E1, BoardCell.G1));

        Position pos2 = Position.fromFEN("r1bqkbnr/pppp1ppp/2n5/4p3/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3").right();
        assertFalse(pos2.canMove(BoardCell.E1, BoardCell.G1));

        Position pos3 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/2b1p3/2B1P3/8/PPPP1PPP/RNBQK1NR w KQkq - 2 3").right();
        assertFalse(pos3.canMove(BoardCell.E1, BoardCell.G1));

        Position pos4 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPPN1PPP/R1BQKB1R b KQkq - 2 4").right();
        assertTrue(pos4.canMove(BoardCell.E8, BoardCell.G8));

        Position pos5 = Position.fromFEN("rnbqkb1r/pppp1ppp/5n2/4p3/4P3/3P1N2/PPP2PPP/RNBQKB1R b KQkq - 0 3").right();
        assertFalse(pos5.canMove(BoardCell.E8, BoardCell.G8));

        Position pos6 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/2b1p3/4P3/3P1N2/PPP2PPP/RNBQKB1R b KQkq - 0 3").right();
        assertFalse(pos6.canMove(BoardCell.E8, BoardCell.G8));

        Position pos7 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w Qkq - 4 4").right();
        assertFalse(pos7.canMove(BoardCell.E1, BoardCell.G1));

        Position pos8 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/3P1N2/PPPN1PPP/R1BQKB1R b KQq - 2 4").right();
        assertFalse(pos8.canMove(BoardCell.E8, BoardCell.G8));
    }

    @Test
    public void kingCanDoQueenSideCastleIfFeatureAvailableAndPathFree(){
        Position pos1 = Position.fromFEN("rnbq1rk1/ppp1bppp/3p1n2/4p1B1/4P3/2NP4/PPPQ1PPP/R3KBNR w KQ - 4 6").right();
        assertTrue(pos1.canMove(BoardCell.E1, BoardCell.C1));

        Position pos2 = Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p1B1/4P3/2NP4/PPP2PPP/R2QKBNR w KQ - 5 5").right();
        assertFalse(pos2.canMove(BoardCell.E1, BoardCell.C1));

        Position pos3 = Position.fromFEN("rnbqk2r/ppp1bppp/3p1n2/4p1B1/4P3/3P4/PPPQ1PPP/RN2KBNR w KQkq - 2 5").right();
        assertFalse(pos3.canMove(BoardCell.E1, BoardCell.C1));

        Position pos4 = Position.fromFEN("rnbq1rk1/ppppbppp/5n2/4p3/4P3/2NP4/PPPQ1PPP/R1B1KBNR w KQ - 5 5").right();
        assertFalse(pos4.canMove(BoardCell.E1, BoardCell.C1));

        Position pos5 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p3/2B1P1b1/P1N2N2/1PPP1PPP/R1BQ1RK1 b kq - 2 6").right();
        assertTrue(pos5.canMove(BoardCell.E8, BoardCell.C8));

        Position pos6 = Position.fromFEN("r2qkbnr/ppp2ppp/2np4/4p3/2B1P1b1/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5").right();
        assertFalse(pos6.canMove(BoardCell.E8, BoardCell.C8));

        Position pos7 = Position.fromFEN("rn2kbnr/pppq1ppp/3p4/4p3/2B1P1b1/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5").right();
        assertFalse(pos7.canMove(BoardCell.E8, BoardCell.C8));

        Position pos8 = Position.fromFEN("r1b1kbnr/pppq1ppp/2np4/4p3/2B1P3/P4N2/1PPP1PPP/RNBQ1RK1 b kq - 0 5").right();
        assertFalse(pos8.canMove(BoardCell.E8, BoardCell.C8));

        Position pos9 = Position.fromFEN("rnbq1rk1/ppp1bppp/3p1n2/4p1B1/4P3/2NP4/PPPQ1PPP/R3KBNR w K - 4 6").right();
        assertFalse(pos9.canMove(BoardCell.E1, BoardCell.C1));

        Position pos10 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p3/2B1P1b1/P1N2N2/1PPP1PPP/R1BQ1RK1 b k - 2 6").right();
        assertFalse(pos10.canMove(BoardCell.E8, BoardCell.C8));
    }

    @Test
    public void kingMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/5P2/8/PPPPP1PP/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.F2);
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/5P2/8/PPPPPKPP/RNBQ1BNR b kq - 1 2").right(),
                wrapPos2.right());

        Position pos3 = Position.fromFEN("rnbqkbnr/ppppp1pp/8/5p2/8/2N4P/PPPPPPP1/R1BQKBNR b KQkq - 0 2").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.F7);
        assertEquals(Position.fromFEN("rnbq1bnr/pppppkpp/8/5p2/8/2N4P/PPPPPPP1/R1BQKBNR w KQ - 1 3").right(),
                wrapPos4.right());
    }

    @Test
    public void kingMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pp1ppppp/8/2p5/8/4P3/PPPP1PPP/RNBQKBNR w KQkq c6 0 2").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.E2);
        assertEquals(Position.fromFEN("rnbqkbnr/pp1ppppp/8/2p5/8/4P3/PPPPKPPP/RNBQ1BNR b kq - 1 2").right(),
                wrapPos2.right());

        Position pos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/4p3/8/3P4/P7/1PP1PPPP/RNBQKBNR b KQkq d3 0 2").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.E7);
        assertEquals(Position.fromFEN("rnbq1bnr/ppppkppp/4p3/8/3P4/P7/1PP1PPPP/RNBQKBNR w KQ - 1 3").right(),
                wrapPos4.right());
    }

    @Test
    public void castleMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 5 4").right(),
                wrapPos2.right());

        Position pos3 = Position.fromFEN("rnbq1rk1/ppp2ppp/3p1n2/2b1p3/4P3/2NPB3/PPPQ1PPP/R3KBNR w KQ - 0 6").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E1, BoardCell.C1);
        assertEquals(Position.fromFEN("rnbq1rk1/ppp2ppp/3p1n2/2b1p3/4P3/2NPB3/PPPQ1PPP/2KR1BNR b - - 1 6").right(),
                wrapPos4.right());

        Position pos5 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 5 4").right();
        Either<Exception, Position> wrapPos6 = pos5.move(BoardCell.E8, BoardCell.G8);
        assertEquals(Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 w - - 6 5").right(),
                wrapPos6.right());

        Position pos7 = Position.fromFEN("r3kbnr/pppqpppp/2n5/1B1pP3/6b1/5N2/PPPP1PPP/RNBQ1RK1 b kq - 6 5").right();
        Either<Exception, Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(Position.fromFEN("2kr1bnr/pppqpppp/2n5/1B1pP3/6b1/5N2/PPPP1PPP/RNBQ1RK1 w - - 7 6").right(),
                wrapPos8.right());
    }

    @Test
    public void castleMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("r1bqkbnr/p1pp1ppp/1pn5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq e6 0 4").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(Position.fromFEN("r1bqkbnr/p1pp1ppp/1pn5/1B2p3/4P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 1 4").right(),
                wrapPos2.right());

        Position pos3 = Position.fromFEN("r2qkb1r/pppb1ppp/2np1n2/4p3/4P3/2NPB3/PPPQ1PPP/R3KBNR w KQkq e6 0 6").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E1, BoardCell.C1);
        assertEquals(Position.fromFEN("r2qkb1r/pppb1ppp/2np1n2/4p3/4P3/2NPB3/PPPQ1PPP/2KR1BNR b kq - 1 6").right(),
                wrapPos4.right());

        Position pos5 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/2NP1N2/PPP2PPP/R1BQKB1R b KQkq e3 0 4").right();
        Either<Exception, Position> wrapPos6 = pos5.move(BoardCell.E8, BoardCell.G8);
        assertEquals(Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p3/4P3/2NP1N2/PPP2PPP/R1BQKB1R w KQ - 1 5").right(),
                wrapPos6.right());

        Position pos7 = Position.fromFEN("r3kbnr/pppqpppp/2n5/3p4/3PP1b1/5N2/PPP1BPPP/RNBQ1RK1 b kq d3 0 5").right();
        Either<Exception, Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(Position.fromFEN("2kr1bnr/pppqpppp/2n5/3p4/3PP1b1/5N2/PPP1BPPP/RNBQ1RK1 w - - 1 6").right(),
                wrapPos8.right());
    }

    @Test
    public void kingAttackAllCellsJustAroundHim(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/2n1P3/3K4/8/8 w - - 0 1").right();
        Piece pos1WhiteKing = pos1.getPieceAt(BoardCell.D3);
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C4, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D4, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E4, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C3, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E3, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C2, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D2, pos1));
        assertTrue(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E2, pos1));
        assertFalse(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D5, pos1));
        assertFalse(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.F1, pos1));
        assertFalse(pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.G3, pos1));

        Position pos2 = Position.fromFEN("8/6R1/5k2/6p1/4K3/8/8/8 b - - 0 1").right();
        Piece pos2BlackKing = pos2.getPieceAt(BoardCell.F6);
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E7, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.F7, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G7, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E6, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G6, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E5, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.F5, pos2));
        assertTrue(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G5, pos2));
        assertFalse(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.H6, pos2));
        assertFalse(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.B6, pos2));
        assertFalse(pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.C3, pos2));
    }

    @Test
    public void weCannotCastleWhenTheKingIsInChess(){
        Position pos1 = Position.fromFEN("r1b1kbnr/ppp1pppp/2n5/4q3/2B5/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 5").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertTrue(wrapPos2.isLeft());

        Position pos3 = Position.fromFEN("rnbqk2r/pppp1ppp/3b1n2/8/8/P3QN2/1PP1PPPP/RNB1KB1R b KQkq - 4 5").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.G8);
        assertTrue(wrapPos4.isLeft());

        Position pos5 = Position.fromFEN("r1bqk1nr/pppp1pp1/n6p/8/1b1QPB2/N7/PPP2PPP/R3KBNR w KQkq - 2 6").right();
        Either<Exception, Position> wrapPos6 = pos5.move(BoardCell.E1, BoardCell.C1);
        assertTrue(wrapPos6.isLeft());

        Position pos7 = Position.fromFEN("r3kbnr/ppp1qppp/n2p4/1B2p3/4P1b1/2N2N2/PPPP1PPP/R1BQ1RK1 b kq - 7 6").right();
        Either<Exception, Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertTrue(wrapPos8.isLeft());
    }

    @Test
    public void weCannotCastleWhenTheKingGoesAcrossAttackedCells(){
        Position pos1 = Position.fromFEN("r2qkbnr/ppp2ppp/2np4/1B2p3/2b1P3/P1N2N2/1PPP1PPP/R1BQK2R w KQkq - 1 6").right();
        Either<Exception, Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertTrue(wrapPos2.isLeft());

        Position pos3 = Position.fromFEN("rnbqk2r/1ppp1ppp/pb3n2/1B2p3/1B2P3/3P1N2/PPP2PPP/RN1QK2R b KQkq - 1 6").right();
        Either<Exception, Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.G8);
        assertTrue(wrapPos4.isLeft());

        Position pos5 = Position.fromFEN("rn1qk2r/ppp2ppp/3p1n2/2b1p1B1/4P1b1/2NP4/PPPQ1PPP/R3KBNR w KQkq - 2 6").right();
        Either<Exception, Position> wrapPos6 = pos5.move(BoardCell.E1, BoardCell.C1);
        assertTrue(wrapPos6.isLeft());

        Position pos7 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p1B1/4P1b1/3P1N2/PPP1BPPP/RN1Q1RK1 b kq - 6 6").right();
        Either<Exception, Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertTrue(wrapPos8.isLeft());

        Position pos9 = Position.fromFEN("r1bqk1nr/ppp2ppp/2np4/2b1p3/2B1PP2/5N2/PPPP2PP/RNBQK2R w KQkq - 2 5").right();
        Either<Exception, Position> wrapPos10 = pos9.move(BoardCell.E1, BoardCell.G1);
        assertTrue(wrapPos10.isLeft());

        Position pos11 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p1B1/4P1b1/P1NP1N2/1PP2PPP/R2QKB1R b KQkq - 2 6").right();
        Either<Exception, Position> wrapPos12 = pos11.move(BoardCell.E8, BoardCell.C8);
        assertTrue(wrapPos12.isLeft());
    }

}
