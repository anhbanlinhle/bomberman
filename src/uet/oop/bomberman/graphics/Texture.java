package uet.oop.bomberman.graphics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.controller.Button;


public class Texture {
    public static final int WIDTH = 16;
    public static final int HEIGHT = 10;

    public static int coordinateX = 0;
    public static int coordinateY = 1;
    public static Font TITLEFONT;
    public static Font DEFAULTFONT;
    public static Font CHOOSENFONT;
    public static Font PIXELFONT;
    public static Font PIXELFONTMINI;


    private GraphicsContext gc;

    public Texture(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        try {
            DEFAULTFONT = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/default.ttf")), 60);
            CHOOSENFONT = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/title.ttf")), 15);
            PIXELFONT = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 75);
            PIXELFONTMINI = Font.loadFont(Files.newInputStream(Paths.get("res/fonts/pixel.ttf")), 40);
        } catch (IOException e) {
            System.out.println("[IOException] Wrong filepaths.");
        }
    }

    public void clearScreen(Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    
    public void renderText(Font font, Text text, int x, int y) {
        gc.setFont(font);
        gc.setFill(text.getFill());
        gc.fillText(text.getText(), x, y);
    }


    public void renderButton(Button button) {
        renderText(button.getFont(), button.getText(), button.getX(), button.getY());
    }
}
