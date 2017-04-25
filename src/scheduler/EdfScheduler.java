package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

/**
 * Created by Karol Pokomeda on 2017-04-24.
 */
public class EdfScheduler implements Scheduler {
    @Override
    public void update(int time) {

    }

    @Override
    public void setOs(OperatingSystem parentOs) {

    }

    @Override
    public void push(ReadInstruction instruction) {

    }

    @Override
    public String getName() {
        return "EDF";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
