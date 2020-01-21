package by.javatr.personalfinance.dao.utill;

import by.javatr.personalfinance.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public long getNextID(String pathToDB) throws DAOException {

        List<String> base;
        long id;

        try {
            base = readRecords(pathToDB);
            if (base.size() == 0) {
                return 1L;
            } else {
                String record = base.get(base.size() - 1);
                Pattern p = Pattern.compile(".+id=([\\d]+),.+");
                Matcher m = p.matcher(record);

                id = Long.parseLong(m.group(0));
            }
        } catch (IOException e) {
            throw new DAOException("Exception while trying to read records from DB: " + e);
        }
        return id + 1L;
    }

    public boolean writeToDB(String pathToDB, String record) {

        return false;
    }

    private List<String> readRecords(String path) throws IOException {
        List<String> recordList = new ArrayList<>();
        FileReader fileReader = new FileReader(path);

        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                recordList.add(record);
            }
        } catch (IOException e) {
            throw new IOException("FileManipulator: readRecords(): " + e.getMessage());
        }

        return recordList;
    }
}
