package ru.nsu.sartakov.task_2_2_1.entities;

import java.util.ArrayList;
import java.util.List;

public class Snake implements Runnable {
    private List<Cell> body;
    private Cell head;
    private Cell tail;
    private int direction;
    private boolean isAlive;
    private int speed;
    private int score;
    private int length;

    public Snake(int x, int y, int speed) {
        this.speed = speed;
        this.isAlive = true;
        this.direction = 0;
        this.score = 0;
        this.length = 1;
        this.body = new ArrayList<>();
        this.head = new Cell(x, y);
        this.body.add(head);
    }

    public void move() {
        if (isAlive) {
            Cell newHead = new Cell(head.getX(), head.getY());
            switch (direction) {
                case 0 -> newHead.setY(head.getY() - 1);
                case 1 -> newHead.setX(head.getX() + 1);
                case 2 -> newHead.setY(head.getY() + 1);
                case 3 -> newHead.setX(head.getX() - 1);
            }
            body.add(0, newHead);
            body.remove(body.size() - 1);
            head = newHead;
        }
    }

    public Cell get(int x) {
        return getBody().get(x);
    }

    public void grow() {
        body.add(new Cell(-1, -1));
        length++;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Cell> getBody() {
        return body;
    }

    public Cell getHead() {
        return head;
    }

    public void setHead(Cell head) {
        this.head = head;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (isAlive) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
        }
    }
}