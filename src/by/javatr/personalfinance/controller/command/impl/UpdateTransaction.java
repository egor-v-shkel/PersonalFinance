package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.TransactionCommand;
import by.javatr.personalfinance.service.utill.RequestParser;

import java.util.Map;

public class UpdateTransaction extends TransactionCommand {
    @Override
    public String execute(String request) {
        Map<String, String> params = RequestParser.getInstance().getParams(request);

        return null;
    }
}
