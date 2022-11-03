package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Entity.ENTITY_TYPE;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Map {
    private List<List<Entity>> mapEntity;
    private List<Enemy> enemyList;
    private int height, width;

    static char[][] levelSymbol;
    private static int currentMapNo;

    public Map() {
        currentMapNo = 1;
        mapEntity = new ArrayList<>();
        enemyList = new ArrayList<>();
    }

    public void loadMap(KeyListener keyListener) {
        File fileMap = new File("res/levels/level" + currentMapNo + ".txt");
        try {
            Scanner scanner = new Scanner(fileMap);
            currentMapNo = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < height; i++) {
                // Read map as individual line
                String fileLine = scanner.nextLine();
                List<Entity> entityList = new ArrayList<>();
                for (int j = 0; j < width; j++) {
                    // Read text form line
                    Entity entity;
                    Enemy tempEnemy;
                    switch (fileLine.charAt(j)) {
                        case '#':
                            entity = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            entity = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        case '1':
                            tempEnemy = new Balloom(j, i, Sprite.balloom_right1.getFxImage());
                            enemyList.add(tempEnemy);
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        case '2':
                            tempEnemy = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                            enemyList.add(tempEnemy);
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        case '3':
                            tempEnemy = new Doll(j, i, Sprite.doll_right1.getFxImage());
                            enemyList.add(tempEnemy);
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        case 'b':
                            entity = new BombItem(j, i, Sprite.brick.getFxImage());
                            break;
                        default:
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                    }
                    entityList.add(entity);
                }
                mapEntity.add(entityList);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void renderMap(GraphicsContext gc, Camera camera) {
        for (int i = 0; i < height; i++) {
            mapEntity.get(i).forEach(g -> g.render(gc, camera));
        }
    }

    public ENTITY_TYPE entityTypeAtCordinate(int x, int y) {
        return mapEntity.get(y).get(x).getType();
    }

    public int getHeight() {
        return this.height;
    }

    //Get Entity as block coordinate not real coordinate
    public Entity getEntity(int x, int y) {
        return mapEntity.get(y).get(x);
    }

    public void replace(int x, int y, Entity entity) {
        mapEntity.get(y).set(x, entity);
    }

    public int getWidth() {
        return this.width;
    }

    public static int getCurrentMapNo() {
        return currentMapNo;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
