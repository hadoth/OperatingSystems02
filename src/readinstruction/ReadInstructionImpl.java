package readinstruction;

import java.util.UUID;

/**
 * Created by Karol on 2017-04-20.
 */
public class ReadInstructionImpl extends ReadInstruction {
    public ReadInstructionImpl(UUID instructionId, int readAddres, int arrivalTime, boolean hasDeadline) {
        super(instructionId, readAddres, arrivalTime, hasDeadline);
    }
}
