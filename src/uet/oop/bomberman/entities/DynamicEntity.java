package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

import java.util.List;

public abstract class DynamicEntity extends Entity {

    private int maxHP;

    protected boolean isAlive;

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

    public boolean checkCollisionMap(Map map, int a, int b, DIRECTION direction, ENTITY_TYPE type) {

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
            if (type_check == type) {
                if (b < (yCheck + 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // up left
            type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
            if (type_check == type) {
                if (b < (yCheck + 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // up right
            type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
            if (type_check == type) {
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
            if (type_check == type) {
                if (b > (yCheck - 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // down left
            type_check = map.entityTypeAtCordinate(xCheck - 1, yCheck);
            if (type_check == type) {
                if (b > (yCheck - 1) * Sprite.SCALED_SIZE && a < xCheck * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // down right
            type_check = map.entityTypeAtCordinate(xCheck + 1, yCheck);
            if (type_check == type) {
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
            if (type_check == type) {
                if (a < (xCheck + 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // left up
            type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
            if (type_check == type) {
                if (a < (xCheck + 1) * Sprite.SCALED_SIZE && b < yCheck * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // left down
            type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
            if (type_check == type) {
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
            if (type_check == type) {
                if (a > (xCheck - 1) * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // right up
            type_check = map.entityTypeAtCordinate(xCheck, yCheck - 1);
            if (type_check == type) {
                if (a > (xCheck - 1) * 32 && b < yCheck * Sprite.SCALED_SIZE) {
                    return false;
                }
            }

            // right down
            type_check = map.entityTypeAtCordinate(xCheck, yCheck + 1);
            if (type_check == type) {
                if (a > (xCheck - 1) * 32 && b > yCheck * Sprite.SCALED_SIZE) {
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