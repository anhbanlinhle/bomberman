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
    public static final int LOSE_DELAY = 120;
    public static final int WIDTH = Texture.WIDTH;
    public static final int HEIGHT = Texture.HEIGHT;

    public static Menu menu = new Menu();
    private GraphicsContext gc;
    private Canvas canvas;
    private Texture textures;
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
        
        // Entity
        createGame();
    }

    public void createGame() {
        map = new Map();
        bombManager = new BombManager();
        enemyManager = new EnemyManager();
        map.loadMap(keyH);
        enemyManager.setEnemyList(map.getEnemyList());
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage(), keyH);
    }

    public void cleanGame() {
        map = null;
        bombManager = null;
        enemyManager = null;
        bomberman = null;
        System.gc();
    }

    public void loop() {
        render();
        update();
    }

    public void update() {
        switch (menu.getGameState()) {
            case IN_MENU:
                menu.update();
                break;
            case GAME_OVER:
                menu.update();
                break;
            case IN_GAME:
                if (bomberman.isAlive()) {
                    bomberman.update();
                    enemyManager.update();
                }
                else {
                    bomberman.update();
                    bombManager.update();
                }
                if (bomberman.loseDelay == LOSE_DELAY) {
                    bomberman = null;
                    menu.setGameState(Menu.GAME_STATE.GAME_OVER);
                    menu.update();
                    cleanGame();
                    createGame();
                }
               break;
            
            case EXIT:
                System.exit(0);
                break;       
        }
    }

    public void render() {
        switch (menu.getGameState()) {
            case IN_MENU:
                menu.render(gc);
                break;
            case GAME_OVER:
                menu.render(gc);
                break;
            case IN_GAME:
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                map.renderMap(gc);
                bomberman.render(gc);
                enemyManager.getEnemyList().forEach(g -> g.render(gc));
                break;
            case EXIT:
                break;
        }
    }
}
