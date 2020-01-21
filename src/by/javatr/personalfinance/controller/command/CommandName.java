package by.javatr.personalfinance.controller.command;

public enum CommandName {

    REGISTER,
    SING_IN,
    UPDATE_USER_DATA,
    DELETE_USER,

    ADD_ACCOUNT,
    GET_ACCOUNT_LIST,
    GET_BALANCE,
    UPDATE_ACCOUNT,
    DELETE_ACCOUNT,

    ADD_TRANSACTION,
    GET_TRANSACTION,
    UPDATE_TRANSACTION,
    DELETE_TRANSACTION,

    WRONG_REQUEST
}