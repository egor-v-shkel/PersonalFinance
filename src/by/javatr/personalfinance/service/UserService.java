package by.javatr.personalfinance.service;

import by.javatr.personalfinance.service.exception.ServiceException;

public interface UserService {
    String singIn(String login, String password) throws ServiceException;
    String singOut(String userID, String password) throws ServiceException;
    String register(String login, String password) throws ServiceException;
    String updateData(String userID, String login, String password) throws ServiceException;
    String delete(String userID, String password) throws ServiceException;
}
