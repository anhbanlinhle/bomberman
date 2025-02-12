package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int centerX;

    protected int centerY;

    protected Image img;

    protected ENTITY_TYPE type;



    public Entity() {
    }

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
    }

    public void render(GraphicsContext gc, Camera camera) {
        gc.drawImage(img, x - camera.getX(), y - camera.getY());
    }

    public void update(Map map) {

    }

    public void update() {

    }

    public ENTITY_TYPE getType() {
        return this.type;
    }

    public void setType(ENTITY_TYPE type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int convertToMapCordinate(int n) {
        int newCor = n + Sprite.DEFAULT_SIZE;
        newCor = newCor - newCor % Sprite.SCALED_SIZE;
        newCor /= Sprite.SCALED_SIZE;
        return newCor;
    }

    public int getMapX() {
        int newCor = x + Sprite.SCALED_SIZE/2;
        newCor = newCor - newCor % Sprite.SCALED_SIZE;
        newCor /= Sprite.SCALED_SIZE;
        return newCor;
    }

    public int getMapY() {
        int newCor = y + Sprite.SCALED_SIZE/2;
        newCor = newCor - newCor % Sprite.SCALED_SIZE;
        newCor /= Sprite.SCALED_SIZE;
        return newCor;
    }

    public enum ENTITY_TYPE {
        BRICK,
        BOMB,
        WALL,
        FLAME,
        GRASS,
        ENEMY,
        BOMB_ITEM,
        SPEED_ITEM,
        FLAME_ITEM,
        PORTAL
    }
}
