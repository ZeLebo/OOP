package ru.nsu.sartakov;

import java.util.*;

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

    /**
     * @return the value from the node
     */
    public E getValue() {
        return value;
    }

    /**
     *
     * @param element the value we are looking for in tree
     * @return a subtree with element
     * @throws NoSuchElementException if the value is not in the tree
     */
    public Tree<E> get(E element) throws NoSuchElementException {
        Queue<Tree<E>> inProcess = new ArrayDeque<>();
        inProcess.offer(this);
        while (! inProcess.isEmpty()) {
            Tree <E> tree = inProcess.poll();
            for (Tree<E> node : tree.children) {
                if (element.equals(node.root)) {
                    return node;
                }
                inProcess.offer(node);
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * @return true if Tree is empty
     */
    public boolean isEmpty() {
        return this.value == null && this.children.isEmpty();
    }

    /**
     *
     * @return the size of the tree
     */
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

    /**
     * @param element to check
     * @return true if element is in Tree
     */
    public boolean contains(E element) {
        if (element.equals(this.value)) {
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
     * @param element object to store in the tree
     */
    public void add(E element) {
        if (isEmpty()) {
            value = element;
        } else {
            children.add(new Tree<>(element));
        }
    }

    /**
     * Adds a new element as the child to parent element
     *
     * @param element - the element should be added.
     * @param parent  - the tree node to which the new element should be added.
     * @return true if succeed else - false
     */
    public boolean add(E parent, E element) {
        if (! contains(parent)) {
            return false;
        }
        Stack<Tree<E>> inProcess = new Stack<>();
        inProcess.push(this);
        while (!inProcess.isEmpty()) {
            Tree<E> tree = inProcess.pop();
            if (parent.equals(tree.value)) {
                Tree<E> subtree = new Tree<>();
                subtree.add(element);
                tree.children.add(subtree);
                return true;
            }
            for (Tree<E> child : tree.children) {
                inProcess.push(child);
            }
        }
        return false;
    }

    /**
     * @param value to be deleted
     * @return whether succeed or not
     */
    public boolean remove(E value) {
        if (this.isEmpty()) {
            return false;
        } else if (this.value == null) {
            return false;
        } else if (this.value.equals(value)) {
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
     * Delete the tree itself for not being around (Hello from the other side…)
     */
    public void erase() {
        this.children.forEach(Tree::erase);
        this.root = this;
        this.value = null;
    }

    @Override
    public Iterator<E> iterator() {
        return DFSIterator();
    }


    public Iterator<E> DFSIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Stack<Tree<E>> stack = new Stack<>();
            private final int size = size();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Tree<E> next;
                if (stack.isEmpty()) {
                    next = Tree.this;
                    visited.add(next);
                } else {
                    next = stack.pop();
                }
                for (Tree<E> child : next.children) {
                    if (!this.visited.contains(child)) {
                        this.stack.push(child);
                        this.visited.add(child);
                    }
                }
                count++;
                return next.value;
            }
        };
    }

    public Iterator<E> BFSIterator() {
        return new Iterator<>() {
            private final Set<Tree<E>> visited = new HashSet<>();
            private final Queue<Tree<E>> queue = new ArrayDeque<>();
            private final int size = size();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() throws NoSuchElementException, ConcurrentModificationException {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Tree<E> next;
                if (queue.isEmpty()) {
                    next = Tree.this;
                    visited.add(next);
                } else {
                    next = queue.poll();
                }
                for (Tree<E> child : next.children) {
                    if (!this.visited.contains(child)) {
                        this.queue.offer(child);
                        this.visited.add(child);
                    }
                }
                count++;
                return next.value;
            }
        };
    }
}