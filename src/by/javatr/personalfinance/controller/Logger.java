package by.javatr.personalfinance.controller;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Logger {

    private Logger() {
    }

    public static <T> void log(Class<T> tClass, Exception e) {

        Properties properties = new Properties();
        String logDir;
        FileWriter fileWriter;

        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {

            properties.load(fis);
            logDir = properties.getProperty("log.dir");
            String fullPath = logDir + String.format("/%s.txt", tClass.getSimpleName());

            File log = new File(fullPath);
            log.getParentFile().mkdirs();
            fileWriter = new FileWriter(log, true);
            String message = String.format("\n%tc - %s\n", new Date(), e.getMessage());
            fileWriter.append(message);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
