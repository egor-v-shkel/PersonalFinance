package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;
import by.javatr.personalfinance.service.exception.ServiceException;

public class UpdateUserData extends UserCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String userID = null;
        String oldPassword = null;
        String newPassword = null;

        try {
            requestParams = parser.getParams(request);
            userID = requestParams.get("user_id");
            login = requestParams.get("login");
            oldPassword = requestParams.get("oldPassword");
            newPassword = requestParams.get("newPassword");
        } catch (RequestFormatException e) {
            response = "Wrong request format";
        }

        try {
            response = userService.updateData(userID, login, oldPassword, newPassword);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }
        return response;
    }
}
