package commands;

import game.objects.Sliceable;

public class SliceCommand implements Command {
    private Sliceable toSlice;

    @Override
    public void execute() {
        toSlice.slice();
    }
}
