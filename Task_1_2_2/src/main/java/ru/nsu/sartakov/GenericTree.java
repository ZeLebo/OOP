package ru.nsu.sartakov;

import java.util.ArrayList;
import java.util.Scanner;

public class GenericTree {
    /**
     * This is with user's input, just screwing around, need to be deleted
     */
    private Node root;

    GenericTree() {
        Scanner s = new Scanner(System.in);
        this.root = constructGT(s, null, 0);
    }

    private Node constructGT(Scanner s, Node parent, int child) {
        if (parent == null) {
            System.out.println("Enter the data for the root node");
        } else {
            System.out.println("enter the data for the " + child + "of the tree");
        }

        int data = s.nextInt();
        Node node = new Node(data);

        System.out.println("Enter the number of children for " + node.data);

        int n = s.nextInt();
        for (int i = 0; i < n; i++) {
            Node newSubTree = constructGT(s, node, child);
            node.children.add(newSubTree);
        }
        return node;
    }
}
