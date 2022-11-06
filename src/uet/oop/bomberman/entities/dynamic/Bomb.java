package uet.oop.bomberman.entities.dynamic;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bombManager;

public class Bomb extends DynamicEntity {

  private final int BOMB_TIME = 90;
  static boolean bombIsPlaced;
  private boolean explode;
  private int count;

  public Bomb(int x, int y, Image img) {
    super(x, y, Sprite.bomb_exploded1.getFxImage());
    explode = false;
    bombIsPlaced = false;
    setType(ENTITY_TYPE.BOMB);
    isAlive = true;
  }

  public void update(Map map) {
  }

  @Override
  public void update() {
    checkColisionFlame(bombManager);
    img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, countFrame, BOMB_TIME).getFxImage();
    if (countFrame >= BOMB_TIME) {
      explode = true;
    }
    countFrame++;
  }

  public boolean isExplode() {
    return explode;
  }

  public void setExplode(boolean explode) {
    this.explode = explode;
  }
}
