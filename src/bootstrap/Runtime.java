package bootstrap;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import os.OperatingSystem;
import os.OperatingSystemImpl;
import scheduler.*;
import utils.Clock;

/**
 * Created by Karol on 2017-04-20.
 */
public class Runtime {
    public static void main(String[] args) {
        String directory = "./inputdata/";
        String fileName = "RAND_CONST_FALSE";
        String extension = ".csv";
        String loadPath = directory + fileName + extension;
        Clock systemClock = new Clock();
        Scheduler[] systemSchedulers = {
                new FcfsScheduler(),
                new ScanScheduler(1024),
                new CScanScheduler(1024),
                new SstfScheduler(false),
                new SstfScheduler(true)
        };
        OperatingSystem myOS;
        for (Scheduler systemScheduler : systemSchedulers) {
            System.out.println("\n" + systemScheduler.getName() + "\n");
            myOS = OperatingSystemImpl.builder()
                    .withClock(systemClock)
                    .withSystemScheduler(systemScheduler)
                    .withInstructionsSource(loadPath)
                    .withConsoleOutput()
                    .build();
            myOS.run();
            systemClock.reset();
            System.out.println("\n\n");
        }
    }
}
