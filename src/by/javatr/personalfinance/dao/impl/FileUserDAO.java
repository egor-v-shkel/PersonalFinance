package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.utill.FileDatabase;

import java.io.*;
import java.util.*;

public class FileUserDAO implements UserDAO {
    @Override
    public String register(User newUser) throws DAOException {
        String response = "REGISTER fail";
        Map<Long, User> userDB = readDB();

        long nextID = FileDatabase.getInstance().getNextID(userDB);

        userDB.put(nextID, newUser);
        boolean writeToDBResponse = write(userDB);

        if (writeToDBResponse) {
            response = "REGISTER fail.";
        }

        return response;
    }

    @Override
    public User getUser(String login) throws DAOException {
        HashMap<Long, User> userDB = readDB();
        Collection<User> values = userDB.values();

        for (User user :
                values) {
            if (user != null && user.getLogin() != null && user.getLogin().equals(login)) {
                return user;
            }
        }

        throw new DAOException("Can't get user with requested login");
    }

    @Override
    public User getUser(long id) throws DAOException {
        Map<Long, User> userDB = readDB();
        return userDB.get(id);
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        return new ArrayList<>(readDB().values());
    }

    @Override
    public String singIn(User user) throws DAOException {
        String response = "SING_IN fail";
        Map<Long, User> userDB = readDB();
        userDB.replace(user.getId(), user);
        boolean writeResponse = write(userDB);
        if(writeResponse){
            response = "SING_IN success";
        }

        return null;
    }

    @Override
    public String singOut(User user) throws DAOException {
        String response = "SING_OUT fail";
        Map<Long, User> userDB = readDB();
        userDB.replace(user.getId(), user);
        boolean writeResponse = write(userDB);
        if(writeResponse){
            response = "SING_OUT success";
        }

        return response;
    }

    @Override
    public String updateData(User user) throws DAOException {
        String response = "UPDATE fail";
        Map<Long, User> userDB = readDB();
        userDB.replace(user.getId(), user);
        boolean writeResponse = write(userDB);
        if(writeResponse){
            response = "UPDATE success";
        }

        return response;
    }

    @Override
    public String delete(User user) throws DAOException {
        String response = "UPDATE fail";
        Map<Long, User> userDB = readDB();
        String id = Long.toString(user.getId());
        userDB.remove(id);
        boolean writeResponse = write(userDB);
        if(writeResponse){
            response = "UPDATE success";
        }

        return response;
    }

    private HashMap<Long, User> readDB() throws DAOException {

        HashMap<Long, User> userDB;
        String userDBName = User.class.getSimpleName();
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String pathToDB;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", userDBName);
        } catch (IOException e) {
            throw new DAOException("Database read exception: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToDB))) {

            userDB = ((HashMap<Long, User>) ois.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException("Error during mapping user info to database.", ex);
        }

        return userDB;
    }

    public boolean write(Map<Long, User> userDB) throws DAOException {
        String userDBName = User.class.getSimpleName();
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String pathToDB;
        boolean response = false;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", userDBName);
        } catch (IOException e) {
            throw new DAOException("Can't get DB directory from config file.", e);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToDB))) {
            oos.writeObject(userDB);
            response = true;
        } catch (Exception ex) {

            throw new DAOException("Error during write to DB.", ex);
        }

        return response;
    }
}
