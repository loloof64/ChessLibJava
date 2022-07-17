package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Queen;
import com.loloof64.chess_lib_java.rules.pieces.Rook;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialSanGenerationTest {
    @Test
    public void pawnGivingCheckSimpleCases() {
        Position pos1 = Position.fromFEN("8/8/8/4k3/8/3PK3/8/8 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.D3, BoardCell.D4)).right();
        assertEquals("d4+", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("8/8/4kp2/8/4K3/8/8/8 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.F6, BoardCell.F5)).right();
        assertEquals("f5+", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("8/8/4kp2/8/4K3/8/8/8 b - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.F7, BoardCell.F5)).right();
        assertEquals("f5+", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("8/8/8/4k3/K7/8/3P4/8 w - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.D2, BoardCell.D4)).right();
        assertEquals("d4+", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("8/8/5k2/4n3/3P4/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.D4, BoardCell.E5)).right();
        assertEquals("dxe5+", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("8/4k3/8/8/4p3/5R2/4K3/8 b - - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.E4, BoardCell.F3)).right();
        assertEquals("exf3+", moveResult6.moveSan);
    }

    @Test
    public void pawnGivingCheckComplexCases() {
        Position pos1 = Position.fromFEN("8/5k2/8/3Pp3/8/8/8/4K3 w - e6 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.D5, BoardCell.E6)).right();
        assertEquals("dxe5+", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("8/6k1/8/8/2Pp4/8/1K6/8 b - c3 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.D4, BoardCell.C3)).right();
        assertEquals("dxc3+", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("4k3/2P5/8/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.C7, BoardCell.C8), new Rook(true)).right();
        assertEquals("c8=R+", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("4k3/8/8/8/8/8/1p6/4K3 b - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.B2, BoardCell.B1), new Queen(false)).right();
        assertEquals("b1=Q+", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("4k3/8/8/8/8/8/2p5/3QK3 b - - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.C2, BoardCell.D1), new Queen(false)).right();
        assertEquals("cxd1=Q+", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("4k2r/6P1/8/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.G7, BoardCell.H8), new Rook(true)).right();
        assertEquals("gxh8=R+", moveResult6.moveSan);
    }

    @Test
    public void pawnGivingCheckmateSimpleCases() {
        Position pos1 = Position.fromFEN("3rkn2/3bb3/4BP2/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.F6, BoardCell.F7)).right();
        assertEquals("f7#", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("4k3/8/8/8/8/4bp2/3BB3/3RKN2 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.F3, BoardCell.F2)).right();
        assertEquals("f2#", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("4k3/8/8/8/8/4b1p1/3BBN2/3RKN2 b - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.G3, BoardCell.F2)).right();
        assertEquals("gxf2#", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("3rkn2/3bbn2/4B1P1/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.G6, BoardCell.F7)).right();
        assertEquals("gxf7#", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("8/8/5Q1r/7k/8/7K/6P1/8 w - - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.G2, BoardCell.G4)).right();
        assertEquals("g4#", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("8/6p1/7k/5b2/7K/8/5B1b/8 b - - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.G7, BoardCell.G5)).right();
        assertEquals("g5#", moveResult5.moveSan);
    }

    @Test
    public void pawnGivingCheckmateComplexCases() {
        Position pos1 = Position.fromFEN("3b1n2/3bk3/3rp3/4QpPB/8/8/8/4K3 w - f6 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.G5, BoardCell.F6)).right();
        assertEquals("gxf6#", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("8/4k3/8/8/4qPpb/3RN3/3BK3/3B1N2 b - f3 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.G4, BoardCell.F3)).right();
        assertEquals("gxf3#", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("4k3/R6P/8/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.H7, BoardCell.H8), new Rook(true)).right();
        assertEquals("h8=R#", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("4k3/8/8/8/8/8/p6r/4K3 b - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.A2, BoardCell.A1), new Queen(false)).right();
        assertEquals("a1=Q#", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("4k3/8/8/8/8/8/p6r/1N2K3 b - - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.A2, BoardCell.B1), new Rook(false)).right();
        assertEquals("axb1=R#", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("1b2k3/2P4R/8/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.C7, BoardCell.B8), new Queen(true)).right();
        assertEquals("cxb8=Q#", moveResult6.moveSan);
    }

    @Test
    public void kingMovesGivingCheck() {
        Position pos1 = Position.fromFEN("8/8/7k/8/8/4K3/3B4/8 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.E3, BoardCell.E4)).right();
        assertEquals("Ke4+", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("8/8/8/2K5/8/4k3/5b2/8 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.E3, BoardCell.D2)).right();
        assertEquals("Kd2+", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("5k2/8/8/8/8/8/8/4K2R w K - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.E1, BoardCell.G1)).right();
        assertEquals("O-O+", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("4k2r/8/8/8/8/8/8/5K2 b k - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.E8, BoardCell.G8)).right();
        assertEquals("O-O+", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("3k4/8/8/8/8/8/8/R3K3 w Q - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.E1, BoardCell.C1)).right();
        assertEquals("O-O-O+", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("r3k3/8/8/8/8/8/8/3K4 b q - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.E8, BoardCell.C8)).right();
        assertEquals("O-O-O+", moveResult6.moveSan);
    }

    @Test
    public void kingMovesGivingCheckmate() {
        Position pos1 = Position.fromFEN("7k/8/8/6RK/7R/8/8/8 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.H5, BoardCell.G4)).right();
        assertEquals("Kg4#", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("8/8/8/r7/kr6/8/8/K7 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.A4, BoardCell.B5)).right();
        assertEquals("Kb5#", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("8/8/8/4bnp1/4pkp1/4p1p1/8/4K2R w K - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.E1, BoardCell.G1)).right();
        assertEquals("O-O#", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("4k2r/8/8/4P1P1/4PKP1/4BNP1/8/8 b k - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.E8, BoardCell.G8)).right();
        assertEquals("O-O#", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("r3k3/8/8/2P1P3/2PKP3/2BNP3/8/8 b q - 1 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.E8, BoardCell.C8)).right();
        assertEquals("O-O-O#", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("8/8/2bnp3/2pkp3/2p1p3/8/8/R3K3 w Q - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.E1, BoardCell.C1)).right();
        assertEquals("O-O-O#", moveResult6.moveSan);
    }

    @Test
    public void knightGivingCheck() {
        Position pos1 = Position.fromFEN("rnb1kbnr/pppp1pp1/7p/1N2p1q1/4P3/8/PPPP1PPP/R1BQKBNR w KQkq - 2 4").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.B5, BoardCell.C7)).right();
        assertEquals("Nxc7+", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("r1bqkbnr/pppppppp/8/8/1n1PP3/5Q2/PPP2PPP/RNB1KBNR b KQkq - 2 3").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.B4, BoardCell.C2)).right();
        assertEquals("Nxc2+", moveResult2.moveSan);
    }

    @Test
    public void knightGivingCheckmate() {
        Position pos1 = Position.fromFEN("6rk/5ppp/7N/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.H6, BoardCell.F7)).right();
        assertEquals("Nxf7#", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("4k3/8/8/8/8/7n/6PP/6RK b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.H3, BoardCell.F2)).right();
        assertEquals("Nf2#", moveResult2.moveSan);
    }

    @Test
    public void longRangePieceGivingCheck() {
        Position pos1 = Position.fromFEN("4k3/p7/8/8/4B3/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.E4, BoardCell.G6)).right();
        assertEquals("Bg6+", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("4k3/8/8/4b3/8/8/P7/4K3 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.E5, BoardCell.C3)).right();
        assertEquals("Bc3+", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("4k3/8/8/8/8/8/R7/4K3 w - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.A2, BoardCell.A8)).right();
        assertEquals("Ra8+", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("4k3/r7/8/8/8/8/8/4K3 b - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.A7, BoardCell.A1)).right();
        assertEquals("Ra1+", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("4k3/8/Q7/8/8/8/8/4K3 w - - 0 1").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.A6, BoardCell.C8)).right();
        assertEquals("Qc8+", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("4k3/8/8/8/8/7q/8/4K3 b - - 0 1").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.H3, BoardCell.F1)).right();
        assertEquals("Qf1+", moveResult6.moveSan);
    }

    @Test
    public void longRangePieceGivingCheckmate() {
        Position pos1 = Position.fromFEN("6bk/5p1p/5n2/8/8/8/8/B5RK w - - 0 1").right();
        MoveResult moveResult1 = pos1.move(new Move(BoardCell.A1, BoardCell.F6)).right();
        assertEquals("Bxf6#", moveResult1.moveSan);

        Position pos2 = Position.fromFEN("1r5k/8/5b2/8/8/2N5/P1P5/KB6 b - - 0 1").right();
        MoveResult moveResult2 = pos2.move(new Move(BoardCell.F6, BoardCell.C3)).right();
        assertEquals("Bxc3#", moveResult2.moveSan);

        Position pos3 = Position.fromFEN("5rk1/5p1p/5B2/8/8/8/1PPP4/2KR4 w - - 0 1").right();
        MoveResult moveResult3 = pos3.move(new Move(BoardCell.D1, BoardCell.G1)).right();
        assertEquals("Rg1#", moveResult3.moveSan);

        Position pos4 = Position.fromFEN("2kr4/1ppp1p1p/8/8/8/5b2/5P1P/5RK1 b - - 0 1").right();
        MoveResult moveResult4 = pos4.move(new Move(BoardCell.D8, BoardCell.G8)).right();
        assertEquals("Rg8#", moveResult4.moveSan);

        Position pos5 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/5PP1/8/PPPPP2P/RNBQKBNR b KQkq g3 0 2").right();
        MoveResult moveResult5 = pos5.move(new Move(BoardCell.D8, BoardCell.H4)).right();
        assertEquals("Qh4#", moveResult5.moveSan);

        Position pos6 = Position.fromFEN("rnbqkbnr/1ppp1pp1/p6p/4p3/2B1P3/5Q2/PPPP1PPP/RNB1K1NR w KQkq - 0 4").right();
        MoveResult moveResult6 = pos6.move(new Move(BoardCell.F3, BoardCell.F7)).right();
        assertEquals("Qxf7#", moveResult6.moveSan);
    }
}
