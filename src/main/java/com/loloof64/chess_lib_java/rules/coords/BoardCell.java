package com.loloof64.chess_lib_java.rules.coords;

/**
 * Board cell coordinate
 */
public class BoardCell {
    public static BoardCell A1 = new BoardCell(0,0);
    public static BoardCell A2 = new BoardCell(1,0);
    public static BoardCell A3 = new BoardCell(2,0);
    public static BoardCell A4 = new BoardCell(3,0);
    public static BoardCell A5 = new BoardCell(4,0);
    public static BoardCell A6 = new BoardCell(5,0);
    public static BoardCell A7 = new BoardCell(6,0);
    public static BoardCell A8 = new BoardCell(7,0);

    public static BoardCell B1 = new BoardCell(0,1);
    public static BoardCell B2 = new BoardCell(1,1);
    public static BoardCell B3 = new BoardCell(2,1);
    public static BoardCell B4 = new BoardCell(3,1);
    public static BoardCell B5 = new BoardCell(4,1);
    public static BoardCell B6 = new BoardCell(5,1);
    public static BoardCell B7 = new BoardCell(6,1);
    public static BoardCell B8 = new BoardCell(7,1);

    public static BoardCell C1 = new BoardCell(0,2);
    public static BoardCell C2 = new BoardCell(1,2);
    public static BoardCell C3 = new BoardCell(2,2);
    public static BoardCell C4 = new BoardCell(3,2);
    public static BoardCell C5 = new BoardCell(4,2);
    public static BoardCell C6 = new BoardCell(5,2);
    public static BoardCell C7 = new BoardCell(6,2);
    public static BoardCell C8 = new BoardCell(7,2);

    public static BoardCell D1 = new BoardCell(0,3);
    public static BoardCell D2 = new BoardCell(1,3);
    public static BoardCell D3 = new BoardCell(2,3);
    public static BoardCell D4 = new BoardCell(3,3);
    public static BoardCell D5 = new BoardCell(4,3);
    public static BoardCell D6 = new BoardCell(5,3);
    public static BoardCell D7 = new BoardCell(6,3);
    public static BoardCell D8 = new BoardCell(7,3);

    public static BoardCell E1 = new BoardCell(0,4);
    public static BoardCell E2 = new BoardCell(1,4);
    public static BoardCell E3 = new BoardCell(2,4);
    public static BoardCell E4 = new BoardCell(3,4);
    public static BoardCell E5 = new BoardCell(4,4);
    public static BoardCell E6 = new BoardCell(5,4);
    public static BoardCell E7 = new BoardCell(6,4);
    public static BoardCell E8 = new BoardCell(7,4);

    public static BoardCell F1 = new BoardCell(0,5);
    public static BoardCell F2 = new BoardCell(1,5);
    public static BoardCell F3 = new BoardCell(2,5);
    public static BoardCell F4 = new BoardCell(3,5);
    public static BoardCell F5 = new BoardCell(4,5);
    public static BoardCell F6 = new BoardCell(5,5);
    public static BoardCell F7 = new BoardCell(6,5);
    public static BoardCell F8 = new BoardCell(7,5);

    public static BoardCell G1 = new BoardCell(0,6);
    public static BoardCell G2 = new BoardCell(1,6);
    public static BoardCell G3 = new BoardCell(2,6);
    public static BoardCell G4 = new BoardCell(3,6);
    public static BoardCell G5 = new BoardCell(4,6);
    public static BoardCell G6 = new BoardCell(5,6);
    public static BoardCell G7 = new BoardCell(6,6);
    public static BoardCell G8 = new BoardCell(7,6);

    public static BoardCell H1 = new BoardCell(0,7);
    public static BoardCell H2 = new BoardCell(1,7);
    public static BoardCell H3 = new BoardCell(2,7);
    public static BoardCell H4 = new BoardCell(3,7);
    public static BoardCell H5 = new BoardCell(4,7);
    public static BoardCell H6 = new BoardCell(5,7);
    public static BoardCell H7 = new BoardCell(6,7);
    public static BoardCell H8 = new BoardCell(7,7);

    public BoardCell(int rank, int file){
        this.rank = rank;
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardCell boardCell = (BoardCell) o;

        if (rank != boardCell.rank) return false;
        return file == boardCell.file;
    }

    @Override
    public int hashCode() {
        int result = rank;
        result = 31 * result + file;
        return result;
    }

    @Override
    public String toString() {
        return "BoardCell{" +
                "file=" + (char) ('A' + file) +
                ", rank=" + (char) ('1' + rank) +
                '}';
    }

    public final int rank;
    public final int file;
}
