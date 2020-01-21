package by.javatr.personalfinance;

import by.javatr.personalfinance.controller.Controller;

import java.io.IOException;

public class PersonalFinanceApp {

    public static void main(String[] args) {

        Controller controller = new Controller();

        controller.executeCommand("REGISTER login:Jack, password:12345678");
        controller.executeCommand("SING_IN login:Jack, password:12345678");
        controller.executeCommand("UPDATE_USER_DATA user_id:1 login:Bob, oldPassword:12345678, newPassword:987654321");
        controller.executeCommand("DELETE_USER user_id:1 login:Bob, password:987654321");

        controller.executeCommand("ADD_ACCOUNT login:Bob, account_name:wallet, type_id:cash, initial_amount:100");
        controller.executeCommand("GET_ACCOUNT_LIST login:Bob");
        controller.executeCommand("GET_BALANCE login:Bob, account_name:wallet");
        controller.executeCommand("UPDATE_ACCOUNT account_id:1, name:Credit card, initial_amount:200");
        controller.executeCommand("DELETE_ACCOUNT account_id:1");

        controller.executeCommand("ADD_TRANSACTION account_id:1, amount:50, description:pay day");
        controller.executeCommand("GET_TRANSACTION transaction_id:1");
        controller.executeCommand("UPDATE_TRANSACTION transaction_id:1, amount:99, description:pay check");
        controller.executeCommand("DELETE_TRANSACTION transaction_id:1");

    }
}
