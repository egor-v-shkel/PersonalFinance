package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.ControllerException;
import by.javatr.personalfinance.controller.command.TransactionCommand;
import by.javatr.personalfinance.service.exception.RequestFormatException;
import by.javatr.personalfinance.service.exception.ServiceException;
import by.javatr.personalfinance.service.utill.RequestParser;

import java.util.Map;

public class DeleteTransaction extends TransactionCommand {
    @Override
    public String execute(String request) throws ControllerException {
        String response;

        try {
            requestParams = parser.getParams(request);
            String transactionId = requestParams.get("transaction_id");
            response = transactionService.delete(transactionId);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ControllerException("Command exception: " + e.getMessage());
        }

        return response;
    }
}
