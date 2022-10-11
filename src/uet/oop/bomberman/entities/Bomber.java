package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.KeyListener;

public class Bomber extends Entity {
    private KeyListener keyListener ;
    int speed;
    public Bomber(int x, int y, Image img, KeyListener keyListener) {
        super( x, y, img);
        this.keyListener = keyListener;
        speed = 2;
    }

    @Override
    public void update() {
        updateMove();
    }

    private void updateMove() {
        if(keyListener.isPressed(KeyCode.W)) {
            this.y -= speed;
        }
        if(keyListener.isPressed(KeyCode.D)) {
            this.x += speed;  
        }
        if(keyListener.isPressed(KeyCode.A)) {
            this.x -= speed;
        }
        if(keyListener.isPressed(KeyCode.S)) {
            this.y += speed;
        }
    }
}
