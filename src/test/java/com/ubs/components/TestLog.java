package com.ubs.components;

import org.apache.log4j.Logger;

public class TestLog {

    static Logger log = Logger.getLogger(TestLog.class.getName());

    public void logStartTestExecution(String testCaseName){
        log.info("Test "+testCaseName+" started");
    }

    public void logEndTestExecution(String testCaseName, int status){
        switch (status) {
            case 1:
                log.info("Test "+testCaseName+" finished --> PASSED");
                break;
            case 2:
                log.info("Test "+testCaseName+" finished --> SKIPPED");
                break;
            default:
                log.info("Test "+testCaseName+" finished --> RESULT UNKNOWN");
                break;
        }
    }

    public void logStartTestClassExecution(String testClassName){
        log.info("Execution of tests from "+testClassName+" class started");
    }

    public void logEndTestClassExecution(String testClassName){
        log.info("Execution of tests from "+testClassName+" class finished");
    }

    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void error(String message) {
        log.error(message);
    }

    public void fatal(String message) {
        log.fatal(message);
    }

    public void debug(String message) {
        log.debug(message);
    }
}
