package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.KeyListener.DIRECTION;
import uet.oop.bomberman.Map;

public class Bomber extends DynamicEntity {
    private KeyListener keyHandle;
    BombManager bombManager = new BombManager();

    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 3;
    }

    @Override
    public void update(Map map) {
        updateMove(map);
        updateBombs();
        bombManager.update();
    }

    @Override
    public void update() {

    }

    private void updateMove(Map map) {
        if (keyHandle.isPressed(KeyCode.W)) {
            if (checkCollisionMap(map, x, y - speed, 0)) {
                y -= speed;
            }
        }
        if (keyHandle.isPressed(KeyCode.D)) {
            if (checkCollisionMap(map, x + speed, y, 3)) {
                x += speed;
            }
        }
        if (keyHandle.isPressed(KeyCode.A)) {
            if (checkCollisionMap(map, x - speed, y, 2)) {
                x -= speed;
            }
        }
        if (keyHandle.isPressed(KeyCode.S)) {
            if (checkCollisionMap(map, x, y + speed, 1)) {
                y += speed;
            }
        }

    }

    private void updateBombs() {

        // Handle Key Press SPACE
        if (keyHandle.isPressed(KeyCode.SPACE)) {
            int xBomb = convertToMapCordinate(x);
            int yBomb = convertToMapCordinate(y);
            System.out.println("Insert Bomb" + xBomb + " " + yBomb);
            bombManager.addBomb(new Bomb(xBomb, yBomb, Sprite.bomb.getFxImage()));
            // tao bom -> dat bom (xBomb, yBomb);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        bombManager.render(gc);
        super.render(gc);
    }
}
