package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class FlameItem extends Entity {
  public FlameItem(int x, int y, Image img) {
    super(x, y, img);
    setType(ENTITY_TYPE.BRICK);
  }

  @Override
  public void update(Map map) {

  }

}
