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

public class Menu {
    public static enum STATE {
        IN_MENU, IN_GAME, GAME_OVER, EXIT
    }

    public static Image backGroundImage;
    public static Image gameOverImage;
    public static Image menuInGameImage;

    public Menu() {
        try {
            backGroundImage = new Image(Files.newInputStream(Paths.get("res/textures/backgr.jpg")));
            gameOverImage = new Image(Files.newInputStream(Paths.get("res/textures/gameOver.png")));
            menuInGameImage = new Image(Files.newInputStream(Paths.get("res/textures/menuingame.png")));
            System.out.println("duoc luon");

        } catch (IOException e) {

            System.out.println("hoi den");
        }
    }

    private final int GAME = 0;
    private final int EXIT = 1;
    private long delayInput = 10;
    public static STATE GAME_STATE = STATE.IN_MENU;
    private KeyListener keyListener;
    private boolean isPlaying;
    private boolean isMuted = false;
    List<Button> buttonMenu = new ArrayList<>();
    List<Button> buttonRetry = new ArrayList<>();
    List<Button> buttonInGame = new ArrayList<>();
    Button startButton;

    private int chooseButton;

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

    public int getGAME() {
        return this.GAME;
    }

    public int getEXIT() {
        return this.EXIT;
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

        Text text = new Text("PLAY");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonMenu.add(new Button(
                (Texture.WIDTH * 3 / 4) * Sprite.SCALED_SIZE + 10 - (int) text.getLayoutBounds().getWidth() / 2 + 50,
                (Texture.HEIGHT / 6) * Sprite.SCALED_SIZE + (int) text.getLayoutBounds().getHeight() / 2, text));

        text = new Text("EXIT");
        text.setFont(Texture.PIXELFONT);
        text.setFill(Color.WHITE);
        buttonMenu.add(new Button(
                (Texture.WIDTH * 3 / 4) * Sprite.SCALED_SIZE + 10 - (int) text.getLayoutBounds().getWidth() / 2 + 50,
                Texture.HEIGHT / 6 * Sprite.SCALED_SIZE + 10 + 3 * (int) text.getLayoutBounds().getHeight() / 2, text));
        chooseButton = GAME;

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
        buttonInGame.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE - 2 * (int) text.getLayoutBounds().getHeight() / 2,
                text));
        text = new Text("Mute");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 0 * (int) text.getLayoutBounds().getHeight() / 2,
                text));
        text = new Text("Unmute");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 0 * (int) text.getLayoutBounds().getHeight() / 2,
                text));
        text = new Text("Exit");
        text.setFont(Texture.PIXELFONTMINI);
        text.setFill(Color.WHITE);
        buttonInGame.add(new Button(
                Texture.WIDTH / 2 * Sprite.SCALED_SIZE - (int) text.getLayoutBounds().getWidth() / 2,
                (Texture.HEIGHT * 2 / 4) * Sprite.SCALED_SIZE + 2 * (int) text.getLayoutBounds().getHeight() / 2,
                text));
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
                    for (int i = 0; i < buttonInGame.size(); i++) {
                        if (chooseButton == i) {
                            buttonInGame.get(i).renderMini(gc);
                        } else {
                            buttonInGame.get(i).render(gc);
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
            default:
                break;

        }
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
                            case 2:
                                setGameState(STATE.IN_MENU);
                                break;
                            case 1:
                                if(isMuted) {
                                    
                                }
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
                if (now - delayInput > Timer.TIME_PER_FRAME * 5) {
                    delayInput = now;
                    if (!isPlaying()) {
                        if (keyListener.isPressed(KeyCode.ENTER)) {
                            SoundFile.menuSelect.play();
                            switch (chooseButton) {
                                case GAME:
                                    setGameState(STATE.IN_GAME);
                                    setIsPlaying(true);
                                    break;
                                case 3:
                                    setGameState(STATE.IN_MENU);
                                    chooseButton = 0;
                                    break;
                            }
                        } else if (keyListener.isPressed(KeyCode.S)) {
                            SoundFile.menuMove.play();
                            chooseButton++;
                            if (chooseButton == buttonInGame.size()) {
                                chooseButton = 0;
                            }
                        } else if (keyListener.isPressed(KeyCode.W)) {
                            SoundFile.menuMove.play();
                            chooseButton--;
                            if (chooseButton < 0) {
                                chooseButton = buttonMenu.size() - 1;
                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
