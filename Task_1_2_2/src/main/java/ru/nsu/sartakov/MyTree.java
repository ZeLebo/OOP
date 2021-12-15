package ru.nsu.sartakov;

import java.util.Arrays;
import java.util.Iterator;

public class MyTree<Node> implements Iterable<Node> {
    /**
     * This class is gonna be the answer, but not now
     * ArrayList needed to be there, not array
     * Functions are just drafts, not the version for passing
     */

    /**
     * Node is something random type (T)
     * Tree is consists of Nodes
     * [n]treeArr = [0, 1, 2, 3, 4, 5, 6, ..., n - 1]
     * if treeArr[i] is None and i < n:
     *      treeArr[i] = new Node(int n)
     * elif treeArr[i] is not None:
     *
     */

    /**
     * @param n the amount of leaves
     */
    private Node[] children;
    private int actposs = 0;

    public Node[] getChildren() {
        return children;
    }

    MyTree(Node value) {
        this.children[actposs++] = value;
    }

    /**
     * @param value object to store in the tree
     * @return true if added, false otherwise
     */
    public boolean add(Node value) {
        this.children[actposs++] = value;
        return true;
    }

    /**
     * @param value to be deleted
     * @return whether succeed or not
     */
    public boolean remove(Node value) {
        for (int i = 0; i < this.children.length; i++) {
            if (this.children[i] == value) {
                this.children[i] = null;
                return true;
            }
        }
        return false;
    }

    private class Iter implements Iterator<Node> {
        int actPos = 0;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Node next() {
            actPos++;
            return children[actPos];
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iter();
    }
}