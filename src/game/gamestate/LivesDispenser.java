package game.gamestate;

import commands.DispenseCommand;
import game.objects.Sliceable;
import game.objects.SliceableType;


public class LivesDispenser extends GameState {
    private int dispensed = 0;

    @Override
    DispenseCommand nextDispense() {
        /*if(dispensed++ > 4) {
            scheduler.interrupt();
            Game.getCurrentGame().setState(CONTINUOUS);
            return ret;
        }*/
        dispensed++;
        double fatalChance = 0.02, dangerousChance = 0.04, special1Chance = 0.04, special2Chance = 0.02;
        double r = random.nextDouble();
        double p = fatalChance;
        if(r<p) {
            delay = random.nextInt(1001);
            return new DispenseCommand(Sliceable.newSliceable(SliceableType.FATAL_BOMB), delay);
        }
        else if(r<(p+=dangerousChance)) {
            delay = random.nextInt(1001);
            return new DispenseCommand(Sliceable.newSliceable(SliceableType.DANGEROUS_BOMB), delay);
        }
        else if(r<(p+=special1Chance)) {
            delay = random.nextInt(1001);
            return new DispenseCommand(Sliceable.newSliceable(SliceableType.SPECIAL_1), delay);
        }
        else if(r<(p+=special2Chance)) {
            delay = random.nextInt(1001);
            return new DispenseCommand(Sliceable.newSliceable(SliceableType.SPECIAL_2), delay);
        }
        else {
            SliceableType type = SliceableType.class.getEnumConstants()[random.nextInt(6)];
            delay = random.nextInt(1001);
            return new DispenseCommand(Sliceable.newSliceable(type), delay);
        }
    }
}
