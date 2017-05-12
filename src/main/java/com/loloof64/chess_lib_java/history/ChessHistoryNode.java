package com.loloof64.chess_lib_java.history;

import com.loloof64.chess_lib_java.rules.Move;
import com.loloof64.chess_lib_java.rules.Position;
import com.loloof64.functional.monad.Either;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Chess history node.
 */
public class ChessHistoryNode {

    private ChessHistoryNode(ChessHistoryNode parent, Position relatedPosition,
                             Move relatedMove,
                             ChessHistoryNode... childrenNodes){

        boolean isRootNode = parent == null;

        if (!isRootNode && relatedMove == null) throw new IllegalArgumentException("Non root node (parent == null)" +
                " must provide a related move !");
        if (parent != null && parent.hasAlreadyThisRelatedMoveInDirectChildren(relatedMove))
            throw new IllegalArgumentException(String.format("The related move (%s) is already " +
                    "present in the parent children !", relatedMove));

        this._parent = parent;
        this._relatedPosition = relatedPosition;
        this._relatedMove = relatedMove;
        this._childrenNodes = new ArrayList<>(Arrays.asList(childrenNodes));

        if (this._parent != null) this._parent._childrenNodes.add(this);
    }

    /**
     * Builds a root {@link ChessHistoryNode}
     * @param relatedPosition - {@link Position} - related position.
     * @param childrenNodes - Ellipsis/Array of {@link ChessHistoryNode}- children nodes.
     * @return Either of Exception and {@link ChessHistoryNode} - Left of Exception if failure otherwise Right of {@link ChessHistoryNode}.
     */
    public static Either<Exception, ChessHistoryNode> rootNode(Position relatedPosition,
                                                           ChessHistoryNode... childrenNodes){
        try {
            return Either.right(new ChessHistoryNode(null, relatedPosition,
                    null, childrenNodes));
        }
        catch (Exception e){
            return Either.left(e);
        }
    }

    /**
     * Builds a non root {@link ChessHistoryNode}
     * @param parent - {@link ChessHistoryNode} - null for a root node.
     * @param relatedMove - {@link Move} - the related move (the move which lead to this position, null for root node).
     * First value is the start cell, and the second value is the target cell.
     * @param childrenNodes - Ellipsis/Array of {@link ChessHistoryNode}- children nodes.
     * @return Either of Exception and ChessHistoryNode - Left of Exception if failure otherwise Right of {@link ChessHistoryNode}.
     */
    public static Either<Exception, ChessHistoryNode> nonRootNode(ChessHistoryNode parent,
                                                                  Move relatedMove,
                                                                  ChessHistoryNode... childrenNodes){
        try {
            if (parent == null) throw new IllegalArgumentException("You must provide a parent node in order to build " +
                    "a non root node !");
            final Either<Exception, Position> relatedPosition = computePosition(parent, relatedMove);
            if (relatedPosition.isLeft()) throw relatedPosition.left();
            return Either.right(new ChessHistoryNode(parent, relatedPosition.right(),
                    relatedMove, childrenNodes));
        }
        catch (Exception e){
            return Either.left(e);
        }
    }


    /**
     * Says if the given node is a direct child of this node.
     * @param nodeToTest - {@link ChessHistoryNode} - the node to test.
     * @return boolean - true if the node is a direct child of this node, false otherwise.
     */
    public boolean hasDirectChild(ChessHistoryNode nodeToTest){
        return _childrenNodes.contains(nodeToTest);
    }

    /**
     * Promotes a child node so that it is placed first. Cancel process if the given child is not a direct child,
     * or if the given child is already the first child.
     * @param childToPromote - {@link ChessHistoryNode} - the node to promote.
     * @return boolean - true if operation successful, false otherwise.
     */
    public boolean promoteVariation(ChessHistoryNode childToPromote){
        if (!_childrenNodes.contains(childToPromote)) return false;

        final int childToPromoteIndex = _childrenNodes.indexOf(childToPromote);
        if (childToPromoteIndex == 0) return false;

        ChessHistoryNode oldMainLine = _childrenNodes.get(0);
        _childrenNodes.set(childToPromoteIndex, oldMainLine);
        _childrenNodes.set(0, childToPromote);

        return true;
    }

    /**
     * Deletes a child node. Cancel process if the given child is not a direct child.
     * @param childToRemove - {@link ChessHistoryNode} - the node to promote.
     * @return boolean - true if operation successful, false otherwise.
     */
    public boolean deleteVariation(ChessHistoryNode childToRemove){
        return _childrenNodes.remove(childToRemove);
    }

    private boolean hasAlreadyThisRelatedMoveInDirectChildren(Move move){
        for (ChessHistoryNode currentChild : _childrenNodes){
            if (currentChild._relatedMove.equals(move)) return true;
        }
        return false;
    }

    private static Either<Exception, Position> computePosition(ChessHistoryNode parent,
                                                               Move relatedMove) {
        return parent._relatedPosition.move(relatedMove);
    }

    public final ChessHistoryNode _parent;
    public final Move _relatedMove;
    public final Position _relatedPosition;
    private final ArrayList<ChessHistoryNode> _childrenNodes;
}
