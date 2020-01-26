package by.javatr.personalfinance.service.utill;

import by.javatr.personalfinance.service.exception.RequestFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private static final RequestParser instance = new RequestParser();

    private RequestParser() {
    }

    public static RequestParser getInstance() {
        return instance;
    }

    public Map<String, String> getParams(String request) throws RequestFormatException {
        final String regex = "([^ ].+?):(.+?)(?:, |$)";
        String commands = request.substring(request.indexOf(' ') + 1);

        if (!commands.matches(regex))
            throw new RequestFormatException("Request didn't meet pattern requirements.\n" +
                    "Example: \"param1:value1, param2:value2\"");

        Map<String, String> params = new HashMap<>();

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(commands);

        while (matcher.find()) {
            String param = matcher.group(1);
            String value = matcher.group(2);
            params.put(param, value);
        }

        return params;
    }
}
