package ru.nsu.sartakov;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyStack <T> implements Iterable<T> {

    private static final int CAP = 4;
    private static final int MULT = 2;

    private int count = 0;
    private T[] stackArr;


    public MyStack() {
        stackArr = (T[]) new Object[CAP];
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    private void resize(int toSize) {
        while(stackArr.length < toSize) {
            stackArr = Arrays.copyOf(stackArr, stackArr.length * MULT);
        }
    }

    public void push (T elem) {
        resize(count + 1);
        stackArr[count] = elem;
        count++;
    }

    public T pop() {
        if ( count < 1 ) {
            throw new NoSuchElementException();
        }
        count--;
        return stackArr[count];
    }

    public void pushStack(MyStack<T> stack) {
        this.resize(this.count + stack.count);
        System.arraycopy(stack.stackArr, 0, this.stackArr, this.count, stack.count);
        this.count += stack.count;
    }

    public MyStack<T> popStack (int size) {
        if ( size < 0 || size > count ) {
            throw new NoSuchElementException();
        }
        MyStack <T> stack = new MyStack<>();
        stack.resize(size);
        System.arraycopy(this.stackArr, 0, stack.stackArr, 0, size);
        stack.count = size;
        this.count -= size;
        return stack;
    }

    // for making foreach working

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T> {
        int actPos = 0;

        @Override
        public boolean hasNext() {
            return actPos < count;
        }

        @Override
        public T next() {
            actPos++;
            return stackArr[actPos - 1];
        }
    }
}
