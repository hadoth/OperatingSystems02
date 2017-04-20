package readinstruction;

import java.util.UUID;

/**
 * Created by Karol on 2017-04-20.
 */
public abstract class ReadInstruction {
    private UUID instructionId;
    private int readAddress;
    private int arrivalTime;
    private boolean hasDeadline;

    public ReadInstruction (UUID instructionId, int readAddress, int arrivalTime, boolean hasDeadline){
        this.arrivalTime = arrivalTime;
        this.instructionId = instructionId;
        this.readAddress = readAddress;
        this.hasDeadline = hasDeadline;
    }


    public UUID getInstructionId() {
        return instructionId;
    }

    public int getReadAddress() {
        return readAddress;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public boolean hasDeadline() {
        return hasDeadline;
    }

    @Override
    public String toString(){
        return this.instructionId.toString() + "'" +
                this.readAddress + "'" +
                this.arrivalTime + "'" +
                this.hasDeadline + "'";
    }

    public static ReadInstructionBuilder builder(){
        return new ReadInstructionBuilder();
    }
}
