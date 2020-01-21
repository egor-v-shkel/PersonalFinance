package by.javatr.personalfinance.dao;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.exception.DAOException;

import java.util.Map;

public interface UserDAO {

    String register(User newUser) throws DAOException;

    User getUser(String login) throws DAOException;

    User getUser(long id) throws DAOException;

    Map<String, User> getAllUsers() throws DAOException;

    String singIn(User user) throws DAOException;

    String singOut(User user) throws DAOException;

    String updateData(User user) throws DAOException;

    String delete(User user) throws DAOException;

}
