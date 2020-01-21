package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.TransactionCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class AddTransaction extends TransactionCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String accountId = requestParams.get("account_id");
            String amount = requestParams.get("amount");
            String description = requestParams.get("description");
            response = transactionService.addTransaction(accountId, amount, description);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
