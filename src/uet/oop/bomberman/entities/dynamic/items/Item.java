package uet.oop.bomberman.entities.dynamic.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.dynamic.DynamicEntity;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bombManager;

public class Item extends DynamicEntity {
  public Item(int x, int y, Image img) {
    super(x, y, img);
    setType(ENTITY_TYPE.BRICK);
    centerX = x;
    centerY = y;
    isAlive = true;
  }

  @Override
  public void update(Map map) {
    centerX = x + Sprite.SCALED_SIZE / 2;
    centerY = y + Sprite.SCALED_SIZE / 2;
    checkColisionFlame(bombManager);
  }

}
