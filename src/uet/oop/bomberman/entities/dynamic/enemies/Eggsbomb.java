package uet.oop.bomberman.entities.dynamic.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Eggsbomb extends Eggs {
  public Eggsbomb(int x, int y, Image img) {
    super(x, y, img);
    speed = 1;
    int randomMove = (int) Math.floor(Math.random() * 4);
    switch (randomMove) {
      case 0:
        direction = DIRECTION.UP;
        break;
      case 1:
        direction = DIRECTION.RIGHT;
        break;
      case 2:
        direction = DIRECTION.DOWN;
        break;
      case 3:
        direction = DIRECTION.LEFT;
        break;
    }
  }

  public Image setFrame() {
    int frameNum = countFrame / 10;
    Image frame = null;
    switch (frameNum) {
      case 0:
        frame = Sprite.eggs4.getFxImage();
        break;
      case 1:
        frame = Sprite.eggs5.getFxImage();
        break;
      case 2:
        frame = Sprite.eggs6.getFxImage();
        break;
      case 3:
        frame = Sprite.eggs7.getFxImage();
        break;
      case 4:
        frame = Sprite.eggs8.getFxImage();
        break;
      case 5:
        frame = Sprite.eggs9.getFxImage();
        break;
      case 6:
        frame = Sprite.eggs10.getFxImage();
        break;
    }
    
    return frame;
  }

  public void updateMove(Map map) {
    getRandomDirection();
  }

  public void update() {
    super.update();
    countFrame++;
    countFrame = countFrame % 70;
    img = setFrame();
  }

}
