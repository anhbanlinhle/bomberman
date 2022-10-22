package uet.oop.bomberman;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.Entity.ENTITY_TYPE;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.SerializablePermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    private List<List<Entity>> mapEntity;
    private int height, width;
    static char[][] levelSymbol;
    private static int currentMapNo;

    public Map() {
        currentMapNo = 1;
        mapEntity = new ArrayList<>();

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
                List<Entity> tempList = new ArrayList<>();
                for (int j = 0; j < width; j++) {
                    // Read text form line
                    switch (fileLine.charAt(j)) {
                        case '#':
                            Entity wall = new Wall(j, i, Sprite.wall.getFxImage());
                            tempList.add(wall);
                            break;
                        case '*':
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            tempList.add(brick);
                            break;
                        default:
                            Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                            tempList.add(grass);
                    }
                }
                mapEntity.add(tempList);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void renderMap(GraphicsContext gc) {
        for (int i = 0; i < height; i++) {
            mapEntity.get(i).forEach(g -> g.render(gc));
        }
    }

    public ENTITY_TYPE entityTypeAtCordinate(int x, int y) {
        return mapEntity.get(y).get(x).getType();
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public static int getCurrentMapNo() {
        return currentMapNo;
    }
}
