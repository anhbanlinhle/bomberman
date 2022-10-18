package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends DynamicEntity {
  private boolean explode;
  private int count;
  private int frameNum;

  public Bomb(int x, int y, Image img) {
    super(x, y, img);
    count = 0;
    explode = false;
  }

  public void update(Map map) {
    count++;
    count = count % 99;
    img = setFrame();
  }

  public Image setFrame() {
    frameNum = count / 33;
    Image frame = null;
    switch (frameNum) {
      case 0:
          frame = Sprite.bomb.getFxImage();
          break;
      case 1:
          frame = Sprite.bomb_1.getFxImage();
          break;
      case 2:
          frame = Sprite.bomb_2.getFxImage();
          break;
    }
    return frame;
  }

}
