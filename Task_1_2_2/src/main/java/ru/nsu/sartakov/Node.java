package ru.nsu.sartakov;

import java.util.ArrayList;

class Node {
    int data;
    ArrayList<Node> children;

    Node (int data) {
        this.data = data;
        children = new ArrayList<>();
    }
}
