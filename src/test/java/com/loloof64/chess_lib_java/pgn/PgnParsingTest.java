package com.loloof64.chess_lib_java.pgn;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class PgnParsingTest {

    private static String fileNameToString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(PgnParsingTest.class.getResourceAsStream(fileName)));

        StringBuilder content = new StringBuilder();
        String currentLine = reader.readLine();

        while(currentLine != null) {
            content.append(currentLine);
            content.append("\n");
            currentLine = reader.readLine();
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    @Test
    public void headersParsingTest_1(){
        String pgnInput = null;
        try {
            pgnInput = fileNameToString("/ParsingPgn_1.pgn");
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        ChessGame game = PgnParser.parse(pgnInput);
        assertEquals("F/S Return Match", game.getHeader().get("Event"));
        assertEquals("Belgrade, Serbia JUG", game.getHeader().get("Site"));
        assertEquals("1992.11.04", game.getHeader().get("Date"));
        assertEquals("29", game.getHeader().get("Round"));
        assertEquals("Fischer, Robert J.", game.getHeader().get("White"));
        assertEquals("Spassky, Boris V.", game.getHeader().get("Black"));
        assertEquals("1/2-1/2", game.getHeader().get("Result"));
    }

}
