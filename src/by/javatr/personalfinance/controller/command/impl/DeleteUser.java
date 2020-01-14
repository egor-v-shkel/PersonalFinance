package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.UserCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;

public class DeleteUser extends UserCommand {
    @Override
    public String execute(String request) {
        String userID = null;
        try {
            requestParams = parser.getParams(request);
            userID = requestParams.get("user_id");
            password = requestParams.get("password");
        } catch (RequestFormatException e) {
            response = "Wrong request format";
        }

        response = userService.delete(userID, password);
        return response;
    }
}
