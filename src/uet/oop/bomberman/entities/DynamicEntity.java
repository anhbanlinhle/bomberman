package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

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
