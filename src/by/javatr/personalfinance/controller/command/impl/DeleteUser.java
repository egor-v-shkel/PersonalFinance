package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;
import by.javatr.personalfinance.service.exception.ServiceException;

public class DeleteUser extends UserCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String userID = null;
        try {
            requestParams = parser.getParams(request);
            userID = requestParams.get("user_id");
            password = requestParams.get("password");
        } catch (RequestFormatException e) {
            response = "Wrong request format";
        }

        try {
            response = userService.delete(userID, password);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }
        return response;
    }
}
