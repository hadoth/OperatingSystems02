package os;

import readinstruction.ReadInstruction;
import scheduler.Scheduler;
import utils.Clock;
import utils.Observer;

import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Karol on 2017-04-20.
 */
public class OperatingSystemImpl implements OperatingSystem, Observer {
    private Clock systemClock;
    private Scheduler systemScheduler;
    private Queue<ReadInstruction> readQueue;
    private boolean consoleFlag;
    private ArrayList<ReadInstruction> readResults;

    public OperatingSystemImpl(
            Clock systemClock,
            Scheduler systemScheduler,
            Queue<ReadInstruction> processQueue,
            boolean consoleFlag){
        this.systemClock = systemClock;
        this.systemScheduler = systemScheduler;
        this.readQueue = processQueue;
        this.consoleFlag = consoleFlag;
        this.readResults = new ArrayList<>();
        this.systemClock.addObserver(this);
        this.systemClock.addObserver(systemScheduler);
        this.systemScheduler.setOs(this);
    }

    @Override
    public void run() {
        this.systemClock.start();
    }

    @Override
    public void push(ReadInstruction instruction) {

    }

    @Override
    public void generateReport() {

    }

    @Override
    public void update(int time) {

    }
}
