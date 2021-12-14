package ru.nsu.sartakov;

import java.util.Iterator;

public class MyTree<Node> implements Iterable<Node> {
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

    MyTree(int arity, Node value){
        children = new Node(arity, value);
    }

    /**
     * @param value object to store in the tree
     * @return true if added, false otherwise
     */
    public boolean add(T value) {
        return true;
    }

    /**
     * @param value to be deleted
     * @return whether succeed or not
     */
    public boolean remove(T value) {
        return true;
    }

    private class Iter implements Iterator<T> {
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
    public Iterator<T> iterator() {
        return new Iter();
    }
}