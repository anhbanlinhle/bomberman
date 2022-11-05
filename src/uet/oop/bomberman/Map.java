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

import static uet.oop.bomberman.BombermanGame.*;


public class Map {
    private List<List<Entity>> mapEntity;
    private List<Enemy> enemyList;
    public static List<Item> itemList;
    private int height, width;

    private int portalX;
    private int portalY;

    static char[][] levelSymbol;
    private int currentMapNo;

    public Map() {
        currentMapNo = 0;
        mapEntity = new ArrayList<>();
        enemyList = new ArrayList<>();
        itemList = new ArrayList<>();
    }


    public void loadMap(KeyListener keyListener) {
        File fileMap = new File("res/levels/Level" + currentMapNo + ".txt");
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
                    Item tempItem;
                    switch (fileLine.charAt(j)) {
                        case '#':
                            entity = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            entity = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        //Load Enemy
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
                        case '4':
                            tempEnemy = new Batfs(j, i, Sprite.batfs_down2.getFxImage());
                            enemyList.add(tempEnemy);
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        case '5':
                            tempEnemy = new Ghest(j, i, Sprite.ghest_right1.getFxImage());
                            enemyList.add(tempEnemy);
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                        case 'b':
                            entity = new BombItem(j, i, Sprite.brick.getFxImage());
                            break;
                        case 's':
                            entity = new SpeedItem(j, i, Sprite.brick.getFxImage());
                            break;
                        case 'f':
                            entity = new FlameItem(j, i, Sprite.brick.getFxImage());
                            break;
                        case 'x':
                            entity = new Portal(j, i, Sprite.brick.getFxImage());
                            map.setPortalX(j * Sprite.SCALED_SIZE);
                            map.setPortalY(i * Sprite.SCALED_SIZE);
                            break;
                        default:
                            entity = new Grass(j, i, Sprite.grass.getFxImage());
                    }
                    entityList.add(entity);
                }
                mapEntity.add(entityList);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<List<Integer>> formatMapData() {
        List<List<Integer>> formatMap = new ArrayList<>();

        //Format the entities in map
        for (int i = 0; i < height; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if (mapEntity.get(i).get(j) instanceof Brick || mapEntity.get(i).get(j) instanceof Wall) {
                    row.add(1);
                } else row.add(0);
            }
            formatMap.add(row);
        }

        for (Entity entity : enemyManager.getEnemyList()) {
            formatMap.get(entity.getMapY()).set(entity.getMapX(), 1);
        }

        //Bomb.
        for (Bomb bomb : bombManager.getBombList()) {
            formatMap.get(bomb.getMapY()).set(bomb.getMapX(), 1);
        }
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.print(formatMap.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
        return formatMap;
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

    public int getCurrentMapNo() {
        return currentMapNo;
    }

    public void setCurrentMapNo(int currentMapNo) {
        this.currentMapNo = currentMapNo;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }


    public int getPortalX() {
        return this.portalX;
    }

    public void setPortalX(int portalX) {
        this.portalX = portalX;
    }

    public int getPortalY() {
        return this.portalY;
    }

    public void setPortalY(int portalY) {
        this.portalY = portalY;
    }
}
