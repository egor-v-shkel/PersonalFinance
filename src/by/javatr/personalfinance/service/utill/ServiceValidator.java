package by.javatr.personalfinance.service.utill;

public class ServiceValidator {

    public static boolean isValidUserdata(String data){
        return (data == null || data.isEmpty());
    }

    public static boolean isValidID(String userID){
        if (userID == null) return false;

        boolean longCheck;
        try {
            longCheck = Long.parseLong(userID) < 0L;
        } catch (NumberFormatException e) {
            longCheck = false;
        }
        return longCheck;
    }

}
