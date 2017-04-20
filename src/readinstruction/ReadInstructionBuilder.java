package readinstruction;

import java.util.UUID;

/**
 * Created by Karol on 2017-04-20.
 */
public class ReadInstructionBuilder {
    private UUID instructionId;
    private int readAddress;
    private int arrivalTime = 0;
    private boolean hasDeadline = false;

    public ReadInstructionBuilder withInstructionId(UUID id){
        this.instructionId = instructionId;
        return this;
    }

    public ReadInstructionBuilder withReadAddress(int readAddres){
        this.readAddress = readAddres;
        return this;
    }

    public ReadInstructionBuilder withArrivalTime(int arrivalTime){
        this.arrivalTime = arrivalTime;
        return this;
    }

    public ReadInstructionBuilder withDeadline(){
        this.hasDeadline = true;
        return this;
    }
}
