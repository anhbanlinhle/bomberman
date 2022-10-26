package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.Map;

public class Enemy extends DynamicEntity {
    int speed;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
    }

    @Override
    public void update(Map map) {
        updateMove(map);
    }

    private void updateMove(Map map) {
        int count = 0;
        int changeDir = 200;

        int min = 1;
        int max = 4;

        int ran;

        if(count++ == changeDir) {
            ran = (int) Math.floor(Math.random() * (max - min + 1) + min);
            count = 0;

            if (ran == 1) {
                if (checkCollisionMap(map, x, y - speed, DIRECTION.UP)) {
                    y -= speed;
                }
            }
            if (ran == 2) {
                if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT)) {
                    x += speed;
                }
            }
            if (ran == 3) {
                if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT)) {
                    x -= speed;
                }
            }
            if (ran == 4) {
                if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN)) {
                    y += speed;
                }
            }
        }
    }
}
