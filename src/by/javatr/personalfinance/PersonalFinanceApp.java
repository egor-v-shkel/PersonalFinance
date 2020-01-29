package by.javatr.personalfinance;

import by.javatr.personalfinance.bean.Account;
import by.javatr.personalfinance.bean.Transaction;
import by.javatr.personalfinance.bean.User;
import by.javatr.personalfinance.controller.Controller;
import by.javatr.personalfinance.controller.command.impl.DeleteTransaction;
import by.javatr.personalfinance.dao.exception.DAOException;
import by.javatr.personalfinance.dao.impl.FileAccountDAO;
import by.javatr.personalfinance.dao.impl.FileTransactionDAO;
import by.javatr.personalfinance.dao.impl.FileUserDAO;
import by.javatr.personalfinance.dao.utill.FileDatabase;
import by.javatr.personalfinance.service.Role;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PersonalFinanceApp {

    static {
        clearUserDB();
        clearAccountDB();
        clearTransactionDB();
    }

    public static void main(String[] args) {

        Controller controller = new Controller();

        String registerResponse = controller.executeCommand("REGISTER login:Bob, password:12345678");
        System.out.println(registerResponse);
        String singIn = controller.executeCommand("SING_IN login:Bob, password:12345678");
        System.out.println(singIn);
        /*String singOut = controller.executeCommand("SING_OUT login:Bob, password:12345678");
        System.out.println(singOut);*/
        String updateResponse = controller
                .executeCommand("UPDATE_USER_DATA user_id:2, login:Paul, oldPassword:12345678, newPassword:987654321");
        System.out.println(updateResponse);
        String delete = controller.executeCommand("DELETE_USER user_id:2, login:Paul, password:987654321");
        System.out.println(delete);

        String adminSingIn = controller.executeCommand("SING_IN login:admin, password:admin");
        System.out.println(adminSingIn);
        String ban = controller.executeCommand("BAN login:admin, password:admin, user_id:2");
        System.out.println(ban);
        String unban = controller.executeCommand("UNBAN login:admin, password:admin, user_id:2");
        System.out.println(unban);
        String adminSingOut = controller.executeCommand("SING_OUT login:admin, password:admin");
        System.out.println(adminSingOut);

        String addAccountResponse = controller
                .executeCommand("ADD_ACCOUNT login:Paul, account_name:wallet, type:cash, initial_amount:100");
        System.out.println(addAccountResponse);
        String getAccountResponse = controller
                .executeCommand("GET_ACCOUNT account_id:1");
        System.out.println(getAccountResponse);
        String getAccountListResponse = controller
                .executeCommand("GET_ACCOUNT_LIST user_id:2");
        System.out.println(getAccountListResponse);
        String getBalanceResponse = controller
                .executeCommand("GET_BALANCE login:Paul, account_name:wallet");
        System.out.println(getBalanceResponse);
        String commonBalanceResponse = controller
                .executeCommand("GET_COMMON_BALANCE user_id:2");
        System.out.println(commonBalanceResponse);
        String updateAccountResponse = controller
                .executeCommand("UPDATE_ACCOUNT account_id:1, name:Credit card, initial_amount:200");
        System.out.println(updateAccountResponse);
        String deleteAccountResponse = controller
                .executeCommand("DELETE_ACCOUNT account_id:1");
        System.out.println(deleteAccountResponse);

        String addTransactionResponse =
                controller.executeCommand("ADD_TRANSACTION account_id:1, amount:50, description:pay day");
        controller.executeCommand("ADD_TRANSACTION account_id:1, amount:150, description:side job");
        System.out.println(addTransactionResponse);
        String getTransactionResponse =
                controller.executeCommand("GET_TRANSACTION transaction_id:1");
        System.out.println(getTransactionResponse);
        String getTransactionListResponse =
                controller.executeCommand("GET_TRANSACTION_LIST account_id:1");
        System.out.println(getTransactionListResponse);
        String updateTransactionResponse =
                controller.executeCommand("UPDATE_TRANSACTION transaction_id:1, amount:99, description:pay check");
        System.out.println(updateTransactionResponse);
        String deleteTransactionResponse =
                controller.executeCommand("DELETE_TRANSACTION transaction_id:1");
        System.out.println(deleteTransactionResponse);

        String commonBalanceResp = controller
                .executeCommand("GET_COMMON_BALANCE user_id:2");
        System.out.println(commonBalanceResp);

    }

    private static void clearTransactionDB() {
        Map<Long, Transaction> transactionDB = new HashMap<>();
        FileTransactionDAO fileTransactionDAO = new FileTransactionDAO();
        try {
            fileTransactionDAO.writeDB(transactionDB);
            System.out.println("Transaction db cleared");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static void clearAccountDB() {
        Map<Long, Account> accountDB = new HashMap<>();
        FileAccountDAO fileAccountDAO = new FileAccountDAO();
        try {
            fileAccountDAO.writeDB(accountDB);
            System.out.println("Account db cleared");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static void clearUserDB() {
        try (FileInputStream fis = new FileInputStream("resources/config.properties")) {
            Map<Long, User> userDB = new HashMap<>();
            FileUserDAO fileUserDAO = new FileUserDAO();
            Properties properties = new Properties();

            properties.load(fis);
            String loginAdmin = properties.getProperty("login.admin");
            String passwordAdmin = properties.getProperty("password.admin");
            long nextID = FileDatabase.getInstance().getNextID(userDB);

            User admin = new User();
            admin.setRoleID(Role.ADMIN.ordinal());
            admin.setBanStat(false);
            admin.setSignInStatus(false);
            admin.setId(nextID);
            admin.setPassword(passwordAdmin);
            admin.setLogin(loginAdmin);

            userDB.put(nextID, admin);
            fileUserDAO.write(userDB);
            System.out.println("User db cleared");
        } catch (IOException | DAOException ex) {
            ex.printStackTrace();
        }
    }

}
