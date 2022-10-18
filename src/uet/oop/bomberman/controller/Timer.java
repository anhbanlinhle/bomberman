package uet.oop.bomberman.controller;

import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import uet.oop.bomberman.BombermanGame;

public class Timer {

    
    private static final int FPS = 30;
    private static final long TIME_PER_FRAME = 1000000000 / FPS;
    private static final long NEXT_TIME = TIME_PER_FRAME + System.nanoTime();
    


    
    private AnimationTimer timer;
    private BombermanGame game;
    
    public Timer(BombermanGame game) {
        
        this.game = game;
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                game.loop();
                try {
                    TimeUnit.NANOSECONDS.sleep(delay());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        };
        timer.start();
    }

    public long delay() {
        long delayTime = NEXT_TIME - System.nanoTime();
        delayTime /= 1000000;
        if (delayTime < 0) {
            return 0;
        }
        return delayTime;
    }

    public static long getNow() {
        return System.nanoTime();
    }
}
