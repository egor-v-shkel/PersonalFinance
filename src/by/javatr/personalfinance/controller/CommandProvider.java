package by.javatr.personalfinance.controller;

import by.javatr.personalfinance.controller.command.Command;
import by.javatr.personalfinance.controller.command.CommandName;
import by.javatr.personalfinance.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final static CommandProvider instance = new CommandProvider();

    public static CommandProvider getInstance() {
        return instance;
    }

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.SING_IN, new SingIn());
        repository.put(CommandName.SING_OUT, new SingOut());
        repository.put(CommandName.UPDATE_USER_DATA, new UpdateUserData());
        repository.put(CommandName.DELETE_USER, new DeleteUser());

        repository.put(CommandName.ADD_ACCOUNT, new AddAccount());
        repository.put(CommandName.GET_ACCOUNT, new GetAccount());
        repository.put(CommandName.GET_ACCOUNT_LIST, new GetAccountList());
        repository.put(CommandName.GET_BALANCE, new GetBalance());
        repository.put(CommandName.GET_COMMON_BALANCE, new GetCommonBalance());
        repository.put(CommandName.UPDATE_ACCOUNT, new UpdateAccount());
        repository.put(CommandName.DELETE_ACCOUNT, new DeleteAccount());

        repository.put(CommandName.ADD_TRANSACTION, new AddTransaction());
        repository.put(CommandName.GET_TRANSACTION, new GetTransaction());
        repository.put(CommandName.GET_TRANSACTION_LIST, new GetTransactionList());
        repository.put(CommandName.UPDATE_TRANSACTION, new UpdateTransaction());
        repository.put(CommandName.DELETE_TRANSACTION, new DeleteTransaction());

        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}