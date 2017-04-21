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
    private int discSize;

    public ScanScheduler(int discSize){
        this.waitingQueue = new LinkedList<>();
        this.headPosition = -1;
        this.discSize = discSize;
    }

    @Override
    public void update(int time) {
        this.headPosition++;
        if (this.headPosition >= this.discSize) this.headPosition = 0;
        int i = 0;
        ReadInstruction result;
        while (!this.isEmpty() && (result = this.waitingQueue.get(i)).getReadAddress() > this.headPosition){
            if(result.getReadAddress() == this.headPosition){
                result.read(time);
                this.parentOs.push(this.waitingQueue.remove(i));
            } else i++;
        }
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