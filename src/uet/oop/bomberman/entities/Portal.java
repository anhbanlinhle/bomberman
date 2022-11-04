package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.map;

public class Portal extends Entity {
  public Portal(int x, int y, Image img) {
    super(x, y, img);
    setType(ENTITY_TYPE.BRICK);
    map.setPortalX(x);
    map.setPortalY(y);
  }
}
