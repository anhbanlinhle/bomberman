package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public abstract class DynamicEntity extends Entity {

  private int maxHP;

  protected int speed;
  protected int direction;
  protected int status;
  protected int healthPoint;
  protected int countFrame;
  protected boolean moving;

  public DynamicEntity(int x, int y, Image img) {
    super(x, y, img);
  }

  public int getMaxHP() {
    return this.maxHP;
  }

  public int getHealthPoint() {
    return this.healthPoint;
  }

  public void setHealthPoint(int healthPoint) {
    this.healthPoint = healthPoint;
  }

  public int getSpeed() {
    return this.speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getDirection() {
    return this.direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int convertToMapCordinate(int n) {
    int newCor = n + Sprite.DEFAULT_SIZE;
    newCor = newCor - newCor % Sprite.SCALED_SIZE;
    newCor /= Sprite.SCALED_SIZE;
    return newCor;
  }

  public boolean checkCollisionMap(Map map, int a, int b, int direction) {

    // player's map cordinate
    int xMap = convertToMapCordinate(this.x);
    int yMap = convertToMapCordinate(this.y);

    // cor for check
    int xCheck;
    int yCheck;

    // check up
    if (direction == 0) {
      xCheck = xMap;
      yCheck = yMap - 1;

      // just up
      if (map.entityTypeAtCordinate(xCheck, yCheck) != 0) {
        if (b + 4 < (yCheck + 1) * 32) {
          return false;
        }
      }

      // up left
      if (map.entityTypeAtCordinate(xCheck - 1, yCheck) != 0) {
        if (b + 4 < (yCheck + 1) * 32 && a < (xCheck - 1) * 32 + 28) {
          return false;
        }
      }

      // up right
      if (map.entityTypeAtCordinate(xCheck + 1, yCheck) != 0) {
        if (b + 4 < (yCheck + 1) * 32 && a > (xCheck + 1) * 32 - 28) {
          return false;
        }
      }

    }

    // check down
    if (direction == 1) {
      xCheck = xMap;
      yCheck = yMap + 1;

      // just down
      if (map.entityTypeAtCordinate(xCheck, yCheck) != 0) {
        if (b - 4 > (yCheck - 1) * 32) {
          return false;
        }
      }

      // down left
      if (map.entityTypeAtCordinate(xCheck - 1, yCheck) != 0) {
        if (b - 4 > (yCheck - 1) * 32 && a < (xCheck - 1) * 32 + 28) {
          return false;
        }
      }

      // down right
      if (map.entityTypeAtCordinate(xCheck + 1, yCheck) != 0) {
        if (b - 4 > (yCheck - 1) * 32 && a > (xCheck + 1) * 32 - 28) {
          return false;
        }
      }
    }

    // check left
    if (direction == 2) {
      xCheck = xMap - 1;
      yCheck = yMap;

      // just left
      if (map.entityTypeAtCordinate(xCheck, yCheck) != 0) {
        if (a + 4 < (xCheck + 1) * 32) {
          return false;
        }
      }

      // left up
      if (map.entityTypeAtCordinate(xCheck, yCheck - 1) != 0) {
        if (a + 4 < (xCheck + 1) * 32 && b < (yCheck - 1) * 32 + 28) {
          return false;
        }
      }

      // left down
      if (map.entityTypeAtCordinate(xCheck, yCheck + 1) != 0) {
        if (a + 4 < (xCheck + 1) * 32 && b > (yCheck + 1) * 32 - 28) {
          return false;
        }
      }
    }
    // check right
    if (direction == 3) {
      xCheck = xMap + 1;
      yCheck = yMap;

      // just right
      if (map.entityTypeAtCordinate(xCheck, yCheck) != 0) {
        if (a - 4 > (xCheck - 1) * 32) {
          return false;
        }
      }

      // right up
      if (map.entityTypeAtCordinate(xCheck, yCheck - 1) != 0) {
        if (a - 4 > (xCheck - 1) * 32 && b < (yCheck - 1) * 32 + 28) {
          return false;
        }
      }

      // right down
      if (map.entityTypeAtCordinate(xCheck, yCheck + 1) != 0) {
        if (a - 4 > (xCheck - 1) * 32 && b > (yCheck + 1) * 32 - 28) {
          return false;
        }
      }
    }
    return true;
  }

  public enum Direction {
    UP,
    RIGHT,
    LEFT,
    DOWN
  }

  public enum Status {
    WALK,
    IDLE
  }

  @Override
  public void render(GraphicsContext gc) {
    super.render(gc);
  }
}
