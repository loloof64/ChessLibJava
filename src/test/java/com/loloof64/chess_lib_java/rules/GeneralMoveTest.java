package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GeneralMoveTest {

    @Test
    public void cannotDefineAMoveWithOriginSquareEqualsToTarget(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/3R4/8/8/4K3 w - - 0 1");
        assertEquals(false, pos1.canMove(BoardCell.D4, BoardCell.D4));

        Position pos2 = Position.fromFEN("4k3/8/8/8/3r4/8/8/4K3 b - - 0 1");
        assertEquals(false, pos2.canMove(BoardCell.D4, BoardCell.D4));
    }

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

    @Test
    public void movingFromAnEmptyCellGeneratesNothing(){
        Position pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1");
        assertEquals(true, pos1.move(BoardCell.E2, BoardCell.E4).isNothing());
        assertEquals(true, pos1.move(BoardCell.G4, BoardCell.F5).isNothing());

        Position pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1");
        assertEquals(true, pos2.move(BoardCell.E6, BoardCell.E7).isNothing());
        assertEquals(true, pos2.move(BoardCell.C6, BoardCell.C5).isNothing());
    }

    @Test
    public void movingAnEnemyPieceGeneratesNothing(){
        Position pos1 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 w - - 0 1");
        assertEquals(true, pos1.move(BoardCell.C7, BoardCell.F7).isNothing());
        assertEquals(true, pos1.move(BoardCell.F6, BoardCell.H5).isNothing());

        Position pos2 = Position.fromFEN("3rr2k/1bq3pp/p2p1n2/np6/5N2/PB5Q/1PPB2PP/1K1R1R2 b - - 0 1");
        assertEquals(true, pos2.move(BoardCell.B3, BoardCell.A2).isNothing());
        assertEquals(true, pos2.move(BoardCell.H3, BoardCell.C8).isNothing());
    }

    /*
    A pseudo move is a move where we don't check for the chess state neither before nor after the move.
     */
    @Test
    public void illegalPseudoMoveGeneratesNothing(){
        Position pos1 = Position.fromFEN("rnbqkb1r/pppppppp/5n2/8/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 1 2");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E4, BoardCell.F5);
        assertEquals(true, wrapPos2.isNothing());
        Maybe<Position> wrapPos3 = pos1.move(BoardCell.A1, BoardCell.A4);
        assertEquals(true, wrapPos3.isNothing());
        Maybe<Position> wrapPos7 = pos1.move(BoardCell.B1, BoardCell.D2);
        assertEquals(true, wrapPos7.isNothing());

        Position pos4 = Position.fromFEN("rnbqkb1r/pppppppp/5n2/4P3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2");
        Maybe<Position> wrapPos5 = pos4.move(BoardCell.E8, BoardCell.D7);
        assertEquals(true, wrapPos5.isNothing());
        Maybe<Position> wrapPos6 = pos4.move(BoardCell.C8, BoardCell.A6);
        assertEquals(true, wrapPos6.isNothing());
        Maybe<Position> wrapPos8 = pos4.move(BoardCell.F6, BoardCell.D7);
        assertEquals(true, wrapPos8.isNothing());
    }

    @Test
    public void kingIsInChess(){
        Position pos1 = Position.fromFEN("rn1qkb1r/ppp2Bpp/3p1n2/4N3/4P3/2N5/PPPP1PPP/R1BbK2R b KQkq - 0 6");
        assertEquals(true, pos1.kingIsInChess(false));

        Position pos2 = Position.fromFEN("rnb1kbnr/ppNp1pp1/7p/4p3/4P2q/8/PPPP1PPP/R1BQKBNR b KQkq - 0 4");
        assertEquals(true, pos2.kingIsInChess(false));

        Position pos3 = Position.fromFEN("rnbqkb1r/pppppppp/7n/1B6/4P3/8/PPPP1PPP/RNBQK1NR b KQkq - 2 2");
        assertEquals(false, pos3.kingIsInChess(false));

        Position pos4 = Position.fromFEN("rnb1kbnr/ppp1pppp/4q3/8/8/2N5/PPPP1PPP/R1BQKBNR w KQkq - 2 4");
        assertEquals(true, pos4.kingIsInChess(true));

        Position pos5 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/4N3/1b6/8/PPPPPPPP/RNBQKB1R w KQkq - 1 3");
        assertEquals(false, pos5.kingIsInChess(true));
    }

    @Test
    public void weCannotDoAMoveThatWouldLeaveOurKingInChess(){
        Position pos1 = Position.fromFEN("8/8/4k3/8/4K3/8/8/8 w - - 0 1");
        Maybe<Position> wrapPos2 = pos1.move(BoardCell.E4, BoardCell.D5);
        assertEquals(true, wrapPos2.isNothing());

        Position pos3 = Position.fromFEN("8/8/4k3/8/4K3/8/8/8 b - - 0 1");
        Maybe<Position> wrapPos4 = pos3.move(BoardCell.E6, BoardCell.E5);
        assertEquals(true, wrapPos4.isNothing());

        Position pos5 = Position.fromFEN("4k3/8/8/8/8/8/2q5/4K3 w - - 0 1");
        Maybe<Position> wrapPos6 = pos5.move(BoardCell.E1, BoardCell.D2);
        assertEquals(true, wrapPos6.isNothing());

        Position pos7 = Position.fromFEN("4k3/8/8/5Q2/8/8/8/4K3 b - - 0 1");
        Maybe<Position> wrapPos8 = pos7.move(BoardCell.E8, BoardCell.F7);
        assertEquals(true, wrapPos8.isNothing());

        Position pos9 = Position.fromFEN("r1bqkbnr/ppp2ppp/2np4/1B2p3/4P3/3P1N2/PPP2PPP/RNBQK2R b KQkq - 0 4");
        Maybe<Position> wrapPos10 = pos9.move(BoardCell.C6, BoardCell.D4);
        assertEquals(true, wrapPos10.isNothing());

        Position pos11 = Position.fromFEN("rnbqk1nr/ppp2ppp/4p3/3p4/1b1P4/2N2N2/PPP1PPPP/R1BQKB1R w KQkq - 2 4");
        Maybe<Position> wrapPos12 = pos11.move(BoardCell.C3, BoardCell.D5);
        assertEquals(true, wrapPos12.isNothing());
    }
}
