package com.loloof64.chess_lib_java.rules.coords;

/**
 * Board file coordinate.
 */
public enum BoardFile {
    FILE_A("a"), FILE_B("b"), FILE_C("c"), FILE_D("d"),
    FILE_E("e"), FILE_F("f"), FILE_G("g"), FILE_H("h");
    private final String repr;
    BoardFile(String repr) {
        this.repr = repr;
    }

    @Override
    public String toString() {
        return repr;
    }
}
