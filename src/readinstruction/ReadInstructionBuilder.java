package readinstruction;

import java.util.UUID;

/**
 * Created by Karol on 2017-04-20.
 */
public class ReadInstructionBuilder {
    private UUID instructionId;
    private int readAddres;
    private int arrivalTime = 0;
    private boolean hasDeadline = false;

    public ReadInstructionBuilder withInstructionId(UUID id){
        this.instructionId = instructionId;
        return this;
    }

    public ReadInstructionBuilder withReadAddres(int readAddres){
        this.readAddres = readAddres;
        return this;
    }
}
