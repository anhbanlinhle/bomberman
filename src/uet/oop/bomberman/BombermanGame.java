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
import uet.oop.bomberman.entities.*;
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

    public static Map map;
    Bomber bomberman;

    public static BombManager bombManager;

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
        // Key
        keyH = new KeyListener(scene);
        map = new Map();
        map.loadMap(keyH);
        bombManager = new BombManager();


        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), keyH);
        Entity enemy1 = new Enemy(10, 5, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        entities.add(enemy1);
        bomberman.update(map);
        enemy1.update(map);

    }

    public void loop() {
        render();
        update(map);

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
        entities.forEach(g -> g.render(gc));
    }
}
