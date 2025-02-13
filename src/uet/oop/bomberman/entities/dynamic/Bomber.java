package uet.oop.bomberman.entities.dynamic;

import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.SoundFile;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.dynamic.enemies.Enemy;
import uet.oop.bomberman.entities.dynamic.items.BombItem;
import uet.oop.bomberman.entities.dynamic.items.FlameItem;
import uet.oop.bomberman.entities.dynamic.items.Item;
import uet.oop.bomberman.entities.dynamic.items.SpeedItem;

import static uet.oop.bomberman.BombermanGame.bombManager;
import static uet.oop.bomberman.BombermanGame.map;
import static uet.oop.bomberman.controller.EnemyManager.eggsy;
import static uet.oop.bomberman.graphics.Map.itemList;
import static uet.oop.bomberman.BombermanGame.enemyManager;

public class Bomber extends DynamicEntity {
    private final int DIE_TIME = 60;
    private KeyListener keyHandle;
    public int loseDelay;
    private boolean meetPortal;

    public boolean isMeetPortal() {
        return this.meetPortal;
    }

    
    public Bomber(int x, int y, Image img, KeyListener keyHandle) {
        super(x, y, img);
        this.keyHandle = keyHandle;
        speed = 2;
        status = STATUS.IDLE;
        direction = DIRECTION.RIGHT;
        isAlive = true;
        meetPortal = false;
    }

    @Override
    public void update() {
        if (isAlive) {
            updateMove(map);
            updateBombs();
            bombManager.update();
            countFrame++;
            countFrame = countFrame % 24;
            img = setFrame();
            checkColisionFlame(bombManager);
            centerX = x + Sprite.SCALED_SIZE / 2;
            centerY = y + Sprite.SCALED_SIZE / 2;
            checkCollisionItem();
            checkCollisionPortal();

            if (keyHandle.isPressed(KeyCode.N) && keyHandle.isPressed(KeyCode.E) 
                && keyHandle.isPressed(KeyCode.X) && keyHandle.isPressed(KeyCode.T)) {
                meetPortal = true;
            }

            if (keyHandle.isPressed(KeyCode.H) && keyHandle.isPressed(KeyCode.A)
                && keyHandle.isPressed(KeyCode.C) && keyHandle.isPressed(KeyCode.K)) {
                live = Integer.MAX_VALUE;
                speed = 6;
                bombManager.setBombRemain(10);
                bombManager.setFlameLength(10);;
            }

            if (checkCollisionEnemy() || checkColisionFlame(bombManager)) {
                SoundFile.bomberDie.play();
                live--;
                if (live == 0) {
                    isAlive = false;
                }
            }
        }
        else {
            SoundFile.playGame.stop();
            countDead++;
            die1(countDead);
            if (countDead >= 60)
                die2(countDead);
            int DELAY_REMOVE = 120;
            if (countDead == DELAY_REMOVE) {
                countDead = 0;
            }
            loseDelay++;
        }

    }

    public Image setFrame() {
        Image frame = null;
        if (status == STATUS.WALK) {
            switch (direction) {
                case UP:
                    frame = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, countFrame, 18).getFxImage();
                    break;
                case DOWN:
                    frame = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, countFrame, 18).getFxImage();
                    break;
                case LEFT:
                    frame = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, countFrame, 18).getFxImage();
                    break;
                case RIGHT:
                    frame = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, countFrame, 18).getFxImage();
                    break;
                default:
                    break;
            }
        } else {
            switch (direction) {
                case UP:
                    frame = Sprite.player_up.getFxImage();
                    break;
                case DOWN:
                    frame = Sprite.player_down.getFxImage();
                    break;
                case LEFT:
                    frame = Sprite.player_left.getFxImage();
                    break;
                case RIGHT:
                    frame = Sprite.player_right.getFxImage();
                    break;
                default:
                    break;
            }
        }
        return frame;
    }

    private void updateMove(Map map) {
        if (keyHandle.isPressed(KeyCode.W)
        || keyHandle.isPressed(KeyCode.A)
        || keyHandle.isPressed(KeyCode.S)
        || keyHandle.isPressed(KeyCode.D)) {
            if (keyHandle.isPressed(KeyCode.W)) {
                status = STATUS.WALK;
                direction = DIRECTION.UP;
                if (checkCollisionMap(map, x, y - speed, direction, ENTITY_TYPE.BRICK)
                && checkCollisionMap(map, x, y - speed, direction, ENTITY_TYPE.WALL)
                && checkCollisionMap(map, x, y - speed, direction, ENTITY_TYPE.BOMB)) {
                    y -= speed;
                }
                else {
                    while (y%Sprite.SCALED_SIZE != 0) {
                        y--;
                    }
                }
            } if (keyHandle.isPressed(KeyCode.D)) {
                status = STATUS.WALK;
                direction = DIRECTION.RIGHT;
                if (checkCollisionMap(map, x + speed, y, direction, ENTITY_TYPE.BRICK)
                && checkCollisionMap(map, x + speed, y, direction, ENTITY_TYPE.WALL)
                && checkCollisionMap(map, x + speed, y, direction, ENTITY_TYPE.BOMB)) {
                    x += speed;
                }
                else {
                    while (x%Sprite.SCALED_SIZE != 0) {
                        x++;
                    }
                }
            } if (keyHandle.isPressed(KeyCode.A)) {
                status = STATUS.WALK;
                direction = DIRECTION.LEFT;
                if (checkCollisionMap(map, x - speed, y, direction, ENTITY_TYPE.BRICK)
                && checkCollisionMap(map, x - speed, y, direction, ENTITY_TYPE.WALL)
                && checkCollisionMap(map, x - speed, y, direction, ENTITY_TYPE.BOMB)) {
                    x -= speed;
                }
                else {
                    while (x%Sprite.SCALED_SIZE != 0) {
                        x--;
                    }
                }
            } if (keyHandle.isPressed(KeyCode.S)) {
                status = STATUS.WALK;
                direction = DIRECTION.DOWN;
                if (checkCollisionMap(map, x, y + speed, direction, ENTITY_TYPE.BRICK)
                && checkCollisionMap(map, x, y + speed, direction, ENTITY_TYPE.WALL)
                && checkCollisionMap(map, x, y + speed, direction, ENTITY_TYPE.BOMB)) {
                    y += speed;
                }
                else {
                    while (y%Sprite.SCALED_SIZE != 0) {
                        y++;
                    }
                }
            }
        } else {
            status = STATUS.IDLE;
        }
    }

    private void checkCollisionItem() {
        List<Item> checkList = itemList;
        for (int i = 0; i < checkList.size(); i++) {
            int difX = Math.abs(checkList.get(i).getX() - x);
            int difY = Math.abs(checkList.get(i).getY() - y);

            if (checkList.get(i).isAlive()) {
                if (difX < Sprite.SCALED_SIZE && difY < Sprite.SCALED_SIZE) {
                    SoundFile.item.play();
                    if (checkList.get(i) instanceof BombItem) {
                        bombManager.increaseBomb();
                    }
                    if (checkList.get(i) instanceof SpeedItem) {
                        speed++;
                        switch (direction) {
                            case UP:
                                while (y % speed != 0) {
                                    y--;
                                }
                                break;
                            case DOWN: 
                                while (y % speed != 0) {
                                    y++;
                                }
                                break;
                            case LEFT:
                                while (x % speed != 0) {
                                    x--;
                                }
                                break;
                            case RIGHT:
                                while (x % speed != 0) {
                                    x++;
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    if (checkList.get(i) instanceof FlameItem) {
                        bombManager.increaseFlameLength();
                    }
                    int grassX = checkList.get(i).getCenterX(),
                        grassY = checkList.get(i).getCenterY();
                    Grass grass = new Grass(grassX, grassY, Sprite.grass.getFxImage());
                    map.replace(grassX, grassY, grass);
                    checkList.remove(i);
                }
            }
            else {
                checkList.remove(i);
            }
        }
    }

    public void checkCollisionPortal() {
        int difX = Math.abs(map.getPortalX() - x),
            difY = Math.abs(map.getPortalY() - y);
        if (difX < Sprite.SCALED_SIZE && difY < Sprite.SCALED_SIZE) {
            if (enemyManager.getEnemyList().size() - eggsy <= 0 ) {
                meetPortal = true;
                System.out.println("Win");
            }
        }
    }

    private void updateBombs() {
        if (keyHandle.isPressed(KeyCode.SPACE)) {
            int xBomb = convertToMapCordinate(x);
            int yBomb = convertToMapCordinate(y);
            bombManager.addBomb(new Bomb(xBomb, yBomb, Sprite.bomb.getFxImage()));
        }
    }

    private boolean checkCollisionEnemy() {
        List<Enemy> checkList = enemyManager.getEnemyList();
        for (int i = 0; i < checkList.size(); i++) {
            
            int difX = Math.abs(checkList.get(i).getCenterX() - centerX);
            int difY = Math.abs(checkList.get(i).getCenterY() - centerY);
            
            if (difX + 6 < Sprite.SCALED_SIZE && difY + 6 < Sprite.SCALED_SIZE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(GraphicsContext gc, Camera camera) {
        bombManager.render(gc, camera);
        super.render(gc,camera );
    }

    public void die1(int count) {
        img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, count, DIE_TIME).getFxImage();
    }

    public void die2(int count) {
        img = Sprite.movingSprite(Sprite.player_dead4, Sprite.player_dead5, Sprite.player_dead6, count, DIE_TIME).getFxImage();
    }

    
}
