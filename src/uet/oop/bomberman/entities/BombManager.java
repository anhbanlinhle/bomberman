package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class BombManager {
    private int bombCount = 0;
    private int bombRemain = 1;
    List<Bomb> bombList;

    public BombManager() {
        bombList = new ArrayList<>();
    }

    public void addBomb(Bomb bomb) {
        boolean checkInPlace = true;
        for (Bomb i :
                bombList) {
            if (bomb.getX() == i.getX() && bomb.getY() == i.getY()) {
                checkInPlace = false;
            }
        }
        if(checkInPlace && bombRemain > 0){
            bombList.add(bomb);
            bombRemain--;
            System.out.println(bombCount++);
        }
    }

    public void removeBomb(Bomb bomb) {
        bombList.remove(bomb);
        bombRemain++;
    }

    public void update(){
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).update();
            if (bombList.get(i).isExplode()) {
                removeBomb(bombList.get(i));
            }
        }
    }
    public void render(GraphicsContext gc) {
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).render(gc);
        }
    }

}
