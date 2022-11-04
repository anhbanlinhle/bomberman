package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.map;

public class Portal extends Entity {
  public Portal(int x, int y, Image img) {
    super(x, y, img);
    setType(ENTITY_TYPE.BRICK);
    map.setPortalX(x*Sprite.SCALED_SIZE);
    map.setPortalY(y*Sprite.SCALED_SIZE);
  }
}
