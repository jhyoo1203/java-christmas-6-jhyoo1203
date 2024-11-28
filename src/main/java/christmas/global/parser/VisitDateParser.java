package christmas.global.parser;

import christmas.global.exception.ChristmasException;
import christmas.global.exception.ErrorMessage;

public class VisitDateParser {

    public static final int NOT_POSITIVE_NUMBER = 0;
    public static final int OUT_OF_DATE_RANGE = 31;

    public static int parseVisitDate(String input) {
        try {
            int visitDate = Integer.parseInt(input);
            validateDateRange(visitDate);
            return visitDate;
        } catch (NumberFormatException e) {
            throw ChristmasException.from(ErrorMessage.INVALID_DATE);
        }
    }

    private static void validateDateRange(int visitDate) {
        if (visitDate <= NOT_POSITIVE_NUMBER || visitDate > OUT_OF_DATE_RANGE) {
            throw ChristmasException.from(ErrorMessage.INVALID_DATE);
        }
    }
}
