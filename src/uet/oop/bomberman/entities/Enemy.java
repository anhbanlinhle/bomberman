package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Map;

import static uet.oop.bomberman.BombermanGame.bombManager;
import static uet.oop.bomberman.BombermanGame.map;

public class Enemy extends DynamicEntity {

    private int randomMove = 1;
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
        setType(ENTITY_TYPE.ENEMY);
    }

    @Override
    public void update() {
        updateMove(map);
        checkColisionFlame(bombManager);
        centerX = x + Sprite.SCALED_SIZE / 2;
        centerY = y + Sprite.SCALED_SIZE / 2;
    }

    public void updateMove(Map map) {

    }

    public void getRandomDirection() {
        int min = 1;
        int max = 4;

        if (randomMove == 1) {
            if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BRICK)
                    && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
                    && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
                direction = DIRECTION.UP;
                y -= speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 2) {
            if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BRICK)
                    && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.WALL)
                    && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BOMB)) {
                direction = DIRECTION.RIGHT;
                x += speed;
            } else
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 3) {
            if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BRICK)
                    && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.WALL)
                    && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BOMB)) {
                direction = DIRECTION.LEFT;
                x -= speed;
            } else
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 4) {
            if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BRICK)
                    && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
                    && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BOMB)) {
                direction = DIRECTION.DOWN;
                y += speed;
            } else
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);;
        }
        if (randomMove >4) randomMove = 1;
    }

    @Override
    public void render(GraphicsContext gc, Camera camera) {
        super.render(gc, camera);
    }
}
