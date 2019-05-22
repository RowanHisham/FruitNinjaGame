package commands;

import application.MainGameFormController;
import application.SliceableTask;

public class DispenseCommand implements Command {
    private SliceableTask task;
    private long delay;

    @Override
    public void execute() {
        MainGameFormController.getInstance().scheduleSliceable(task, delay);
    }
}
