package com.loloof64.chess_lib_java.rules.coords;

/**
 * Board rank coordinate..
 */
public enum BoardRank {
    RANK_1("1"), RANK_2("2"), RANK_3("3"), RANK_4("4"),
    RANK_5("5"), RANK_6("6"), RANK_7("7"), RANK_8("8");

    private final String repr;

    BoardRank(String repr) {
        this.repr = repr;
    }

    @Override
    public String toString() {
        return repr;
    }
}
