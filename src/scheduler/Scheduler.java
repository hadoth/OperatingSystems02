package scheduler;

import os.OperatingSystem;
import readinstruction.ReadInstruction;
import utils.Observer;

/**
 * Created by Karol on 2017-04-20.
 */
public interface Scheduler extends Observer {
    void setOs(OperatingSystem parentOs);
    void push(ReadInstruction instruction);
    String getName();
    boolean isEmpty();
    int getHeadPositionMove();
}
