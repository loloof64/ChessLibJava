package com.loloof64.chess_lib_java.history;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ChessHistoryNodeTest {

    @Test
    public void rootHistoryNodeShouldNotHaveRelatedMove(){
        final Either<Exception, ChessHistoryNode> wrapNode1 = ChessHistoryNode.from(
                null,
                BoardCell.E2, BoardCell.E4);
        assertTrue(wrapNode1.isLeft());

        final Either<Exception, ChessHistoryNode> wrapNode2 = ChessHistoryNode.from(
                null,
                null, BoardCell.E4);
        assertTrue(wrapNode2.isLeft());

        final Either<Exception, ChessHistoryNode> wrapNode3 = ChessHistoryNode.from(
                null,
                BoardCell.E2, null);
        assertTrue(wrapNode3.isLeft());

        final Either<Exception, ChessHistoryNode> wrapNode4 = ChessHistoryNode.from(
                null,
                null, null);
        assertFalse(wrapNode4.isLeft());
    }

    @Test
    public void nonRootHistoryNodeMustHaveARelatedMove(){
        //TODO
    }

    @Test
    public void addingANodeToAnotherUpdatesFamilyLinksCorrectly(){
        final ChessHistoryNode rootNode1 = ChessHistoryNode.from(
                null, null, null
        ).right();
        final ChessHistoryNode childNode1 = ChessHistoryNode.from(
             rootNode1, BoardCell.E2, BoardCell.E4
        ).right();

        assertTrue(rootNode1.hasChild(childNode1));
    }

    @Test
    public void cannotAddANodeWithAGivenMoveTwice(){
        //TODO
    }

    @Test
    public void childPositionMoveIsLegalRelativelyToParentPosition(){
        //TODO
    }
}
