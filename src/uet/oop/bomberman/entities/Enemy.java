package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Map;

import static uet.oop.bomberman.BombermanGame.bombManager;
import static uet.oop.bomberman.BombermanGame.map;

public class Enemy extends DynamicEntity {
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
}
