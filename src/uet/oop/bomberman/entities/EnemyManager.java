package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.SoundFile;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private final int DELAY_REMOVE_ENEMY = 36;
    private List<Enemy> enemyList;

    private int count = 0;

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
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            if(!enemyList.get(i).isAlive()){
                // SoundFile.monsterDie.play();
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
                    count = 0;
                }
            }
            else {
                enemyList.get(i).update();
            }
        }
    }

    public void render(GraphicsContext gc, Camera camera) {
        for (Enemy enemy :
                enemyList) {
            enemy.render(gc, camera);
        }
    }
}
