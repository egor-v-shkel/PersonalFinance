package by.javatr.personalfinance.controller.command;

import by.javatr.personalfinance.service.TransactionService;

public abstract class TransactionCommand extends AbstractCommand {
    TransactionService transactionService = serviceFactory.getTransactionService();
}
