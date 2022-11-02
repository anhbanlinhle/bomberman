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
import static uet.oop.bomberman.BombermanGame.enemyManager;

public class Bomber extends DynamicEntity {
    private KeyListener keyHandle;
    
    public int loseDelay;


    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 3;
<<<<<<< HEAD
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
=======
        status = STATUS.IDLE;
        direction = DIRECTION.RIGHT;
        isAlive = true;
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f
    }

    @Override
    public void update() {
        if (isAlive) {
            updateMove(map);
            updateBombs();
            bombManager.update();
            countFrame++;
            countFrame = countFrame % 24;
            img = setFrame();
            checkColisionFlame(bombManager);
            centerX = x + Sprite.SCALED_SIZE / 2;
            centerY = y + Sprite.SCALED_SIZE / 2;

            if (checkCollisionEnemy() || checkColisionFlame(bombManager))
                setAlive(false);
        }
        else {
            countDead++;
            die1(countDead);
            if (countDead >= 60)
                die2(countDead);
            int DELAY_REMOVE = 120;
            if (countDead == DELAY_REMOVE) {
                System.out.println("Game Over");
                countDead = 0;
            }
            loseDelay++;
        }

    }

<<<<<<< HEAD
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
=======
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
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f
            }
        } else {
            moving = false;
        }
<<<<<<< HEAD
=======
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
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f
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

    private boolean checkCollisionEnemy() {
        List<Enemy> checkList = enemyManager.getEnemyList();
        for (int i = 0; i < checkList.size(); i++) {
            int difX = Math.abs(checkList.get(i).getCenterX() - centerX);
            int difY = Math.abs(checkList.get(i).getCenterY() - centerY);
            
            if (difX + 6 < Sprite.SCALED_SIZE && difY < Sprite.SCALED_SIZE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext gc) {
        bombManager.render(gc);
        super.render(gc);
    }

    public void die1(int count) {
        img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, count, 60).getFxImage();
    }

    public void die2(int count) {
        img = Sprite.movingSprite(Sprite.player_dead4, Sprite.player_dead5, Sprite.player_dead6, count, 60).getFxImage();
    }
}
