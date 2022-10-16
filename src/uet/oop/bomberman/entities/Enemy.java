package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.controller.KeyListener;
public class Enemy extends Entity{
    int speed;
    public Enemy(int x, int y, Image img) {
        super( x, y, img);
        speed = 2;
    }

    @Override
    public void update() {
        updateMove();
    }

    private void updateMove() {
        int min = 1;
        int max = 4;
        
        int ran = (int)Math.floor(Math.random()*(max-min+1)+min);
        
        if(ran == 1) {
            this.y -= speed;
        }
        if(ran == 2) {
            this.x += speed;  
        }
        if(ran == 3) {
            this.x -= speed;
        }
        if(ran == 2) {
            this.y += speed;
        }
    }
}
