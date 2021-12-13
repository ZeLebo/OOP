package ru.nsu.sartakov;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyTree<T> implements Iterable<T> {
    // some thought about that lab
    public static class TreeNode {
        int val;
        List<TreeNode> children = new LinkedList<>();

        TreeNode(int data) {
            val = data;
        }

        TreeNode(int data, List<TreeNode> child) {
            val = data;
            children = child;
        }
    }
    /**
     * @param n the amount of leaves
     */
    private T[] treeArr;
    MyTree(int n){}

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
        public T next() {
            actPos++;
            return treeArr[actPos];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }
}