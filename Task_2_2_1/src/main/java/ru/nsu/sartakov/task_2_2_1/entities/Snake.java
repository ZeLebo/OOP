package ru.nsu.sartakov.task_2_2_1.entities;

import java.util.ArrayList;
import java.util.List;
import static ru.nsu.sartakov.task_2_2_1.entities.Direction.*;

public class Snake implements Runnable {
    private final List<Cell> body;
    private Cell head;
    private Direction direction;
    private final boolean isAlive;
    private int speed;

    public Snake(int x, int y, int speed) {
        this.speed = speed;
        this.isAlive = true;
        this.direction = LEFT;
        this.body = new ArrayList<>();
        this.head = new Cell(x, y);
        body.add(head);
        this.grow();
    }

    public void move() {
        switch (direction) {
            case UP -> head.y--;
            case DOWN -> head.y++;
            case LEFT -> head.x--;
            case RIGHT -> head.x++;
        }
    }

    public boolean isBumped(){
        if (body.size() < 4) {
            return false;
        }
        for (int i = 1; i < body.size() - 1; i++) {
            if (body.get(i).x == head.x && body.get(i).y == head.y) {
                return true;
            }
        }
        return false;
        //return body.contains(head);
    }

    public Cell get(int x) {
        return getBody().get(x);
    }

    public List<Cell> getBody() {
        return body;
    }

    public Cell getHead() {
        return head;
    }

    public void grow() {
        if (body.size() == 0) {
            body.add(head);
        }
        body.add(new Cell(-1, -1));
    }

    public void speedUp() {
        speed++;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean contains(int x, int y) {
        Cell cell = new Cell(x, y);
        return body.contains(cell);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setHead(Cell head) {
        this.head = head;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (isAlive()) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //move();
        }
    }
}