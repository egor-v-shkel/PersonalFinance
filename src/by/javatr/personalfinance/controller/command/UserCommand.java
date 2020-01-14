package by.javatr.personalfinance.controller.command;

import by.javatr.personalfinance.service.UserService;

public abstract class UserCommand extends AbstractCommand {
    protected String login;
    protected String password;
    protected String response;
    protected UserService userService = serviceFactory.getUserService();
}
