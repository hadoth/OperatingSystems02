package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Karol Pokomeda on 2017-04-21.
 */
public class CScanScheduler implements Scheduler {
    private LinkedList<ReadInstruction> waitingQueue;
    private OperatingSystem parentOs;
    private int headPosition;
    private int discSize;

    public CScanScheduler(int discSize){
        this.waitingQueue = new LinkedList<>();
        this.headPosition = -1;
        this.discSize = discSize;
    }

    @Override
    public void update(int time) {
        this.headPosition++;
        if (this.headPosition == 0){
            System.out.println("Start");
        }
        if (this.headPosition >= this.discSize) this.headPosition = -discSize;
        ReadInstruction result;
        for (int i = 0; i < this.waitingQueue.size() && !this.isEmpty() && this.headPosition >= 0; i++){
            result = this.waitingQueue.get(i);
            if(result.getReadAddress() == this.headPosition){
                result.read(time);
                this.parentOs.push(this.waitingQueue.remove(i));
                i--;
            } else if(result.getReadAddress() > this.headPosition) break;;
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
            boolean isInserted = false;
            int indexToSet = 0;
            for (int i = 0; !isInserted && i < this.waitingQueue.size(); i++){
                if (this.waitingQueue.get(i).getReadAddress() < instruction.getReadAddress()){
                    indexToSet = i+1;
                } else {
                    this.waitingQueue.add(indexToSet, instruction);
                }
            }
            if (!isInserted) this.waitingQueue.add(instruction);
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