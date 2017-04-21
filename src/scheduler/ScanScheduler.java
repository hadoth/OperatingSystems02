package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class ScanScheduler implements Scheduler {
    private LinkedList<ReadInstruction> waitingQueue;
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
        if (this.isEmpty()) this.waitingQueue.add(instruction);
        else {
            int i = 0;
            while (instruction.getReadAddress() < this.waitingQueue.get(i).getReadAddress()) i++;
            this.waitingQueue.add(i, instruction);
        }
    }

    @Override
    public String getName() {
        return "SCAN";
    }

    @Override
    public boolean isEmpty() {
        return this.waitingQueue.isEmpty();
    }
}