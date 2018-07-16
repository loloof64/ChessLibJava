package com.loloof64.chess_lib_java.rules;

import com.loloof64.chess_lib_java.rules.coords.BoardFile;
import com.loloof64.chess_lib_java.rules.coords.BoardRank;
import com.loloof64.chess_lib_java.rules.pieces.*;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;

/**
 * Immutable chess position.
 */
public class Position {

    /**
     * The very first position of any game.
     */
    public final static Position INITIAL = Position.fromFEN(
            "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1").right();

    /**
     * Constructor with a board and a game info.
     * @param board - {@link Board} - board part.
     * @param gameInfo - {@link GameInfo} - game info part.
     */
    public Position(Board board, GameInfo gameInfo){
        if (board == null) throw new IllegalArgumentException("Board can't be null !");
        if (gameInfo == null) throw new IllegalArgumentException("Game info can't be null !");

        this.board = board;
        this.info = gameInfo;
    }

    /**
     * Generates a position from a Forsyth-Edwards Notation.
     * @param fenStr - String - the FEN to convert.
     * @return Either of Exception and {@link Position} - the converted position wrapped in Right if success,
     * the exception wrapped in Left otherwise.
     */
    public static Either<Exception, Position> fromFEN(String fenStr){
        Position resultingPosition;
        try {
            resultingPosition = new Position(Board.fromFEN(fenStr), GameInfo.fromFEN(fenStr));
        } catch(Exception ex) {
            return Either.left(ex);
        }
        final Either<Exception, Void> piecesCountCheckReport = rightPiecesCountForPosition(resultingPosition);
        final Either<Exception, Void> rightRemainingCastlesCheckReport = rightCastleRightsRemainsInPosition(resultingPosition);

        if (piecesCountCheckReport.isLeft()) return Either.left(piecesCountCheckReport.left());
        if (rightRemainingCastlesCheckReport.isLeft()) return Either.left(rightRemainingCastlesCheckReport.left());

        return Either.right(resultingPosition);
    }

    /**
     * Checks that the position has the right remaining castles availabilities.
     * @param position - {@link Position} - the position to test.
     * @return Either of Exception and Void - exception wrapped in Left if checks is a failure, Right of Void otherwise.
     */
    private static Either<Exception, Void> rightCastleRightsRemainsInPosition(final Position position){
        Either<Exception, Boolean> thePlayerWhoHasNotTheTurnHasKingInChess = position.kingIsInChess(!position.info.whiteTurn);
        if (thePlayerWhoHasNotTheTurnHasKingInChess.isLeft()) return Either.left(thePlayerWhoHasNotTheTurnHasKingInChess.left());
        if (thePlayerWhoHasNotTheTurnHasKingInChess.right()) return Either.left(new RuntimeException("The player who has not the " +
                "turn has its king in chess ! Faulty position : "+position.toFEN()));

        final boolean whiteHasLostRightToCastleBecauseOfKingPosition =
                !position.findKingCell(true).right().equals(BoardCell.E1);
        final boolean blackHasLostRightToCastleBecauseOfKingPosition =
                !position.findKingCell(false).right().equals(BoardCell.E8);
        if (whiteHasLostRightToCastleBecauseOfKingPosition &&
                (position.info.castlesRights.whiteKingSide ||
                        position.info.castlesRights.whiteQueenSide)) return Either.left(new RuntimeException(
                                "White has lost right to castle because its king has moved but its castle(s) flag " +
                                "is/are still active. Faulty position : "+position.toFEN()
        ));
        if (blackHasLostRightToCastleBecauseOfKingPosition &&
                (position.info.castlesRights.blackKingSide ||
                        position.info.castlesRights.blackQueenSide)) return Either.left(new RuntimeException(
                "Black has lost right to castle because its king has moved but its castle(s) flag " +
                        "is/are still active. Faulty position : "+position.toFEN()
        ));

        final Piece pieceOnH1 = position.board.values()[BoardRank.RANK_1.ordinal()][BoardFile.FILE_H.ordinal()];
        final boolean whiteKingSideCastleLostBecauseOfMissingRook =
                pieceOnH1 == null || !pieceOnH1.equals(new Rook(true));
        if (whiteKingSideCastleLostBecauseOfMissingRook &&
                position.info.castlesRights.whiteKingSide) return Either.left(new RuntimeException(
                "White has lost right to castle because its king has moved but its kingSide castle flag " +
                        "is still active. Faulty position : "+position.toFEN()
        ));

        final Piece pieceOnA1 = position.board.values()[BoardRank.RANK_1.ordinal()][BoardFile.FILE_A.ordinal()];
        final boolean whiteQueenSideCastleLostBecauseOfMissingRook =
                pieceOnA1 == null || !pieceOnA1.equals(new Rook(true));
        if (whiteQueenSideCastleLostBecauseOfMissingRook &&
                position.info.castlesRights.whiteQueenSide) return Either.left(new RuntimeException(
                "White has lost right to castle because its king has moved but its queenSide castle flag " +
                        "is still active. Faulty position : "+position.toFEN()
        ));

        final Piece pieceOnH8 = position.board.values()[BoardRank.RANK_8.ordinal()][BoardFile.FILE_H.ordinal()];
        final boolean blackKingSideCastleLostBecauseOfMissingRook =
                pieceOnH8 == null || !pieceOnH8.equals(new Rook(false));
        if (blackKingSideCastleLostBecauseOfMissingRook &&
                position.info.castlesRights.blackKingSide) return Either.left(new RuntimeException(
                "Black has lost right to castle because its king has moved but its kingSide castle flag " +
                        "is still active. Faulty position : "+position.toFEN()
        ));

        final Piece pieceOnA8 = position.board.values()[BoardRank.RANK_8.ordinal()][BoardFile.FILE_A.ordinal()];
        final boolean blackQueenSideCastleLostBecauseOfMissingRook =
                pieceOnA8 == null || !pieceOnA8.equals(new Rook(false));
        if (blackQueenSideCastleLostBecauseOfMissingRook &&
                position.info.castlesRights.blackQueenSide) return Either.left(new RuntimeException(
                "Black has lost right to castle because its king has moved but its queenSide castle flag " +
                        "is still active. Faulty position : "+position.toFEN()
        ));

        return Either.right(null);
    }

    /**
     * Checks that the count of all pieces are right for the given position.
     * @param position - {@link Position} - the position to check.
     * @return Either of Exception and Void - wraps the error in Left if failure, Void in Right otherwise.
     */
    private static Either<Exception, Void> rightPiecesCountForPosition(final Position position){
        final int whiteKingCount = countPiece(position, new King(true));
        final int blackKingCount = countPiece(position, new King(false));
        if (whiteKingCount != 1 || blackKingCount != 1) return Either.left(new RuntimeException(
                "Each player must have exactly one king ! Faulty position : "+position.toFEN()
        ));

        final int whitePawnsCount = countPiece(position, new Pawn(true));
        final int blackPawnsCount = countPiece(position, new Pawn(false));
        if (whitePawnsCount > 8 || blackPawnsCount > 8) return Either.left(new RuntimeException(
                "Each player must have less than 8 pawns ! Faulty position : "+position.toFEN()
        ));

        final int whiteKnightsCount = countPiece(position, new Knight(true));
        final int blackKnightsCount = countPiece(position, new Knight(false));
        final int whiteBishopsCount = countPiece(position, new Bishop(true));
        final int blackBishopsCount = countPiece(position, new Bishop(false));
        final int whiteRooksCount = countPiece(position, new Rook(true));
        final int blackRooksCount = countPiece(position, new Rook(false));
        if (whiteKnightsCount > 10 - whitePawnsCount || blackKnightsCount > 10 - blackPawnsCount ||
                whiteBishopsCount > 10 - whitePawnsCount || blackBishopsCount > 10 - blackPawnsCount ||
                whiteRooksCount > 10 - whitePawnsCount || blackRooksCount > 10 - blackPawnsCount) return Either.left(new RuntimeException(
                "A player knights,bishops or rooks count cannot exceed (10-pawnsCount) ! Faulty position : "+position.toFEN()
        ));

        final int whiteQueensCount = countPiece(position, new Queen(true));
        final int blackQueensCount = countPiece(position, new Queen(false));
        if (whiteQueensCount > 9 - whitePawnsCount || blackQueensCount > 9 - blackPawnsCount) return Either.left(new RuntimeException(
                "A player queens cannot exceed (9-pawnsCount) ! Faulty position : "+position.toFEN()
        ));

        final int pawnsOnRank1Or8Count = countPawnsOnRank1Or8(position);
        if (pawnsOnRank1Or8Count > 0) return Either.left(new RuntimeException(
                "A player knights,bishops or rooks cannot exceed (10-pawnsCount) ! Faulty position : "+position.toFEN()
        ));

        return Either.right(null);
    }

    private static int countPawnsOnRank1Or8(Position resultingPosition) {
        int count = 0;

        final int ranks[] = {BoardRank.RANK_1.ordinal(), BoardRank.RANK_8.ordinal()};
        for (int rankIndex : ranks){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = resultingPosition.board.values()[rankIndex][fileIndex];
                if (currentPiece instanceof Pawn) count++;
            }
        }

        return count;
    }

    /**
     * Simply count the given piece in the given position.
     * @param position - {@link Position} - position where we want to count.
     * @param pieceToCount - {@link Piece} - the piece we want to count.
     * @return int - the count.
     */
    private static int countPiece(Position position, Piece pieceToCount) {
        int count = 0;

        for (int rankIndex = 0; rankIndex < 8; rankIndex++){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = position.board.values()[rankIndex][fileIndex];
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
        return board.toFEN() + " " + info.toFEN();
    }

    /**
     * Says if a move can be done on this position.
     * @param moveToDo - {@link Move} - the move to do
     * @return boolean - can we move as described ?
     */
    public boolean canMove(Move moveToDo){
        final BoardCell from = moveToDo.from();
        final BoardCell to = moveToDo.to();

        final Piece movingPiece = board.values()[from.rank][from.file];
        final boolean noPieceAtStartCell = movingPiece == null;
        final boolean pieceIsNotOurs = movingPiece != null && movingPiece.isWhitePiece() != info.whiteTurn;
        final boolean originSquareEqualsToTarget = from == to;

        if (noPieceAtStartCell || pieceIsNotOurs || originSquareEqualsToTarget) return false;
        return movingPiece.canMove(moveToDo, this);
    }

    /**
     * Gets the piece (can be null) at the given cell.
     * @param cell - {@link BoardCell} - the cell where we want to get the piece.
     * @return Piece - the piece in the given cell.
     */
    public Piece getPieceAt(BoardCell cell){
        return board.values()[cell.rank][cell.file];
    }

    /**
     * Says if there is any piece between those two cells. Notice that if the two cells does not define
     * a straight line, the result will be false (no obstacle).
     * @param cell1 - {@link BoardCell} - first bound (not included)
     * @param cell2 - {@link BoardCell} - second bound (not included)
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

            if (board.values()[computedCell.rank][computedCell.file] != null) return true;
        }

        return false;
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * If this move leads to promotion, the queen will be chose.
     * @param moveToDo - {@link Move} - the move to execute
     * @return Either of Exception and {@link Position} - Left of Exception on failure, otherwise Right of Position : wrapping the result.
     */
    public Either<Exception, Position> move(Move moveToDo){
        return move(moveToDo, Queen.class);
    }

    /**
     * Executes the given move on the position (without modifying it) and returns the resulting position.
     * @param moveToDo - {@link Move} - the move to execute
     * @param promotionPiece - Class of {@link PromotablePiece} - promotion piece if the move leads to pawn promotion.
     * @return Either of Exception and {@link Position} - Left of Exception on failure, otherwise Right of Position : wrapping the result.
     */
    public Either<Exception, Position> move(Move moveToDo, Class<? extends PromotablePiece> promotionPiece){
        final BoardCell from = moveToDo.from();
        final Piece movingPiece = board.values()[from.rank][from.file];
        boolean noPieceAtStartCell = movingPiece == null;

        if (noPieceAtStartCell) return Either.left(new RuntimeException(String.format(
                "No piece at move start cell (%s) ! Faulty position : %s", from, this.toFEN())));
        if (!canMove(moveToDo)) return Either.left(new IllegalArgumentException(String.format(
                "Illegal move %s ! Faulty position : %s", moveToDo, this.toFEN())));
        Either<Exception, Position> positionAfterMove = movingPiece.move(moveToDo, this, promotionPiece);
        if (positionAfterMove.isLeft()) return Either.left(positionAfterMove.left());

        Either<Exception, Boolean> theMovingSideHasLeftHisKingInChess = positionAfterMove.right().kingIsInChess(info.whiteTurn);
        if (theMovingSideHasLeftHisKingInChess.isLeft()) return Either.left(theMovingSideHasLeftHisKingInChess.left());
        if (theMovingSideHasLeftHisKingInChess.right()) return Either.left(new RuntimeException(String.format(
                "The moving side has left its king in chess (%s player) ! Faulty position : %s", info.whiteTurn ? "white" : "black", this.toFEN())));
        else return positionAfterMove;
    }

    /**
     * Says if the king of the given side is in chess, whatever is the player turn.
     * @param whiteKing - boolean - true if we want to test if white king is attacked, false otherwise.
     * @return Either of Exception and Boolean - Left of Exception if failure, Right of Boolean otherwise : the wrapped
     * result will say if the tested king is in chess.
     */
    public Either<Exception, Boolean> kingIsInChess(boolean whiteKing) {
        Either<Exception, BoardCell> wrapPlayerKingCell = findKingCell(whiteKing);
        if (wrapPlayerKingCell.isLeft()) return Either.left(new RuntimeException("Player has no king !"));
        BoardCell playerKingCell = wrapPlayerKingCell.right();

        return checkIfThisSquareUnderAttackByAnEnemyPiece(playerKingCell);
    }

    /**
     * Find the cell of the king of the given side.
     * @param whiteKing - boolean - true if we want to find white king, false otherwise.
     * @return Either of Exception and BoardCell - if failure, the exception wrapped in Left, otherwise
     * the cell of the king of the player to move wrapped in Right.
     */
    private Either<Exception, BoardCell> findKingCell(boolean whiteKing) {
        for (int rankIndex = 0; rankIndex < 8; rankIndex++){
            for (int fileIndex = 0; fileIndex < 8; fileIndex++){
                final Piece currentPiece = board.values()[rankIndex][fileIndex];
                final boolean isSideKing = currentPiece != null && (currentPiece instanceof King) &&
                        currentPiece.isWhitePiece() == whiteKing;
                if (isSideKing) return Either.right(new BoardCell(rankIndex, fileIndex));
            }
        }
        return Either.left(new RuntimeException(String.format("Could not find king cell for %s player ! Faulty position : %s",
                whiteKing ? "white" : "black", this.toFEN())));
    }

    /**
     * Says if the given cell is under attack (by an enemy piece of the testedCell).
     * Caution ! Does not take into account the fact that the attacker may be pinned.
     * @param testedCell - {@link BoardCell} - cell to test.
     * @return Either of Exception and Boolean - Left of Exception if failure, Right of
     * Boolean if success (true if cell is under attack, false otherwise).
     */
    private Either<Exception, Boolean> checkIfThisSquareUnderAttackByAnEnemyPiece(BoardCell testedCell) {
        final Piece pieceAtTestedCell = board.values()[testedCell.rank][testedCell.file];
        if (pieceAtTestedCell == null) return Either.left(new IllegalArgumentException(String.format(
                "No piece at tested cell (%s) ! Faulty position : %s", testedCell, this.toFEN())));

        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
                final BoardCell currentCell = new BoardCell(rankIndex, fileIndex);
                final Piece currentPiece = board.values()[rankIndex][fileIndex];
                boolean pieceAtCurrentCellIsEnemyPiece =
                        currentPiece != null && currentPiece.isWhitePiece() != pieceAtTestedCell.isWhitePiece();
                if (pieceAtTheFirstCellIsAttackingTheSecondCell(currentCell, testedCell) && pieceAtCurrentCellIsEnemyPiece) return Either.right(true);
            }
        }
        return Either.right(false);
    }

    /**
     * Says if the piece at the first cell is attacking piece at the second cell.
     * @param attackerCell - {@link BoardCell} - cell where we suppose there is an attacking piece.
     * @param testedCell - {@link BoardCell} - cell where we suppose to be attacked.
     * @return true if testedCell is attacked, false otherwise.
     */
    private boolean pieceAtTheFirstCellIsAttackingTheSecondCell(BoardCell attackerCell, BoardCell testedCell) {
        final Piece pieceAtAttackerCell = board.values()[attackerCell.rank][attackerCell.file];
        return pieceAtAttackerCell != null && pieceAtAttackerCell.isAttackingCell(attackerCell, testedCell, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return info.equals(position.info) && board.equals(position.board);
    }

    @Override
    public int hashCode() {
        int result = board.hashCode();
        result = 31 * result + info.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "board=" + board +
                ", info=" + info +
                '}';
    }

    public final Board board;
    public final GameInfo info;
}
