package by.javatr.personalfinance;

import by.javatr.personalfinance.controller.Controller;

import java.io.IOException;

public class PersonalFinanceApp {

    public static void main(String[] args) throws IOException {

        Controller controller = new Controller();

        controller.executeCommand("REGISTER login:Jack, password:12345678");
        controller.executeCommand("SING_IN login:Jack, password:12345678");
        controller.executeCommand("UPDATE_USER_DATA user_id:1 login:Bob, password:987654321");
        controller.executeCommand("DELETE_USER user_id:1 login:Bob, password:987654321");

        controller.executeCommand("ADD_ACCOUNT login:Bob, name:Wallet, type_id:cash, initial_amount:100");
        controller.executeCommand("GET_ACCOUNT login:Bob, name:Wallet");
        controller.executeCommand("UPDATE_ACCOUNT account_id:1, name:Credit card, initial_amount:200");
        controller.executeCommand("DELETE_ACCOUNT account_id:1");

        controller.executeCommand("ADD_TRANSACTION account_id:1, amount:50, description:pay day");
        controller.executeCommand("GET_TRANSACTION transaction_id:1");
        controller.executeCommand("UPDATE_TRANSACTION transaction_id:1, amount:99");
        controller.executeCommand("DELETE_TRANSACTION transaction_id:1");

    }
}
