package com.loloof64.chess_lib_java.pgn;

import com.loloof64.chess_lib_java.history.ChessHistoryNode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PgnParser {

    public static ChessGame parsePgnString(String pgnContent) throws IOException {
        String fileContent = streamToString(PgnParser.class.getResourceAsStream(pgnContent));
        return new ChessGame(headerFromPgnString(fileContent), historyFromPgnString(fileContent));
    }

    public static ChessGame parsePgnFromFileName(String filePath) throws IOException {
        String pgnString =  streamToString(PgnParser.class.getResourceAsStream(filePath));
        return new ChessGame(headerFromPgnString(pgnString), historyFromPgnString(pgnString));
    }

    private static String streamToString(InputStream fileStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

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

    private static Map<String, String> headerFromPgnString(String pgnString) {
        Map<String, String> returnValue = new HashMap<>();

        final String headerRegex = "\\[(\\p{Alpha}+)\\s+\"([^\"]*)\"\\]";
        final Pattern headerPattern = Pattern.compile(headerRegex);
        final Matcher headerMatcher = headerPattern.matcher(pgnString);

        while(headerMatcher.find()){
            String tag = headerMatcher.group(1);
            String value = headerMatcher.group(2);

            returnValue.put(tag, value);
        }

        return returnValue;
    }

    private static ChessHistoryNode historyFromPgnString(String pgn) {
        ChessHistoryNode rootNode = null;



        return rootNode;
    }

}
