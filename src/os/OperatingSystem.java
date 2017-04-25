package os;

import readinstruction.ReadInstruction;

/**
 * Created by Karol on 2017-04-20.
 */
public interface OperatingSystem {
    void run();
    void push(ReadInstruction instruction);
    void saveReport(String path);
    String generateReport(String path);
}