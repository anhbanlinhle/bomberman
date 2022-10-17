package uet.oop.bomberman.controller;

import javafx.scene.input.KeyCode;

public class Menu {
    public static enum STATE {
        IN_MENU, IN_GAME;
    }

    private final int GAME = 0;
    private final int EXIT = 1;
    public static STATE GAME_STATE;
    private KeyListener keyListener;
    
    void render() {}

    void update() {
        switch(GAME_STATE) {
            case IN_MENU:
            //load ảnh menu.
            if(keyListener.isPressed(KeyCode.DIGIT1)) {
                //play game.
            }
            if(keyListener.isPressed(KeyCode.DIGIT2)) {
                //exit.
            }
        }
    }
}
