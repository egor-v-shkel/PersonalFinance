package by.javatr.personalfinance.service.utill;

import by.javatr.personalfinance.controller.ControllerException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

public class Logger {

    private Logger() {
    }

    public static <T> void log(Class<T> tClass, Exception e) {

        Properties properties = new Properties();

        String logDir;

        PrintWriter printWriter = null;
        FileWriter fileWriter;

        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {

            properties.load(fis);
            logDir = properties.getProperty("log.dir");
            String fullPath = logDir + String.format("/%s.txt", tClass.getSimpleName());

            fileWriter = new FileWriter(fullPath, true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.printf("\n%s - %s\n", new Date(), e.getMessage());

        } catch (IOException ex) {
            e.printStackTrace();
        }
    }
}
