package uet.oop.bomberman.entities.dynamic.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


public class Balloom extends Enemy{

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        direction = DIRECTION.RIGHT;
        speed = 1;
    }


    public Image setFrame() {
        return switch (direction) {
            case UP, DOWN, NOT_MOVE, LEFT ->
                    Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, countFrame, 60).getFxImage();
            case RIGHT ->
                    Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, countFrame, 60).getFxImage();

        };
    }

    @Override
    public void update() {
        super.update();
        super.getRandomDirection();
        countFrame++;
        img = setFrame();
    }

    @Override
    public void die() {
        img = Sprite.balloom_dead.getFxImage();
    }
    public void loadDie(int count) {
        img = Sprite.movingSprite(Sprite.yellow_dead1, Sprite.yellow_dead2, Sprite.yellow_dead3, count, 36).getFxImage();
    }
}
