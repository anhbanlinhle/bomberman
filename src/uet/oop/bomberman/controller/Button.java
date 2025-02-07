package uet.oop.bomberman.controller;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.graphics.Texture;

public class Button {

    private Text buttonName;
    private int x;
    private int y;

    /**
     * Constructor 1.
     */
    public Button(int x, int y, String buttonName, Color color, Font font) {
        this.x = x;
        this.y = y;
        this.buttonName = new Text(buttonName);
        this.buttonName.setFont(font);
        this.buttonName.setFill(color);
    }

    /**
     * Constructor 1.
     */
    public Button(int x, int y, Text text) {
        this.x = x;
        this.y = y;
        this.buttonName = text;
    }

    /**
     * getFont().
     */
    
    public Font getFont() {
        return buttonName.getFont();
    }

    public Text getText() {
        return buttonName;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void render(GraphicsContext gc) {
        gc.setFont(buttonName.getFont());
        gc.setFill(buttonName.getFill());
        gc.fillText(buttonName.getText(), x, y);
    }

    public void renderChoosen(GraphicsContext gc) {
        gc.strokeText(buttonName.getText(), x, y);
        gc.setFont(Texture.PIXELFONT);
        gc.setFill(Color.CYAN);
        gc.fillText(buttonName.getText(), x, y);
    }

    public void renderMini(GraphicsContext gc) {
        gc.strokeText(buttonName.getText(), x, y);
        gc.setFont(Texture.PIXELFONTMINI);
        gc.setFill(Color.CYAN);
        gc.fillText(buttonName.getText(), x, y);
    }
    
}