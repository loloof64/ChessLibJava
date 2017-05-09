package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.functional.monad.Maybe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingMoveTest {

    private Position pos1, pos2, pos3, pos4, pos5, pos6;

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

    @Test
    public void kingMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/5P2/8/PPPPP1PP/RNBQKBNR w KQkq e6 0 2");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.F2);
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/5P2/8/PPPPPKPP/RNBQ1BNR b kq - 1 2"),
                wrapPos2.fromJust());

        Position pos3 = Position.fromFEN("rnbqkbnr/ppppp1pp/8/5p2/8/2N4P/PPPPPPP1/R1BQKBNR b KQkq - 0 2");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.F7);
        assertEquals(Position.fromFEN("rnbq1bnr/pppppkpp/8/5p2/8/2N4P/PPPPPPP1/R1BQKBNR w KQ - 1 3"),
                wrapPos4.fromJust());
    }

    @Test
    public void kingMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pp1ppppp/8/2p5/8/4P3/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.E2);
        assertEquals(Position.fromFEN("rnbqkbnr/pp1ppppp/8/2p5/8/4P3/PPPPKPPP/RNBQ1BNR b kq - 1 2"),
                wrapPos2.fromJust());

        Position pos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/4p3/8/3P4/P7/1PP1PPPP/RNBQKBNR b KQkq d3 0 2");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.E7);
        assertEquals(Position.fromFEN("rnbq1bnr/ppppkppp/4p3/8/3P4/P7/1PP1PPPP/RNBQKBNR w KQ - 1 3"),
                wrapPos4.fromJust());
    }

    @Test
    public void castleMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 4");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(Position.fromFEN("r1bqk1nr/pppp1ppp/2n5/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 5 4"),
                wrapPos2.fromJust());

        Position pos3 = Position.fromFEN("rnbq1rk1/ppp2ppp/3p1n2/2b1p3/4P3/2NPB3/PPPQ1PPP/R3KBNR w KQ - 0 6");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E1, BoardCell.C1);
        assertEquals(Position.fromFEN("rnbq1rk1/ppp2ppp/3p1n2/2b1p3/4P3/2NPB3/PPPQ1PPP/2KR1BNR b - - 1 6"),
                wrapPos4.fromJust());

        Position pos5 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 5 4");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.E8, BoardCell.G8);
        assertEquals(Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p3/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 w - - 6 5"),
                wrapPos6.fromJust());

        Position pos7 = Position.fromFEN("r3kbnr/pppqpppp/2n5/1B1pP3/6b1/5N2/PPPP1PPP/RNBQ1RK1 b kq - 6 5");
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(Position.fromFEN("2kr1bnr/pppqpppp/2n5/1B1pP3/6b1/5N2/PPPP1PPP/RNBQ1RK1 w - - 7 6"),
                wrapPos8.fromJust());
    }

    @Test
    public void castleMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("r1bqkbnr/p1pp1ppp/1pn5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R w KQkq e6 0 4");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(Position.fromFEN("r1bqkbnr/p1pp1ppp/1pn5/1B2p3/4P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 1 4"),
                wrapPos2.fromJust());

        Position pos3 = Position.fromFEN("r2qkb1r/pppb1ppp/2np1n2/4p3/4P3/2NPB3/PPPQ1PPP/R3KBNR w KQkq e6 0 6");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E1, BoardCell.C1);
        assertEquals(Position.fromFEN("r2qkb1r/pppb1ppp/2np1n2/4p3/4P3/2NPB3/PPPQ1PPP/2KR1BNR b kq - 1 6"),
                wrapPos4.fromJust());

        Position pos5 = Position.fromFEN("rnbqk2r/pppp1ppp/5n2/2b1p3/4P3/2NP1N2/PPP2PPP/R1BQKB1R b KQkq e3 0 4");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.E8, BoardCell.G8);
        assertEquals(Position.fromFEN("rnbq1rk1/pppp1ppp/5n2/2b1p3/4P3/2NP1N2/PPP2PPP/R1BQKB1R w KQ - 1 5"),
                wrapPos6.fromJust());

        Position pos7 = Position.fromFEN("r3kbnr/pppqpppp/2n5/3p4/3PP1b1/5N2/PPP1BPPP/RNBQ1RK1 b kq d3 0 5");
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(Position.fromFEN("2kr1bnr/pppqpppp/2n5/3p4/3PP1b1/5N2/PPP1BPPP/RNBQ1RK1 w - - 1 6"),
                wrapPos8.fromJust());
    }

    @Test
    public void kingAttackAllCellsJustAroundHim(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/2n1P3/3K4/8/8 w - - 0 1");
        Piece pos1WhiteKing = pos1.getPieceAt(BoardCell.D3);
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C4, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D4, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E4, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C3, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E3, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.C2, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D2, pos1));
        assertEquals(true, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.E2, pos1));
        assertEquals(false, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.D5, pos1));
        assertEquals(false, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.F1, pos1));
        assertEquals(false, pos1WhiteKing.isAttackingCell(BoardCell.D3, BoardCell.G3, pos1));

        Position pos2 = Position.fromFEN("8/6R1/5k2/6p1/4K3/8/8/8 b - - 0 1");
        Piece pos2BlackKing = pos2.getPieceAt(BoardCell.F6);
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E7, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.F7, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G7, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E6, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G6, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.E5, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.F5, pos2));
        assertEquals(true, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.G5, pos2));
        assertEquals(false, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.H6, pos2));
        assertEquals(false, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.B6, pos2));
        assertEquals(false, pos2BlackKing.isAttackingCell(BoardCell.F6, BoardCell.C3, pos2));
    }

    @Test
    public void weCannotCastleWhenTheKingIsInChess(){
        Position pos1 = Position.fromFEN("r1b1kbnr/ppp1pppp/2n5/4q3/2B5/5N2/PPPP1PPP/RNBQK2R w KQkq - 4 5");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(true, wrapPos2.isNothing());

        Position pos3 = Position.fromFEN("rnbqk2r/pppp1ppp/3b1n2/8/8/P3QN2/1PP1PPPP/RNB1KB1R b KQkq - 4 5");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.G8);
        assertEquals(true, wrapPos4.isNothing());

        Position pos5 = Position.fromFEN("r1bqk1nr/pppp1pp1/n6p/8/1b1QPB2/N7/PPP2PPP/R3KBNR w KQkq - 2 6");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.E1, BoardCell.C1);
        assertEquals(true, wrapPos6.isNothing());

        Position pos7 = Position.fromFEN("r3kbnr/ppp1qppp/n2p4/1B2p3/4P1b1/2N2N2/PPPP1PPP/R1BQ1RK1 b kq - 7 6");
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(true, wrapPos8.isNothing());
    }

    @Test
    public void weCannotCastleWhenTheKingGoesAcrossAttackedCells(){
        Position pos1 = Position.fromFEN("r2qkbnr/ppp2ppp/2np4/1B2p3/2b1P3/P1N2N2/1PPP1PPP/R1BQK2R w KQkq - 1 6");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E1, BoardCell.G1);
        assertEquals(true, wrapPos2.isNothing());

        Position pos3 = Position.fromFEN("rnbqk2r/1ppp1ppp/pb3n2/1B2p3/1B2P3/3P1N2/PPP2PPP/RN1QK2R b KQkq - 1 6");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E8, BoardCell.G8);
        assertEquals(true, wrapPos4.isNothing());

        Position pos5 = Position.fromFEN("rn1qk2r/ppp2ppp/3p1n2/2b1p1B1/4P1b1/2NP4/PPPQ1PPP/R3KBNR w KQkq - 2 6");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.E1, BoardCell.C1);
        assertEquals(true, wrapPos6.isNothing());

        Position pos7 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p1B1/4P1b1/3P1N2/PPP1BPPP/RN1Q1RK1 b kq - 6 6");
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.C8);
        assertEquals(true, wrapPos8.isNothing());

        Position pos9 = Position.fromFEN("r1bqk1nr/ppp2ppp/2np4/2b1p3/2B1PP2/5N2/PPPP2PP/RNBQK2R w KQkq - 2 5");
        Maybe<Position> wrapPos10 = pos9.move(BoardCell.E1, BoardCell.G1);
        assertEquals(true, wrapPos10.isNothing());

        Position pos11 = Position.fromFEN("r3kbnr/pppq1ppp/2np4/4p1B1/4P1b1/P1NP1N2/1PP2PPP/R2QKB1R b KQkq - 2 6");
        Maybe<Position> wrapPos12 = pos11.move(BoardCell.E8, BoardCell.C8);
        assertEquals(true, wrapPos12.isNothing());
    }

}
