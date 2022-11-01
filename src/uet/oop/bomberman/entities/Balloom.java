package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy{

    private int randomMove = 1;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        direction = DIRECTION.RIGHT;
    }

    @Override
    public void updateMove(Map map) {
        int min = 1;
        int max = 4;

        if (randomMove == 1) {
            if (checkCollisionMap(map, x, y - speed, DIRECTION.UP)) {
                direction = DIRECTION.UP;
                y -= speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 2) {
            if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT)) {
                direction = DIRECTION.RIGHT;
                x += speed;
            } else 
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 3) {
            if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT)) {
                direction = DIRECTION.LEFT;
                x -= speed;
            } else 
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 4) {
            if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN)) {
                direction = DIRECTION.DOWN;
                y += speed;
            } else 
                randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);;
        }
        if (randomMove >4) randomMove = 1;
    }

    public Image setFrame() {
        int frameNum = countFrame / 20;
        Image frame = null;
        switch (direction) {
            case UP:
                switch (frameNum) {
                    case 0:
                        frame = Sprite.balloom_right1.getFxImage();
                        break;
                    case 1:
                        frame = Sprite.balloom_right2.getFxImage();
                        break;
                    case 2:
                        frame = Sprite.balloom_right3.getFxImage();
                        break;
                }
                break;
            case DOWN:
                switch (frameNum) {
                    case 0:
                        frame = Sprite.balloom_left1.getFxImage();
                        break;
                    case 1:
                        frame = Sprite.balloom_left2.getFxImage();
                        break;
                    case 2:
                        frame = Sprite.balloom_left3.getFxImage();
                        break;
                }
                break;
            case LEFT:
                switch (frameNum) {
                    case 0:
                        frame = Sprite.balloom_left1.getFxImage();
                        break;
                    case 1:
                        frame = Sprite.balloom_left2.getFxImage();
                        break;
                    case 2:
                        frame = Sprite.balloom_left3.getFxImage();
                        break;
                }
                break;
            case RIGHT:
                switch (frameNum) {
                    case 0:
                        frame = Sprite.balloom_right1.getFxImage();
                        break;
                    case 1:
                        frame = Sprite.balloom_right2.getFxImage();
                        break;
                    case 2:
                        frame = Sprite.balloom_right3.getFxImage();
                        break;
                }
                break;
        }
        return frame;
    }

    @Override
    public void update() {
        super.update();
        countFrame++;
        countFrame = countFrame % 60;
        img = setFrame();
    }

    @Override
    public void die() {
        img = Sprite.balloom_dead.getFxImage();
    }
    public void loadDie(int count) {
        img = Sprite.movingSprite(Sprite.pink_dead1, Sprite.pink_dead2, Sprite.pink_dead3, count, 40).getFxImage();
    }
}
