package by.javatr.personalfinance.controller.command;

import by.javatr.personalfinance.service.utill.RequestParser;
import by.javatr.personalfinance.service.factory.ServiceFactory;

import java.util.Map;

public abstract class AbstractCommand implements Command {
    protected RequestParser parser = RequestParser.getInstance();
    protected ServiceFactory serviceFactory = ServiceFactory.getInstance();
    protected Map<String, String> requestParams;
}
