package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;

/**
 * Created by Karol on 2017-04-20.
 */
public interface Scheduler {
    void setOs(OperatingSystem parentOs);
    void push(ReadInstruction instruction);
    String getName();
}
