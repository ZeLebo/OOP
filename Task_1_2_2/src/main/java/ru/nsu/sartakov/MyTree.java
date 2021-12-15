package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyTree<Node> implements Iterable<Node> {
    private Node root;
    private ArrayList<Node> children;

    MyTree(Node value) {
        this.root = value;
    }

    MyTree() {
        this.children = new ArrayList<Node>();
    }
    // todo make working
    public void add(Node parent, Node value) {
        Node temp = this.children.get(this.children.indexOf(parent));
    }

    /**
     * @param value object to store in the tree
     * @return true if added, false otherwise
     */
    public boolean add(Node value) {
        if (this.root == null) {
            this.root = value;
        } else {
            this.children.add(value);
        }
        return true;
    }

    /**
     * @param value to be deleted
     * @return whether succeed or not
     */
    public boolean remove(Node value) {
        this.children.stream()
                .filter(i -> !i.equals(value));
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
            return children.get(actPos);
        }
    }

    @Override
    public Iterator<Node> iterator() {
        return new Iter();
    }
}