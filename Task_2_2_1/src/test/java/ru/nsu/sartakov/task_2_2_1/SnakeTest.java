package ru.nsu.sartakov.task_2_2_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.task_2_2_1.entities.Cell;
import ru.nsu.sartakov.task_2_2_1.entities.Direction;
import ru.nsu.sartakov.task_2_2_1.entities.Snake;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    Snake snake;

    @BeforeEach
    void setUp() {
        snake = new Snake(10, 10, 5);
    }

    @Test
    void move() {
        snake.setDirection(Direction.DOWN);
        snake.move();
        Assertions.assertEquals(11, snake.getHead().getY());
    }

    @Test
    void shift() {
        snake.setDirection(Direction.DOWN);
        snake.shift();
        Assertions.assertEquals(13, snake.getHead().getY());
    }

    @Test
    void grow() {
        snake.grow();
        Assertions.assertEquals(2, snake.getBody().size());
    }

    @Test
    void speedUp() {
        snake.speedUp();
        Assertions.assertEquals(6, snake.getSpeed());
    }

    @Test
    void getSpeed() {
        Assertions.assertEquals(5, snake.getSpeed());
    }

    @Test
    void getHead() {
        Assertions.assertEquals(10, snake.getHead().getX());
        Assertions.assertEquals(10, snake.getHead().getY());
    }

    @Test
    void getBody() {
        Assertions.assertEquals(1, snake.getBody().size());
    }

    @Test
    void setGetDirection() {
        snake.setDirection(Direction.DOWN);
        Assertions.assertEquals(Direction.DOWN, snake.getDirection());
    }

    @Test
    void setHead() {
        snake.setHead(new Cell(2, 2));
        Assertions.assertEquals(2, snake.getHead().getX());
        Assertions.assertEquals(2, snake.getHead().getY());
    }
}