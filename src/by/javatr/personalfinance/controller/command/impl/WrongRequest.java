package by.javatr.personalfinance.controller.command.impl;

import by.javatr.personalfinance.controller.command.Command;

public class WrongRequest implements Command {
    @Override
    public String execute(String request) {
        return "Wrong request: " + request;
    }
}
