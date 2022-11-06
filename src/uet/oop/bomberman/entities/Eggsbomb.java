package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Eggsbomb extends Eggs {
  public Eggsbomb(int x, int y, Image img) {
    super(x, y, img);
    speed = 1;
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

  public void update() {
    super.update();
    countFrame++;
    countFrame = countFrame % 70;
    img = setFrame();
  }

}
