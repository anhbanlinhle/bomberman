package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class BombItem extends Entity {
  public BombItem(int x, int y, Image img) {
    super(x, y, img);
    setType(ENTITY_TYPE.BRICK);
  }

  @Override
  public void update(Map map) {

  }

}
