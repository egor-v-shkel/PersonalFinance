package by.javatr.personalfinance.service.utill;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileDatabase {
    private static FileDatabase ourInstance = new FileDatabase();

    public static FileDatabase getInstance() {
        return ourInstance;
    }

    private FileDatabase() {
    }

    public String getDB(String propertyName){
        Properties property = new Properties();
        FileInputStream inputStream = null;
        String response;

        try {
            inputStream = new FileInputStream("resources/config.properties");
            property.load(inputStream);

            response = property.getProperty(propertyName);

        } catch (IOException e) {
            throw new IOException("config.property not found." + e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new IOException("getFileAddress(): close thread problem; " + e.getMessage());
            }
        }
        return response;

    }

    public long getFreeID(String nameDB){
        //TODO method realisation
        return 1L;
    }
}
