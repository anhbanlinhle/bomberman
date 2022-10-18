package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public abstract class DynamicEntity extends Entity {

  private int maxHP;

  protected int speed;
  protected int direction;
  protected int status;
  protected int healthPoint;

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


  public int retMapH(Map map) {
    return map.getHeight();
  }
  public boolean checkCollision(Map map) {
    int xMap = convertToMapCordinate(this.x);
    int yMap = convertToMapCordinate(this.y);

    if(map.entityTypeAtCordinate(xMap, yMap) == 0) {
      return false;
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
}
