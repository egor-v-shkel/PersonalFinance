package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.AccountCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class GetCommonBalance extends AccountCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String userId = requestParams.get("user_id");
            response = accountService.calculateCommonBalance(userId);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
