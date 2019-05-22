package game.gamestate;

import commands.Controller;
import commands.DispenseCommand;
import game.Game;

import java.util.Random;

public abstract class GameState {
    static Random random = new Random();
    int delay = 0;
    int defaultInterval = 1500;

    GameState() {
        scheduler.setDaemon(true);
        scheduler.start();
        Game.getCurrentGame().addObserver((observable, arg) -> {
            if(Game.GAME_STOPPED.equals(arg))
                scheduler.interrupt();
        });
    }

    private Thread scheduler = new Thread(() -> {
        while(!Thread.interrupted()) {
            Controller.executeCommand(nextDispense());
            try {
                Thread.sleep(delay);
                delay = 0;
            } catch (InterruptedException e) {
                break;
            }
        }
    });

    abstract DispenseCommand nextDispense();
}
