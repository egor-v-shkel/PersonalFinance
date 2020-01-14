package by.javatr.personalfinance.controller;

import by.javatr.personalfinance.controller.command.Command;

public class Controller {
    private final CommandProvider provider = CommandProvider.getInstance();

    private final char paramDelimiter = ' ';

    public String executeCommand(String request) {

        String commandName;
        Command executionCommand;
        String response;

        commandName = (request != null && request.indexOf(paramDelimiter) != -1) ?
                request.substring(0, request.indexOf(paramDelimiter)) :
                "WRONG_REQUEST";
        executionCommand = provider.getCommand(commandName);

        response = executionCommand.execute(request);
        return response;
    }
}