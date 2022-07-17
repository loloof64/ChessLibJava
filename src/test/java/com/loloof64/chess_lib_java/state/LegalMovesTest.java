package com.loloof64.chess_lib_java.state;

import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.MoveResult;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
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

    }

    @Test
    public void withAPinnedPiece() {

    }

    @Test
    public void withEnPassantSquare() {

    }

    @Test
    public void withCastle() {

    }

    @Test
    public void withCastlePreventedByEnemyPiece() {

    }

    @Test
    public void withPromotion() {

    }

    @Test
    public void furtherInOpening() {

    }

    @Test
    public void inMiddleGame() {

    }

    @Test
    public void inEndgame() {

    }

}
