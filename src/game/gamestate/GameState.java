package game.gamestate;

import commands.Controller;
import commands.DispenseCommand;

import java.util.Random;

public abstract class GameState {
    static Random random = new Random();
    int delay = 0;
    int defaultInterval = 2000;

    public GameState() {
        scheduler.start();
    }

    Thread scheduler = new Thread(() -> {
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
    {
        scheduler.setDaemon(true);
    }

    public void stop() {
        scheduler.interrupt();
    }

    abstract DispenseCommand nextDispense();
}
