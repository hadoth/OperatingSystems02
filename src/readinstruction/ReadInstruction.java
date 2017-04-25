package readinstruction;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Karol on 2017-04-20.
 */
public abstract class ReadInstruction {
    private UUID instructionId;
    private int readAddress;
    private int arrivalTime;
    private boolean hasDeadline;
    private int waitTime;

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

    public int read(int actualTime){
        this.waitTime = actualTime - this.readAddress;
        return this.readAddress;
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

    public static List<ReadInstruction> parseList(String filePath){
        ArrayList<ReadInstruction> result = new ArrayList<>();
        File inputFile = new File(filePath);
        try{
            FileReader fileIn = new FileReader(inputFile);
            Scanner dataIn = new Scanner(fileIn);
            while(dataIn.hasNextLine()){
                String[] processText = dataIn.nextLine().split(";");
                result.add(new ReadInstructionImpl(
                        UUID.fromString(processText[0]),
                        Integer.parseInt(processText[1]),
                        Integer.parseInt(processText[2]),
                        Boolean.parseBoolean(processText[3])));
            }
            if (fileIn != null) fileIn.close();
            if (dataIn != null) fileIn.close();
        } catch (IOException e){
            throw new IllegalArgumentException("File not found or data corrupted");
        }
        return result;
    }
}
