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
    private int headPosition;
    private int cacheSize;
    private int headWay;

    public FcfsScheduler(){
        this.waitingQueue = new LinkedList<>();
        this.headPosition = 0;
        this.cacheSize = 64;
        this.headWay = 0;
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
        return "FCFS";
    }

    @Override
    public boolean isEmpty() {
        return this.waitingQueue.isEmpty();
    }

    @Override
    public void update(int time){
        if (this.waitingQueue.isEmpty()) return;
        if (this.waitingQueue.peek().getReadAddress() > this.headPosition) this.headPosition++;
        if (this.waitingQueue.peek().getReadAddress() < this.headPosition) this.headPosition--;
        if (this.waitingQueue.peek().getReadAddress() == this.headPosition){
            this.waitingQueue.peek().read(time);
            this.parentOs.push(this.waitingQueue.remove());
        }
    }

    @Override
    public boolean isFull() {
        return this.waitingQueue.size() >= this.cacheSize;
    }

    @Override
    public int getHeadWay() {
        return this.headWay;
    }
}
