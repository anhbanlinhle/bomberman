package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map;

public class Grass extends Entity {
    public Grass(int x, int y, Image img) {
        super(x, y, img);
        setType(0);
    }

    @Override
    public void update(Map map) {

    }
}
