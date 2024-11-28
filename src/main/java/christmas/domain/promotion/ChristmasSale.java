package christmas.domain.promotion;

import christmas.global.constants.DiscountAmount;

public class ChristmasSale {

    private static final int CHRISTMAS_DATE = 25;

    public static boolean isQualified(int visitDate) {
        return visitDate <= CHRISTMAS_DATE;
    }

    public int getDiscountAmount(int visitDate) {
        return DiscountAmount.SPECIAL_DISCOUNT_AMOUNT +
                DiscountAmount.INCREASE_AMOUNT * (visitDate - 1);
    }
}
