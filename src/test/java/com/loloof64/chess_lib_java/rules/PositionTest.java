package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.pieces.Piece;
import com.loloof64.chess_lib_java.rules.coords.BoardFile;
import com.loloof64.functional.monad.Either;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PositionTest {

    /**
     * Brute string representation of a board, as opposed to FEN string : each empty cell is represented,
     * empty cells are not grouped. Furthermore, first rank is coded first : the order is preserved, as opposite to FEN.
     * An empty cell is simply coded as null in the input array.
     * @param boardLines1To8 - Array of array of Piece - Board FEN values, from rank 1 to rank 8.
     * @return String - the brute string representation of the board.
     */
    private String boardArrayToBruteString(Piece[][] boardLines1To8){
        StringBuilder strBuilder  = new StringBuilder();
        int lineIndex = 0;
        for (Piece[] line : boardLines1To8){
            for (Piece cell : line){
                strBuilder.append(cell != null ? cell.toFEN() : '_');
            }
            if (lineIndex < boardLines1To8.length - 1) strBuilder.append('/');
            lineIndex++;
        }
        return strBuilder.toString();
    }

    private Piece[][] boardValuesFromBruteString(String boardStr){
        Piece [][] boardToReturn = new Piece[8][8];

        final String boardPart = boardStr.split("\\s+")[0];
        final String lines[] = boardPart.split("/");
        int rankIndex = 0;
        for (String line : lines){
            int fileIndex = 0;
            char [] cellsValues = line.toCharArray();
            for (char cell : cellsValues){
                boardToReturn[rankIndex][fileIndex++] = cell == '_' ? null : Piece.fromFEN(cell);
            }
            rankIndex++;
        }

        return boardToReturn;
    }

    @Test
    public void boardPartIsGeneratedCorrectlyFromFEN(){
        final Board board1 = Board.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertEquals("RNBQKBNR/PPPPPPPP/________/________/________/________/pppppppp/rnbqkbnr",
                boardArrayToBruteString(board1.values()));

        final Board board2 = Board.fromFEN("3r1rk1/5pp1/7p/8/3Q4/3B4/5PPP/5RK1 w - - 0 1");
        assertEquals("_____RK_/_____PPP/___B____/___Q____/________/_______p/_____pp_/___r_rk_",
                boardArrayToBruteString(board2.values()));

        final Board board3 = Board.fromFEN("5r2/5p1p/4N1p1/2n5/8/5kPq/5P1P/5RK1 b - - 0 1");
        assertEquals("_____RK_/_____P_P/_____kPq/________/__n_____/____N_p_/_____p_p/_____r__",
                boardArrayToBruteString(board3.values()));
    }

    @Test
    public void boardPartGenerateFENCorrectly(){
        final Board board1 = new Board(boardValuesFromBruteString(
                "RNBQKBNR/PPPPPPPP/________/________/________/________/pppppppp/rnbqkbnr"));
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board1.toFEN());

        final Board board2 = new Board(boardValuesFromBruteString(
                "_____RK_/_____PPP/___B____/___Q____/________/_______p/_____pp_/___r_rk_"));
        assertEquals("3r1rk1/5pp1/7p/8/3Q4/3B4/5PPP/5RK1", board2.toFEN());

        final Board board3 = new Board(boardValuesFromBruteString(
                "_____RK_/_____P_P/_____kPq/________/__n_____/____N_p_/_____p_p/_____r__"));
        assertEquals("5r2/5p1p/4N1p1/2n5/8/5kPq/5P1P/5RK1", board3.toFEN());
    }

    @Test
    public void castlesRightsAreGeneratedCorrectlyFromFEN(){
        final CastlesRights rights1 = CastlesRights.fromFEN(
                "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertEquals(new CastlesRights(true, true, true,true),
                rights1);

        final CastlesRights rights2 = CastlesRights.fromFEN(
                "8/8/8/3k4/8/8/3K4/8 w - - 0 30");
        assertEquals(new CastlesRights(false, false, false,false),
                rights2);

        final CastlesRights rights3 = CastlesRights.fromFEN(
                "r1bq1rk1/pppp1ppp/2n2n2/2b1p3/2B1P3/P1N2N2/1PPP1PPP/R1BQK2R w KQ - 0 10");
        assertEquals(new CastlesRights(true, true, false,false),
                rights3);

        final CastlesRights rights4 = CastlesRights.fromFEN(
                "rnbqkbnr/ppp2ppp/3p4/4p3/7R/P4N2/1PPPPPP1/RNBQKB2 b Qkq - 0 8");
        assertEquals(new CastlesRights(false, true, true,true),
                rights4);
    }

    @Test
    public void castlesRightsGenerateFENCorrectly(){
        final CastlesRights rights1 = new CastlesRights(true, true,
                true, true);
        assertEquals("KQkq", rights1.toFEN());

        final CastlesRights rights2 = new CastlesRights(false, false,
                false, false);
        assertEquals("-", rights2.toFEN());

        final CastlesRights rights3 = new CastlesRights(true, true,
                false, false);
        assertEquals("KQ", rights3.toFEN());

        final CastlesRights rights4 = new CastlesRights(false, true,
                true, true);
        assertEquals("Qkq", rights4.toFEN());
    }

    @Test
    public void gameInfoIsGeneratedCorrectlyFromFEN(){
        final GameInfo info1 = GameInfo.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        assertEquals(new GameInfo(true, new CastlesRights(true, true,
                true, true),
                null, 0, 1), info1);

        final GameInfo info2 = GameInfo.fromFEN("k2rr3/2pn2pp/p4p2/8/8/6P1/2P2P1P/RR4K1 b - - 13 20");
        assertEquals(new GameInfo(false, new CastlesRights(false, false,
                false, false),
                null, 13, 20), info2);

        final GameInfo info3 = GameInfo.fromFEN("r1bqkbnr/ppp1pppp/2n5/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3");
        assertEquals(new GameInfo(true, new CastlesRights(true, true,
                true, true),
                BoardFile.FILE_D, 0, 3), info3);

        final GameInfo info4 = GameInfo.fromFEN("rnbqkbnr/ppp1pppp/8/8/3pP3/5N1P/PPPP1PP1/RNBQKB1R b KQkq e3 0 3");
        assertEquals(new GameInfo(false, new CastlesRights(true, true,
                true, true),
                BoardFile.FILE_E, 0, 3), info4);
    }

    @Test
    public void gameInfoGeneratesFENCorrectly(){
        final GameInfo info1 = new GameInfo(true, new CastlesRights(true, true,
                true, true), null, 0, 1);
        assertEquals("w KQkq - 0 1", info1.toFEN());

        final GameInfo info2 = new GameInfo(false, new CastlesRights(false, false,
                false, false), null, 13, 20);
        assertEquals("b - - 13 20", info2.toFEN());

        final GameInfo info3 = new GameInfo(true, new CastlesRights(true, true,
                true, true), BoardFile.FILE_D, 0, 3);
        assertEquals("w KQkq d6 0 3", info3.toFEN());

        final GameInfo info4 = new GameInfo(false, new CastlesRights(true, true,
                true, true), BoardFile.FILE_E, 0, 3);
        assertEquals("b KQkq e3 0 3", info4.toFEN());
    }

    @Test
    public void positionIsGeneratedFromFENCorrectly(){
        final Position pos1 = Position.fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1").right();
        final Board board1 = new Board(boardValuesFromBruteString(
                "RNBQKBNR/PPPPPPPP/________/________/________/________/pppppppp/rnbqkbnr"));
        final GameInfo info1 = new GameInfo(true, new CastlesRights(
                true, true, true, true),
                null, 0, 1);
        assertEquals(new Position(board1, info1), pos1);

        final Position pos2 = Position.fromFEN("k2rr3/2pn2pp/p4p2/8/8/6P1/2P2P1P/RR4K1 b - - 13 20").right();
        final Board board2 = new Board(boardValuesFromBruteString(
                "RR____K_/__P__P_P/______P_/________/________/p____p__/__pn__pp/k__rr___"));
        final GameInfo info2 = new GameInfo(false, new CastlesRights(
                false, false, false, false),
                null, 13, 20);
        assertEquals(new Position(board2, info2), pos2);

        final Position pos3 = Position.fromFEN("r1bqkbnr/ppp1pppp/2n5/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3").right();
        final Board board3 = new Board(boardValuesFromBruteString(
                "RNBQKBNR/PPPP_PPP/________/________/___pP___/__n_____/ppp_pppp/r_bqkbnr"));
        final GameInfo info3 = new GameInfo(true, new CastlesRights(
                true, true, true, true),
                BoardFile.FILE_D, 0, 3);
        assertEquals(new Position(board3, info3), pos3);

        final Position pos4 = Position.fromFEN("rnbqkbnr/ppp1pppp/8/8/3pP3/5N1P/PPPP1PP1/RNBQKB1R b KQkq e3 0 3").right();
        final Board board4 = new Board(boardValuesFromBruteString(
                "RNBQKB_R/PPPP_PP_/_____N_P/___pP___/________/________/ppp_pppp/rnbqkbnr"));
        final GameInfo info4 = new GameInfo(false, new CastlesRights(
                true, true, true, true),
                BoardFile.FILE_E, 0, 3);
        assertEquals(new Position(board4, info4), pos4);
    }

    @Test
    public void positionGeneratesFENCorrectly(){
        final Board board1 = new Board(boardValuesFromBruteString(
                "RNBQKBNR/PPPPPPPP/________/________/________/________/pppppppp/rnbqkbnr"));
        final GameInfo info1 = new GameInfo(true, new CastlesRights(
                true, true, true, true),
                null, 0, 1);
        final Position pos1 = new Position(board1, info1);
        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", pos1.toFEN());

        final Board board2 = new Board(boardValuesFromBruteString(
                "RR____K_/__P__P_P/______P_/________/________/p____p__/__pn__pp/k__rr___"));
        final GameInfo info2 = new GameInfo(false, new CastlesRights(
                false, false, false, false),
                null, 13, 20);
        final Position pos2 = new Position(board2, info2);
        assertEquals("k2rr3/2pn2pp/p4p2/8/8/6P1/2P2P1P/RR4K1 b - - 13 20", pos2.toFEN());

        final Board board3 = new Board(boardValuesFromBruteString(
                "RNBQKBNR/PPPP_PPP/________/________/___pP___/__n_____/ppp_pppp/r_bqkbnr"));
        final GameInfo info3 = new GameInfo(true, new CastlesRights(
                true, true, true, true),
                BoardFile.FILE_D, 0, 3);
        final Position pos3 = new Position(board3, info3);
        assertEquals("r1bqkbnr/ppp1pppp/2n5/3pP3/8/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 3", pos3.toFEN());

        final Board board4 = new Board(boardValuesFromBruteString(
                "RNBQKB_R/PPPP_PP_/_____N_P/___pP___/________/________/ppp_pppp/rnbqkbnr"));
        final GameInfo info4 = new GameInfo(false, new CastlesRights(
                true, true, true, true),
                BoardFile.FILE_E, 0, 3);
        final Position pos4 = new Position(board4, info4);
        assertEquals("rnbqkbnr/ppp1pppp/8/8/3pP3/5N1P/PPPP1PP1/RNBQKB1R b KQkq e3 0 3", pos4.toFEN());
    }

    @Test
    public void weCannotGenerateAPositionFromAFENIfTheKingNumberPerSideIsNotOne(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("8/8/8/8/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("8/8/8/8/8/8/8/8 b - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("K7/K7/8/8/8/8/k7/8 w - - 0 1");
        assertTrue(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("K7/k7/8/8/8/8/k7/8 w - - 0 1");
        assertTrue(wrapPos4.isLeft());

        Either<Exception, Position> wrapPos5 = Position.fromFEN("K7/K7/K7/8/8/8/k/8 w - - 0 1");
        assertTrue(wrapPos5.isLeft());
    }

    @Test
    public void weCannotGenerateAPositionWhereAPlayerHasMoreThanEightPawns(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("8/K1PP4/PPPPPPPP/8/8/pppppppp/k1pp4/8 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("8/K1P5/PPPPPPPP/8/8/ppp5/k7/8 w - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("8/K1p5/ppp5/1ppp4/pp6/PPP5/k7/8 w - - 0 1");
        assertTrue(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("8/K1p5/ppp5/1ppp4/p7/PPP5/k7/8 w - - 0 1");
        assertFalse(wrapPos4.isLeft());

    }

    @Test
    public void whenGeneratingPositionEachPlayerRooksCountCannotBeGreaterThanTenMinusPawnsCount(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("K7/RRRRRRRR/RR6/R7/8/8/1P6/1k6 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("K1k5/rrrrrrrr/rr6/r7/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("K1k5/rrrrrrrr/rr6/8/8/8/8/8 w - - 0 1");
        assertFalse(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("K7/RRRRR3/PPPPPP2/8/8/8/8/k7 w - - 0 1");
        assertTrue(wrapPos4.isLeft());

        Either<Exception, Position> wrapPos5 = Position.fromFEN("K1k5/rrrrr3/pppppp2/8/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos5.isLeft());

        Either<Exception, Position> wrapPos6 = Position.fromFEN("K1k5/rrr5/ppppppp1/8/8/8/8/8 w - - 0 1");
        assertFalse(wrapPos6.isLeft());
    }

    @Test
    public void whenGeneratingPositionEachPlayerBishopsCountCannotBeGreaterThanTenMinusPawnsCount(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("K7/BBBBBBBB/BB6/B7/8/8/1p6/k7 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("2k5/bbbbbbbb/bb6/b7/8/8/1P6/K7 w - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("K1k5/bbbbbbbb/bb6/8/8/8/8/8 w - - 0 1");
        assertFalse(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("K7/BBBBB3/PPPPPP2/8/8/8/8/k7 w - - 0 1");
        assertTrue(wrapPos4.isLeft());

        Either<Exception, Position> wrapPos5 = Position.fromFEN("2k5/bbbbb3/pppppp2/8/8/8/8/K7 w - - 0 1");
        assertTrue(wrapPos5.isLeft());

        Either<Exception, Position> wrapPos6 = Position.fromFEN("2k5/bbb5/ppppppp1/8/8/8/8/K7 w - - 0 1");
        assertFalse(wrapPos6.isLeft());
    }

    @Test
    public void whenGeneratingPositionEachPlayerKnightsCountCannotBeGreaterThanTenMinusPawnsCount(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("K7/NNNNNNNN/NN6/N7/8/8/8/k7 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("2k5/nnnnnnnn/nn6/n7/8/8/8/K7 w - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("K1k5/nnnnnnnn/nn6/8/8/8/8/8 w - - 0 1");
        assertFalse(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("K7/NNNNN3/PPPPPP2/8/8/8/8/k7 w - - 0 1");
        assertTrue(wrapPos4.isLeft());

        Either<Exception, Position> wrapPos5 = Position.fromFEN("K1k5/nnnnn3/pppppp2/8/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos5.isLeft());

        Either<Exception, Position> wrapPos6 = Position.fromFEN("K1k5/nnn5/ppppppp1/8/8/8/8/8 w - - 0 1");
        assertFalse(wrapPos6.isLeft());
    }

    @Test
    public void whenGeneratingPositionEachPlayerQueensCountCannotBeGreaterThanElevenMinusPawnsCount(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("Qn5k/QnQ3n1/Q1Q5/Q7/Q7/Q7/Q7/Q6K w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("qn5K/q1q5/q7/q7/q7/q7/qnq5/q5nk w - - 0 1");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("Qn5k/Qn4n1/Q7/Q7/Q7/Q1Q5/Q7/Q6K w - - 0 1");
        assertFalse(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("qn5K/q7/q7/q7/q1q5/q7/qn6/q5nk w - - 0 1");
        assertFalse(wrapPos4.isLeft());

        Either<Exception, Position> wrapPos5 = Position.fromFEN("Qn5k/Qn4n1/Q7/Q7/Q7/Q3P1P1/Q4P2/7K w - - 0 1");
        assertTrue(wrapPos5.isLeft());

        Either<Exception, Position> wrapPos6 = Position.fromFEN("1n5k/Qn4n1/Q7/Q7/Q7/Q3P1P1/Q4P2/7K w - - 0 1");
        assertFalse(wrapPos6.isLeft());

        Either<Exception, Position> wrapPos7 = Position.fromFEN("qn5K/q7/q7/q7/q7/q4p2/qn2p1p1/6nk w - - 0 1");
        assertTrue(wrapPos7.isLeft());

        Either<Exception, Position> wrapPos8 = Position.fromFEN("1n5K/q7/q7/q7/q7/q4p2/qn2p1p1/6nk w - - 0 1");
        assertFalse(wrapPos8.isLeft());
    }

    @Test
    public void cannotGenerateAPositionWithPawnOnRankOneOrHeight(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("P7/K1k5/8/8/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("8/K1k5/8/8/8/8/8/2p5 w - - 0 1");
        assertTrue(wrapPos2.isLeft());
    }

    @Test
    public void cannotGenerateAPositionWhereKingOfPlayerNotHavingTheTurnIsInChess(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("kK6/8/8/8/8/8/8/8 w - - 0 1");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("rnbqk1nr/pppp1ppp/8/4p3/4P3/P4N2/1PPb1PPP/RNBQKB1R b KQkq - 0 4");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("rnbqkb1r/pppp1Bpp/5n2/4p3/4P3/8/PPPP1PPP/RNBQK1NR w KQkq - 0 3");
        assertTrue(wrapPos3.isLeft());
    }

    @Test
    public void cannotGenerateAPositionWhereKingOutsideE1E8AndMatchingCastleRightsAreStillActive(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPPKPPP/RNBQ1BNR b KQkq - 1 2");
        assertTrue(wrapPos1.isLeft());
        Either<Exception, Position> wrapPos2 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPPKPPP/RNBQ1BNR b Kkq - 1 2");
        assertTrue(wrapPos2.isLeft());
        Either<Exception, Position> wrapPos3 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPPKPPP/RNBQ1BNR b Qkq - 1 2");
        assertTrue(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("rnbq1bnr/pppkpppp/8/3p4/4P3/5N2/PPPP1PPP/RNBQKB1R w KQkq - 2 3");
        assertTrue(wrapPos4.isLeft());
        Either<Exception, Position> wrapPos5 = Position.fromFEN("rnbq1bnr/pppkpppp/8/3p4/4P3/5N2/PPPP1PPP/RNBQKB1R w KQk - 2 3");
        assertTrue(wrapPos5.isLeft());
        Either<Exception, Position> wrapPos6 = Position.fromFEN("rnbq1bnr/pppkpppp/8/3p4/4P3/5N2/PPPP1PPP/RNBQKB1R w KQq - 2 3");
        assertTrue(wrapPos6.isLeft());

        Either<Exception, Position> wrapPos7 = Position.fromFEN("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPPKPPP/RNBQ1BNR b kq - 1 2");
        assertFalse(wrapPos7.isLeft());
        Either<Exception, Position> wrapPos8 = Position.fromFEN("rnbq1bnr/pppkpppp/8/3p4/4P3/5N2/PPPP1PPP/RNBQKB1R w KQ - 2 3");
        assertFalse(wrapPos8.isLeft());
    }

    @Test
    public void cannotGenerateAPositionWhereARookIsMissingInCornerAndTheMatchingCastleRightStillActive(){
        Either<Exception, Position> wrapPos1 = Position.fromFEN("r1bqkbnr/pppppppp/2n5/8/7P/8/PPPPPPPR/RNBQKBN1 w K - 2 2");
        assertTrue(wrapPos1.isLeft());

        Either<Exception, Position> wrapPos2 = Position.fromFEN("rnbqkb1r/pppppppp/5n2/8/P7/R7/1PPPPPPP/1NBQKBNR w Q - 2 2");
        assertTrue(wrapPos2.isLeft());

        Either<Exception, Position> wrapPos3 = Position.fromFEN("1nbqkbnr/1ppppppp/r7/p7/8/4PN2/PPPP1PPP/RNBQKB1R b q - 1 3");
        assertTrue(wrapPos3.isLeft());

        Either<Exception, Position> wrapPos4 = Position.fromFEN("rnbqkbn1/ppppppp1/7r/7p/8/2NP4/PPP1PPPP/R1BQKBNR b k - 1 3");
        assertTrue(wrapPos4.isLeft());
    }
}
