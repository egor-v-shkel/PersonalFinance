package by.javatr.personalfinance.service;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.service.exception.ServiceException;

public interface AccountService {

    void singIn(String login, String password) throws ServiceException;
    void singOut(String login) throws ServiceException;
    void registration(User user, String password) throws ServiceException;
}

