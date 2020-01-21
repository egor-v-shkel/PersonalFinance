package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.TransactionCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.utill.RequestParser;

import java.util.Map;

public class UpdateTransaction extends TransactionCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String accountId = requestParams.get("account_id");
            String amount = requestParams.get("amount");
            String description = requestParams.get("description");
            response = transactionService.update(accountId, amount, description);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
