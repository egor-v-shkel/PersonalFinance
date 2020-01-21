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
        Map<String, User> userDB = mapDB();
        String id = Long.toString(newUser.getId());
        userDB.put(id, newUser);
        String writeDBResponse = writeDB(userDB);

        if (!writeDBResponse.equals("OK")) throw new DAOException("Write to DB exception");

        return writeDBResponse;
    }

    @Override
    public User getUser(String login) throws DAOException {
        HashMap<String, User> userDB = mapDB();
        Collection<User> values = userDB.values();


        for (User user :
                values) {
            if (user.getLogin().equals(login)){
                return user;
            }
        }

        throw new DAOException("Can't get user with requested login");
    }

    @Override
    public User getUser(long id) throws DAOException {
        Map<String, User> userDB = mapDB();
        return userDB.get(Long.toString(id));
    }

    @Override
    public Map<String, User> getAllUsers() throws DAOException {
        return mapDB();
    }

    @Override
    public String singIn(User user) throws DAOException {
        Map<String, User> userDB = mapDB();
        String id = Long.toString(user.getId());
        userDB.put(id, user);
        writeDB(userDB);

        return null;
    }

    @Override
    public String singOut(User user) throws DAOException {
        Map<String, User> userDB = mapDB();
        String id = Long.toString(user.getId());
        userDB.put(id, user);
        writeDB(userDB);

        return null;
    }

    @Override
    public String updateData(User user) throws DAOException {
        Map<String, User> userDB = mapDB();
        String id = Long.toString(user.getId());
        userDB.put(id, user);
        writeDB(userDB);

        return null;
    }

    @Override
    public String delete(User user) throws DAOException {
        Map<String, User> userDB = mapDB();
        String id = Long.toString(user.getId());
        userDB.remove(id);
        writeDB(userDB);

        return null;
    }

    private HashMap<String, User> mapDB() throws DAOException {

        HashMap<String, User> userDB;
        String userDBName = User.class.getSimpleName();
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String pathToDB;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", userDBName);
        } catch (IOException e) {
            throw new DAOException("Database read exception: "+e.getMessage());
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToDB)))
        {

            userDB=((HashMap<String, User>)ois.readObject());
        }
        catch(Exception ex){

            throw new DAOException("Error during mapping database.", ex);
        }

        return userDB;
    }

    public String writeDB(Map<String, User> userDB) throws DAOException {
        String userDBName = User.class.getSimpleName();
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String pathToDB;
        String response = null;
        try {
            pathToDB = fileDatabase.getDBDir("db.host", userDBName);
        } catch (IOException e) {
            throw new DAOException("Can't get DB directory from config file.", e);
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToDB)))
        {
            oos.writeObject(userDB);
            response = "OK";
        }
        catch(Exception ex){

            throw new DAOException("Error during write to DB.", ex);
        }

        return response;
    }
}
