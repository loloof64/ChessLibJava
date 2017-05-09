package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardFile;
import com.loloof64.chess_lib_java.rules.coords.BoardRank;
import com.loloof64.chess_lib_java.rules.pieces.*;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Just;
import com.loloof64.functional.monad.Maybe;
import com.loloof64.functional.monad.Nothing;

/**
 * Immutable chess position.
 */
public class Position {

    /**
     * Constructor with a board and a game info.
     * @param board - Board - board part.
     * @param gameInfo - GameInfo - game info part.
     */
    public Position(Board board, GameInfo gameInfo){
        if (board == null) throw new IllegalArgumentException("Board can't be null !");
        if (gameInfo == null) throw new IllegalArgumentException("Game info can't be null !");

        this._board = board;
        this._info = gameInfo;
    }

    /**
     * Generates a position from a Forsyth-Edwards Notation.
     * @param fenStr - String - the FEN to convert.
     * @return Maybe of Position - the converted position wrapped in Just if success, Nothing otherwise.
     */
    public static Maybe<Position> fromFEN(String fenStr){
        Position resultingPosition = new Position(Board.fromFEN(fenStr), GameInfo.fromFEN(fenStr));

        if (!rightPiecesCountForPosition(resultingPosition)) return new Nothing<>();
        if (!rightCastleRightsRemainsInPosition(resultingPosition)) return new Nothing<>();

        return new Just<>(resultingPosition);
    }

    private static boolean rightCastleRightsRemainsInPosition(final Position resultingPosition){
        boolean thePlayerWhoHasNotTheTurnHasKingInChess = resultingPosition
                .kingIsInChess(!resultingPosition._info.whiteTurn);
        if (thePlayerWhoHasNotTheTurnHasKingInChess) return false;

        final boolean whiteHasLostRightToCastleBecauseOfKingPosition =
                !resultingPosition.findKingCell(true).fromJust().equals(BoardCell.E1);
        final boolean blackHasLostRightToCastleBecauseOfKingPosition =
                !resultingPosition.findKingCell(false).fromJust().equals(BoardCell.E8);
        if (whiteHasLostRightToCastleBecauseOfKingPosition &&
                (resultingPosition._info.castlesRights.whiteKingSide ||
                        resultingPosition._info.castlesRights.whiteQueenSide)) return false;
        if (blackHasLostRightToCastleBecauseOfKingPosition &&
                (resultingPosition._info.castlesRights.blackKingSide ||
                        resultingPosition._info.castlesRights.blackQueenSide)) return false;

        final Piece pieceOnH1 = resultingPosition._board.values()[BoardRank.RANK_1.ordinal()][BoardFile.FILE_H.ordinal()];
        final boolean whiteKingSideCastleLostBecauseOfMissingRook =
                pieceOnH1 == null || !pieceOnH1.equals(new Rook(true));
        if (whiteKingSideCastleLostBecauseOfMissingRook &&
                resultingPosition._info.castlesRights.whiteKingSide) return false;

        final Piece pieceOnA1 = resultingPosition._board.values()[BoardRank.RANK_1.ordinal()][BoardFile.FILE_A.ordinal()];
        final boolean whiteQueenSideCastleLostBecauseOfMissingRook =
                pieceOnA1 == null || !pieceOnA1.equals(new Rook(true));
        if (whiteQueenSideCastleLostBecauseOfMissingRook &&
                resultingPosition._info.castlesRights.whiteQueenSide) return false;

        final Piece pieceOnH8 = resultingPosition._board.values()[BoardRank.RANK_8.ordinal()][BoardFile.FILE_H.ordinal()];
        final boolean blackKingSideCastleLostBecauseOfMissingRook =
                pieceOnH8 == null || !pieceOnH8.equals(new Rook(false));
        if (blackKingSideCastleLostBecauseOfMissingRook &&
                resultingPosition._info.castlesRights.blackKingSide) return false;

        final Piece pieceOnA8 = resultingPosition._board.values()[BoardRank.RANK_8.ordinal()][BoardFile.FILE_A.ordinal()];
        final boolean blackQueenSideCastleLostBecauseOfMissingRook =
                pieceOnA8 == null || !pieceOnA8.equals(new Rook(false));
        return !blackQueenSideCastleLostBecauseOfMissingRook ||
                !resultingPosition._info.castlesRights.blackQueenSide;
    }

    private static boolean rightPiecesCountForPosition(final Position position){
        final int whiteKingCount = countPiece(position, new King(true));
        final int blackKingCount = countPiece(position, new King(false));
        if (whiteKingCount != 1 || blackKingCount != 1) return false;

        final int whitePawnsCount = countPiece(position, new Pawn(true));
        final int blackPawnsCount = countPiece(position, new Pawn(false));
        if (whitePawnsCount > 8 || blackPawnsCount > 8) return false;

        final int whiteKnightsCount = countPiece(position, new Knight(true));
        final int blackKnightsCount = countPiece(position, new Knight(false));
        final int whiteBishopsCount = countPiece(position, new Bishop(true));
        final int blackBishopsCount = countPiece(position, new Bishop(false));
        final int whiteRooksCount = countPiece(position, new Rook(true));
        final int blackRooksCount = countPiece(position, new Rook(false));
        if (whiteKnightsCount > 10 - whitePawnsCount || blackKnightsCount > 10 - blackPawnsCount ||
                whiteBishopsCount > 10 - whitePawnsCount || blackBishopsCount > 10 - blackPawnsCount ||
                whiteRooksCount > 10 - whitePawnsCount || blackRooksCount > 10 - blackPawnsCount) return false;

        final int whiteQueensCount = countPiece(position, new Queen(true));
        final int blackQueensCount = countPiece(position, new Queen(false));
        if (whiteQueensCount > 9 - whitePawnsCount || blackQueensCount > 9 - blackPawnsCount) return false;

        final int pawnsOnRank1Or8Count = countPawnsOnRank1Or8(position);
        return pawnsOnRank1Or8Count == 0;
    }

    private static int countPawnsOnRank1Or8(Position resultingPosition) {
        int count = 0;

        final int ranks[] = {BoardRank.RANK_1.ordinal(), BoardRank.RANK_8.ordinal()};
        for (int rankIndex : ranks){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = resultingPosition._board.values()[rankIndex][fileIndex];
                if (currentPiece instanceof Pawn) count++;
            }
        }

        return count;
    }

    /**
     * Simply count the given piece in the given position.
     * @param position - Position - position where we want to count.
     * @param pieceToCount - Piece - the piece we want to count.
     * @return int - the count.
     */
    private static int countPiece(Position position, Piece pieceToCount) {
        int count = 0;

        for (int rankIndex = 0; rankIndex < 8; rankIndex++){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = position._board.values()[rankIndex][fileIndex];
                if (currentPiece != null && currentPiece.equals(pieceToCount)) count++;
            }
        }

        return count;
    }

    /**
     * Gets the Forsyth-Edwards Notation of the position.
     * @return String - the converted FEN.
     */
    public String toFEN(){
        return _board.toFEN() + " " + _info.toFEN();
    }

    /**
     * Says if a move can be done on this position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the end cell
     * @return boolean - can we move as described ?
     */
    public boolean canMove(BoardCell from, BoardCell to){
        final Piece movingPiece = _board.values()[from.rank][from.file];
        final boolean noPieceAtStartCell = movingPiece == null;
        final boolean pieceIsNotOurs = movingPiece != null && movingPiece.isWhitePiece() != _info.whiteTurn;
        final boolean originSquareEqualsToTarget = from == to;

        if (noPieceAtStartCell || pieceIsNotOurs || originSquareEqualsToTarget) return false;
        return movingPiece.canMove(from, to,this);
    }

    /**
     * Gets the piece (can be null) at the given cell.
     * @param cell - BoardCell - the cell where we want to get the piece.
     * @return Piece - the piece in the given cell.
     */
    public Piece getPieceAt(BoardCell cell){
        return _board.values()[cell.rank][cell.file];
    }

    /**
     * Says if there is any piece between those two cells. Notice that if the two cells does not define
     * a straight line, the result will be false (no obstacle).
     * @param cell1 - BoardCell
     * @param cell2 - BoardCell
     * @return true if and only if there is an obstacle between the two cells (both not included, of course).
     */
    public boolean obstacleBetween(BoardCell cell1, BoardCell cell2){
        final int deltaX = cell2.file - cell1.file;
        final int deltaY = cell2.rank - cell1.rank;
        final int absDeltaX = Math.abs(deltaX);
        final int absDeltaY = Math.abs(deltaY);

        final boolean notAStraightLine = (absDeltaX > 0 && absDeltaY > 0) && (absDeltaX != absDeltaY);
        final boolean sameCell = absDeltaX == 0 && absDeltaY == 0;

        if (notAStraightLine || sameCell) return false;

        final int xSide = deltaX == 0 ? 0 : deltaX > 0 ? 1 : -1;
        final int ySide = deltaY == 0 ? 0 : deltaY > 0 ? 1 : -1;

        final int loopLimit = absDeltaX > 0 ? absDeltaX : absDeltaY;

        for (int i = 1; i < loopLimit; i++){ // Caution ! The start cell and target cell are not included !
            final int dxi = i * xSide;
            final int dyi = i * ySide;
            final BoardCell computedCell = new BoardCell(cell1.rank + dyi, cell1.file + dxi);

            if (_board.values()[computedCell.rank][computedCell.file] != null) return true;
        }

        return false;
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the target cell
     * @return Maybe of Position - Nothing on failure, otherwise Just of Position : wrapping the result.
     */
    public Maybe<Position> move(BoardCell from, BoardCell to){
        return move(from, to, Queen.class);
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * @param from - BoardCell - the start cell
     * @param to - BoardCell - the target cell
     * @param promotionPiece - Class of PromotablePiece - promotion piece if the move leads to pawn promotion.
     * @return Maybe of Position - Nothing on failure, otherwise Just of Position : wrapping the result.
     */
    public Maybe<Position> move(BoardCell from, BoardCell to, Class<? extends PromotablePiece> promotionPiece){
        final Piece movingPiece = _board.values()[from.rank][from.file];
        boolean noPieceAtStartCell = movingPiece == null;

        if (noPieceAtStartCell || !canMove(from, to)) return new Nothing<>();
        Maybe<Position> positionAfterMove = movingPiece.move(from, to, this, promotionPiece);
        if (positionAfterMove.isNothing()) return positionAfterMove;

        boolean theMovingSideHasLeftHisKingInChess = positionAfterMove.isJust() &&
                positionAfterMove.fromJust().kingIsInChess(_info.whiteTurn);
        if (theMovingSideHasLeftHisKingInChess) return new Nothing<>();
        else return positionAfterMove;
    }

    /**
     * Says if the king of the given side is in chess, whatever is the player turn.
     * @param whiteKing - boolean - true if we want to test if white king is attacked, false otherwise.
     * @return boolean - true if the king of the given side is in chess.
     */
    public boolean kingIsInChess(boolean whiteKing) {
        Maybe<BoardCell> wrapPlayerKingCell = findKingCell(whiteKing);
        if (wrapPlayerKingCell.isNothing()) throw new RuntimeException("Player has no king !");
        BoardCell playerKingCell = wrapPlayerKingCell.fromJust();

        return checkIfThisSquareUnderAttackByAnEnemyPiece(playerKingCell);
    }

    /**
     * Find the cell of the king of the given side.
     * @param whiteKing - boolean - true if we want to find white king, false otherwise.
     * @return BoardCell - the cell of the king of the player to move.
     */
    private Maybe<BoardCell> findKingCell(boolean whiteKing) {
        for (int rankIndex = 0; rankIndex < 8; rankIndex++){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = _board.values()[rankIndex][fileIndex];
                final boolean isSideKing = currentPiece != null && (currentPiece instanceof King) &&
                        currentPiece.isWhitePiece() == whiteKing;
                if (isSideKing) return new Just<>(new BoardCell(rankIndex, fileIndex));
            }
        }
        return new Nothing<>();
    }

    /**
     * Says if the given cell is under attack (by an enemy piece of the testedCell).
     * Caution ! Does not take into account the fact that the attacker may be pinned.
     * @param testedCell - BoardCell - cell to test.
     * @return boolean - true if cell is under attack, false otherwise.
     */
    private boolean checkIfThisSquareUnderAttackByAnEnemyPiece(BoardCell testedCell) {
        final Piece pieceAtTestedCell = _board.values()[testedCell.rank][testedCell.file];
        if (pieceAtTestedCell == null) return false;

        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
                final BoardCell currentCell = new BoardCell(rankIndex, fileIndex);
                final Piece currentPiece = _board.values()[rankIndex][fileIndex];
                boolean pieceAtCurrentCellIsEnemyPiece =
                        currentPiece != null && currentPiece.isWhitePiece() != pieceAtTestedCell.isWhitePiece();
                if (pieceAtTheFirstCellIsAttackingTheSecondCell(currentCell, testedCell) && pieceAtCurrentCellIsEnemyPiece) return true;
            }
        }
        return false;
    }

    /**
     * Says if the piece at the first cell is attacking piece at the second cell.
     * @param attackerCell - BoardCell - cell where we suppose there is an attacking piece.
     * @param testedCell - BoardCell - cell where we suppose to be attacked.
     * @return true if testedCell is attacked, false otherwise.
     */
    private boolean pieceAtTheFirstCellIsAttackingTheSecondCell(BoardCell attackerCell, BoardCell testedCell) {
        final Piece pieceAtAttackerCell = _board.values()[attackerCell.rank][attackerCell.file];
        return pieceAtAttackerCell != null && pieceAtAttackerCell.isAttackingCell(attackerCell, testedCell, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return _info.equals(position._info) && _board.equals(position._board);
    }

    @Override
    public int hashCode() {
        int result = _board.hashCode();
        result = 31 * result + _info.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "_board=" + _board +
                ", _info=" + _info +
                '}';
    }

    private final Board _board;
    public final GameInfo _info;
}
