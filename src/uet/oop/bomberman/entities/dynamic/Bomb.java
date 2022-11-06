package uet.oop.bomberman.entities.dynamic;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends DynamicEntity {

  private final int BOMB_TIME = 90;
  static boolean bombIsPlaced;
  private boolean explode;
  private int count;

  public Bomb(int x, int y, Image img) {
    super(x, y, Sprite.bomb_exploded1.getFxImage());
    count = 0;
    explode = false;
    bombIsPlaced = false;
    setType(ENTITY_TYPE.BOMB);
    isAlive = true;
  }

  public void update(Map map) {
  }

  @Override
  public void update() {
    img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, BOMB_TIME).getFxImage();
    count++;
    explode = !isAlive;
    if (count >= BOMB_TIME) {
      explode = true;
    }
  }

  public boolean isExplode() {
    return explode;
  }

  public void setExplode(boolean explode) {
    this.explode = explode;
  }
}
