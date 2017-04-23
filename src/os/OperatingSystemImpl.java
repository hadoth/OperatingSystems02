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
        this.readResults.add(instruction);
        if (this.consoleFlag)
            System.out.println(this.systemClock.getTime() + "\tfinish process ID: " + instruction.getInstructionId());
    }

    @Override
    public void generateReport() {
        //TODO: implement method
    }

    @Override
    public void update(int time) {
        this.tick(time);
    }

    public static OSBuilder builder(){
        return new OSBuilder();
    }

    private void tick(int time) {
        boolean listChecked = false;
        do {
            if (!this.readQueue.isEmpty() && this.readQueue.peek().getArrivalTime() == time) {
                if (this.consoleFlag)
                    System.out.println(time + "\tload process; ID: " + this.readQueue.peek().getInstructionId());
                this.systemScheduler.push(this.readQueue.remove());
            } else {
                listChecked = true;
            }
        } while (!listChecked && this.readQueue.size() > 0);
        if (this.readQueue.isEmpty() &&
                this.systemScheduler.isEmpty()) {
            this.systemClock.stop();
        }
    }
}
