package uet.oop.bomberman.entities;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.map;

public class Doll extends Enemy {
  private DIRECTION newDir;

  public Doll(int x, int y, Image img) {
    super(x, y, img);
    direction = DIRECTION.RIGHT;
    speed = 1;
    newDir = DIRECTION.RIGHT;
  }

  public void findDirection(Map map) {
    int xMap = convertToMapCordinate(this.x);
    int yMap = convertToMapCordinate(this.y);

    ENTITY_TYPE type_check;

    List<DIRECTION> listDir = new ArrayList<>();

    // case up
    type_check = map.entityTypeAtCordinate(xMap, yMap - 1);
    if (type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.UP);
    }

    // case down
    type_check = map.entityTypeAtCordinate(xMap, yMap + 1);
    if (type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.DOWN);
    }

    // case left
    type_check = map.entityTypeAtCordinate(xMap - 1, yMap);
    if (type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.LEFT);
    }

    // case right
    type_check = map.entityTypeAtCordinate(xMap + 1, yMap);
    if (type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.RIGHT);
    }

    if (listDir.size() > 1) {
      DIRECTION curDir = direction;
      switch (direction) {
        case UP:
          curDir = DIRECTION.DOWN;
          break;
        case DOWN:
          curDir = DIRECTION.UP;
          break;
        case LEFT:
          curDir = DIRECTION.RIGHT;
          break;
        case RIGHT:
          curDir = DIRECTION.LEFT;
          break;
      }
      if (listDir.size() > 1) {
        for (int i = listDir.size() - 1; i >= 0; i--) {
          if (listDir.get(i) == curDir) {
            listDir.remove(i);
            break;
          }
        }
      }
    }
    int ranDir = (int) Math.floor(Math.random() * (listDir.size() - 1 + 1) + 1);

    newDir = listDir.get(ranDir - 1);
  }

  @Override
  public void updateMove(Map map) {
    if (x % 48 == 0 && y % 48 == 0 && convertToMapCordinate(x) % 2 == 1 && convertToMapCordinate(y) % 2 == 1)
      findDirection(map);

    if (newDir == DIRECTION.UP) {
      if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.UP;
        y -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.RIGHT) {
      if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.RIGHT;
        x += speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.LEFT) {
      if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.LEFT;
        x -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.DOWN) {
      if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.DOWN;
        y += speed;
      } else
        findDirection(map);
    }
  }

  public Image setFrame() {
    return switch (direction) {
      case UP, DOWN, NOT_MOVE, LEFT ->
              Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, countFrame, 60).getFxImage();
      case RIGHT ->
              Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, countFrame, 60).getFxImage();

    };
  }

  @Override
  public void update() {
    super.update();
    countFrame++;
    img = setFrame();
  }

  @Override
  public void die() {
    img = Sprite.doll_dead.getFxImage();
  }

  public void loadDie(int count) {
    img = Sprite.movingSprite(Sprite.red_dead1, Sprite.red_dead2, Sprite.red_dead3, count, 36).getFxImage();
  }
}
