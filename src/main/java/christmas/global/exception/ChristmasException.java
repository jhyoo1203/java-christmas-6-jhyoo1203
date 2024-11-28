package christmas.global.exception;

public class ChristmasException extends IllegalArgumentException {

    public ChristmasException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static ChristmasException from(ErrorMessage errorMessage) {
        return new ChristmasException(errorMessage);
    }
}
