package uet.oop.bomberman.entities;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

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
  public boolean checkCollisionMap(Map map, int a, int b, DIRECTION direction) {
    // player's map cordinate
    int xMap = convertToMapCordinate(this.x);
    int yMap = convertToMapCordinate(this.y);

    // cor for check
    int xCheck;
    int yCheck;
    ENTITY_TYPE type_check;

    // check up
    if (direction == DIRECTION.UP) {
      xCheck = xMap;
      yCheck = yMap - 1;

      // just up
      type_check = map.entityTypeAtCordinate(xCheck, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b < (yCheck + 1) * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // up left
      type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b < (yCheck + 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // up right
      type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b < (yCheck + 1) * Sprite.SCALED_SIZE && a > xCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }

    }

    // check down
    if (direction == DIRECTION.DOWN) {
      xCheck = xMap;
      yCheck = yMap + 1;

      // just down
      type_check = map.entityTypeAtCordinate(xCheck, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b > (yCheck - 1) * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // down left
      type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b > (yCheck - 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // down right
      type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (b > (yCheck - 1) * Sprite.SCALED_SIZE && a > xCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }
    }

    // check left
    if (direction == DIRECTION.LEFT) {
      xCheck = xMap - 1;
      yCheck = yMap;

      // just left
      type_check = map.entityTypeAtCordinate(xCheck, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a < (xCheck + 1) * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // left up
      type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a < (xCheck + 1) * Sprite.SCALED_SIZE && b < yCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // left down
      type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a < (xCheck + 1) * Sprite.SCALED_SIZE && b > yCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }
    }
    // check right
    if (direction == DIRECTION.RIGHT) {
      xCheck = xMap + 1;
      yCheck = yMap;

      // just right
      type_check = map.entityTypeAtCordinate(xCheck, yCheck);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a > (xCheck - 1) * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // right up
      type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a > (xCheck - 1) * 32 && b < yCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }

      // right down
      type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
      if (type_check == ENTITY_TYPE.WALL
          || type_check == ENTITY_TYPE.BOMB) {
        if (a > (xCheck - 1) * 32 && b > yCheck * Sprite.SCALED_SIZE) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void updateMove(Map map) {
    if (x % 48 == 0 && y % 48 == 0 && convertToMapCordinate(x) % 2 == 1 && convertToMapCordinate(y) % 2 == 1)
      findDirection(map);

    if (newDir == DIRECTION.UP) {
      if (checkCollisionMap(map, x, y - speed, DIRECTION.UP)) {
        direction = DIRECTION.UP;
        y -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.RIGHT) {
      if (checkCollisionMap(map, x + speed, y, DIRECTION.RIGHT)) {
        direction = DIRECTION.RIGHT;
        x += speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.LEFT) {
      if (checkCollisionMap(map, x - speed, y, DIRECTION.LEFT)) {
        direction = DIRECTION.LEFT;
        x -= speed;
      } else
        findDirection(map);
    }
    if (newDir == DIRECTION.DOWN) {
      if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN)) {
        direction = DIRECTION.DOWN;
        y += speed;
      } else
        findDirection(map);
    }
  }

  public Image setFrame() {
    int frameNum = countFrame / 20;
    Image frame = null;
    switch (direction) {
      case UP:
        switch (frameNum) {
          case 0:
            frame = Sprite.doll_left1.getFxImage();
            break;
          case 1:
            frame = Sprite.doll_left2.getFxImage();
            break;
          case 2:
            frame = Sprite.doll_left3.getFxImage();
            break;
        }
        break;
      case DOWN:
        switch (frameNum) {
          case 0:
            frame = Sprite.doll_right1.getFxImage();
            break;
          case 1:
            frame = Sprite.doll_right2.getFxImage();
            break;
          case 2:
            frame = Sprite.doll_right3.getFxImage();
            break;
        }
        break;
      case LEFT:
        switch (frameNum) {
          case 0:
            frame = Sprite.doll_left1.getFxImage();
            break;
          case 1:
            frame = Sprite.doll_left2.getFxImage();
            break;
          case 2:
            frame = Sprite.doll_left3.getFxImage();
            break;
        }
        break;
      case RIGHT:
        switch (frameNum) {
          case 0:
            frame = Sprite.doll_right1.getFxImage();
            break;
          case 1:
            frame = Sprite.doll_right2.getFxImage();
            break;
          case 2:
            frame = Sprite.doll_right3.getFxImage();
            break;
        }
        break;
    }
    return frame;
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
    img = Sprite.doll_dead.getFxImage();
  }

  public void loadDie(int count) {
    img = Sprite.movingSprite(Sprite.red_dead1, Sprite.red_dead2, Sprite.red_dead3, count, 36).getFxImage();
  }
}
