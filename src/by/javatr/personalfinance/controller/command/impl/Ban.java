package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class Ban extends UserCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String login = requestParams.get("login");
            String password = requestParams.get("password");
            String userId = requestParams.get("user_id");
            response = userService.ban(login, password, userId);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
