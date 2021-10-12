package ru.nsu.sartakov;

import java.util.Stack;


public class Main {
    public static void main(String[] args) {
        Stack x = new Stack();

        x.push(1);
        x.push(2);
        x.push(3);
        System.out.println(x);


        MyStack y = new MyStack();
        y.push(1);
        y.push(2);
        y.push(3);
        System.out.println(y);
        for (int i = 0; i <= y.count() + 1; ++i) {
            System.out.print(y.pop() + " ");
        }

        MyStack testStack = new MyStack();
        MyStack<Integer> stack = new MyStack<>();
        stack.push(12);
        stack.push(223);
        stack.push(3334);
        testStack.push(stack);
        System.out.println("\n" + testStack);


    }
}
