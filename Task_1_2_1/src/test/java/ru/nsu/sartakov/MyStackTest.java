package ru.nsu.sartakov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    MyStack<Object> testStack;

    @BeforeEach
    void stack_init() {
        testStack = new MyStack<>();
    }

    @Test
    void pushEmpty() {
        MyStack<Object> stack = new MyStack<>();
        testStack.pushStack(stack);

        Assertions.assertTrue(testStack.isEmpty());
    }

    @Test
    void popStack_empty() {
        MyStack<Object> stack = testStack.popStack(0);
        Assertions.assertEquals(0, stack.count());
    }

    @Test
    void popAndPopInteger() {
        testStack.push(145);
        testStack.push(0);
        testStack.push(-457643);
        testStack.push(2);
        testStack.push(-23324);
        testStack.push(23948023);

        Assertions.assertEquals(23948023, testStack.pop());
        Assertions.assertEquals(-23324, testStack.pop());
        Assertions.assertEquals(2, testStack.pop());
        Assertions.assertEquals(-457643, testStack.pop());
        Assertions.assertEquals(0, testStack.pop());
        Assertions.assertEquals(145, testStack.pop());
    }


    @Test
    void popNoSuchElem() {
        testStack.push(1);
        testStack.push(2);
        testStack.pop();
        testStack.pop();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            testStack.pop();
        });
    }

    @Test
    void popStackException() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            testStack.popStack(-1);
        });
    }


    @Test
    void countBigInteger() {
        final int BigNum = 1000000;
        Integer[] arr = new Integer[BigNum];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 5;
            testStack.push(arr[i]);
            assertEquals(i + 1, testStack.count());
        }
    }

    @Test
    void pushStackInteger() {
        MyStack<Object> stack = new MyStack<>();
        stack.push(12);
        stack.push(223);
        stack.push(3334);
        testStack.pushStack(stack);

        assertEquals(3334, testStack.pop());
        assertEquals(223, testStack.pop());
        assertEquals(12, testStack.pop());

    }

    @Test
    void popStackInteger() {
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);

        MyStack<Object> stack = testStack.popStack(3);

        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertEquals(0, testStack.count());
    }

    class StrangeClass {};
    @Test
    void pushStrangeCustomType() {
        MyStack<StrangeClass> stack = new MyStack<>();
        MyStack<StrangeClass> stack2 = new MyStack<>();
        StrangeClass strangeClass = new StrangeClass();

        stack2.push(strangeClass);

        stack.pushStack( stack2 );

        Assertions.assertEquals(strangeClass, stack.pop());
    }

    @Test
    void popStangeCustomType() {
        MyStack<StrangeClass> stack1 = new MyStack<>();
        StrangeClass customClass = new StrangeClass();
        stack1.push(customClass);

        MyStack<StrangeClass> stack2 = stack1.popStack(1);

        assertEquals(customClass, stack2.pop());
    }

    @Test
    void foreach_integer() {
        Integer[] expected = {12332, 0, -4786, 2};

        testStack.push(12332);
        testStack.push(0);
        testStack.push(-4786);
        testStack.push(2);

        List<Object> check = new ArrayList<>();

        for (Object x : testStack) {
            check.add(x);
        }
        Assertions.assertArrayEquals(expected, check.toArray());
    }
}
