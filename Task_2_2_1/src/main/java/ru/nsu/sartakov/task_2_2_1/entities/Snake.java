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
        // random direction at start
        this.direction = Direction.values()[(int) (Math.random() * 4)];
        this.body = new ArrayList<>();
        this.head = new Cell(x, y);
        body.add(head);
    }

    // move snake by adding new head and deleting tail
    public void move() {
        Cell newHead = new Cell(head.x, head.y);
        switch (direction) {
            case UP -> newHead.y--;
            case DOWN -> newHead.y++;
            case LEFT -> newHead.x--;
            case RIGHT -> newHead.x++;
        }
        setHead(newHead);
        body.add(0, head);
        body.remove(body.size() - 1);
    }

    public void shift() {
        // cycle for 3
        for (int i = 0; i < 3; i++) {
            move();
        }
    }

    public boolean isBumpedIntoSnake(){
        if (body.size() < 4) {
            return false;
        }
        for (int i = 1; i < body.size() - 1; i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isBumpedIntoWall(Board board) {
        return head.x < 0 || head.x > board.getWidth() || head.y < 0 || head.y > board.getHeight();
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

    public int getSpeed() {
        return speed;
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