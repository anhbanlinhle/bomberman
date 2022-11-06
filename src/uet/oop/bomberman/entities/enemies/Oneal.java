package uet.oop.bomberman.entities.enemies;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
  private DIRECTION newDir;
  
  public Oneal(int x, int y, Image img) {
    super(x, y, img);
    direction = DIRECTION.RIGHT;
    speed = 2;
    newDir = DIRECTION.LEFT;
  }

  public void findDirection(Map map) {
    int xMap = convertToMapCordinate(this.x);
    int yMap = convertToMapCordinate(this.y);

    ENTITY_TYPE type_check;

    List<DIRECTION> listDir = new ArrayList<>();

    // case up
    type_check = map.entityTypeAtCordinate(xMap, yMap - 1);
    if (type_check != ENTITY_TYPE.BRICK
        && type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.UP);
    }

    // case down
    type_check = map.entityTypeAtCordinate(xMap, yMap + 1);
    if (type_check != ENTITY_TYPE.BRICK
        && type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.DOWN);
    }

    // case left
    type_check = map.entityTypeAtCordinate(xMap - 1, yMap);
    if (type_check != ENTITY_TYPE.BRICK
        && type_check != ENTITY_TYPE.WALL
        && type_check != ENTITY_TYPE.BOMB) {
      listDir.add(DIRECTION.LEFT);
    }

    // case right
    type_check = map.entityTypeAtCordinate(xMap + 1, yMap);
    if (type_check != ENTITY_TYPE.BRICK
        && type_check != ENTITY_TYPE.WALL
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
        default:
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

    newDir = listDir.get(ranDir-1);
  }

  @Override
  public void updateMove(Map map) {
    if (this.x % 48 == 0 && this.y % 48 == 0)
      findDirection(map);
      
    if (newDir == DIRECTION.UP) {
      if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BRICK)
          && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.UP;
        y -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.RIGHT) {
      if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BRICK)
          && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.RIGHT;
        x += speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.LEFT) {
      if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BRICK)
          && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x - speed, y, DIRECTION.LEFT, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.LEFT;
        x -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.DOWN) {
      if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BRICK)
          && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
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
              Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, countFrame, 60).getFxImage();
      case RIGHT ->
              Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, countFrame, 60).getFxImage();

    };
  }

  @Override
  public void update() {
    super.update();
    countFrame++;
    countFrame = countFrame % 60;
    img = setFrame();
  }

  @Override
  public void die() {
    img = Sprite.oneal_dead.getFxImage();
  }

  public void loadDie(int count) {
    img = Sprite.movingSprite(Sprite.blue_dead1, Sprite.blue_dead2, Sprite.blue_dead3, count, 36).getFxImage();
  }
}
