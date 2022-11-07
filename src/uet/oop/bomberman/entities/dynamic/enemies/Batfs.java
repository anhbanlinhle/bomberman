package uet.oop.bomberman.entities.dynamic.enemies;

import javafx.scene.image.Image;
import javafx.util.Pair;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.map;

public class Batfs extends Enemy {
    DIRECTION lastDir;

    boolean foundPlayer = false;

    public Batfs(int x, int y, Image img) {
        super(x, y, img);
        direction = DIRECTION.NOT_MOVE;
        lastDir = DIRECTION.NOT_MOVE;
        speed = 4;
    }

    public void getNextDirection() {
        List<List<Integer>> formatMap = map.formatMapData();

        int endX = bomberman.getMapX();
        int endY = bomberman.getMapY();

        int startX = getMapX();
        int startY = getMapY();


        int width = map.getWidth();
        int height = map.getHeight();

        if (startX == endX && startY == endY) direction = DIRECTION.NOT_MOVE;

        formatMap.get(endY).set(endX, 0);
        formatMap.get(startY).set(startX, 0);


        //List of BFS block
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(startY, startX));

        //Distance from block to Baft
        int[][] distance = new int[height][width];

        //Check visited block
        boolean[][] visited = new boolean[height][width];
        visited[startY][startX] = true;

        //List of Parent block
        Pair<Integer, Integer>[][] last = new Pair[height][width];
        last[startY][startX] = new Pair<>(-1, -1);

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                System.out.println(formatMap.h);
//            }
//        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        //Start BFS
        while (!q.isEmpty()) {
            Pair<Integer, Integer> tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int newY = tmp.getKey() + dy[i];
                int newX = tmp.getValue() + dx[i];

                if (newX >= 0 && newX < width && newY >= 0 && newY < height && formatMap.get(newY).get(newX) == 0 && !visited[newY][newX]) {
                    q.add(new Pair<>(newY, newX));
                    distance[newY][newX] = distance[tmp.getKey()][tmp.getValue()] + 1;
                    last[newY][newX] = new Pair<>(tmp.getKey(), tmp.getValue());
                    visited[newY][newX] = true;
                }
            }
        }

        //Check if found Player
        if (!visited[endY][endX]) {
            foundPlayer = false;
            super.getRandomDirection();
            return;
        } else {
            foundPlayer = true;
        }

        if (distance[endY][endX] == 0) return;

        //Get path from Back_Tracking
        List<Pair<Integer, Integer>> pathCoordinate = new ArrayList<>();
        int prevX = last[endY][endX].getValue();
        int prevY = last[endY][endX].getKey();
        pathCoordinate.add(0, new Pair<>(endY, endX));

        while (true) {
            if (last[prevY][prevX].getKey() == -1 && last[prevY][prevX].getValue() == -1) {
                pathCoordinate.add(0, new Pair<>(prevY, prevX));
                break;
            }

            pathCoordinate.add(0, new Pair<>(prevY, prevX));
            int tmpX = prevX;
            int tmpY = prevY;
            prevX = last[tmpY][tmpX].getValue();
            prevY = last[tmpY][tmpX].getKey();
        }

        //get next direction
        if (pathCoordinate.get(1).getKey() - pathCoordinate.get(0).getKey() == 0 && pathCoordinate.get(1).getValue() - pathCoordinate.get(0).getValue() > 0) {
            direction = DIRECTION.RIGHT;
        } else if (pathCoordinate.get(1).getKey() - pathCoordinate.get(0).getKey() == 0 && pathCoordinate.get(1).getValue() - pathCoordinate.get(0).getValue() < 0) {
            direction = DIRECTION.LEFT;
        } else if (pathCoordinate.get(1).getKey() - pathCoordinate.get(0).getKey() < 0 && pathCoordinate.get(1).getValue() - pathCoordinate.get(0).getValue() == 0) {
            direction = DIRECTION.UP;
        } else if (pathCoordinate.get(1).getKey() - pathCoordinate.get(0).getKey() > 0 && pathCoordinate.get(1).getValue() - pathCoordinate.get(0).getValue() == 0) {
            direction = DIRECTION.DOWN;
        }

    }

    public Image setFrame() {
        return switch (direction) {
            case LEFT ->
                    Sprite.movingSprite(Sprite.batfs_left1, Sprite.batfs_left2, Sprite.batfs_left3, countFrame, 60).getFxImage();
            case RIGHT ->
                    Sprite.movingSprite(Sprite.batfs_right1, Sprite.batfs_right2, Sprite.batfs_right3, countFrame, 60).getFxImage();
            case UP ->
                    Sprite.movingSprite(Sprite.batfs_up1, Sprite.batfs_up2, Sprite.batfs_up3, countFrame, 60).getFxImage();
            case DOWN ->
                    Sprite.movingSprite(Sprite.batfs_down1, Sprite.batfs_down2, Sprite.batfs_down3, countFrame, 60).getFxImage();
            case NOT_MOVE ->
                    Sprite.movingSprite(Sprite.batfs_down3, Sprite.batfs_down3, Sprite.batfs_down3, countFrame, 60).getFxImage();
        };
    }

    public void pathFindingMove() {
        switch (direction) {
            case UP:
                if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
                    lastDir = DIRECTION.UP;
                    y -= speed;
                } else alternateMovement();
                break;
            case DOWN:
                if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BOMB)) {
                    lastDir = DIRECTION.DOWN;
                    y += speed;
                } else alternateMovement();
                break;
            case LEFT:
                if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BOMB)) {
                    lastDir = DIRECTION.LEFT;
                    x -= speed;
                } else alternateMovement();
                break;
            case RIGHT:
                if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BOMB)) {
                    lastDir = DIRECTION.RIGHT;
                    x += speed;
                } else alternateMovement();
                break;
            default:
//                System.out.println("move random");
                break;
        }
    }

    @Override
    public void update() {
        super.update();
        getNextDirection();
        if (foundPlayer) pathFindingMove();
        img = setFrame();
        countFrame++;
    }

    public void alternateMovement() {
        switch (lastDir) {
            case UP:
                if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
                    y -= speed;
                }
                break;
            case DOWN:
                if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BOMB)) {
                    y += speed;
                }
                break;
            case LEFT:
                if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BOMB)) {
                    x -= speed;
                }
                break;
            case RIGHT:
                if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BRICK)
                        && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.WALL)
                        && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BOMB)) {
                    x += speed;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void die() {
        img = Sprite.batfs_dead.getFxImage();
    }

    public void loadDie(int count) {
        img = Sprite.movingSprite(Sprite.purple_dead1, Sprite.purple_dead2, Sprite.purple_dead3, count, 36).getFxImage();
    }
}
