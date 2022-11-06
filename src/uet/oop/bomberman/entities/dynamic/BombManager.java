package uet.oop.bomberman.entities.dynamic;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.SoundFile;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.Entity.ENTITY_TYPE;
import uet.oop.bomberman.entities.dynamic.items.BombItem;
import uet.oop.bomberman.entities.dynamic.items.FlameItem;
import uet.oop.bomberman.entities.dynamic.items.Item;
import uet.oop.bomberman.entities.dynamic.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.map;
import static uet.oop.bomberman.graphics.Map.itemList;

public class BombManager {

    private final int DELAY_PLACE_BOMB = 10;
    int countDelayBomb = 0;

    Flame.DIRECTION[] flameDirection = {Flame.DIRECTION.UP, Flame.DIRECTION.DOWN, Flame.DIRECTION.RIGHT, Flame.DIRECTION.LEFT};
    private int bombRemain;
    private int flameLength;
    int flamePosX, flamePosY;
    boolean[] bombPath;
    private List<Bomb> bombList;
    private List<Flame> flameList;



    public BombManager() {
        bombList = new ArrayList<>();
        flameList = new ArrayList<>();
        bombRemain = 1;
        flameLength = 1;
        flamePosX = 0;
        flamePosY = 0;
    }

    public void addBomb(Bomb bomb) {
        boolean checkDuplicate = true;
        for (Bomb i :
                bombList) {
            if (bomb.getX() == i.getX() && bomb.getY() == i.getY()) {
                checkDuplicate = false;
            }
        }
        if (checkDuplicate && bombRemain > 0
            && countDelayBomb % DELAY_PLACE_BOMB == 0 ) {
            int difX = Math.abs(bomb.convertToMapCordinate(bomb.getX()) * Sprite.SCALED_SIZE - map.getPortalX());
            int difY = Math.abs(bomb.convertToMapCordinate(bomb.getY()) * Sprite.SCALED_SIZE - map.getPortalY());
            if (difX != 0 || difY != 0) {
                SoundFile.bombPlace.play();
                bombList.add(bomb);
                map.replace(bomb.convertToMapCordinate(bomb.getX()), bomb.convertToMapCordinate(bomb.getY()), bomb);
                bombRemain--;
            }
        }
    }

    public void bombExploded(int index) {
        SoundFile.bombExplose.play();
        bombPath = new boolean[]{true, true, true, true};

        int bomX = bombList.get(index).getX() / Sprite.SCALED_SIZE;
        int bomY = bombList.get(index).getY() / Sprite.SCALED_SIZE;



        for (int i = 1; i <= flameLength; i++) {
            flamePosX = bomX;
            flamePosY = bomY;
            flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BOMB, Flame.DIRECTION.DOWN));
            Flame.FLAME_TYPE type = Flame.FLAME_TYPE.MIDDLE;
            if(i == flameLength) type = Flame.FLAME_TYPE.END;
            for (int j = 0; j < 4; j++) {
                //Update flame position
                switch (flameDirection[j]) {
                    case UP:
                        flamePosX = bomX;
                        flamePosY = bomY - i;
                        break;
                    case DOWN:
                        flamePosX = bomX;
                        flamePosY = bomY + i;
                        break;
                    case LEFT:
                        flamePosX = bomX - i;
                        flamePosY = bomY;
                        break;
                    case RIGHT:
                        flamePosX = bomX + i;
                        flamePosY = bomY;
                        break;
                }
                if(bombPath[j]){
                    if(!(map.getEntity(flamePosX, flamePosY) instanceof Wall)
                            && !(map.entityTypeAtCordinate(flamePosX, flamePosY) == ENTITY_TYPE.BRICK)
                            && !(map.entityTypeAtCordinate(flamePosX, flamePosY) == ENTITY_TYPE.BOMB_ITEM)
                            && !(map.entityTypeAtCordinate(flamePosX, flamePosY) == ENTITY_TYPE.SPEED_ITEM)
                            && !(map.entityTypeAtCordinate(flamePosX, flamePosY) == ENTITY_TYPE.FLAME_ITEM)) {

                        flameList.add(new Flame(flamePosX, flamePosY, type, flameDirection[j]));
                    }
                    
                    else if(map.getEntity(flamePosX, flamePosY) instanceof Brick) {
                        bombPath[j] = false;
                        Grass grass =  new Grass(flamePosX, flamePosY, Sprite.grass.getFxImage());
                        map.replace(flamePosX, flamePosY,grass);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    } 
                    
                    else if (map.getEntity(flamePosX, flamePosY) instanceof BombItem
                            && map.getEntity(flamePosX, flamePosY).getType() == ENTITY_TYPE.BRICK) {
                        bombPath[j] = false;
                        BombItem item = new BombItem(flamePosX, flamePosY, Sprite.powerup_bombs.getFxImage());
                        item.setType(ENTITY_TYPE.BOMB_ITEM);
                        map.replace(flamePosX, flamePosY, item);
                        itemList.add(item);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    } 

                    else if (map.getEntity(flamePosX, flamePosY) instanceof SpeedItem
                            && map.getEntity(flamePosX, flamePosY).getType() == ENTITY_TYPE.BRICK) {
                        bombPath[j] = false;
                        SpeedItem item = new SpeedItem(flamePosX, flamePosY, Sprite.powerup_speed.getFxImage());
                        item.setType(ENTITY_TYPE.SPEED_ITEM);
                        map.replace(flamePosX, flamePosY, item);
                        itemList.add(item);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    }

                    else if (map.getEntity(flamePosX, flamePosY) instanceof FlameItem
                            && map.getEntity(flamePosX, flamePosY).getType() == ENTITY_TYPE.BRICK) {
                        bombPath[j] = false;
                        FlameItem item = new FlameItem(flamePosX, flamePosY, Sprite.powerup_flames.getFxImage());
                        item.setType(ENTITY_TYPE.FLAME_ITEM);
                        map.replace(flamePosX, flamePosY, item);
                        itemList.add(item);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    }

                    else if (map.getEntity(flamePosX, flamePosY) instanceof Portal
                            && map.getEntity(flamePosX, flamePosY).getType() == ENTITY_TYPE.BRICK) {
                        bombPath[j] = false;
                        Portal portal = new Portal(flamePosX, flamePosY, Sprite.portal.getFxImage());
                        portal.setType(ENTITY_TYPE.PORTAL);
                        map.replace(flamePosX, flamePosY, portal);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    }

                    else if (map.getEntity(flamePosX, flamePosY) instanceof Item) {
                        bombPath[j] = false;
                        ((DynamicEntity)map.getEntity(flamePosX, flamePosY)).setAlive(false);
                        Grass grass = new Grass(flamePosX, flamePosY, Sprite.grass.getFxImage());
                        map.replace(flamePosX, flamePosY, grass);
                        flameList.add(new Flame(flamePosX, flamePosY, Flame.FLAME_TYPE.BRICK, flameDirection[j]));
                    }
                   else {
                       bombPath[j] = false;
                   }
                }
            }
        }
        Grass restore = new Grass(bomX, bomY, Sprite.grass.getFxImage());
        map.replace(bomX, bomY, restore);
        removeBomb(index);

    }

    public void removeBomb(int index) {
        bombList.remove(index);
        bombRemain++;
    }

    public void update() {
        for (int i = 0; i < bombList.size(); i++) {
            bombList.get(i).update();
            if (bombList.get(i).isExplode() || !bombList.get(i).isAlive()) {
                bombExploded(i);
            }
        }
        for (int i = 0; i < flameList.size(); i++) {
            flameList.get(i).update();
            if (flameList.get(i).isFlameEnd()) {
                flameList.remove(i);
                i--;
            }
        }
        countDelayBomb++;
    }

    public void render(GraphicsContext gc, Camera camera) {
        for (Bomb bomb : bombList) {
            bomb.render(gc, camera);
        }
        for (Flame flame : flameList) {
            flame.render(gc, camera);
        }
    }

    public List<Flame> getFlameList() {
        return flameList;
    }
    public List<Bomb> getBombList() {return bombList;}

    public void increaseBomb() {
        bombRemain++;
    }

    public int getBombRemain() {
        return bombRemain;
    }

    public void increaseFlameLength() {
        flameLength++;
    }

    public int getFlameLength() {
        return flameLength;
    }

    public void setBombRemain(int bombRemain) {
        this.bombRemain = bombRemain;
    }

    public void setFlameLength(int flameLength) {
        this.flameLength = flameLength;
    }

}
