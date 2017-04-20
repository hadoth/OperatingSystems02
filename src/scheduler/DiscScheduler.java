package scheduler;

import readinstruction.ReadInstruction;

/**
 * Created by Karol on 2017-04-20.
 */
public interface DiscScheduler {
    void run();
    void push(ReadInstruction instruction);
    void generateReport();
}