package bootstrap;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import os.OperatingSystem;
import os.OperatingSystemImpl;
import readinstruction.ReadInstruction;
import scheduler.*;
import utils.Clock;

import java.util.List;

/**
 * Created by Karol on 2017-04-20.
 */
public class Runtime {
    public static void main(String[] args) {
        String directory = "./inputdata/";
        String fileName = "RAND_CONST_TRUE";
        String extension = ".csv";
        String loadPath = directory + fileName + extension;
        Clock systemClock = new Clock();
        List<ReadInstruction> instructionList = ReadInstruction.parseList(loadPath);
        Scheduler[] systemSchedulers = {
                new FcfsScheduler(),
                new ScanScheduler(1024),
                new CScanScheduler(1024),
                new SstfScheduler(false),
                new SstfScheduler(true)
        };


        OperatingSystem myOS;
        for (Scheduler systemScheduler : systemSchedulers) {
            myOS = OperatingSystemImpl.builder()
                    .withClock(systemClock)
                    .withSystemScheduler(systemScheduler)
                    .withReadInstructions(instructionList)
//                    .withConsoleOutput()
                    .build();
            myOS.run();
            systemClock.reset();
            System.out.println(myOS.generateReport(loadPath));
            System.out.println();
            System.out.println();
        }
    }
}
