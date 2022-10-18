package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.KeyListener.DIRECTION;
import uet.oop.bomberman.Map;

public class Bomber extends DynamicEntity {
    private KeyListener keyHandle;

    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 1;
    }

    @Override
    public void update(Map map) {
        updateMove(map);
        updateBombs();
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
        System.out.print("X map: " + convertToMapCordinate(x));

        System.out.print("\tY map: " + convertToMapCordinate(y) + "\t\n");

        System.out.print("X: " + x);

        System.out.print("\tY: " + y + "\t\n");
    }

    private void updateBombs() {

        // Handle Key Press SPACE
        if (keyHandle.isPressed(KeyCode.SPACE)) {
            int xBomb = x + Sprite.DEFAULT_SIZE;
            xBomb = xBomb - xBomb % Sprite.SCALED_SIZE;
            int yBomb = y + Sprite.DEFAULT_SIZE;
            yBomb = yBomb - yBomb % Sprite.SCALED_SIZE;
            xBomb /= Sprite.SCALED_SIZE;
            yBomb /= Sprite.SCALED_SIZE;
            // tao bom -> dat bom (xBomb, yBomb);
        }
    }
}
