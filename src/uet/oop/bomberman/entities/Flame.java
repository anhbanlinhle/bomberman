package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.graphics.Sprite.*;

public class Flame extends Entity {

    enum FLAME_TYPE {
        MIDDLE,
        END,
        BOMB,
        BRICK,
        BOMB_ITEM,
        SPEED_ITEM,
        FLAME_ITEM,
    };

    enum DIRECTION {
        UP, DOWN, LEFT, RIGHT
    }

    ;
    FLAME_TYPE flameType;
    DIRECTION direction;
    int flameTime;
    int count;
    boolean flameEnd;

    public Flame(int x, int y, FLAME_TYPE flameType, DIRECTION direction ) {
        super(x, y, Sprite.bomb_exploded1.getFxImage());
        setType(ENTITY_TYPE.FLAME);
        flameEnd = false;
        this.flameType = flameType;
        this.direction = direction;
        flameTime = 20;
        count = 0;

    }

    public void update() {
        img = getImage();
        count++;
        if (count >= flameTime) {
            flameEnd = true;
        }
    }

    public boolean isFlameEnd() {
        return flameEnd;
    }

    public Image getImage() {
        switch (flameType) {
            case END:
                switch (direction) {
                    case LEFT:
                        return movingSprite(explosion_horizontal_left_last2, explosion_horizontal_left_last1,
                                explosion_horizontal_left_last, count, flameTime).getFxImage();
                    case RIGHT:
                        return movingSprite(explosion_horizontal_right_last2, explosion_horizontal_right_last1,
                                explosion_horizontal_right_last, count, flameTime).getFxImage();
                    case DOWN:
                        return movingSprite(explosion_vertical_down_last2, explosion_vertical_down_last1,
                                explosion_vertical_down_last, count, flameTime).getFxImage();
                    case UP:
                        return movingSprite(explosion_vertical_top_last2, explosion_vertical_top_last1,
                                explosion_vertical_top_last, count, flameTime).getFxImage();
                }
                break;
            case MIDDLE:
                switch (direction) {
                    case LEFT:
                    case RIGHT:
                        return movingSprite(explosion_horizontal2, explosion_horizontal1,
                                explosion_horizontal, count, flameTime).getFxImage();
                    case DOWN:
                    case UP:
                        return movingSprite(explosion_vertical2, explosion_vertical1,
                                explosion_vertical, count, flameTime).getFxImage();

                }
                break;
            case BRICK:
                return movingSprite(brick_exploded,brick_exploded1,brick_exploded2, count, flameTime).getFxImage();
            case BOMB:
                return movingSprite(bomb_exploded2, bomb_exploded1,
                        bomb_exploded, count, flameTime).getFxImage();
        }
        return null;
    }
}

