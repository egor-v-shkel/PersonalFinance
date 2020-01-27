package by.javatr.personalfinance;

import by.javatr.personalfinance.controller.Controller;

import java.io.IOException;

public class PersonalFinanceApp {

    public static void main(String[] args) {

        Controller controller = new Controller();

        String registerResponse = controller.executeCommand("REGISTER login:Bob, password:12345678");
        System.out.println(registerResponse);
        String singIn = controller.executeCommand("SING_IN login:Paul, password:12345678");
        System.out.println(singIn);
        String singOut = controller.executeCommand("SING_OUT login:Paul, password:12345678");
        System.out.println(singOut);
        String updateResponse = controller
                .executeCommand("UPDATE_USER_DATA user_id:1, login:Bob, oldPassword:12345678, newPassword:987654321");
        System.out.println(updateResponse);
        String delete = controller.executeCommand("DELETE_USER user_id:1, login:Bob, password:987654321");
        System.out.println(delete);

        /*String adminSingIn = controller.executeCommand("SING_IN login:admin, password:admin");
        System.out.println(adminSingIn);
        String ban = controller.executeCommand("BAN login:admin, password:admin, user_id:1");
        System.out.println(adminSingIn);
        String unban = controller.executeCommand("UNBAN login:admin, password:admin, user_id:1");
        System.out.println(adminSingIn);
        String adminSingOut = controller.executeCommand("SING_OUT login:admin, password:admin");
        System.out.println(adminSingOut);*/

        String addAccountResponse = controller
                .executeCommand("ADD_ACCOUNT login:Bob, account_name:wallet, type:cash, initial_amount:100");
        System.out.println(addAccountResponse);
        String getAccountResponse = controller
                .executeCommand("GET_ACCOUNT account_id:1");
        System.out.println(getAccountResponse);
        String getAccountListResponse = controller
                .executeCommand("GET_ACCOUNT_LIST user_id:Bob");
        System.out.println(getAccountListResponse);
        String getBalanceResponse = controller
                .executeCommand("GET_BALANCE login:Bob, account_name:wallet");
        System.out.println(getBalanceResponse);
        String commonBalanceResponse = controller
                .executeCommand("GET_COMMON_BALANCE user_id:1");
        System.out.println(commonBalanceResponse);
        String updateAccountResponse = controller
                .executeCommand("UPDATE_ACCOUNT account_id:1, name:Credit card, initial_amount:200");
        System.out.println(updateAccountResponse);
        String deleteAccountResponse = controller
                .executeCommand("DELETE_ACCOUNT account_id:1");
        System.out.println(deleteAccountResponse);

        controller.executeCommand("ADD_TRANSACTION account_id:1, amount:50, description:pay day");
        controller.executeCommand("GET_TRANSACTION transaction_id:1");
        controller.executeCommand("GET_TRANSACTION_LIST account_id:1");
        controller.executeCommand("UPDATE_TRANSACTION transaction_id:1, amount:99, description:pay check");
        controller.executeCommand("DELETE_TRANSACTION transaction_id:1");

    }
}
