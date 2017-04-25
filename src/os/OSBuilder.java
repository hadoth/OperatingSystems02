package os;

import readinstruction.ReadInstruction;
import readinstruction.ReadInstructionImpl;
import scheduler.Scheduler;
import utils.Clock;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Karol on 2017-04-20.
 */
public class OSBuilder {
    private Clock systemClock;
    private Scheduler systemScheduler;
    private Queue<ReadInstruction> readQueue;
    private boolean consoleFlag;

    public OSBuilder withReadInstructions(List<ReadInstruction> readQueue){
        this.readQueue = new LinkedList<>();
        for (ReadInstruction instruction : readQueue) this.readQueue.add(instruction);
        return this;
    }

    public OSBuilder withInstructionsSource(String filePath){
        return this.withReadInstructions(ReadInstruction.parseList(filePath));
    }

    public OSBuilder withClock(Clock systemClock){
        this.systemClock = systemClock;
        return this;
    }

    public OSBuilder withSystemScheduler(Scheduler systemScheduler){
        this.systemScheduler = systemScheduler;
        return this;
    }

    public OSBuilder withConsoleOutput(){
        this.consoleFlag = true;
        return this;
    }

    public OperatingSystemImpl build() {
        return new OperatingSystemImpl(
                this.systemClock,
                this.systemScheduler,
                this.readQueue,
                this.consoleFlag
        );
    }
}
