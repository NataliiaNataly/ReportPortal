package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {

    public CustomLogger(){

    }
    public static Logger getLogger() {
        Throwable t = new Throwable();
        t.fillInStackTrace();
        String clazz = t.getStackTrace()[1].getClassName();
        return LoggerFactory.getLogger(clazz);
    }
}

