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
        updateBombs();
    }
    
    private void updateMove() {
        if(keyListener.isPressed(KeyCode.W)) {
            this.y -= speed;
        } else
        if(keyListener.isPressed(KeyCode.D)) {
            this.x += speed;  
        } else
        if(keyListener.isPressed(KeyCode.A)) {
            this.x -= speed;
        } else
        if(keyListener.isPressed(KeyCode.S)) {
            this.y += speed;
        }
    }

    private void updateBombs() {

        // Handle Key Press SPACE
        if (keyListener.isPressed(KeyCode.SPACE)) {
            int xBomb = x + Sprite.DEFAULT_SIZE;
            xBomb = xBomb - xBomb % Sprite.SCALED_SIZE;
            int yBomb = y + Sprite.DEFAULT_SIZE;
            yBomb = yBomb - yBomb % Sprite.SCALED_SIZE;
            xBomb /= Sprite.SCALED_SIZE;
            yBomb /= Sprite.SCALED_SIZE;
            //tao bom -> dat bom (xBomb, yBomb);
        }
    }
}
