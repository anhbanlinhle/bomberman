package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bombManager;

public class Bomb extends DynamicEntity {

  private final int BOMB_TIME = 90;
  static boolean bombIsPlaced;
  private boolean explode;

  private int frameNum;

  public Bomb(int x, int y, Image img) {
    super(x, y, Sprite.bomb_exploded1.getFxImage());
    explode = false;
    bombIsPlaced = false;
    setType(ENTITY_TYPE.BOMB);
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
}
