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
        moving = false;
        direction = 3;
    }

    @Override
    public void update(Map map) {
        updateMove(map);
        updateBombs();
        bombManager.update();
        countFrame++;
        countFrame = countFrame % 9;
        img = setFrame();
    }

    public Image setFrame() {
        int frameNum = countFrame / 3;
        Image frame = null;
        if (moving) {
            switch (direction) {
                case 0:
                    switch (frameNum) {
                        case 0:
                            frame = Sprite.player_up.getFxImage();
                            break;
                        case 1:
                            frame = Sprite.player_up_1.getFxImage();
                            break;
                        case 2:
                            frame = Sprite.player_up_2.getFxImage();
                            break;
                    }
                    break;
                case 1:
                    switch (frameNum) {
                        case 0:
                            frame = Sprite.player_down.getFxImage();
                            break;
                        case 1:
                            frame = Sprite.player_down_1.getFxImage();
                            break;
                        case 2:
                            frame = Sprite.player_down_2.getFxImage();
                            break;
                    }
                    break;
                case 2:
                    switch (frameNum) {
                        case 0:
                            frame = Sprite.player_left.getFxImage();
                            break;
                        case 1:
                            frame = Sprite.player_left_1.getFxImage();
                            break;
                        case 2:
                            frame = Sprite.player_left_2.getFxImage();
                            break;
                    }
                    break;
                case 3:
                    switch (frameNum) {
                        case 0:
                            frame = Sprite.player_right.getFxImage();
                            break;
                        case 1:
                            frame = Sprite.player_right_1.getFxImage();
                            break;
                        case 2:
                            frame = Sprite.player_right_2.getFxImage();
                            break;
                    }
                    break;
            }
        } else {
            switch (direction) {
                case 0:
                    frame = Sprite.player_up.getFxImage();
                    break;
                case 1:
                    frame = Sprite.player_down.getFxImage();
                    break;
                case 2:
                    frame = Sprite.player_left.getFxImage();
                    break;
                case 3:
                    frame = Sprite.player_right.getFxImage();
                    break;
            }
        }
        return frame;
    }

    @Override
    public void update() {

    }

    private void updateMove(Map map) {
        if (keyHandle.isPressed(KeyCode.W)) {
            moving = true;
            direction = 0;
            if (checkCollisionMap(map, x, y - speed, direction)) {
                y -= speed;
            }
        } else if (keyHandle.isPressed(KeyCode.D)) {
            moving = true;
            direction = 3;
            if (checkCollisionMap(map, x + speed, y, direction)) {
                x += speed;
            }
        } else if (keyHandle.isPressed(KeyCode.A)) {
            moving = true;
            direction = 2;
            if (checkCollisionMap(map, x - speed, y, direction)) {
                x -= speed;
            }
        } else if (keyHandle.isPressed(KeyCode.S)) {
            moving = true;
            direction = 1;
            if (checkCollisionMap(map, x, y + speed, direction)) {
                y += speed;
            }
        } else {
            moving = false;
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
