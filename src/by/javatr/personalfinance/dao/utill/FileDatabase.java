package by.javatr.personalfinance.dao.utill;

import by.javatr.personalfinance.dao.exception.DAOException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class FileDatabase {
    private static FileDatabase ourInstance = new FileDatabase();

    public static FileDatabase getInstance() {
        return ourInstance;
    }

    private FileDatabase() {
    }

    public String getDBDir(String propertyName, String nameDB) throws IOException {
        Properties property = new Properties();
        String response;

        try (FileInputStream inputStream = new FileInputStream("resources/config.properties")) {
            property.load(inputStream);

            response = property.getProperty(propertyName);
        } catch (IOException e) {
            throw new IOException("config.property not found." + e.getMessage());
        }

        return response + String.format("/%s.txt", nameDB);
    }

    public <T> long getNextID(Map<Long, T> database) throws DAOException {
        Set<Long> idSet = database.keySet();

        long max = Long.MIN_VALUE;

        for (Long id : idSet) {
            if (id > max) {
                max = id;
            }
        }

        if (max == Long.MIN_VALUE) {
            max = 1L;
        }

        return max;

    }
}
