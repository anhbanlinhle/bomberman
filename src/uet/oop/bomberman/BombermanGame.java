package uet.oop.bomberman;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
import uet.oop.bomberman.entities.dynamic.enemies.*;
import uet.oop.bomberman.entities.dynamic.items.*;
import uet.oop.bomberman.graphics.LoadGame;
import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.Texture;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Entity.ENTITY_TYPE;

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
    public static char saveMap[][];
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
        // saveMap = new char[map.getHeight()][map.getWidth()];

        // Load file
        saveScanner.nextLine();
        bomberman.setX(saveScanner.nextInt());
        bomberman.setY(saveScanner.nextInt());
        saveScanner.nextLine();
        int maxBomb = saveScanner.nextInt();
        int bombRadius = saveScanner.nextInt();
        int speed = saveScanner.nextInt();
        bombManager.setBombRemain(maxBomb - bombManager.getBombList().size());
        bombManager.setFlameLength(bombRadius);
        bomberman.setSpeed(speed);
        saveScanner.nextLine();
        for (int i = 0; i < map.getHeight(); i++) {
            String s = saveScanner.nextLine();
            for (int j = 0; j < map.getWidth(); j++) {
                char temp = s.charAt(j);
                switch (temp) {
                    case '#':
                        Wall wall = new Wall(j, i, Sprite.wall.getFxImage());
                        map.replace(j, i, wall);
                        break;
                    case '*':
                        Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                        map.replace(j, i, brick);
                        break;
                    case 'x':
                        Portal portal = new Portal(j, i, Sprite.brick.getFxImage());
                        map.replace(j, i, portal);
                        break;
                    case 'X':
                        portal = new Portal(j, i, Sprite.portal.getFxImage());
                        portal.setType(ENTITY_TYPE.PORTAL);
                        map.replace(j, i, portal);
                        break;
                    case 'b':
                        BombItem bombItem = new BombItem(j, i, Sprite.brick.getFxImage());
                        map.replace(j, i, bombItem);
                        break;
                    case 'B':
                        bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                        bombItem.setType(ENTITY_TYPE.BOMB_ITEM);
                        map.itemList.add(bombItem);
                        map.replace(j, i, bombItem);
                        break;
                    case 'f':
                        FlameItem flameItem = new FlameItem(j, i, Sprite.brick.getFxImage());
                        map.replace(j, i, flameItem);
                        break;
                    case 'F':
                        flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                        flameItem.setType(ENTITY_TYPE.FLAME_ITEM);
                        map.itemList.add(flameItem);
                        map.replace(j, i, flameItem);
                        break;
                    case 's':
                        SpeedItem speedItem = new SpeedItem(j, i, Sprite.brick.getFxImage());
                        map.replace(j, i, speedItem);
                        break;
                    case 'S':
                        speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                        speedItem.setType(ENTITY_TYPE.SPEED_ITEM);
                        map.itemList.add(speedItem);
                        map.replace(j, i, speedItem);
                        break;
                    default:
                        Grass grass = new Grass(j, i, Sprite.grass.getFxImage());
                        map.replace(j, i, grass);
                        break;
                }
            }
        }
        List<Enemy> enemies = new ArrayList<>();
        int enemyNo = saveScanner.nextInt();
        for (int i = 0; i < enemyNo; i++) {
            saveScanner.nextLine();
            int type = saveScanner.nextInt();
            int x = saveScanner.nextInt();
            int y = saveScanner.nextInt();
            Enemy temp = null;
            switch (type) {
                case 1:
                    temp = new Balloom(y/Sprite.SCALED_SIZE, x/Sprite.SCALED_SIZE, Sprite.balloom_left1.getFxImage());
                    break;
                case 2:
                    temp = new Oneal(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.oneal_left1.getFxImage());
                    break;
                case 3:
                    temp = new Doll(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.doll_left1.getFxImage());
                    break;
                case 4:
                    temp = new Ghest(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.ghest_left1.getFxImage());
                    break;
                case 5:
                    temp = new Eggs(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.eggs1.getFxImage());
                    break;
                case 6:
                    temp = new Eggsbomb(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.eggs4.getFxImage());
                    break;
                case 7:
                    temp = new Batfs(y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE, Sprite.batfs_down1.getFxImage());
                    break;
                default:
                    break;
            }
            temp.setX(y);
            temp.setY(x);
            enemies.add(temp);
        }
        enemyManager.setEnemyList(enemies);
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
        saveMap = new char[map.getHeight()][map.getWidth()];
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

    public void writeMap() {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getEntity(j, i) instanceof Wall)
                    saveMap[i][j] = '#';
                else if (map.getEntity(j, i) instanceof Brick)
                    saveMap[i][j] = '*';
                else if (map.getEntity(j, i) instanceof Portal) {
                    if (map.entityTypeAtCordinate(j, i) == ENTITY_TYPE.BRICK)
                        saveMap[i][j] = 'x';
                    else
                        saveMap[i][j] = 'X';
                }
                else if (map.getEntity(j, i) instanceof BombItem)
                    if (map.entityTypeAtCordinate(j, i) == ENTITY_TYPE.BRICK)
                        saveMap[i][j] = 'b';
                    else
                        saveMap[i][j] = 'B';
                else if (map.getEntity(j, i) instanceof FlameItem)
                    if (map.entityTypeAtCordinate(j, i) == ENTITY_TYPE.BRICK)
                        saveMap[i][j] = 'f';
                    else
                        saveMap[i][j] = 'F';
                else if (map.getEntity(j, i) instanceof SpeedItem)
                    if (map.entityTypeAtCordinate(j, i) == ENTITY_TYPE.BRICK)
                        saveMap[i][j] = 's';
                    else
                        saveMap[i][j] = 'S';
                else
                    saveMap[i][j] = ' ';
            }
        }
    }

    public void saveGame() {
        try {
            fout = new FileOutputStream(loadGame.getFile());
            loadGame.writeFile(loadGame, fout, levelNo);
            loadGame.writeFile(loadGame, fout, bomberman.getX(), bomberman.getY());
            loadGame.writeFile(loadGame, fout, bombManager.getMaxBomb(), bombManager.getFlameLength(), bomberman.getSpeed());
            loadGame.writeFile(loadGame, fout, saveMap, map.getHeight(), map.getWidth());
            loadGame.writeFile(loadGame, fout, enemyManager.getEnemyList().size());
            for (int i = 0; i < enemyManager.getEnemyList().size(); i++) {
                int type = 0;
                if (enemyManager.getEnemyList().get(i) instanceof Balloom)
                    type = 1;
                else if (enemyManager.getEnemyList().get(i) instanceof Oneal)
                    type = 2;
                else if (enemyManager.getEnemyList().get(i) instanceof Doll)
                    type = 3;
                else if (enemyManager.getEnemyList().get(i) instanceof Ghest)
                    type = 4;
                else if (enemyManager.getEnemyList().get(i) instanceof Eggs)
                    type = 5;
                else if (enemyManager.getEnemyList().get(i) instanceof Eggsbomb)
                    type = 6;
                else if (enemyManager.getEnemyList().get(i) instanceof Batfs)
                    type = 7;
                
                loadGame.writeFile(loadGame, fout, type, enemyManager.getEnemyList().get(i).getY(), enemyManager.getEnemyList().get(i).getX());
            }
            fout.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
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
                    writeMap();
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
                        saveGame();
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
