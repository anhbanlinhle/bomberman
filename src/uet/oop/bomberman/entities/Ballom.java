package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Ballom extends Enemy{

    private int randomMove = 1;

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void updateMove(Map map) {
        int min = 1;
        int max = 4;

        if (randomMove == 1) {
            if (checkCollisionMap(map, x, y - speed, DIRECTION.UP)) {
                y -= speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 2) {
            if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT)) {
                x += speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 3) {
            if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT)) {
                x -= speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }
        if (randomMove == 4) {
            if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN)) {
                y += speed;
            } else randomMove = (int) Math.floor(Math.random() * (max - min + 1) + min);;
        }
        if (randomMove >4) randomMove = 1;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void die() {
        img = Sprite.balloom_dead.getFxImage();
    }
}
