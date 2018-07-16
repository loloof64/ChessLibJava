package com.loloof64.chess_lib_java.history;

import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChessHistoryNodeTest {

    @Test
    public void nonRootNodeMustHaveARelatedMove(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.rootNode(Position.INITIAL, "", "").right();

        final Either<Exception, ChessHistoryNode> childNode1 = ChessHistoryNode.nonRootNode(rootNode1,
                null, "", "");
        assertTrue(childNode1.isLeft());


        final Either<Exception, ChessHistoryNode> childNode2 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertFalse(childNode2.isLeft());
    }

    @Test
    public void nonRootNodeIsAddedToTheGivenParentNode(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.rootNode(Position.INITIAL, "", "").right();

        final Either<Exception, ChessHistoryNode> childNode1 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertTrue(rootNode1.hasDirectChild(childNode1.right()));
    }

    @Test
    public void cannotAddANodeWithARelatedMoveAlreadyInDirectChildren(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.rootNode(Position.INITIAL, "", "").right();

        final Either<Exception, ChessHistoryNode> childNode1 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertFalse(childNode1.isLeft());

        final Either<Exception, ChessHistoryNode> childNode2 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertTrue(childNode2.isLeft());

        final Either<Exception, ChessHistoryNode> childNode3 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.D2, BoardCell.D4),
                "", "");
        assertFalse(childNode3.isLeft());

        final Either<Exception, ChessHistoryNode> childNode4 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.D2, BoardCell.D4),
                "", "");
        assertTrue(childNode4.isLeft());
    }

    @Test
    public void nonRootNodeMustProvideLegalMoveRelativelyToParentPosition(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.rootNode(Position.INITIAL, "", "").right();

        final Either<Exception, ChessHistoryNode> childNode1 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.A1, BoardCell.A8),
                "", "");
        assertTrue(childNode1.isLeft());

        final Either<Exception, ChessHistoryNode> childNode2 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertFalse(childNode2.isLeft());

        final Either<Exception, ChessHistoryNode> childNode21 = ChessHistoryNode.nonRootNode(childNode2.right(),
                new Move(BoardCell.A8, BoardCell.A1),
                "", "");
        assertTrue(childNode21.isLeft());

        final Either<Exception, ChessHistoryNode> childNode22 = ChessHistoryNode.nonRootNode(childNode2.right(),
                new Move(BoardCell.E7, BoardCell.E5),
                "", "");
        assertFalse(childNode22.isLeft());
    }

    @Test
    public void nonRootNodeAssociatedPositionHasBeenGeneratedCorrectlyFromItsRelatedMove(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.rootNode(Position.INITIAL, "", "").right();

        final Either<Exception, ChessHistoryNode> childNode1 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.E2, BoardCell.E4),
                "", "");
        assertEquals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1",
                childNode1.right().relatedPosition.toFEN());

        final Either<Exception, ChessHistoryNode> childNode2 = ChessHistoryNode.nonRootNode(rootNode1,
                new Move(BoardCell.G1, BoardCell.F3),
                "", "");
        assertEquals("rnbqkbnr/pppppppp/8/8/8/5N2/PPPPPPPP/RNBQKB1R b KQkq - 1 1",
                childNode2.right().relatedPosition.toFEN());


        final Either<Exception, ChessHistoryNode> childNode11 = ChessHistoryNode.nonRootNode(childNode2.right(),
                new Move(BoardCell.E7, BoardCell.E5),
                "", "");
        assertEquals("rnbqkbnr/pppp1ppp/8/4p3/8/5N2/PPPPPPPP/RNBQKB1R w KQkq e6 0 2",
                childNode11.right().relatedPosition.toFEN());
    }

}
