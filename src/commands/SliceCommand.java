package commands;

import game.objects.Fruit;
import game.objects.Sliceable;

public class SliceCommand implements Command {
    private Sliceable toSlice;

    public SliceCommand(Sliceable toSlice) {
        this.toSlice = toSlice;
    }
    @Override
    public void execute() {
        toSlice.slice();
        if(toSlice instanceof Fruit)
            Controller.executeCommand(new UpdateScoreCommand());
    }
}
