package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

import java.util.List;

public abstract class DynamicEntity extends Entity {

    private int maxHP;

<<<<<<< HEAD
  protected int speed;
  protected int direction;
  protected int status;
  protected int healthPoint;
  protected int countFrame;
  protected boolean moving;
=======
    protected boolean isAlive;
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f

    protected int speed;
    protected DIRECTION direction;
    protected STATUS status;
    protected int healthPoint;
    protected int countFrame;
    protected int countDead;

    List<Entity> mapEntity;

    public DynamicEntity(int x, int y, Image img) {
        super(x, y, img);
        isAlive = true;
        countDead = 0;
    }
//
//    public DynamicEntity(int x, int y, Image img, List<Entity> map) {
//        super(x, y, img);
//    }

<<<<<<< HEAD
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
=======
    public int getMaxHP() {
        return this.maxHP;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f
    }

<<<<<<< HEAD
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
=======
    public int getSpeed() {
        return this.speed;
>>>>>>> 3e9c13dab7e27ade5a75c06d450287e78823587f
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public DIRECTION getDirection() {
        return this.direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

    public STATUS getStatus() {
        return this.status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getCountDead() {
        return this.countDead;
    }

    public void increaseCountDead() {
        this.countDead++;
    }

    public int convertToMapCordinate(int n) {
        int newCor = n + Sprite.DEFAULT_SIZE;
        newCor = newCor - newCor % Sprite.SCALED_SIZE;
        newCor /= Sprite.SCALED_SIZE;
        return newCor;
    }

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
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b < (yCheck + 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }


            // up left
            type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b < (yCheck + 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE - 6) {
                    return false;
                }
            }

            // up right
            type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b < (yCheck + 1) * Sprite.SCALED_SIZE && a > xCheck * Sprite.SCALED_SIZE + 6) {
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
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b > (yCheck - 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // down left
            type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b > (yCheck - 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE - 6) {
                    return false;
                }
            }

            // down right
            type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (b > (yCheck - 1) * Sprite.SCALED_SIZE && a > xCheck * Sprite.SCALED_SIZE + 6) {
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
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a < (xCheck + 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // left up
            type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a < (xCheck + 1) * Sprite.SCALED_SIZE && b < yCheck * Sprite.SCALED_SIZE - 3) {
                    return false;
                }
            }

            // left down
            type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a < (xCheck + 1) * Sprite.SCALED_SIZE && b > yCheck * Sprite.SCALED_SIZE + 3) {
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
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a - 4 > (xCheck - 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // right up
            type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a - 4 > (xCheck - 1) * 32 && b < yCheck * Sprite.SCALED_SIZE - 3) {
                    return false;
                }
            }

            // right down
            type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
            if (type_check == ENTITY_TYPE.BRICK
                    || type_check == ENTITY_TYPE.WALL
                    || type_check == ENTITY_TYPE.BOMB) {
                if (a - 4 > (xCheck - 1) * 32 && b > yCheck * Sprite.SCALED_SIZE + 3) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkColisionFlame(BombManager bombManager) {
        int flameLeftPos = 0,
                flameRightPos = 0,
                flameUpPos = 0,
                flameDownPos = 0;
        for (int i = 0; i < bombManager.getFlameList().size(); i++) {
            if (bombManager.getFlameList().get(i) != null) {
                flameLeftPos = bombManager.getFlameList().get(i).getX();
                flameRightPos = bombManager.getFlameList().get(i).getX() + Sprite.SCALED_SIZE;
                flameUpPos = bombManager.getFlameList().get(i).getY();
                flameDownPos = bombManager.getFlameList().get(i).getY() + Sprite.SCALED_SIZE;

                if (this.x + Sprite.SCALED_SIZE  - 12 > flameLeftPos
                        && this.x + 12 < flameRightPos
                        && this.y < flameDownPos
                        && this.y + Sprite.SCALED_SIZE > flameUpPos) {
                    System.out.println(flameLeftPos + " " + flameRightPos + " " + flameUpPos + " " + flameDownPos + "||" + this.x + " " + this.y);
                    System.out.println("DIE");
                    isAlive = false;
                }
            }
        }
        flameLeftPos = 0;
        flameRightPos = 0;
        flameUpPos = 0;
        flameDownPos = 0;
        return false;
    }

    public void die(){
    }
    public void loadDie(int count){
    }

    public enum DIRECTION {
        UP,
        RIGHT,
        LEFT,
        DOWN
    }

    public enum STATUS {
        WALK,
        IDLE
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}