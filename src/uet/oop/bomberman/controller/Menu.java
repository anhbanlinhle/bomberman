package uet.oop.bomberman.controller;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Texture;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bombManager;
import static uet.oop.bomberman.BombermanGame.bomberman;

public class Menu {
    public static enum STATE {
        IN_MENU, NEW_GAME, IN_GAME, GAME_OVER, NEXT_STAGE, NEXT_LEVEL, WIN_GAME, EXIT
    }

    public static Image backGroundImage;
    public static Image gameOverImage;
    public static Image menuInGameImage;
    public static Image nextStageImage;
    public static Image winGameImage;
    public static Image bombItem;
    public static Image flameItem;
    public static Image speedItem;

    public Menu() {
        try {
            backGroundImage = new Image(Files.newInputStream(Paths.get("res/textures/backgr.jpg")));
            gameOverImage = new Image(Files.newInputStream(Paths.get("res/textures/gameOver.png")));
            menuInGameImage = new Image(Files.newInputStream(Paths.get("res/textures/menuingame.png")));
            nextStageImage = new Image(Files.newInputStream(Paths.get("res/textures/nextstage.png")));
            winGameImage = new Image(Files.newInputStream(Paths.get("res/textures/win_image.png")));
            bombItem = new Image(Files.newInputStream(Paths.get("res/textures/bomb_item.png")));
            flameItem = new Image(Files.newInputStream(Paths.get("res/textures/flame_item.png")));
            speedItem = new Image(Files.newInputStream(Paths.get("res/textures/speed_item.png")));
            System.out.println("duoc luon");

        } catch (IOException e) {

            System.out.println("hoi den");
        }
    }

    private long delayInput = 10;
    public static STATE GAME_STATE = STATE.IN_MENU;
    private KeyListener keyListener;
    private boolean isPlaying;
    private boolean isMuted = false;

    private int speed = 0;
    private int flame = 0;
    private int bomb = 0;

    List<Button> buttonMenu = new ArrayList<>();
    List<Button> buttonRetry = new ArrayList<>();
    List<Button> buttonInGame1 = new ArrayList<>();
    List<Button> buttonInGame2 = new ArrayList<>();
    List<Button> buttonNextStage = new ArrayList<>();
    List<Button> buttonWin = new ArrayList<>();
    List<Button> buttonFunc = new ArrayList<>();

    Button startButton;

    private int chooseButton;

    public boolean isMuted() {
        return this.isMuted;
    }

    public void setIsMuted(boolean isMuted) {
        this.isMuted = isMuted;
    }

    public long getDelayInput() {
        return this.delayInput;
    }

    public void setDelayInput(long delayInput) {
        this.delayInput = delayInput;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public KeyListener getKeyListener() {
        return this.keyListener;
    }

    public void setKeyListener(KeyListener keyListener) {
        this.keyListener = keyListener;
    }

    public Menu(KeyListener keyListener) {
        this.GAME_STATE = STATE.IN_MENU;
        this.keyListener = keyListener;

        Text text = new Text("Continue");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonMenu.add(new Button(
                (Texture.WIDTH * 3 / 4) * Sprite.SCALED_SIZE + 10 - (int) text.getLayoutBounds().getWidth() / 2 + 38,
                (Texture.HEIGHT / 6) * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2, text));

        text = new Text("New Game");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonMenu.add(new Button(
                (Texture.WIDTH * 3 / 4) * Sprite.SCALED_SIZE + 10 - (int) text.getLayoutBounds().getWidth() / 2 + 25,
                Texture.HEIGHT / 6 * Sprite.SCALED_SIZE + 10 + 3 * (int) text.getLayoutBounds().getHeight() / 2, text));

        text = new Text("Quit");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonMenu.add(new Button(
                (Texture.WIDTH * 3 / 4) * Sprite.SCALED_SIZE + 10 - (int) text.getLayoutBounds().getWidth() / 2 + 100,
                Texture.HEIGHT / 6 * Sprite.SCALED_SIZE + 10 + 12 * (int) text.getLayoutBounds().getHeight() / 2, text));
        chooseButton = 0;

        text = new Text("Yes");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonRetry.add(new Button(
                Texture.WIDTH * 2 / 8 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 3 / 4) * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2, text));

        text = new Text("No");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonRetry.add(new Button(
                Texture.WIDTH * 6 / 8 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 3 / 4) * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2, text));
        text = new Text("Continue");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame1.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE - 2 * (int) text.getLayoutBounds().getHeight() / 2 + 35,
                text));
        buttonInGame2.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE - 2 * (int) text.getLayoutBounds().getHeight() / 2 + 35,
                text));
        text = new Text("Mute");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame1.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 0 * (int) text.getLayoutBounds().getHeight() / 2 + 40,
                text));
        text = new Text("Unmute");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame2.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 0 * (int) text.getLayoutBounds().getHeight() / 2 + 40,
                text));
        text = new Text("Menu");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame1.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 2 * (int) text.getLayoutBounds().getHeight() / 2 + 45,
                text));
        buttonInGame2.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 2 * (int) text.getLayoutBounds().getHeight() / 2 + 45,
                text));
        text = new Text("Continue");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonNextStage.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 5 / 8) * Sprite.SCALED_SIZE + 1 * (int) text.getLayoutBounds().getHeight() / 2,
                text));
        text = new Text("Menu");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonNextStage.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 6 / 8) * Sprite.SCALED_SIZE + 2 * (int) text.getLayoutBounds().getHeight() / 2,
                text));

        text = new Text("QuangCute");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonWin.add(new Button(Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                Texture.HEIGHT / 2 * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2 + 100, text));
        text = new Text("VuongChan");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonWin.add(new Button(Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                Texture.HEIGHT / 2 * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2 + 50, text));
        text = new Text("Lynn");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonWin.add(new Button(Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                Texture.HEIGHT / 2 * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2, text));
        text = new Text("Thanks a lot game Devs!!!");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonWin.add(new Button(Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                Texture.HEIGHT / 2 * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2 - 50, text));
                
        text = new Text("" + speed);
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonFunc.add(new Button(480, 115, text));
        text = new Text("" + flame);
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonFunc.add(new Button(384, 115, text));
        text = new Text("" + bomb);
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonFunc.add(new Button(288, 115, text));

        }

    public STATE getGameState() {
        return GAME_STATE;
    }

    public void setGameState(STATE state) {
        GAME_STATE = state;
    }

    public void render(GraphicsContext gc) {
        switch (GAME_STATE) {
            case IN_GAME:
                if (!isPlaying()) {
                    gc.drawImage(menuInGameImage,
                            Texture.WIDTH * Sprite.SCALED_SIZE / 2 - (Texture.WIDTH * Sprite.SCALED_SIZE) / 4,
                            Texture.HEIGHT * Sprite.SCALED_SIZE / 2 - (Texture.HEIGHT * Sprite.SCALED_SIZE) / 4,
                            (Texture.WIDTH * Sprite.SCALED_SIZE) / 2,
                            (Texture.HEIGHT * Sprite.SCALED_SIZE) / 2);

                    gc.drawImage(bombItem, 248, 85, 32, 32);

                    gc.drawImage(flameItem, 344, 85, 32, 32);

                    gc.drawImage(speedItem, 440, 85, 32, 32);
                    
                    updateFunc();

                    for (int i = 0; i < buttonFunc.size(); i++) {
                        buttonFunc.get(i).render(gc);
                    }

                    if (!isMuted) {
                        for (int i = 0; i < buttonInGame1.size(); i++) {
                            if (chooseButton == i) {
                                buttonInGame1.get(i).renderMini(gc);
                            } else {
                                buttonInGame1.get(i).render(gc);
                            }
                        }
                    }
                    if (isMuted) {
                        for (int i = 0; i < buttonInGame2.size(); i++) {
                            if (chooseButton == i) {
                                buttonInGame2.get(i).renderMini(gc);
                            } else {
                                buttonInGame2.get(i).render(gc);
                            }
                        }
                    }
                }
                break;
            case IN_MENU:
                gc.drawImage(backGroundImage, 0, 0, Texture.WIDTH * Sprite.SCALED_SIZE,
                        Texture.HEIGHT * Sprite.SCALED_SIZE);
                for (int i = 0; i < buttonMenu.size(); i++) {
                    if (chooseButton == i) {
                        buttonMenu.get(i).renderChoosen(gc);
                    } else {
                        buttonMenu.get(i).render(gc);
                    }
                }
                break;
            case GAME_OVER:
                gc.drawImage(gameOverImage, 0, 0, Texture.WIDTH * Sprite.SCALED_SIZE,
                        Texture.HEIGHT * Sprite.SCALED_SIZE);
                for (int i = 0; i < buttonRetry.size(); i++) {
                    if (chooseButton == i) {
                        buttonRetry.get(i).renderChoosen(gc);
                    } else {
                        buttonRetry.get(i).render(gc);
                    }
                }
                break;
            case NEXT_STAGE:
                gc.drawImage(nextStageImage, 0, 0, Texture.WIDTH * Sprite.SCALED_SIZE,
                        Texture.HEIGHT * Sprite.SCALED_SIZE);
                for (int i = 0; i < buttonNextStage.size(); i++) {
                    if (chooseButton == i) {
                        buttonNextStage.get(i).renderChoosen(gc);
                    } else {
                        buttonNextStage.get(i).render(gc);
                    }
                }
                break;
            case WIN_GAME:
                gc.drawImage(winGameImage, 0, 0, Texture.WIDTH * Sprite.SCALED_SIZE,
                        Texture.HEIGHT * Sprite.SCALED_SIZE);
                for (int i = 0; i < buttonWin.size(); i++) {
                    if (chooseButton == i) {
                        buttonWin.get(i).renderChoosen(gc);
                    } else {
                        buttonWin.get(i).render(gc);
                    }
                }
                break;
            default:
                break;

        }
    }

    public void updateFunc() {
        buttonFunc = new ArrayList<>();
        Text text = new Text("" + (speed - 1));
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.BLACK);
        buttonFunc.add(new Button(480, 115, text));
        text = new Text("" + flame);
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.BLACK);
        buttonFunc.add(new Button(384, 115, text));
        text = new Text("" + bomb);
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.BLACK);
        buttonFunc.add(new Button(288, 115, text));
    }

    public void update() {
        switch (GAME_STATE) {
            case IN_MENU:
                long now = Timer.getNow();
                if (now - delayInput > Timer.TIME_PER_FRAME * 5) {
                    delayInput = now;
                    if (keyListener.isPressed(KeyCode.ENTER)) {
                        SoundFile.menuSelect.play();
                        switch (chooseButton) {
                            case 0:
                                setGameState(STATE.IN_GAME);
                                setIsPlaying(true);
                                break;
                            case 1:
                                setGameState(STATE.NEW_GAME);
                                break;
                            case 2:
                                setGameState(STATE.EXIT);
                                break;
                        }
                    } else if (keyListener.isPressed(KeyCode.S)) {
                        SoundFile.menuMove.play();
                        chooseButton++;
                        if (chooseButton == buttonMenu.size()) {
                            chooseButton = 0;
                        }
                    } else if (keyListener.isPressed(KeyCode.W)) {
                        SoundFile.menuMove.play();
                        chooseButton--;
                        if (chooseButton < 0) {
                            chooseButton = buttonMenu.size() - 1;
                        }

                    }
                    break;
                }
            case GAME_OVER:
                now = Timer.getNow();
                if (now - delayInput > Timer.TIME_PER_FRAME * 5) {
                    delayInput = now;
                    if (keyListener.isPressed(KeyCode.ENTER)) {
                        SoundFile.menuSelect.play();
                        switch (chooseButton) {
                            case 0:
                                setGameState(STATE.IN_GAME);
                                break;
                            case 1:
                                setGameState(STATE.IN_MENU);
                                break;
                        }
                    } else if (keyListener.isPressed(KeyCode.A)) {
                        SoundFile.menuMove.play();
                        chooseButton++;
                        if (chooseButton == buttonRetry.size()) {
                            chooseButton = 0;
                        }
                    } else if (keyListener.isPressed(KeyCode.D)) {
                        SoundFile.menuMove.play();
                        chooseButton--;
                        if (chooseButton < 0) {
                            chooseButton = buttonRetry.size() - 1;
                        }
                    }
                }
                break;
            case IN_GAME:               
                now = Timer.getNow();
                speed = bomberman.getSpeed();
                flame = bombManager.getFlameLength();
                bomb = bombManager.getBombRemain();
                if (now - delayInput > Timer.TIME_PER_FRAME * 5) {
                    delayInput = now;
                    if (!isPlaying()) {
                        if (keyListener.isPressed(KeyCode.ENTER)) {
                            SoundFile.menuSelect.play();
                            switch (chooseButton) {
                                case 0:
                                    setGameState(STATE.IN_GAME);
                                    setIsPlaying(true);
                                    break;
                                case 1:
                                    if (isMuted) {
                                        isMuted = false;
                                        SoundFile.playGame.loop();
                                    } else if (!isMuted) {
                                        isMuted = true;
                                        SoundFile.playGame.stop();
                                    }
                                    break;
                                case 2:
                                    setGameState(STATE.IN_MENU);
                                    chooseButton = 0;
                                    break;
                            }
                        } else if (keyListener.isPressed(KeyCode.S)) {
                            SoundFile.menuMove.play();
                            chooseButton++;
                            if (chooseButton == buttonInGame1.size()) {
                                chooseButton = 0;
                            }
                        } else if (keyListener.isPressed(KeyCode.W)) {
                            SoundFile.menuMove.play();
                            chooseButton--;
                            if (chooseButton < 0) {
                                chooseButton = buttonInGame1.size() - 1;
                            }
                        }
                    }
                }
                break;
            case NEXT_STAGE:
                now = Timer.getNow();
                if (now - delayInput > Timer.TIME_PER_FRAME * 5) {
                    delayInput = now;
                    if (keyListener.isPressed(KeyCode.ENTER)) {
                        SoundFile.menuSelect.play();
                        switch (chooseButton) {
                            case 0:
                                setGameState(STATE.NEXT_LEVEL);
                                break;
                            case 1:
                                setGameState(STATE.IN_MENU);
                                break;
                        }
                    } else if (keyListener.isPressed(KeyCode.S)) {
                        SoundFile.menuMove.play();
                        chooseButton++;
                        if (chooseButton == buttonNextStage.size()) {
                            chooseButton = 0;
                        }
                    } else if (keyListener.isPressed(KeyCode.W)) {
                        SoundFile.menuMove.play();
                        chooseButton--;
                        if (chooseButton < 0) {
                            chooseButton = buttonNextStage.size() - 1;
                        }
                    }
                }
                break;
            case WIN_GAME:
                
                    if (keyListener.isPressed(KeyCode.ESCAPE)) {
                        setGameState(GAME_STATE.IN_MENU);
                    } else {
                        SoundFile.menuMove.play();
                        chooseButton--;
                        if (chooseButton < 0) {
                            chooseButton = buttonWin.size() - 1;
                        }
                    }
                
                break;
            default:
                break;
        }
    }
}
