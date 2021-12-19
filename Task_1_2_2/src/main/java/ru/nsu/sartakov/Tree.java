package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<E> implements Iterable<E> {

    private Tree<E> root;
    private E value;
    private List<Tree<E>> children;

    public Tree() {
        this.root = this;
        this.children = new ArrayList<>();
        this.value = null;
    }

    public Tree(E root) {
        this();
        this.value = root;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public E getValue() {
        return value;
    }

    public Tree<E> getRoot() {
        return root;
    }

    /**
     * @param element object to store in the tree
     * @return true if added, false otherwise
     */
    public void add(E element){
        this.children.add(new Tree<>(element));
        this.children.get(this.children.size() - 1).root = this;
    }

    /**
     * @param parent the leaf after that add new element
     * @param element object to store in the treee
     */
    public void add(Tree parent, E element) {
        this.children.get(this.children.indexOf(parent)).add(element);
    }

    public void erase() {
        this.children.forEach(Tree::erase);
        this.root = this;
        this.value = null;
    }

    /**
     * @param value to be deleted
     * @return whether succeed or not
     */
    public boolean remove(E value) {
        if (this.isEmpty()) {
            return false;
        }
        if (this.value.equals(value)) {
            this.children = null;
            this.value = null;
            return true;
        }
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i).remove(value)) {
                if (this.children.get(i).getValue() == null) {
                    this.children.remove(i);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @param element to check
     * @return true if element is in Tree
     */
    public boolean contains(E element) {
        if (this.value.equals(element)) {
            return true;
        }
        for (Tree<E> i : this.children) {
            if (i.contains(element)) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return true if Tree is empty
     */
    public boolean isEmpty() {
        return this.value == null && this.children.isEmpty();
    }

    public int size() {
        int size = 0;
        if (this.value != null) {
            size++;
        }
        if (this.children != null) {
            for (Tree<E> child : children) {
                size += child.size();
            }
        }
        return size;
    }


    private class Iter implements Iterator<E> {
        int actPos = 0;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public E next() {
            return null;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }
}