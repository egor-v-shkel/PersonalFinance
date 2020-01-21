package by.javatr.personalfinance.controller.command;

import by.javatr.personalfinance.controller.ControllerException;

public interface Command {
    public String execute(String request) throws ControllerException;
}
