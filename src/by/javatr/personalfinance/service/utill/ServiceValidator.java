package by.javatr.personalfinance.service.utill;

import by.javatr.personalfinance.service.AccountType;

public class ServiceValidator {

    public static boolean isValidUserdata(String data) {
        return (data == null || data.isEmpty());
    }

    public static boolean isValidID(String userID) {
        if (userID == null) return false;

        boolean longCheck;
        try {
            longCheck = Long.parseLong(userID) < 0L;
        } catch (NumberFormatException e) {
            longCheck = false;
        }
        return longCheck;
    }

    public static boolean isValidAmount(String initialAmount) {
        if (initialAmount == null) return false;

        boolean longCheck;
        try {
            long l = Long.parseLong(initialAmount);
            longCheck = (l > Long.MIN_VALUE && l < Long.MAX_VALUE);
        } catch (NumberFormatException e) {
            longCheck = false;
        }
        return longCheck;
    }

    public static boolean isValidAccountTypeId(String typeId) {

        return isValidID(typeId) && AccountType.values().length >= Long.parseLong(typeId);
    }
}
