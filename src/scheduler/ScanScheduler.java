package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class ScanScheduler implements Scheduler {
    private Queue<ReadInstruction> waitingQueue;
    private OperatingSystem parentOs;
    private int headPosition;

    public ScanScheduler(){
        this.waitingQueue = new LinkedList<>();
        this.headPosition = 0;
    }

    @Override
    public void update(int time) {

    }

    @Override
    public void setOs(OperatingSystem parentOs) {
        this.parentOs = parentOs;
    }

    @Override
    public void push(ReadInstruction instruction) {
        this.waitingQueue.add(instruction);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.waitingQueue.isEmpty();
    }
}
