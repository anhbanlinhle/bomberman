package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setType(ENTITY_TYPE.WALL);
    }

    @Override
    public void update(Map map) {

    }

}
