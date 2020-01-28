package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.TransactionCommand;
import by.javatr.personalfinance.service.exception.ServiceException;

public class GetTransactionList extends TransactionCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String accountId = requestParams.get("account_id");
            response = transactionService.getList(accountId);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
