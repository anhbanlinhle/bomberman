package uet.oop.bomberman.controller;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.dynamic.enemies.Eggs;
import uet.oop.bomberman.entities.dynamic.enemies.Eggsbomb;
import uet.oop.bomberman.entities.dynamic.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private final int DELAY_REMOVE_ENEMY = 36;
    private List<Enemy> enemyList;
    public static int eggsy;

    public EnemyManager() {
        enemyList = new ArrayList<>();
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public void remove(Enemy enemy){
        enemyList.remove(enemy);
    }

    public void update() {
        // Remove dead enemy
        eggsy = 0;
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            if(!enemyList.get(i).isAlive()){
                enemyList.get(i).die();
                enemyList.get(i).increaseCountDead();
                if (enemyList.get(i).getCountDead() >= 9)
                    enemyList.get(i).loadDie(enemyList.get(i).getCountDead());

                if (enemyList.get(i).getCountDead() == DELAY_REMOVE_ENEMY){
                    if (enemyList.get(i) instanceof Eggs) {
                        int x = enemyList.get(i).getMapX();
                        int y = enemyList.get(i).getMapY();
                        enemyList.add(new Eggsbomb(x, y, Sprite.eggs4.getFxImage()));
                        enemyList.add(new Eggsbomb(x, y, Sprite.eggs4.getFxImage()));
                    }
                    enemyList.remove(i);
                }
            }
            else {
                if (enemyList.get(i) instanceof Eggsbomb) {
                    eggsy++;
                }
                enemyList.get(i).update();
            }
        }
        if (eggsy > 6) {
            bomberman.setAlive(false);
        }
    }

    public void render(GraphicsContext gc, Camera camera) {
        for (Enemy enemy :
                enemyList) {
            enemy.render(gc, camera);
        }
    }
}
