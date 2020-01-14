package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.AccountCommand;
import by.javatr.personalfinance.service.utill.RequestParser;

import java.util.Map;

public class DeleteAccount extends AccountCommand {
    @Override
    public String execute(String request) {
        Map<String, String> params = RequestParser.getInstance().getParams(request);

        return null;
    }
}
