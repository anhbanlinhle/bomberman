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
import uet.oop.bomberman.graphics.Texture;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    private Timer timer;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static Menu menu = new Menu();
    private GraphicsContext gc;
    private Canvas canvas;
    private Texture textures;
    
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
        textures = new Texture(canvas);
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        timer = new Timer(this);

        // Control system
        keyH = new KeyListener(scene);
        menu = new Menu(keyH);
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
        switch (menu.getGameState()) {
            case IN_MENU:
                menu.update();
                break;
            case IN_GAME:
                for (Entity entity : entities) {
//            entities.get(i).update(map);
                    entity.update();
                }
                enemyManager.update();
                break;
            case EXIT:
                System.exit(0);
                break;
                
        }
        // entities.forEach(Entity::update);

    }

    public void render() {
        switch (menu.getGameState()) {
            case IN_MENU:
                menu.render(gc);
                System.out.println("IN menu");
                break;
            case IN_GAME:
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            map.renderMap(gc);
            entities.forEach(g -> g.render(gc));
            enemyManager.getEnemyList().forEach(g -> g.render(gc));
                break;
            case EXIT:
                break;
        }

    // public void update() {
    //     // switch (menu.)
    //     // entities.forEach(Entity::update);
    // }
    }
}
