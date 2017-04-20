package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Karol on 2017-04-20.
 */
public class FcfsScheduler implements Scheduler {
    private Queue<ReadInstruction> waitingQueue;
    private OperatingSystem parentOs;

    public FcfsScheduler(){
        this.waitingQueue = new LinkedList<>();
    }

    @Override
    public void setOs(OperatingSystem parentOs) {

    }

    @Override
    public void push(ReadInstruction instruction) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void update(int time) {

    }
}
