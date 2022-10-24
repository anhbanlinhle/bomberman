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
    public static final int HEIGHT = 15;

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

    Map map = new Map();
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

        keyH = new KeyListener(scene);
        menu = new Menu(keyH);
        map.loadMap(keyH);
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
        switch (menu.getGameState()) {
            case IN_MENU:
                menu.update();
                break;
            case IN_GAME:
                for (int i = 0; i < entities.size(); i++) {
                    entities.get(i).update(map);
                }
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
                break;
            case EXIT:
                break;
        }
    }
}
