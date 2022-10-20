package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        setType(2);
    }

    @Override
    public void update(Map map) {
        // TODO Auto-generated method stub

    }

}
