package com.loloof64.chess_lib_java.state;

import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.MoveResult;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Bishop;
import com.loloof64.chess_lib_java.rules.pieces.Knight;
import com.loloof64.chess_lib_java.rules.pieces.Queen;
import com.loloof64.chess_lib_java.rules.pieces.Rook;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;

public class LegalMovesTest {

    boolean areMoveResultsArrayEquivalents(MoveResult[] arr1, MoveResult[] arr2) {
        Arrays.sort(arr1, Comparator.comparing(MoveResult::getMoveSan));
        Arrays.sort(arr2, Comparator.comparing(MoveResult::getMoveSan));

        return Arrays.equals(arr1, arr2);
    }

    @Test
    public void fromStartPosition() {
        Position pos = Position.INITIAL;
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[]{
                pos.move(new Move(BoardCell.A2, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B3)).right(),
                pos.move(new Move(BoardCell.C2, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.D2, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.E2, BoardCell.E3)).right(),
                pos.move(new Move(BoardCell.F2, BoardCell.F3)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G3)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H3)).right(),

                pos.move(new Move(BoardCell.A2, BoardCell.A4)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.C2, BoardCell.C4)).right(),
                pos.move(new Move(BoardCell.D2, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.E2, BoardCell.E4)).right(),
                pos.move(new Move(BoardCell.F2, BoardCell.F4)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H4)).right(),

                pos.move(new Move(BoardCell.B1, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.B1, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.G1, BoardCell.F3)).right(),
                pos.move(new Move(BoardCell.G1, BoardCell.H3)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void fromBlackVeryFirstMove() {
        Position pos = Position.fromFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[]{
                pos.move(new Move(BoardCell.A7, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B6)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C6)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.E6)).right(),
                pos.move(new Move(BoardCell.F7, BoardCell.F6)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G6)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H6)).right(),

                pos.move(new Move(BoardCell.A7, BoardCell.A5)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C5)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.E5)).right(),
                pos.move(new Move(BoardCell.F7, BoardCell.F5)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G5)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H5)).right(),

                pos.move(new Move(BoardCell.B8, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.B8, BoardCell.C6)).right(),
                pos.move(new Move(BoardCell.G8, BoardCell.F6)).right(),
                pos.move(new Move(BoardCell.G8, BoardCell.H6)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withAPinnedPiece_white() {
        Position pos = Position.fromFEN("4k3/3n4/8/1B6/8/8/8/4K3 b - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[]{
                pos.move(new Move(BoardCell.E8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F7)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withAPinnedPiece_black() {
        Position pos = Position.fromFEN("4k3/8/8/8/1b6/8/3N4/4K3 w - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[]{
                pos.move(new Move(BoardCell.E1, BoardCell.D1)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.F2)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withEnPassantSquare_white() {
        Position pos = Position.fromFEN("rnbqkbnr/1pppp1pp/p7/4Pp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[]{
                pos.move(new Move(BoardCell.E5, BoardCell.F6)).right(),
                pos.move(new Move(BoardCell.E5, BoardCell.E6)).right(),
                pos.move(new Move(BoardCell.A2, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.A2, BoardCell.A4)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B3)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.C2, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.C2, BoardCell.C4)).right(),
                pos.move(new Move(BoardCell.D2, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.D2, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.F2, BoardCell.F3)).right(),
                pos.move(new Move(BoardCell.F2, BoardCell.F4)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G3)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H3)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H4)).right(),
                pos.move(new Move(BoardCell.B1, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.B1, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.G1, BoardCell.F3)).right(),
                pos.move(new Move(BoardCell.G1, BoardCell.H3)).right(),
                pos.move(new Move(BoardCell.G1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.F1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.F1, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.F1, BoardCell.C4)).right(),
                pos.move(new Move(BoardCell.F1, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.F1, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.F3)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.H5)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withEnPassantSquare_black() {
        Position pos = Position.fromFEN("rnbqkbnr/ppp1pppp/8/8/3pP3/P1N5/1PPP1PPP/R1BQKBNR b KQkq e3 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.D4, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.D4, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.D4, BoardCell.E3)).right(),
                pos.move(new Move(BoardCell.A7, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.A7, BoardCell.A5)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B6)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C6)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C5)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.E6)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.E5)).right(),
                pos.move(new Move(BoardCell.F7, BoardCell.F6)).right(),
                pos.move(new Move(BoardCell.F7, BoardCell.F5)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G6)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G5)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H6)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H5)).right(),
                pos.move(new Move(BoardCell.B8, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.B8, BoardCell.C6)).right(),
                pos.move(new Move(BoardCell.B8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.G8, BoardCell.F6)).right(),
                pos.move(new Move(BoardCell.G8, BoardCell.H6)).right(),
                pos.move(new Move(BoardCell.C8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.C8, BoardCell.E6)).right(),
                pos.move(new Move(BoardCell.C8, BoardCell.F5)).right(),
                pos.move(new Move(BoardCell.C8, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.C8, BoardCell.H3)).right(),
                pos.move(new Move(BoardCell.D8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.D8, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.D8, BoardCell.D5)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withCastle_white() {
        Position pos = Position.fromFEN("r1bqkb1r/pppp1ppp/2n2n2/4p3/2B1P3/5N2/PPPP1PPP/RNBQK2R w KQkq - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
          pos.move(new Move(BoardCell.A2, BoardCell.A3)).right(),
          pos.move(new Move(BoardCell.A2, BoardCell.A4)).right(),
          pos.move(new Move(BoardCell.B2, BoardCell.B3)).right(),
          pos.move(new Move(BoardCell.B2, BoardCell.B4)).right(),
          pos.move(new Move(BoardCell.C2, BoardCell.C3)).right(),
          pos.move(new Move(BoardCell.D2, BoardCell.D3)).right(),
          pos.move(new Move(BoardCell.D2, BoardCell.D4)).right(),
          pos.move(new Move(BoardCell.G2, BoardCell.G3)).right(),
          pos.move(new Move(BoardCell.G2, BoardCell.G4)).right(),
          pos.move(new Move(BoardCell.H2, BoardCell.H3)).right(),
          pos.move(new Move(BoardCell.H2, BoardCell.H4)).right(),
          pos.move(new Move(BoardCell.B1, BoardCell.A3)).right(),
          pos.move(new Move(BoardCell.B1, BoardCell.C3)).right(),
          pos.move(new Move(BoardCell.F3, BoardCell.G1)).right(),
          pos.move(new Move(BoardCell.F3, BoardCell.H4)).right(),
          pos.move(new Move(BoardCell.F3, BoardCell.G5)).right(),
          pos.move(new Move(BoardCell.F3, BoardCell.E5)).right(),
          pos.move(new Move(BoardCell.F3, BoardCell.D4)).right(),
          pos.move(new Move(BoardCell.H1, BoardCell.G1)).right(),
          pos.move(new Move(BoardCell.H1, BoardCell.F1)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.E2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.G1)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withCastle_black() {
        Position pos = Position.fromFEN("r1bqk2r/pppp1ppp/2n2n2/2b1p3/2B1P3/3P1N2/PPP2PPP/RNBQ1RK1 b kq - 2 5").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.A8, BoardCell.B8)).right(),
                pos.move(new Move(BoardCell.D8, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.G8)).right(),
                pos.move(new Move(BoardCell.H8, BoardCell.F8)).right(),
                pos.move(new Move(BoardCell.H8, BoardCell.G8)).right(),
                pos.move(new Move(BoardCell.A7, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.A7, BoardCell.A5)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B6)).right(),
                pos.move(new Move(BoardCell.B7, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G6)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G5)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H6)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H5)).right(),
                pos.move(new Move(BoardCell.C6, BoardCell.B8)).right(),
                pos.move(new Move(BoardCell.C6, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.C6, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.C6, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.C6, BoardCell.A5)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.G8)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.H5)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.E4)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.B6)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.F8)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.E3)).right(),
                pos.move(new Move(BoardCell.C5, BoardCell.F2)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withCastlePreventedByEnemyPiece_white() {
        Position pos = Position.fromFEN("r1bq1rk1/1ppp1ppp/p1n2n2/2b5/2B1PP2/5N2/PPP3PP/RNBQK2R w KQ - 0 8").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.B1, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.B1, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.B1, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.C1, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.C1, BoardCell.E3)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.D1, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
                pos.move(new Move(BoardCell.H1, BoardCell.G1)).right(),
                pos.move(new Move(BoardCell.A2, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.A2, BoardCell.A4)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B3)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.C2, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G3)).right(),
                pos.move(new Move(BoardCell.G2, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H3)).right(),
                pos.move(new Move(BoardCell.H2, BoardCell.H4)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.G1)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.D4)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.E5)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.G5)).right(),
                pos.move(new Move(BoardCell.F3, BoardCell.H4)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.A6)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.B3)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.E6)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.F7)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.D3)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.C4, BoardCell.F1)).right(),
                pos.move(new Move(BoardCell.E4, BoardCell.E5)).right(),
                pos.move(new Move(BoardCell.F4, BoardCell.F5)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withCastlePreventedByEnemyPiece_black() {
        Position pos = Position.fromFEN("r3kb1r/p1ppqppp/Qp2pn2/8/4P3/5N2/PPPP1PPP/RNB2RK1 b kq - 3 7").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.A8, BoardCell.B8)).right(),
                pos.move(new Move(BoardCell.A8, BoardCell.C8)).right(),
                pos.move(new Move(BoardCell.A8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.H8, BoardCell.G8)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C6)).right(),
                pos.move(new Move(BoardCell.C7, BoardCell.C5)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.D7, BoardCell.D5)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.D6)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.C5)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.B4)).right(),
                pos.move(new Move(BoardCell.E7, BoardCell.A3)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G6)).right(),
                pos.move(new Move(BoardCell.G7, BoardCell.G5)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H6)).right(),
                pos.move(new Move(BoardCell.H7, BoardCell.H5)).right(),
                pos.move(new Move(BoardCell.B6, BoardCell.B5)).right(),
                pos.move(new Move(BoardCell.E6, BoardCell.E5)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.G8)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.H5)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.G4)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.E4)).right(),
                pos.move(new Move(BoardCell.F6, BoardCell.D5)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withPawnCapture_white() {
        Position pos = Position.fromFEN("8/8/2n2k2/3P4/8/8/8/4K3 w - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
          pos.move(new Move(BoardCell.D5, BoardCell.C6)).right(),
          pos.move(new Move(BoardCell.D5, BoardCell.D6)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.D1)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.D2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.E2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.F2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withPawnCapture_black() {
        Position pos = Position.fromFEN("4k3/8/8/8/1p6/2N5/8/4K3 b - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.B4, BoardCell.B3)).right(),
                pos.move(new Move(BoardCell.B4, BoardCell.C3)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F8)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withPromotion_white() {
        Position pos = Position.fromFEN("2n1k3/1P6/8/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
          pos.move(new Move(BoardCell.B7, BoardCell.B8), new Queen(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.B8), new Rook(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.B8), new Bishop(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.B8), new Knight(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.C8), new Queen(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.C8), new Rook(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.C8), new Bishop(true)).right(),
          pos.move(new Move(BoardCell.B7, BoardCell.C8), new Knight(true)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.D1)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.D2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.E2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.F2)).right(),
          pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void withPromotion_black() {
        Position pos = Position.fromFEN("4k3/8/8/8/8/8/1p6/2N1K3 b - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.B2, BoardCell.B1), new Queen(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B1), new Rook(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B1), new Bishop(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.B1), new Knight(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.C1), new Queen(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.C1), new Rook(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.C1), new Bishop(false)).right(),
                pos.move(new Move(BoardCell.B2, BoardCell.C1), new Knight(false)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F8)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void whiteToMoveAndKingInChess() {
        Position pos = Position.fromFEN("4k3/8/8/8/8/4r3/8/2N1K3 w - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.C1, BoardCell.E2)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.D1)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.D2)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.F1)).right(),
                pos.move(new Move(BoardCell.E1, BoardCell.F2)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

    @Test
    public void blackToMoveAndKingInChess() {
        Position pos = Position.fromFEN("2n1k3/8/4R3/8/8/8/8/4K3 b - - 0 1").right();
        MoveResult[] legalMoves = pos.legalMoves();
        MoveResult[] expectedMoves = new MoveResult[] {
                pos.move(new Move(BoardCell.C8, BoardCell.E7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.D7)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F8)).right(),
                pos.move(new Move(BoardCell.E8, BoardCell.F7)).right(),
        };
        assertTrue(areMoveResultsArrayEquivalents(expectedMoves, legalMoves));
    }

}
