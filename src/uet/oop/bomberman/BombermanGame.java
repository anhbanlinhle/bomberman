package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.Menu;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Enemy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    private Timer timer;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private KeyListener keyH;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    Map map = new Map();

    @Override
    public void start(Stage stage) {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        Menu menu = new Menu();
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        timer = new Timer(this);

        createMap();
        keyH = new KeyListener(scene);
        map.loadMap(keyH);
        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), keyH);
        Entity enemy1 = new Enemy(10, 5, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        entities.add(enemy1);
        bomberman.update(map);
        enemy1.update(map);

        

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                System.out.print(
                        map.entityTypeAtCordinate(i, j) + " ");
            }
            System.out.println();
        }
    }

    public void loop() {
        render();
        update(map);

    }

    public void createMap() {
        // for (int i = 0; i < WIDTH; i++) {
        // for (int j = 0; j < HEIGHT; j++) {
        // Entity object;
        // if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
        // object = new Wall(i, j, Sprite.wall.getFxImage());
        // }
        // else {
        // object = new Grass(i, j, Sprite.grass.getFxImage());
        // }
        // stillObjects.add(object);
        // }
        // }
    }

    public void update(Map map) {
        // switch (menu.)
        // entities.forEach(Entity::update);
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update(map);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.renderMap(gc);
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
