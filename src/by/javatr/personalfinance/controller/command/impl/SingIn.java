package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;

public class SingIn extends UserCommand {

    @Override
    public String execute(String request) {
        try {
            requestParams = parser.getParams(request);
            login = requestParams.get("login");
            password = requestParams.get("password");
        } catch (RequestFormatException e) {
            response = "Wrong request format";
        }

        response = userService.singIn(login, password);
        return response;
    }
}
