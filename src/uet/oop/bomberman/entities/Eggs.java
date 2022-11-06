package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

public class Eggs extends Enemy {

  private int randomMove = 1;

  public Eggs(int x, int y, Image img) {
    super(x, y, img);
    direction = DIRECTION.RIGHT;
    speed = 1;
  }

  @Override
  public void updateMove(Map map) {
    int min = 1;
    int max = 2;

    if (randomMove == 1) {
      if (checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x, y - speed, DIRECTION.UP, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.UP;
        y -= speed;
      } else
        randomMove = 2;
    }
    if (randomMove == 2) {
      if (checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.WALL)
          && checkCollisionMap(map, x, y + speed, DIRECTION.DOWN, ENTITY_TYPE.BOMB)) {
        direction = DIRECTION.DOWN;
        y += speed;
      } else
        randomMove = 1;
      ;
    }
  }

  public Image setFrame() {
    return Sprite.movingSprite(Sprite.eggs1, Sprite.eggs2, Sprite.eggs3, countFrame, 60).getFxImage();
  }

  @Override
  public void update() {
    super.update();
    countFrame++;
    countFrame = countFrame % 60;
    img = setFrame();
  }

  @Override
  public void die() {
    img = Sprite.eggs_dead.getFxImage();
  }

  public void loadDie(int count) {
    img = Sprite.movingSprite(Sprite.green_dead1, Sprite.green_dead2, Sprite.green_dead3, count, 36).getFxImage();
  }
}
