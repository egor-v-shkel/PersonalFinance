package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.exception.DAOException;

public interface UserDAO {

    String register(User newUser) throws DAOException;

    String singIn(String login, String password) throws DAOException;

    String updateData(long userID, String login, String password) throws DAOException;

    String delete(long userID, String password) throws DAOException;

    String singOut(long userID, String password);
}
