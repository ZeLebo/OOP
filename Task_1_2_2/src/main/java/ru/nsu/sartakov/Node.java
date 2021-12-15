package ru.nsu.sartakov;

import java.util.ArrayList;

class Node {
    Object data;
    public ArrayList<Node> children;

    Node (Object data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
}
