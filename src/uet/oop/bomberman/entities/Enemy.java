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
        int min = 1;
        int max = 4;

        int ran = (int) Math.floor(Math.random() * (max - min + 1) + min);

        if (ran == 1) {
            if (checkCollisionMap(map, x, y - speed, 0)) {
                y -= speed;
            }
        }
        if (ran == 2) {
            if (checkCollisionMap(map, x + speed, y, 3)) {
                x += speed;
            }
        }
        if (ran == 3) {
            if (checkCollisionMap(map, x - speed, y, 2)) {
                x -= speed;
            }
        }
        if (ran == 4) {
            if (checkCollisionMap(map, x, y + speed, 1)) {
                y += speed;
            }
        }
    }
}
