package os;

import readinstruction.ReadInstruction;
import scheduler.Scheduler;
import utils.Clock;
import java.util.Queue;

/**
 * Created by Karol on 2017-04-20.
 */
public class OSBuilder {
    private Clock systemClock;
    private Scheduler systemScheduler;
    private Queue<ReadInstruction> readQueue;
    private boolean consoleFlag;
}
