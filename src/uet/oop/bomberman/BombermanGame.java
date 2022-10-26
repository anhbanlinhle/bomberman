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
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    private KeyListener keyH;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static Map map;
    public static BombManager bombManager;
    public static EnemyManager enemyManager;
    Bomber bomberman;

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

        // Control system
        keyH = new KeyListener(scene);
        map = new Map();
        bombManager = new BombManager();
        enemyManager = new EnemyManager();
        map.loadMap(keyH);
        enemyManager.setEnemyList(map.getEnemyList());

        // Entity
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), keyH);
        entities.add(bomberman);
        bomberman.update();
    }

    public void loop() {
        render();
        update();
    }


    public void update() {
        // switch (menu.)
        // entities.forEach(Entity::update);
        for (Entity entity : entities) {
//            entities.get(i).update(map);
            entity.update();
        }
        enemyManager.update();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        map.renderMap(gc);
        entities.forEach(g -> g.render(gc));
        enemyManager.getEnemyList().forEach(g -> g.render(gc));
    }
}
