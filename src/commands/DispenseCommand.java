package commands;

import application.MainGameFormController;
import application.SliceableTask;
import game.objects.Sliceable;

public class DispenseCommand implements Command {
    private SliceableTask task;
    private long delay;

    public DispenseCommand(Sliceable sliceable, long delay) {
        task = new SliceableTask(sliceable);
        this.delay = delay;
    }
    @Override
    public void execute() {
        MainGameFormController.getInstance().scheduleSliceable(task, delay);
    }
}
