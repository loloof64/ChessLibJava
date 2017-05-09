package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.*;
import com.loloof64.functional.monad.Maybe;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PawnMoveTest {

    private Position pos1, pos2, pos3, pos4;

    @Before
    public void beforeEach(){
        pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1").fromJust();
        pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1").fromJust();
        pos3 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K w - - 0 1").fromJust();
        pos4 = Position.fromFEN("kb2n3/p6p/1PP3p1/P4p2/8/4B2P/6P1/7K b - - 0 1").fromJust();
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

        Position pos5 = Position.fromFEN("r6k/pp2N1pp/1bp5/6N1/1P6/P6P/5PP1/6K1 b - - 0 1").fromJust();
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
        Position pos5 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 w - - 0 1").fromJust();
        assertEquals(true, pos5.canMove(BoardCell.G3, BoardCell.F4));
        assertEquals(true, pos5.canMove(BoardCell.G3, BoardCell.H4));
        assertEquals(false, pos5.canMove(BoardCell.G3, BoardCell.E4));
        assertEquals(false, pos5.canMove(BoardCell.C4, BoardCell.D5));

        Position pos6 = Position.fromFEN("4k3/8/8/2p5/RPPNnbpp/6P1/8/4K3 b - - 0 1").fromJust();
        assertEquals(true, pos6.canMove(BoardCell.C5, BoardCell.B4));
        assertEquals(true, pos6.canMove(BoardCell.C5, BoardCell.D4));
        assertEquals(false, pos6.canMove(BoardCell.C5, BoardCell.E4));
        assertEquals(false, pos6.canMove(BoardCell.G4, BoardCell.F3));
    }

    @Test
    public void pawnCanCaptureEnPassantIfFeatureAvailable(){
        Position pos5 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3").fromJust();
        assertEquals(true, pos5.canMove(BoardCell.E5, BoardCell.D6));
        assertEquals(false, pos5.canMove(BoardCell.E5, BoardCell.F6));

        Position pos6 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq e3 0 3").fromJust();
        assertEquals(true, pos6.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos6.canMove(BoardCell.D4, BoardCell.C3));

        Position pos7 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq - 0 3").fromJust();
        assertEquals(false, pos7.canMove(BoardCell.E5, BoardCell.D6));
        assertEquals(false, pos7.canMove(BoardCell.E5, BoardCell.F6));

        Position pos8 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq - 0 3").fromJust();
        assertEquals(false, pos8.canMove(BoardCell.D4, BoardCell.E3));
        assertEquals(false, pos8.canMove(BoardCell.D4, BoardCell.C3));

        Position pos9 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/3Pp3/8/3P4/PPP2PPP/RNBQKBNR w KQkq e6 0 1").fromJust();
        assertEquals(true, pos9.canMove(BoardCell.D5, BoardCell.E6));
        assertEquals(false, pos9.canMove(BoardCell.D3, BoardCell.E4));

        Position pos10 = Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/3pP3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").fromJust();
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

    @Test
    public void legalStandardPawnMoveGeneratesCorrectPosition(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1").fromJust();
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E2, BoardCell.E4);
        Position pos2 = wrapPos2.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").fromJust(), pos2);

        Maybe<Position> wrapPos3 = pos2.move(BoardCell.D7, BoardCell.D6);
        Position pos3 = wrapPos3.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/3p4/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2").fromJust(), pos3);

        Maybe<Position> wrapPos4 = pos3.move(BoardCell.D2, BoardCell.D4);
        Position pos4 = wrapPos4.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/3p4/8/3PP3/8/PPP2PPP/RNBQKBNR b KQkq d3 0 2").fromJust(), pos4);

        Maybe<Position> wrapPos5 = pos4.move(BoardCell.E7, BoardCell.E5);
        Position pos5 = wrapPos5.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/4p3/3PP3/8/PPP2PPP/RNBQKBNR w KQkq e6 0 3").fromJust(), pos5);

        Maybe<Position> wrapPos6 = pos5.move(BoardCell.D4, BoardCell.E5);
        Position pos6 = wrapPos6.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/4P3/4P3/8/PPP2PPP/RNBQKBNR b KQkq - 0 3").fromJust(), pos6);

        Position pos7 = Position.fromFEN("8/2P4k/8/8/8/8/8/4K3 w - - 0 1").fromJust();
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.C7, BoardCell.C8, Queen.class);
        Position pos8 = wrapPos8.fromJust();
        assertEquals(Position.fromFEN("2Q5/7k/8/8/8/8/8/4K3 b - - 0 1").fromJust(), pos8);
        Maybe<Position> wrapPos9 = pos7.move(BoardCell.C7, BoardCell.C8, Bishop.class);
        Position pos9 = wrapPos9.fromJust();
        assertEquals(Position.fromFEN("2B5/7k/8/8/8/8/8/4K3 b - - 0 1").fromJust(), pos9);

        Position pos10 = Position.fromFEN("4k3/8/8/8/8/8/3p3K/2N5 b - - 0 1").fromJust();
        Maybe<Position> wrapPos11 = pos10.move(BoardCell.D2, BoardCell.C1, Rook.class);
        Position pos11 = wrapPos11.fromJust();
        assertEquals(Position.fromFEN("4k3/8/8/8/8/8/7K/2r5 w - - 0 2").fromJust(), pos11);
        Maybe<Position> wrapPos12 = pos10.move(BoardCell.D2, BoardCell.C1, Knight.class);
        Position pos12 = wrapPos12.fromJust();
        assertEquals(Position.fromFEN("4k3/8/8/8/8/8/7K/2n5 w - - 0 2").fromJust(), pos12);
    }

    @Test
    public void enPassantPawnMoveGeneratesCorrectPosition(){
        Position pos1 = Position.fromFEN("rnbqkb1r/ppp1pppp/7n/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3").fromJust();
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E5, BoardCell.D6);
        Position pos2 = wrapPos2.fromJust();
        assertEquals(Position.fromFEN("rnbqkb1r/ppp1pppp/3P3n/8/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 3").fromJust(), pos2);

        Position pos3 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/3pP3/8/PPPP1PPP/RNBQKB1R b KQkq e3 0 3").fromJust();
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.D4, BoardCell.E3);
        Position pos4 = wrapPos4.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp1pppp/8/6N1/8/4p3/PPPP1PPP/RNBQKB1R w KQkq - 0 4").fromJust(), pos4);

        Position pos5 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/3Pp3/8/3P4/PPP2PPP/RNBQKBNR w KQkq e6 0 1").fromJust();
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.D5, BoardCell.E6);
        Position pos6 = wrapPos6.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/4P3/8/8/3P4/PPP2PPP/RNBQKBNR b KQkq - 0 1").fromJust(), pos6);

        Position pos7 = Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/3pP3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1").fromJust();
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.D4, BoardCell.E3);
        Position pos8 = wrapPos8.fromJust();
        assertEquals(Position.fromFEN("rnbqkbnr/ppp2ppp/3p4/8/8/4p3/PPPP1PPP/RNBQKBNR w KQkq - 0 2").fromJust(), pos8);
    }

    @Test
    public void pawnAttackCellsDirectlyInFrontOfHimAndInDiagonal(){
        Position pos1 = Position.fromFEN("4kr1R/6P1/8/2p5/2P5/8/1p6/N1n1K3 w - - 0 1").fromJust();

        Pawn pos1Pawn1 = (Pawn) pos1.getPieceAt(BoardCell.C4);
        assertEquals(true, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B5, pos1));
        assertEquals(true, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.D5, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B4, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.C5, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.A6, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.F7, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.B3, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.C3, pos1));
        assertEquals(false, pos1Pawn1.isAttackingCell(BoardCell.C4, BoardCell.D3, pos1));
        Pawn pos1Pawn2 = (Pawn) pos1.getPieceAt(BoardCell.G7);
        assertEquals(true, pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.F8, pos1));
        assertEquals(true, pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.H8, pos1));
        assertEquals(false, pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.G8, pos1));
        assertEquals(false, pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.H7, pos1));
        assertEquals(false, pos1Pawn2.isAttackingCell(BoardCell.G7, BoardCell.F6, pos1));

        Position pos2 = Position.fromFEN("4kr1R/6P1/8/2p5/2P5/8/1p6/N1n1K3 b - - 0 1").fromJust();
        Pawn pos2Pawn1 = (Pawn) pos2.getPieceAt(BoardCell.C5);
        assertEquals(true, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.B4, pos2));
        assertEquals(true, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D4, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.C4, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D5, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.F2, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.A3, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.B6, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.C6, pos2));
        assertEquals(false, pos2Pawn1.isAttackingCell(BoardCell.C5, BoardCell.D6, pos2));
        Pawn pos2Pawn2 = (Pawn) pos2.getPieceAt(BoardCell.B2);
        assertEquals(true, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A1, pos2));
        assertEquals(true, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C1, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.B1, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.E5, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A2, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C2, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.A3, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.B3, pos2));
        assertEquals(false, pos2Pawn2.isAttackingCell(BoardCell.B2, BoardCell.C3, pos2));
    }

}
