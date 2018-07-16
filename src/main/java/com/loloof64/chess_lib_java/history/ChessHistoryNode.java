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
                             Move relatedMove, String commentBefore,
                             String commentAfter){

        boolean isRootNode = parent == null;

        if (!isRootNode && relatedMove == null) throw new IllegalArgumentException("Non root node (parent == null)" +
                " must provide a related move !");
        if (parent != null && parent.hasAlreadyThisRelatedMoveInDirectChildren(relatedMove))
            throw new IllegalArgumentException(String.format("The related move (%s) is already " +
                    "present in the parent children !", relatedMove));

        this.parent = parent;
        this.relatedPosition = relatedPosition;
        this.relatedMove = relatedMove;
        this._commentBefore = commentBefore != null ? commentBefore : "";
        this._commentAfter = commentAfter != null ? commentAfter : "";
        this._childrenNodes = new ArrayList<>();

        if (this.parent != null) this.parent._childrenNodes.add(this);
    }

    /**
     * Builds a root {@link ChessHistoryNode}
     * @param relatedPosition - {@link Position} - related position.
     * @param commentBefore - String - comment before the move : will be empty if null is passed.
     * @param commentAfter - String - comment after the move : will be empty if null is passed.
     * @return Either of Exception and {@link ChessHistoryNode} - Left of Exception if failure otherwise Right of {@link ChessHistoryNode}.
     */
    public static Either<Exception, ChessHistoryNode> rootNode(Position relatedPosition,
                                                           String commentBefore,
                                                           String commentAfter){
        try {
            return Either.right(new ChessHistoryNode(null, relatedPosition,
                    null, commentBefore, commentAfter));
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
     * @param commentBefore - String - comment before the move : will be empty if null is passed.
     * @param commentAfter - String - comment before the move : will be empty if null is passed.
     * @return Either of Exception and ChessHistoryNode - Left of Exception if failure otherwise Right of {@link ChessHistoryNode}.
     */
    public static Either<Exception, ChessHistoryNode> nonRootNode(ChessHistoryNode parent,
                                                                  Move relatedMove,
                                                                  String commentBefore,
                                                                  String commentAfter){
        try {
            if (parent == null) throw new IllegalArgumentException("You must provide a parent node in order to build " +
                    "a non root node !");
            final Either<Exception, Position> relatedPosition = computePosition(parent, relatedMove);
            if (relatedPosition.isLeft()) throw relatedPosition.left();
            return Either.right(new ChessHistoryNode(parent, relatedPosition.right(),
                    relatedMove, commentBefore, commentAfter));
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

    /**
     * Get related comment after move : won't be null.
     * @return String - the associated comment.
     */
    public String commentAfter(){
        return _commentAfter;
    }

    /**
     * Get related comment before move : won't be null.
     * @return String - the associated comment.
     */
    public String commentBefore(){
        return _commentBefore;
    }

    /**
     * Sets associated comment before the move : will be empty if null is passed.
     * @param comment - String - the comment, can be null.
     */
    public void setCommentBefore(String comment){
        this._commentBefore = comment != null ? comment : "";
    }

    /**
     * Sets associated comment after the move : will be empty if null is passed.
     * @param comment - String - the comment, can be null.
     */
    public void setCommentAfter(String comment){
        this._commentAfter = comment != null ? comment : "";
    }

    /**
     * Simply clears the associated comment before the move.
     */
    public void removeCommentBefore(){
        this._commentBefore = "";
    }


    /**
     * Simply clears the associated comment after the move.
     */
    public void removeCommentAfter(){
        this._commentAfter = "";
    }

    /**
     *
     * @return ChessHistoryNode or null
     */
    public ChessHistoryNode getMainLineChildIfAny(){
        if (this._childrenNodes.size() < 1) return null;
        return this._childrenNodes.get(0);
    }

    public int getVariationsChildrenCount() {
        return this._childrenNodes.size() - 1;
    }

    /**
     * Gets the variation whose index is given, if it exists.
     * E.g if given index is 2, will try to get the child of index 3, because
     * of the existence of the main line.
     * @param index - int
     * @return ChessHistoryNode or null
     */
    public ChessHistoryNode getVariationChildIfExists(int index) {
        if (this._childrenNodes.size() < index+1) return null;
        return this._childrenNodes.get(index+1);
    }

    public Position getRelatedPosition() {
        return relatedPosition;
    }

    private boolean hasAlreadyThisRelatedMoveInDirectChildren(Move move){
        for (ChessHistoryNode currentChild : _childrenNodes){
            if (currentChild.relatedMove.equals(move)) return true;
        }
        return false;
    }

    private static Either<Exception, Position> computePosition(ChessHistoryNode parent,
                                                               Move relatedMove) {
        return parent.relatedPosition.move(relatedMove);
    }

    public final ChessHistoryNode parent;
    public final Move relatedMove;
    public final Position relatedPosition;
    private String _commentBefore, _commentAfter;
    private ArrayList<ChessHistoryNode> _childrenNodes;
}
