package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

import java.util.LinkedList;

/**
 * Created by Karol Pokomeda on 2017-04-24.
 */
public class EdfScheduler implements Scheduler {
    private LinkedList<ReadInstruction> waitingQueue;
    private OperatingSystem parentOs;
    private int headPosition;
    private int increment;
    private boolean allowsInterruptions;

    public EdfScheduler(boolean allowsInterruptions){
        this.waitingQueue = new LinkedList<>();
        this.headPosition = 0;
        this.increment = 0;
        this.allowsInterruptions = allowsInterruptions;
    }

    @Override
    public void update(int time) {
        this.headPosition += this.increment;
        ReadInstruction result;
        boolean readSuccess = false;
        for (int i = 0; i < this.waitingQueue.size(); i++){
            result = this.waitingQueue.get(i);
            if (result.getReadAddress() == this.headPosition){
                result.read(time);
                this.parentOs.push(this.waitingQueue.remove(i));
                i--;
                readSuccess = true;
            } else if (result.getReadAddress() > this.headPosition) break;
        }
        if (readSuccess) this.setDirection();
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
                    isInserted = true;
                }
            }
            if (!isInserted) this.waitingQueue.add(instruction);
        }
        if (allowsInterruptions || this.increment == 0) this.setDirection();
    }

    private void setDirection(){
        if (this.isEmpty()) {
            increment = 0;
            return;
        }
        int previous = this.waitingQueue.get(0).getReadAddress();
        boolean isPreviousImportant = false;
        int next = this.waitingQueue.get(this.waitingQueue.size()-1).getReadAddress();
        boolean isNextImportant = false;
        int temp;
        for (int i = 0; i < this.waitingQueue.size(); i++){
            if ((temp = this.waitingQueue.get(i).getReadAddress()) < this.headPosition){
                if (this.waitingQueue.get(i).hasDeadline()){
                    previous = temp;
                    isPreviousImportant = true;
                } else if (!isPreviousImportant){
                    previous = temp;
                }
            } else if (temp > this.headPosition){
                if (this.waitingQueue.get(i).hasDeadline()){
                    next = temp;
                    isNextImportant = true;
                    break;
                }
            } else {
                this.increment = 0;
                return;
            }
        }
        int distanceDown = this.headPosition - previous;
        int distanceUp = next - this.headPosition;
        if (distanceDown < 0 || (isNextImportant && !isPreviousImportant)){
            this.increment = 1;
            return;
        }
        if (distanceUp < 0 || (isPreviousImportant && !isNextImportant)){
            this.increment = -1;
            return;
        }
        if (distanceDown < distanceUp){
            this.increment = -1;
            return;
        }
        if (distanceDown > distanceUp){
            increment = 1;
            return;
        }
        if (increment == 0){
            increment = 1;
        }
    }

    @Override
    public String getName() {
        return "EDF";
    }

    @Override
    public boolean isEmpty() {
        return this.waitingQueue.isEmpty();
    }
}
