package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends DynamicEntity {
    static boolean bombisPlaced;
  private boolean explode;
  private int count;
  private int frameNum;

  public Bomb(int x, int y, Image img) {
    super(x, y, img);
    count = 0;
    explode = false;
    bombisPlaced = false;
  }

  public void update(Map map) {
  }

  @Override
  public void update() {
    count ++;
    if(count >= 60){
      explode = true;
    }
//    count = count % 99;
    img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, count, 60).getFxImage();
  }

//  public Image setFrame() {
//    frameNum = count / 33;
//    Image frame = null;
//    switch (frameNum) {
//      case 0:
//          frame = Sprite.bomb.getFxImage();
//          break;
//      case 1:
//          frame = Sprite.bomb_1.getFxImage();
//          break;
//      case 2:
//          frame = Sprite.bomb_2.getFxImage();
//          break;
//    }
//    return frame;
//  }


  public boolean isExplode() {
    return explode;
  }
}
