package os;

import readinstruction.ReadInstruction;
import readinstruction.ReadInstructionImpl;
import scheduler.Scheduler;
import utils.Clock;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Karol on 2017-04-20.
 */
public class OSBuilder {
    private Clock systemClock;
    private Scheduler systemScheduler;
    private Queue<ReadInstruction> readQueue;
    private boolean consoleFlag;

    public OSBuilder withReadInstructions(ArrayList<ReadInstruction> readQueue){
        this.readQueue = new LinkedList<>();
        for (ReadInstruction instruction : readQueue) this.readQueue.add(instruction);
        return this;
    }

    public OSBuilder withInstructionsSource(String filePath){
        Queue<ReadInstruction> result = new LinkedList<>();
        File inputFile = new File(filePath);
        try(FileReader fileIn = new FileReader(inputFile);
            Scanner dataIn = new Scanner(fileIn)){
            while(dataIn.hasNextLine()){
                String[] processText = dataIn.nextLine().split(", ");
                result.add(new ReadInstructionImpl(
                        UUID.fromString(processText[0]),
                        Integer.parseInt(processText[1]),
                        Integer.parseInt(processText[2]),
                        Boolean.parseBoolean(processText[3])));
            }
            this.readQueue = result;
        } catch (IOException e){
            throw new IllegalArgumentException("File not found or data corrupted");
        }
        return this;
    }
}
