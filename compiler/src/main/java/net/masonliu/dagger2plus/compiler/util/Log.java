package net.masonliu.dagger2plus.compiler.util;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by MasonLiu on 7/30/15.
 */
public class Log {
    private static Logger log;

    public static void i(String message) {
        if (log == null) {
            try {
                FileHandler fileHandler = new FileHandler(new File("build/apt.log").getAbsolutePath(), true);
                fileHandler.setLevel(Level.INFO);
                fileHandler.setFormatter(new MyLogHander());

                Logger log = Logger.getLogger("");
                log.addHandler(fileHandler);
                log.info(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info(message);
        }
    }

    static class MyLogHander extends Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getLevel() + ":" + record.getMessage() + "\n";
        }
    }
}
