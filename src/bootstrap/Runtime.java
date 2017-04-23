package bootstrap;

import os.OperatingSystem;
import os.OperatingSystemImpl;
import scheduler.CScanScheduler;
import scheduler.FcfsScheduler;
import scheduler.ScanScheduler;
import scheduler.Scheduler;
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
//        Scheduler systemScheduler = new FcfsScheduler();
//        Scheduler systemScheduler = new ScanScheduler(1024);
        Scheduler systemScheduler = new ScanScheduler(1024);
        OperatingSystem myOS =
                OperatingSystemImpl.builder()
                        .withClock(systemClock)
                        .withSystemScheduler(systemScheduler)
                        .withInstructionsSource(loadPath)
                        .withConsoleOutput()
                        .build();
        myOS.run();
    }
}
