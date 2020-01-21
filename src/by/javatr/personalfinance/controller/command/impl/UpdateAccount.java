package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.AccountCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class UpdateAccount extends AccountCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String accountId = requestParams.get("account_id");
            String name = requestParams.get("name");
            String initialAmount = requestParams.get("initial_amount");

            response = accountService.updateAccount(accountId, name, initialAmount);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
