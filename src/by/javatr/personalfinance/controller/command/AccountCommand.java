package by.javatr.personalfinance.controller.command;

import by.javatr.personalfinance.service.AccountService;

public abstract class AccountCommand extends AbstractCommand {
    protected AccountService accountService = serviceFactory.getAccountService();
}