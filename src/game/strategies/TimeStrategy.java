package game.strategies;
import commands.Controller;
import commands.EndGameCommand;
import java.util.Timer;
import java.util.TimerTask;

public class TimeStrategy implements GameStrategy {
    private int GameTime = 30;
    
    @Override
    public void initialize() {
        TimeStrategy timeStrategy = new TimeStrategy();
        Controller game = new Controller(timeStrategy);
        startTimer();
        EndGameCommand endGame = new EndGameCommand();
        game.executeCommand(endGame);

    }
    
    private void startTimer(){        
        TimerTask timerTask = new MyTimerTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 1*1000);
        try {
            Thread.sleep(GameTime*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    
    public void setGameTime(int GameTime) {
        this.GameTime = GameTime * 1000;
    }
}

class MyTimerTask extends TimerTask {
    int time = 0;
    @Override
    public void run() {
        completeTask();
    }
    private void completeTask() {
        try {
            Thread.sleep(1000);
            time++;
            System.out.println("" + time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

