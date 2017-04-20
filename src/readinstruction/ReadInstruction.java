package readinstruction;

import java.util.UUID;

/**
 * Created by Karol on 2017-04-20.
 */
public abstract class ReadInstruction {
    private UUID instructionId;
    private int readAddres;
    private int arrivalTime;
    private boolean hasDeadline;

    public ReadInstruction (UUID instructionId, int readAddres, int arrivalTime, boolean hasDeadline){
        this.arrivalTime = arrivalTime;
        this.instructionId = instructionId;
        this.readAddres = readAddres;
        this.hasDeadline = hasDeadline;
    }
}
