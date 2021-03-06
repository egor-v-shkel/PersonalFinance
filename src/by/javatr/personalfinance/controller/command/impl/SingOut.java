package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;
import by.javatr.personalfinance.service.exception.ServiceException;

public class SingOut extends UserCommand {

    @Override
    public String execute(String request) throws ControllerException {
        try {
            requestParams = parser.getParams(request);
            login = requestParams.get("login");
            password = requestParams.get("password");
        } catch (RequestFormatException e) {
            response = "Wrong request format";
        }

        try {
            response = userService.singOut(login, password);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command layer exception: " + e.getMessage());
        }
        return response;
    }
}
