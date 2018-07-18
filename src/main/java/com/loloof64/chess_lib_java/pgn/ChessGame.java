package com.loloof64.chess_lib_java.pgn;

import com.loloof64.chess_lib_java.history.ChessHistoryNode;

import java.util.Map;

public class ChessGame {

    private Map<String, String> header;
    private ChessHistoryNode gameRootNode;

    public ChessGame(Map<String, String> header, ChessHistoryNode gameRootNode) {
        this.header = header;
        this.gameRootNode = gameRootNode;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public ChessHistoryNode getGameRootNode() {
        return gameRootNode;
    }

    public void setGameRootNode(ChessHistoryNode gameRootNode) {
        this.gameRootNode = gameRootNode;
    }
}
