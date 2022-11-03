package uet.oop.bomberman.controller;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Texture;

public class Camera {
    private int x;
    private int y;
    private int screenWidth;
    private int screenHeight;

    private int mapWidth;
    private int mapHeight;

    public Camera(int x, int y, int mapWidth, int mapHeight, int screenWidth, int screenHeight) {
        this.x = x;
        this.y = y;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
    }

    public void update(Bomber bomber) {
        x = bomber.getX() - Texture.WIDTH * Sprite.SCALED_SIZE/2;
        if (x < 0) x = 0;
       if (x + screenWidth * Sprite.SCALED_SIZE > mapWidth * Sprite.SCALED_SIZE) {
            x = mapWidth * Sprite.SCALED_SIZE - screenWidth * Sprite.SCALED_SIZE;
        }

        y = bomber.getY() - Texture.HEIGHT * Sprite.SCALED_SIZE/2;
        if (y < 0) y = 0;
        if (y + screenHeight * Sprite.SCALED_SIZE > mapHeight * Sprite.SCALED_SIZE) {
            y = mapHeight * Sprite.SCALED_SIZE - screenHeight * Sprite.SCALED_SIZE;
        }

        System.out.println(mapWidth + " " + mapHeight);
    }

    /**
     * Getter for x.
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
