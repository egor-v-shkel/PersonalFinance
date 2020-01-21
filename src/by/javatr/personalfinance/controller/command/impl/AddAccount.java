package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.AccountCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class AddAccount extends AccountCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String login = requestParams.get("login");
            String name = requestParams.get("name");
            String typeId = requestParams.get("type_id");
            String initialAmount = requestParams.get("initial_amount");
            response = accountService.addAccount(login, name, typeId, initialAmount);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
