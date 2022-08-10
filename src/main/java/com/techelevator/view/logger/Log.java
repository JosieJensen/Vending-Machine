package com.techelevator.view.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final LocalDateTime now = LocalDateTime.now();
    private static final File log = new File("data\\log.txt");

    private static PrintWriter logWriter;

    static {
        try {
            logWriter = new PrintWriter(new FileOutputStream(log,true));
        } catch (FileNotFoundException e) {
            System.out.println("Logging file not found.");
        }
    }

    public static String getCurrentLocalDateTime() {
        return dtf.format(now);
    }

    public static void logEvent(String message) {
        logWriter.println(getCurrentLocalDateTime() + " " + message);
    }

    public static void closePrintWriter() {
        logWriter.close();
    }

    public static void logLineSeparator() {
        logWriter.println("******************************************");
    }

}
