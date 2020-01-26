package by.javatr.personalfinance.service.impl;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.factory.DAOFactory;
import by.javatr.personalfinance.service.Role;
import by.javatr.personalfinance.service.UserService;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.exception.UserDataException;
import by.javatr.personalfinance.service.utill.ServiceValidator;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

    @Override
    public String register(String login, String password) throws ServiceException {
        String response = "REGISTER user exist";
        if (ServiceValidator.isValidUserdata(login))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");

        User user = null;
        try {
            user = DAOFactory.getInstance()
                    .getFileUserDAO()
                    .getUser(login);
        } catch (DAOException e) {
            response = "USER_REGISTERED";
        }

        if (user == null) {
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPassword(password);
            newUser.setSignInStatus(true);
            newUser.setRoleID(Role.USER.ordinal());
            newUser.setAccountIDList(new ArrayList<>());

            try {
                response = DAOFactory.getInstance().getFileUserDAO().register(newUser);
            } catch (DAOException e) {
                e.printStackTrace();
                response = "DATABASE_CONNECTION_PROBLEM";
                return response;
            }
        }

        return response;
    }

    @Override
    public String singIn(String login, String password) throws ServiceException {

        if (ServiceValidator.isValidUserdata(login)) {
            throw new UserDataException("Login can't be null or empty.");
        }
        if (ServiceValidator.isValidUserdata(password)) {
            throw new UserDataException("Password can't be null or empty.");
        }

        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();
        String response = null;

        User user;
        try {
            user = fileUserDAO.getUser(login);

            if (user.isSigned()) {
                response = "SING_IN already signed in";
                return response;
            }

            if (user.getPassword().equals(password)
                    && user.getLogin().equals(login)) {
                user.setSignInStatus(true);
                fileUserDAO.singIn(user);
                response = "SIGN_IN success";
            }
        } catch (DAOException e) {
            response = "SING_IN unknown login.";
            //e.printStackTrace();
        }

        return response;
    }

    @Override
    public String singOut(String login, String password) throws ServiceException {

        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(login))
            throw new UserDataException("Login must be natural long number.");

        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();
        String response = null;


        User user;
        try {
            user = fileUserDAO.getUser(login);

            if (!user.isSigned()) {
                response = "SING_OUT already signed out";
                return response;
            }

            if (user.getPassword().equals(password)
                    && user.getLogin().equals(login)) {
                user.setSignInStatus(false);
                fileUserDAO.singOut(user);
                response = "SIGN_OUT success";
            }
        } catch (DAOException e) {
            response = "SING_OUT problem occur while accessing DB";
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String updateData(String userID, String newLogin, String oldPassword, String newPassword)
            throws ServiceException {

        if (ServiceValidator.isValidUserdata(newLogin))
            throw new UserDataException("Login can't be null or empty.");
        if (ServiceValidator.isValidUserdata(oldPassword))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidUserdata(newPassword))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(userID))
            throw new UserDataException("UserID must be natural long number.");

        String response = "UPDATE_DATA success";
        long id = Long.parseLong(userID);
        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();

        try {
            User user = fileUserDAO.getUser(id);
            if (user.getPassword().equals(oldPassword)) {
                user.setLogin(newLogin);
                user.setPassword(newPassword);
                fileUserDAO.updateData(user);
            } else response = "UPDATE_DATA passwords mismatch";
        } catch (DAOException e) {
            response = "UPDATE_DATA problem occur while accessing DB";
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public String delete(String userID, String password) throws ServiceException {
        if (ServiceValidator.isValidUserdata(password))
            throw new UserDataException("Password can't be null or empty.");
        if (ServiceValidator.isValidID(userID))
            throw new UserDataException("UserID must be natural long number.");

        String response = "DELETE success";
        long id = Long.parseLong(userID);
        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();

        try {
            User user = fileUserDAO.getUser(id);
            if (user.getPassword().equals(password)) {
                fileUserDAO.delete(user);
            }
        } catch (DAOException e) {
            response = "UPDATE_DATA problem occur while accessing DB";
            e.printStackTrace();
        }

        return response;

    }
}
