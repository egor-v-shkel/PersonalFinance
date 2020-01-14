package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.AccountCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;

public class AddAccount extends AccountCommand {
    @Override
    public String execute(String request) {
        try {
            requestParams = parser.getParams(request);
            login = requestParams.get("login");
            password = requestParams.get("password");
        } catch (RequestFormatException e) {
            //log
        }

        userService.register(login, password);
        return response;
    }
}
