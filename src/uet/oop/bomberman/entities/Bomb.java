package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends DynamicEntity {
  static boolean bombIsPlaced;
  private boolean explode;
  private int count;
  private int frameNum;

  public Bomb(int x, int y, Image img) {
    super(x, y, Sprite.bomb_exploded1.getFxImage());
    count = 0;
    explode = false;
    bombIsPlaced = false;
    setType(ENTITY_TYPE.BOMB);
  }

  public void update(Map map) {
  }

  @Override
  public void update() {
    // count = count % 99;
    img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, 60).getFxImage();
    count++;
    if (count >= 60) {
      explode = true;
    }
  }

  public boolean isExplode() {
    return explode;
  }
}
