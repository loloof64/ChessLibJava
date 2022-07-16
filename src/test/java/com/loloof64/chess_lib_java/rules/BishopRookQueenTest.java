package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.functional.monad.Either;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BishopRookQueenTest {

    @Test
    public void bishopCanMoveInDiagonalAndAwayIfNoObstacle(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/3B4/8/8/4K3 w - - 0 1").right();
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.B3)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.E6)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.B4)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.G4)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.D7)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.D3)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4,BoardCell.B2)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4,BoardCell.H8)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4,BoardCell.E3)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4,BoardCell.A7)));

        Position pos2 = Position.fromFEN("4k3/8/8/8/4b3/8/8/4K3 b - - 0 1").right();
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.G5)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.C3)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.E6)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.E3)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.A4)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.G4)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.G6)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.B1)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.A8)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.F3)));
    }

    @Test
    public void bishopCanCaptureEnemyPieceIfOnItsPathButNotFriendPiece(){
        Position pos1 = Position.fromFEN("N3k3/8/6n1/8/4B3/8/6P1/1b2K3 w - - 0 1").right();
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.G6)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.B1)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.A8)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.G2)));

        Position pos2 = Position.fromFEN("n3k3/8/6N1/8/4b3/8/6p1/1B2K3 b - - 0 1").right();
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.G6)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.B1)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.A8)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.G2)));
    }

    @Test
    public void bishopCannotJumpOverObstacles(){
        Position pos1 = Position.fromFEN("n3k3/8/2p5/5P2/4B3/5N2/2b3p1/4K3 w - - 0 1").right();
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.A8)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.G2)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.B1)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.G6)));

        Position pos2 = Position.fromFEN("7r/4k3/1R3n2/8/3b4/2r5/5N2/b3K3 b - - 0 1").right();
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.A1)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.H8)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.A7)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.G1)));
    }

    @Test
    public void rookCanMoveHorizontallyOrVerticallyIfNoObstacle(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/3R4/8/8/4K3 w - - 0 1").right();
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.C6)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.F3)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.G7)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.C3)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.A7)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.G1)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.C4)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.H4)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.D2)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.D7)));

        Position pos2 = Position.fromFEN("4k3/8/8/8/3r4/8/8/4K3 b - - 0 1").right();
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.C6)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.F3)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.G7)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.C3)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.A7)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.G1)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.C4)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.H4)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.D2)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.D7)));
    }

    @Test
    public void rookCanCaptureEnemyPieceIfOnItsTargetSquareButNotFriendPiece(){
        Position pos1 = Position.fromFEN("3bk3/8/8/8/n2R2B1/8/3N4/4K3 w - - 0 1").right();
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.D2)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.G4)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.A4)));
        assertTrue(pos1.canMove(new Move(BoardCell.D4, BoardCell.D8)));

        Position pos2 = Position.fromFEN("3bk3/8/8/8/n2r2B1/8/3N4/4K3 b - - 0 1").right();
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.A4)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.D8)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.D2)));
        assertTrue(pos2.canMove(new Move(BoardCell.D4, BoardCell.G4)));
    }

    @Test
    public void rookCannotJumpOverObstacles(){
        Position pos1 = Position.fromFEN("3bk3/3R4/8/8/nN1R1n2/3P4/8/4K3 w - - 0 1").right();
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.A4)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.G4)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.D8)));
        assertFalse(pos1.canMove(new Move(BoardCell.D4, BoardCell.D2)));

        Position pos2 = Position.fromFEN("3Bk3/3R4/8/8/BN1r1n2/3P4/8/4K3 b - - 0 1").right();
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.A4)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.G4)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.D8)));
        assertFalse(pos2.canMove(new Move(BoardCell.D4, BoardCell.D2)));
    }

    @Test
    public void queenCanMoveEitherAsRookOrBishop(){
        Position pos1 = Position.fromFEN("4k3/1n2B3/2n5/8/bP2Q1P1/4p3/6N1/4K3 w - - 0 1").right();
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.E3)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.C2)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.C4)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.F3)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.F4)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.C6)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.E5)));
        assertTrue(pos1.canMove(new Move(BoardCell.E4, BoardCell.H7)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.A4)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.B7)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.E7)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.H1)));
        assertFalse(pos1.canMove(new Move(BoardCell.E4, BoardCell.G4)));

        Position pos2 = Position.fromFEN("4k3/1n6/4B3/8/1N2q1nN/4p3/2P5/4K3 b - - 0 1").right();
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.D3)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.H7)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.B4)));
        assertTrue(pos2.canMove(new Move(BoardCell.E4, BoardCell.E6)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.E3)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.E2)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.H4)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.B7)));
        assertFalse(pos2.canMove(new Move(BoardCell.E4, BoardCell.A8)));
    }

    @Test
    public void bishopMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("4k3/8/5n2/8/3B4/8/8/4K3 w - - 0 1").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.D4, BoardCell.B2));
        Position pos2 = wrapPos2.right().position;
        assertEquals(Position.fromFEN("4k3/8/5n2/8/8/8/1B6/4K3 b - - 1 1").right(), pos2);
        Either<Exception, MoveResult> wrapPos3 = pos1.move(new Move(BoardCell.D4, BoardCell.F6));
        Position pos3 = wrapPos3.right().position;
        assertEquals(Position.fromFEN("4k3/8/5B2/8/8/8/8/4K3 b - - 0 1").right(), pos3);

        Position pos4 = Position.fromFEN("4k3/8/8/4N3/3b4/8/8/4K3 b - - 0 1").right();
        Either<Exception, MoveResult> wrapPos5 = pos4.move(new Move(BoardCell.D4, BoardCell.A1));
        Position pos5 = wrapPos5.right().position;
        assertEquals(Position.fromFEN("4k3/8/8/4N3/8/8/8/b3K3 w - - 1 2").right(), pos5);
        Either<Exception, MoveResult> wrapPos6 = pos4.move(new Move(BoardCell.D4, BoardCell.E5));
        Position pos6 = wrapPos6.right().position;
        assertEquals(Position.fromFEN("4k3/8/8/4b3/8/8/8/4K3 w - - 0 2").right(), pos6);
    }

    @Test
    public void bishopMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.F1, BoardCell.C4));
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/2B1P3/8/PPPP1PPP/RNBQK1NR b KQkq - 1 2").right(),
                wrapPos2.right().position);

        Position pos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/3PP3/8/PPP2PPP/RNBQKBNR b KQkq d3 0 2").right();
        Either<Exception, MoveResult> wrapPos4 = pos3.move(new Move(BoardCell.F8, BoardCell.B4));
        assertEquals(Position.fromFEN("rnbqk1nr/pppp1ppp/8/4p3/1b1PP3/8/PPP2PPP/RNBQKBNR w KQkq - 1 3").right(),
                wrapPos4.right().position);
    }

    @Test
    public void rookMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("4k3/8/8/8/2R3n1/8/8/4K3 w - - 0 1").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.C4, BoardCell.C7));
        Position pos2 = wrapPos2.right().position;
        assertEquals(Position.fromFEN("4k3/2R5/8/8/6n1/8/8/4K3 b - - 1 1").right(), pos2);
        Either<Exception, MoveResult> wrapPos3 = pos1.move(new Move(BoardCell.C4, BoardCell.G4));
        Position pos3 = wrapPos3.right().position;
        assertEquals(Position.fromFEN("4k3/8/8/8/6R1/8/8/4K3 b - - 0 1").right(), pos3);

        Position pos4 = Position.fromFEN("4k3/8/8/8/2r3N1/8/8/4K3 b - - 0 1").right();
        Either<Exception, MoveResult> wrapPos5 = pos4.move(new Move(BoardCell.C4, BoardCell.G4));
        Position pos5 = wrapPos5.right().position;
        assertEquals(Position.fromFEN("4k3/8/8/8/6r1/8/8/4K3 w - - 0 2").right(), pos5);
        Either<Exception, MoveResult> wrapPos6 = pos4.move(new Move(BoardCell.C4, BoardCell.C7));
        Position pos6 = wrapPos6.right().position;
        assertEquals(Position.fromFEN("4k3/2r5/8/8/6N1/8/8/4K3 w - - 1 2").right(), pos6);
    }

    @Test
    public void rookMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("rnbqkbnr/ppppppp1/8/7p/7P/8/PPPPPPP1/RNBQKBNR w KQkq h6 0 2").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.H1, BoardCell.H3));
        assertEquals(Position.fromFEN("rnbqkbnr/ppppppp1/8/7p/7P/7R/PPPPPPP1/RNBQKBN1 b Qkq - 1 2").right(),
                wrapPos2.right().position);

        Position pos3 = Position.fromFEN("rnbqkbnr/ppppppp1/8/7p/7P/5N2/PPPPPPP1/RNBQKB1R b KQkq h3 0 2").right();
        Either<Exception, MoveResult> wrapPos4 = pos3.move(new Move(BoardCell.H8, BoardCell.H6));
        assertEquals(Position.fromFEN("rnbqkbn1/ppppppp1/7r/7p/7P/5N2/PPPPPPP1/RNBQKB1R w KQq - 1 3").right(),
                wrapPos4.right().position);
    }

    @Test
    public void a1h1a8Orh8RookMoveCancelCastlesAccordinglyInGeneratedPosition(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/7P/8/PPPPPPP1/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.H1, BoardCell.H3));
        Position pos2 = wrapPos2.right().position;
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/7P/7R/PPPPPPP1/RNBQKBN1 b Qkq - 1 2").right(), pos2);

        Position pos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/P7/8/1PPPPPPP/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, MoveResult> wrapPos4 = pos3.move(new Move(BoardCell.A1, BoardCell.A3));
        Position pos4 = wrapPos4.right().position;
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/P7/R7/1PPPPPPP/1NBQKBNR b Kkq - 1 2").right(), pos4);

        Position pos5 = Position.fromFEN("rnbqk1nr/ppppppb1/6p1/7p/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 b kq - 3 4").right();
        Either<Exception, MoveResult> wrapPos6 = pos5.move(new Move(BoardCell.H8, BoardCell.H6));
        Position pos6 = wrapPos6.right().position;
        assertEquals(Position.fromFEN("rnbqk1n1/ppppppb1/6pr/7p/2B1P3/5N2/PPPP1PPP/RNBQ1RK1 w q - 4 5").right(), pos6);

        Position pos7 = Position.fromFEN("rnbqkbnr/1ppppppp/8/p7/4P3/5N2/PPPP1PPP/RNBQKB1R b KQkq - 1 2").right();
        Either<Exception, MoveResult> wrapPos8 = pos7.move(new Move(BoardCell.A8, BoardCell.A6));
        Position pos8 = wrapPos8.right().position;
        assertEquals(Position.fromFEN("1nbqkbnr/1ppppppp/r7/p7/4P3/5N2/PPPP1PPP/RNBQKB1R w KQk - 2 3").right(), pos8);
    }

    @Test
    public void queenMoveGeneratesPositionCorrectly(){
        Position pos1 = Position.fromFEN("4k3/1b6/8/8/1Q6/8/8/4K3 w - - 0 1").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.B4, BoardCell.B7));
        assertEquals(Position.fromFEN("4k3/1Q6/8/8/8/8/8/4K3 b - - 0 1").right(), wrapPos2.right().position);
        Either<Exception, MoveResult> wrapPos3 = pos1.move(new Move(BoardCell.B4, BoardCell.D2));
        assertEquals(Position.fromFEN("4k3/1b6/8/8/8/8/3Q4/4K3 b - - 1 1").right(), wrapPos3.right().position);

        Position pos4 = Position.fromFEN("4k3/8/1B5q/8/8/8/8/4K3 b - - 1 1").right();
        Either<Exception, MoveResult> wrapPos5 = pos4.move(new Move(BoardCell.H6, BoardCell.B6));
        assertEquals(Position.fromFEN("4k3/8/1q6/8/8/8/8/4K3 w - - 0 2").right(), wrapPos5.right().position);
        Either<Exception, MoveResult> wrapPos6 = pos4.move(new Move(BoardCell.H6, BoardCell.C1));
        assertEquals(Position.fromFEN("4k3/8/1B6/8/8/8/8/2q1K3 w - - 2 2").right(), wrapPos6.right().position);
    }

    @Test
    public void queenMoveClearsEnPassantFile(){
        Position pos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2").right();
        Either<Exception, MoveResult> wrapPos2 = pos1.move(new Move(BoardCell.D1, BoardCell.H5));
        assertEquals(Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p2Q/4P3/8/PPPP1PPP/RNB1KBNR b KQkq - 1 2").right(),
                wrapPos2.right().position);

        Position pos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/3PP3/8/PPP2PPP/RNBQKBNR b KQkq d3 0 2").right();
        Either<Exception, MoveResult> wrapPos4 = pos3.move(new Move(BoardCell.D8, BoardCell.H4));
        assertEquals(Position.fromFEN("rnb1kbnr/pppp1ppp/8/4p3/3PP2q/8/PPP2PPP/RNBQKBNR w KQkq - 1 3").right(),
                wrapPos4.right().position);
    }

    @Test
    public void bishopRookAndQueenAttackCellsAsTheyMoveIfNoObstacleInPath(){
        Position pos1 = Position.fromFEN("4k3/7n/6b1/3p4/4B3/3p4/8/4K3 w - - 0 1").right();
        Piece pos1WhiteBishop = pos1.getPieceAt(BoardCell.E4);
        assertTrue(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.D3, pos1));
        assertTrue(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.G6, pos1));
        assertTrue(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.G2, pos1));
        assertFalse(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.C6, pos1));
        assertFalse(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.H7, pos1));
        assertFalse(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.E2, pos1));
        assertFalse(pos1WhiteBishop.isAttackingCell(BoardCell.E4, BoardCell.A4, pos1));

        Position pos2 = Position.fromFEN("4k2N/8/5R2/2n5/3b4/2P5/8/4K3 b - - 0 1").right();
        Piece pos2BlackBishop = pos2.getPieceAt(BoardCell.D4);
        assertTrue(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.C3, pos2));
        assertTrue(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.C5, pos2));
        assertTrue(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.E3, pos2));
        assertTrue(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.F6, pos2));
        assertTrue(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.G1, pos2));
        assertFalse(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.B6, pos2));
        assertFalse(pos2BlackBishop.isAttackingCell(BoardCell.D4, BoardCell.H8, pos2));

        Position pos3 = Position.fromFEN("4k3/8/2p5/2P5/PpR1n3/8/8/4K3 w - - 0 1").right();
        Piece pos3WhiteRook = pos3.getPieceAt(BoardCell.C4);
        assertTrue(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.C2, pos3));
        assertTrue(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.C1, pos3));
        assertTrue(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.E4, pos3));
        assertTrue(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.B4, pos3));
        assertTrue(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.C5, pos3));
        assertFalse(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.A4, pos3));
        assertFalse(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.F4, pos3));
        assertFalse(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.C6, pos3));
        assertFalse(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.A6, pos3));
        assertFalse(pos3WhiteRook.isAttackingCell(BoardCell.C4, BoardCell.F7, pos3));

        Position pos4 = Position.fromFEN("4k3/8/6N1/5pr1/8/8/8/4K3 b - - 0 1").right();
        Piece pos4BlackRook = pos4.getPieceAt(BoardCell.G5);
        assertTrue(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.F5, pos4));
        assertTrue(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.G6, pos4));
        assertTrue(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.H5, pos4));
        assertTrue(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.G2, pos4));
        assertFalse(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.D5, pos4));
        assertFalse(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.G7, pos4));
        assertFalse(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.E7, pos4));
        assertFalse(pos4BlackRook.isAttackingCell(BoardCell.G5, BoardCell.D2, pos4));

        Position pos5 = Position.fromFEN("4k3/8/2p5/4B3/8/2Q1n3/8/4K3 w - - 0 1").right();
        Piece pos5WhiteQueen = pos5.getPieceAt(BoardCell.C3);
        assertTrue(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.C4, pos5));
        assertTrue(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.C6, pos5));
        assertTrue(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.E5, pos5));
        assertTrue(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.E3, pos5));
        assertFalse(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.F3, pos5));
        assertFalse(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.F6, pos5));
        assertFalse(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.C7, pos5));
        assertFalse(pos5WhiteQueen.isAttackingCell(BoardCell.C3, BoardCell.D5, pos5));

        Position pos6 = Position.fromFEN("4k3/8/3P4/4q1n1/8/4B3/8/4K3 b - - 0 1").right();
        Piece pos6BlackQueen = pos6.getPieceAt(BoardCell.E5);
        assertTrue(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.D6, pos6));
        assertTrue(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.G5, pos6));
        assertTrue(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.E3, pos6));
        assertTrue(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.E8, pos6));
        assertFalse(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.E1, pos6));
        assertFalse(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.H5, pos6));
        assertFalse(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.C7, pos6));
        assertFalse(pos6BlackQueen.isAttackingCell(BoardCell.E5, BoardCell.C4, pos6));
    }

}
