package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setType(1);
    }

    @Override
    public void update(Map map) {

    }

}
