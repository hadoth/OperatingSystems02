package bootstrap;

import os.OperatingSystem;
import os.OperatingSystemImpl;
import scheduler.FcfsScheduler;
import scheduler.Scheduler;
import utils.Clock;

/**
 * Created by Karol on 2017-04-20.
 */
public class Runtime {
    public static void main(String[] args) {
        String directory = "./inputdata/";
        String fileName = "GOOD_IMMIDIATE_FALSE";
        String extension = ".csv";
        String loadPath = directory + fileName + extension;
        Clock systemClock = new Clock();
        Scheduler systemScheduler = new FcfsScheduler();
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
