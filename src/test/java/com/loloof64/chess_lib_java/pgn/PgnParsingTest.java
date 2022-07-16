package com.loloof64.chess_lib_java.pgn;

import com.loloof64.chess_lib_java.history.ChessHistoryNode;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class PgnParsingTest {


    @Test
    public void headersParsingTest_1() {
        ChessGame game = null;
        try {
            game = PgnParser.parsePgnFromFileName("/ParsingPgn_1.pgn");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals("F/S Return Match", game.getHeader().get("Event"));
        assertEquals("Belgrade, Serbia JUG", game.getHeader().get("Site"));
        assertEquals("1992.11.04", game.getHeader().get("Date"));
        assertEquals("29", game.getHeader().get("Round"));
        assertEquals("Fischer, Robert J.", game.getHeader().get("White"));
        assertEquals("Spassky, Boris V.", game.getHeader().get("Black"));
        assertEquals("1/2-1/2", game.getHeader().get("Result"));
    }

    @Test
    public void headersParsingTest_2() {
        ChessGame game = null;
        try {
            game = PgnParser.parsePgnFromFileName("/ParsingPgn_2.pgn");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals("Interclubs FRA", game.getHeader().get("Event"));
        assertEquals("?", game.getHeader().get("Site"));
        assertEquals("????.??.??", game.getHeader().get("Date"));
        assertEquals("?", game.getHeader().get("Round"));
        assertEquals("Calistri, Tristan", game.getHeader().get("White"));
        assertEquals("Bauduin, Etienne", game.getHeader().get("Black"));
        assertEquals("1-0", game.getHeader().get("Result"));
    }
}
