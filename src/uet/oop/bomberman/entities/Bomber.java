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

import static uet.oop.bomberman.BombermanGame.bombManager;
import static uet.oop.bomberman.BombermanGame.map;

public class Bomber extends DynamicEntity {
    private KeyListener keyHandle;
    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 3;
        status = STATUS.IDLE;
        direction = DIRECTION.RIGHT;

    }

    @Override
    public void update() {
        updateMove(map);
        updateBombs();
        bombManager.update();
        countFrame++;
        countFrame = countFrame % 24;
        img = setFrame();
        checkColisionFlame(bombManager);
    }

    public Image setFrame() {
        int frameNum = countFrame / 8;
        Image frame = null;
        if (status == STATUS.WALK) {
            switch (direction) {
                case UP:
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
                case DOWN:
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
                case LEFT:
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
                case RIGHT:
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
                case UP:
                    frame = Sprite.player_up.getFxImage();
                    break;
                case DOWN:
                    frame = Sprite.player_down.getFxImage();
                    break;
                case LEFT:
                    frame = Sprite.player_left.getFxImage();
                    break;
                case RIGHT:
                    frame = Sprite.player_right.getFxImage();
                    break;
            }
        }
        return frame;
    }

    private void updateMove(Map map) {
        if (keyHandle.isPressed(KeyCode.W)
        || keyHandle.isPressed(KeyCode.A)
        || keyHandle.isPressed(KeyCode.S)
        || keyHandle.isPressed(KeyCode.D)) {
            if (keyHandle.isPressed(KeyCode.W)) {
                status = STATUS.WALK;
                direction = DIRECTION.UP;
                if (checkCollisionMap(map, x, y - speed, direction)) {
                    y -= speed;
                }
            } if (keyHandle.isPressed(KeyCode.D)) {
                status = STATUS.WALK;
                direction = DIRECTION.RIGHT;
                if (checkCollisionMap(map, x + speed, y, direction)) {
                    x += speed;
                }
            } if (keyHandle.isPressed(KeyCode.A)) {
                status = STATUS.WALK;
                direction = DIRECTION.LEFT;
                if (checkCollisionMap(map, x - speed, y, direction)) {
                    x -= speed;
                }
            } if (keyHandle.isPressed(KeyCode.S)) {
                status = STATUS.WALK;
                direction = DIRECTION.DOWN;
                if (checkCollisionMap(map, x, y + speed, direction)) {
                    y += speed;
                }
            }
        } else {
            status = STATUS.IDLE;
        }
    }

    private void updateBombs() {

        // Handle Key Press SPACE
        if (keyHandle.isPressed(KeyCode.SPACE)) {
            int xBomb = convertToMapCordinate(x);
            int yBomb = convertToMapCordinate(y);
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
