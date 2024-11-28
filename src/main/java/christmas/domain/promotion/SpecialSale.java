package christmas.domain.promotion;

import christmas.global.constants.DiscountAmount;

import java.util.List;

public class SpecialSale {

    private static final List<Integer> starDates = List.of(3, 10, 17, 24, 25, 31);

    public static boolean isQualified(int visitDate) {
        return starDates.contains(visitDate);
    }

    public int getDiscountAmount() {
        return DiscountAmount.SPECIAL_DISCOUNT_AMOUNT;
    }
}
