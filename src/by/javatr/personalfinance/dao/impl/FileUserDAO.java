package by.javatr.personalfinance.dao.impl;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.service.utill.FileDatabase;

import java.io.IOException;

public class FileUserDAO implements UserDAO {
    @Override
    public String register(String login, String password) throws DAOException {
        FileDatabase fileDatabase = FileDatabase.getInstance();
        String userDBPath;
        String path2;
        String response;

        try {
            userDBPath = fileDatabase.getDB("user.db");
            path2 = fileDatabase.getDB("access.info.base");
            String userFromBase = findUser(accessInfo);
            if (userFromBase == null) {

                if (user.getId() == 0) {
                    user.setId(IdGenerator.getInstance().generate(userDBPath));
                }

                fileDatabase.addLine(userDBPath, user.toString());
                accessInfo.setUserId(user.getId());
                fileDatabase.addLine(path2, accessInfo.toString());
                response = "Done.";
            } else response = "USER_EXIST";

        } catch (IOException | RuntimeException e) {
            throw new DAOException("DAO layer: " + e.getMessage());
        }
        return response;

    }

    @Override
    public String singIn(User newUser) throws DAOException {
        return null;
    }

    @Override
    public String updateData(long userID, String login, String password) throws DAOException {
        return null;
    }

    @Override
    public String delete(long userID, String password) throws DAOException {
        return null;
    }

    @Override
    public String singOut(long userID, String password) {
        return null;
    }
}
