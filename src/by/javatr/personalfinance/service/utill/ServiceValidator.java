package by.javatr.personalfinance.service.utill;

import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.dao.UserDAO;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.factory.DAOFactory;
import by.javatr.personalfinance.service.AccountType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServiceValidator {

    public static boolean checkUserdata(String data) {
        return (data == null || data.isEmpty());
    }

    public static boolean checkId(String userID) {
        if (userID == null) return false;

        boolean longCheck;
        try {
            longCheck = Long.parseLong(userID) < 0L;
        } catch (NumberFormatException e) {
            longCheck = false;
        }
        return longCheck;
    }

    public static boolean checkAmount(String initialAmount) {
        if (initialAmount == null) return true;

        boolean longCheck;
        try {
            long l = Long.parseLong(initialAmount);
            longCheck = !(l > Long.MIN_VALUE && l < Long.MAX_VALUE);
        } catch (NumberFormatException e) {
            longCheck = true;
        }
        return longCheck;
    }

    public static boolean checkAccountType(String type) {
        boolean stat = true;

        AccountType[] values = AccountType.values();
        for (AccountType t :
                values) {
            if (t.toString().toLowerCase().equals(type)) {
                stat = false;
            }
        }
        return stat;
    }

    public static boolean checkAdmin(String login, String password) {
        boolean stat = false;
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {

            properties.load(fis);
            String adminLogin = properties.getProperty("login.admin");
            String adminPassword = properties.getProperty("password.admin");

            stat = (login.equals(adminLogin) && password.equals(adminPassword));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return stat;
    }

    public static boolean checkBan(String login) {
        boolean stat = false;
        UserDAO fileUserDAO = DAOFactory.getInstance().getFileUserDAO();
        try {
            User user = fileUserDAO.getUser(login);
            stat = user.isBanStat();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return stat;
    }


}
