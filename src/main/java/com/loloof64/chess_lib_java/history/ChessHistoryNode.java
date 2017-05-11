package com.loloof64.chess_lib_java.history;

import com.loloof64.chess_lib_java.rules.coords.BoardCell;
import com.loloof64.functional.monad.Either;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Chess history node.
 */
public class ChessHistoryNode {

    private ChessHistoryNode(ChessHistoryNode parent,
                             BoardCell relatedMoveFrom, BoardCell relatedMoveTo,
                             ChessHistoryNode... childrenNodes){

        final boolean isRootNode = parent == null;
        if (isRootNode && (relatedMoveFrom != null || relatedMoveTo != null))
            throw new IllegalArgumentException("Both relatedMoveFrom and relatedMoveTo should be initialised !");

        this._parent = parent;
        this._relatedMoveFrom = relatedMoveFrom;
        this._relatedMoveTo = relatedMoveTo;
        this._childrenNodes = new ArrayList<>(Arrays.asList(childrenNodes));

        if (this._parent != null)  this._parent._childrenNodes.add(this);
    }

    /**
     * Builds a ChessHistoryNode
     * @param parent - ChessHistoryNode - null for a root node.
     * @param relatedMoveFrom - BoardCell - the related move start cell
     * @param relatedMoveTo - BoardCell - the related move target cell
     * @param childrenNodes - Ellipsis/Array of ChessHistoryNode - children nodes.
     * @return Either of Exception and ChessHistoryNode - Left of Exception if failure otherwise Right of ChessHistoryNode.
     */
    public static Either<Exception, ChessHistoryNode> from(ChessHistoryNode parent,
                                                           BoardCell relatedMoveFrom, BoardCell relatedMoveTo,
                                                           ChessHistoryNode... childrenNodes){
        try {
            return Either.right(new ChessHistoryNode(parent,
                    relatedMoveFrom, relatedMoveTo, childrenNodes));
        }
        catch (Exception e){
            return Either.left(e);
        }
    }

    public boolean hasChild(ChessHistoryNode child) {
        return _childrenNodes.contains(child);
    }

    public final ChessHistoryNode _parent;
    public final BoardCell _relatedMoveFrom;
    public final BoardCell _relatedMoveTo;
    private final ArrayList<ChessHistoryNode> _childrenNodes;
}
