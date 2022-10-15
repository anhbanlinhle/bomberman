package uet.oop.bomberman.entities;

public abstract class MovableEntity extends Entity {

  private final int maxHP = 100;

  private int speed;
  private int direction;
  private int status;
  private int healthPoint;

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
