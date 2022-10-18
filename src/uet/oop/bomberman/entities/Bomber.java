package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.KeyListener;

public class Bomber extends DynamicEntity {
    private KeyListener keyHandle;

    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 2;
    }

    @Override
    public void update() {
        updateMove();
    }

    private void updateMove() {
        if (keyHandle.isPressed(KeyCode.W)) {
            this.y -= speed;
        }
        if (keyHandle.isPressed(KeyCode.D)) {
            this.x += speed;
        }
        if (keyHandle.isPressed(KeyCode.A)) {
            this.x -= speed;
        }
        if (keyHandle.isPressed(KeyCode.S)) {
            this.y += speed;
        }
    }
}
