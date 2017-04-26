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
        String[] fileNames = {
                "GOOD_IMMIDIATE_FALSE",
                "BAD_IMMIDIATE_FALSE",
                "RAND_IMMIDIATE_FALSE",
                "GOOD_CONST_TRUE",
                "BAD_CONST_TRUE",
                "RAND_CONST_TRUE"
        };
        String extension = ".csv";
        Clock systemClock = new Clock();

        for (String fileName : fileNames) {
            String loadPath = directory + fileName + extension;
            List<ReadInstruction> instructionList = ReadInstruction.parseList(loadPath);
            Scheduler[] systemSchedulers = {
                new FcfsScheduler(),
                new ScanScheduler(1024),
                new CScanScheduler(1024),
                new SstfScheduler(false),
                new SstfScheduler(true),
                new EdfScheduler(false),
                new EdfScheduler(true)
            };


            OperatingSystem myOS;
            for (Scheduler systemScheduler : systemSchedulers) {
                myOS = OperatingSystemImpl.builder()
                        .withClock(systemClock)
                        .withSystemScheduler(systemScheduler)
                        .withReadInstructions(instructionList)
//                        .withConsoleOutput()
                        .build();
                myOS.run();
                systemClock.reset();
                System.out.println(myOS.generateReport(loadPath));
                System.out.println();
                System.out.println();
            }
        }
    }
}
