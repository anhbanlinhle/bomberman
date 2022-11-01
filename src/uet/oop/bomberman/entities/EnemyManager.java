package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
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
        for (int i = 0; i < enemyList.size(); i++) {
            if(enemyList.get(i).isAlive()){
                enemyList.get(i).update();
            } else {
                enemyList.get(i).die();
                count++;
                int DELAY_REMOVE_ENEMY = 15;
                if (count == DELAY_REMOVE_ENEMY){
                    enemyList.remove(i);
                    count = 0;
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (Enemy i :
                enemyList) {
            i.render(gc);
        }
    }
}
