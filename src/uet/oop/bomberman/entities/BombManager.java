package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombManager {

    Flame.DIRECTION[] flameDirection = {Flame.DIRECTION.UP, Flame.DIRECTION.DOWN, Flame.DIRECTION.RIGHT, Flame.DIRECTION.LEFT};
    private int bombRemain;
    private int flameLength;
    int flamePosX, flamePosY;
    List<Bomb> bombList;
    List<Flame> flameList;

    public BombManager() {
        bombList = new ArrayList<>();
        flameList = new ArrayList<>();
        bombRemain = 1;
        flameLength = 1;
        flamePosX = 0;
        flamePosY = 0;
    }

    public void addBomb(Bomb bomb) {
        boolean checkDuplicate = true;
        for (Bomb i :
                bombList) {
            if (bomb.getX() == i.getX() && bomb.getY() == i.getY()) {
                checkDuplicate = false;
            }
        }
        if (checkDuplicate && bombRemain > 0) {
            bombList.add(bomb);
            bombRemain--;
        }
    }

    public void bombExploded(int index) {
        for (int i = 1; i <= flameLength; i++) {
            int bomX = bombList.get(index).getX() / Sprite.SCALED_SIZE;
            int bomY = bombList.get(index).getY() / Sprite.SCALED_SIZE;
            flamePosX = bomX;
            flamePosY = bomY;
            flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BOMB, Flame.DIRECTION.DOWN));
            Flame.FLAME_TYPE type = Flame.FLAME_TYPE.MIDDLE;
            if(i == flameLength) type = Flame.FLAME_TYPE.END;
            for (int j = 0; j < 4; j++) {
                //Update flame position
                switch (flameDirection[j]) {
                    case UP:
                        flamePosY = bomY - i;
                        break;
                    case DOWN:
                        flamePosY = bomY + i;
                        break;
                    case LEFT:
                        flamePosX = bomX - i;
                        flamePosY = bomY;
                        break;
                    case RIGHT:
                        flamePosX = bomX + i;
                        flamePosY = bomY;
                        break;
                }
                flameList.add(new Flame(flamePosX, flamePosY, type, flameDirection[j]));
                System.out.println(flamePosX +  " " + flamePosY);
            }

            System.out.println("Next");
        }
        removeBomb(index);

    }

    public void removeBomb(int index) {
        bombList.remove(index);
        bombRemain++;
    }

    public void update() {
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).update();
            if (bombList.get(i).isExplode()) {
                bombExploded(i);
            }
        }
        for (int i = 0; i < flameList.size(); i++) {
            flameList.get(i).update();
            if (flameList.get(i).isFlameEnd()) {
                flameList.remove(i);
                i--;
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (Bomb bomb : bombList) {
            bomb.render(gc);
        }
        for (Flame flame : flameList) {
            flame.render(gc);
        }
    }

}
