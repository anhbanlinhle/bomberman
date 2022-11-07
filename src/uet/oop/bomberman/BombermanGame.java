package uet.oop.bomberman;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.controller.BombManager;
import uet.oop.bomberman.controller.Camera;
import uet.oop.bomberman.controller.EnemyManager;
import uet.oop.bomberman.controller.Timer;
import uet.oop.bomberman.controller.Menu.STATE;
import uet.oop.bomberman.controller.KeyListener;
import uet.oop.bomberman.controller.Menu;
import uet.oop.bomberman.controller.SoundFile;
import uet.oop.bomberman.entities.dynamic.Bomber;
import uet.oop.bomberman.graphics.LoadGame;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Texture;

import static uet.oop.bomberman.controller.Menu.STATE;
import static uet.oop.bomberman.graphics.Map.bomberX;
import static uet.oop.bomberman.graphics.Map.bomberY;

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
    public Camera camera;
    
    public Scanner saveScanner;
    public static LoadGame loadGame = new LoadGame();
    public static FileOutputStream fout;
    public static Map map;
    public static BombManager bombManager;
    public static EnemyManager enemyManager;

    public static int levelNo = 0;

    public static Bomber bomberman;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException{

        // Tao Canvas
        saveScanner = new Scanner(loadGame.getFile());
        SoundFile.backgroundGame.loop();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        this.textures = new Texture(canvas);
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Level
        levelNo = saveScanner.nextInt();

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        timer = new Timer(this);

        // Control system

        keyH = new KeyListener(scene);
        menu = new Menu(keyH);

        // Entity
        createGame(levelNo);
    }

    public void createGame(int mapNo) {
        map = new Map();
        map.setCurrentMapNo(mapNo + 1);
        bombManager = new BombManager();
        enemyManager = new EnemyManager();
        map.loadMap(keyH);
        camera = new Camera(1, 1, map.getWidth(), map.getHeight(), Texture.WIDTH, Texture.HEIGHT);
        enemyManager.setEnemyList(map.getEnemyList());
        bomberman = new Bomber(bomberY, bomberX, Sprite.player_right.getFxImage(), keyH);
        map.formatMapData();
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
                SoundFile.backgroundGame.loop();
                SoundFile.win.stop();
                SoundFile.playGame.stop();
                SoundFile.lose.stop();
                menu.update();
                break;
            case GAME_OVER:
                menu.update();
                SoundFile.lose.loop();
                break;
            case NEW_GAME:
                levelNo = 0;
                cleanGame();
                createGame(levelNo);
                menu.setGameState(STATE.IN_GAME);
                menu.setIsPlaying(true);
                menu.update();
                break;
            case IN_GAME:
                SoundFile.lose.stop();
                SoundFile.backgroundGame.stop();
                SoundFile.win.stop();
                
                if (bomberman.isAlive()) {
                    if (!menu.isMuted()) {
                        SoundFile.playGame.loop();
                    }
                    if (bomberman.isMeetPortal()) {
                        SoundFile.win.play();
                        menu.setGameState(STATE.NEXT_STAGE);
                        menu.update();
                    }
                    if (keyH.isPressed(KeyCode.ESCAPE)) {
                        menu.setIsPlaying(false);
                    }
                    if (menu.isPlaying()) {
                        bomberman.update();
                        enemyManager.update();
                        camera.update(bomberman);
                    } else {
                        
                        //SoundFile.playGame.stop();
                        menu.update();
                        try {
                            fout = new FileOutputStream(loadGame.getFile());
                            loadGame.writeFile(loadGame,fout, levelNo);
                            loadGame.writeFile(loadGame, fout, bomberman.getX(), bomberman.getY());
                            fout.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            //e.printStackTrace();
                        }

                    }
                    if (keyH.isPressed(KeyCode.ESCAPE)) {
                        menu.setIsPlaying(false);
                        menu.update();
                    }
                } else {
                    bomberman.update();
                    bombManager.update();
                }
                if (bomberman.loseDelay == LOSE_DELAY) {
                    System.out.println("Game Over");
                    bomberman = null;
                    menu.setGameState(STATE.GAME_OVER);
                    menu.update();
                    cleanGame();
                    createGame(levelNo);
                }
                break;
            case NEXT_STAGE:
                SoundFile.playGame.stop();
                menu.update();
                break;
            case NEXT_LEVEL:
                if (levelNo < 2) {
                    levelNo++;
                    cleanGame();
                    createGame(levelNo);
                    menu.setGameState(STATE.IN_GAME);
                    menu.update();
                } else {
                    menu.setGameState(STATE.WIN_GAME);
                    menu.update();
                }
                break;
            case WIN_GAME:
                menu.update();
                break;
            case EXIT:
                cleanGame();
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
                if (!menu.isPlaying()) {
                    menu.render(gc);
                }
                map.renderMap(gc, camera);
                bomberman.render(gc, camera);
                enemyManager.render(gc, camera);
                if (!menu.isPlaying()) {
                    menu.render(gc);
                }
                break;
            case NEXT_STAGE:
                menu.render(gc);
                break;
            case WIN_GAME:
                menu.render(gc);
                break;
            case EXIT:
                break;
            default:
                break;
        }
    }
}
