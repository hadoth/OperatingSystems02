package os;

import readinstruction.ReadInstruction;
import scheduler.Scheduler;
import utils.Clock;
import utils.Observer;
import utils.Statistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Created by Karol on 2017-04-20.
 */
public class OperatingSystemImpl implements OperatingSystem, Observer {
    private Clock systemClock;
    private Scheduler systemScheduler;
    private Queue<ReadInstruction> readQueue;
    private boolean consoleFlag;
    private ArrayList<ReadInstruction> readResults;

    public OperatingSystemImpl(
            Clock systemClock,
            Scheduler systemScheduler,
            Queue<ReadInstruction> processQueue,
            boolean consoleFlag){
        this.systemClock = systemClock;
        this.systemScheduler = systemScheduler;
        this.readQueue = processQueue;
        this.consoleFlag = consoleFlag;
        this.readResults = new ArrayList<>();
        this.systemClock.addObserver(this);
        this.systemClock.addObserver(systemScheduler);
        this.systemScheduler.setOs(this);
    }

    @Override
    public void run() {
        this.systemClock.start();
    }

    @Override
    public void push(ReadInstruction instruction) {
        this.readResults.add(instruction);
        if (this.consoleFlag)
            System.out.println(this.systemClock.getTime() + "\tfinish process ID: " + instruction.getInstructionId());
    }

    @Override
    public void saveReport(String path) {


        File myFile = new File(path);
        try (
                FileWriter fileOut = new FileWriter(myFile);
                PrintWriter dataOut = new PrintWriter(fileOut)
        ){
            myFile.createNewFile();

            //TODO: add content

            if (fileOut != null) fileOut.close();
            if (dataOut != null) dataOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String generateReport(String path) {
        int counter = this.readResults.size();
        int[] waitTime = new int[counter];
        ArrayList<Integer> priorityWaitTimeList = new ArrayList<>();

        for (int i = 0; i < counter; i++){
            waitTime[i] = this.readResults.get(i).getWaitTime();
            if (this.readResults.get(i).hasDeadline()) priorityWaitTimeList.add(waitTime[i]);
        }

        int[] priorityWaitTime = new int[priorityWaitTimeList.size()];
        for (int i = 0; i < priorityWaitTimeList.size(); i++) priorityWaitTime[i] = priorityWaitTimeList.get(i);
        double waitTimeMean = Statistics.meanValue(waitTime);
        double waitTimeDeviation = Statistics.standardDeviation(waitTime);
        int waitTimeMax = Statistics.maxValue(waitTime);
        double priorityWaitTimeMean = Integer.MIN_VALUE;
        double priorityWaitTimeDeviation = Integer.MIN_VALUE;
        int priorityWaitTimeMax = Integer.MIN_VALUE;
        if (!priorityWaitTimeList.isEmpty()){
            priorityWaitTimeMean = Statistics.meanValue(priorityWaitTime);
            priorityWaitTimeDeviation = Statistics.standardDeviation(priorityWaitTime);
            priorityWaitTimeMax = Statistics.maxValue(priorityWaitTime);
        }

        StringBuilder resultBuilder = new StringBuilder();
        String[] pathElements = path.split("/");
        path = pathElements[pathElements.length-1];
        String[] description = path.replace(".csv", "").split("_");


        resultBuilder.append("DISC READ REPORT\n");
        resultBuilder.append("Scheduler:\t\t\t\t\t\t");
        resultBuilder.append(this.systemScheduler.getName());
        resultBuilder.append("\nRead location trend:\t\t\t" );
        resultBuilder.append(description[0]);
        resultBuilder.append("\nInstruction arrival trend:\t\t");
        resultBuilder.append(description[1]);
        resultBuilder.append("\nHas priority instructions:\t\t");
        resultBuilder.append(description[2]);
        if (!priorityWaitTimeList.isEmpty())resultBuilder.append("\nSTANDARD PROCESSES");
        resultBuilder.append(String.format("\nMean wait time:\t\t\t\t\t%.3f +- %.3f (MAX: %d)", waitTimeMean, waitTimeDeviation, waitTimeMax));
        if (!priorityWaitTimeList.isEmpty())resultBuilder.append("\nPRIORITY PROCESSES\n");
        if (!priorityWaitTimeList.isEmpty())resultBuilder.append(String.format("Mean wait time:\t\t\t\t\t%.3f +- %.3f (MAX: %d)", priorityWaitTimeMean, priorityWaitTimeDeviation, priorityWaitTimeMax));
        resultBuilder.append("\nHead move distance:\t\t\t\t");
        resultBuilder.append(this.systemScheduler.getHeadPositionMove());

        return resultBuilder.toString();
    }

    @Override
    public void update(int time) {
        this.tick(time);
    }

    public static OSBuilder builder(){
        return new OSBuilder();
    }

    private void tick(int time) {
        boolean listChecked = false;
        if (this.systemScheduler.isEmpty()) {
            do {
                if (!this.readQueue.isEmpty() && this.readQueue.peek().getArrivalTime() <= time) {
                    if (this.consoleFlag) {
                        System.out.print(time + "\tload process; ID: " + this.readQueue.peek().getInstructionId());
                        if (readQueue.peek().hasDeadline()) System.out.println("\t*\n");
                        else System.out.println();
                    }
                    this.systemScheduler.push(this.readQueue.remove());
                } else {
                    listChecked = true;
                }
            } while (!listChecked && this.readQueue.size() > 0 && !this.systemScheduler.isFull());
        }
        if (this.readQueue.isEmpty() &&
                this.systemScheduler.isEmpty()) {
            this.systemClock.stop();
        }
    }
}
