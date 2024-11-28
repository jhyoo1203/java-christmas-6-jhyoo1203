package christmas.domain.promotion;

public record BenefitResult(
        int christmasDiscount,
        int weekdayDiscount,
        int weekendDiscount,
        int specialDiscount,
        int giftPrice
) {
    public static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인";
    public static final String WEEKDAY_DISCOUNT = "평일 할인";
    public static final String WEEKEND_DISCOUNT = "주말 할인";
    public static final String SPECIAL_DISCOUNT = "특별 할인";
    public static final String GIFT_EVENT = "증정 이벤트";

    public static BenefitResult from(
            int christmasDiscount,
            int weekdayDiscount,
            int weekendDiscount,
            int specialDiscount,
            int giftPrice
    ) {
        return new BenefitResult(christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount, giftPrice);
    }

    public int getTotalDiscountAmount() {
        return christmasDiscount + weekdayDiscount + weekendDiscount + specialDiscount;
    }

    public int getTotalBenefitAmount() {
        return getTotalDiscountAmount() + giftPrice;
    }

    public boolean hasDiscount() {
        return christmasDiscount > 0 || weekdayDiscount > 0 || weekendDiscount > 0 || specialDiscount > 0 || giftPrice > 0;
    }

    public boolean hasChristmasDiscount() {
        return christmasDiscount > 0;
    }

    public boolean hasWeekdayDiscount() {
        return weekdayDiscount > 0;
    }

    public boolean hasWeekendDiscount() {
        return weekendDiscount > 0;
    }

    public boolean hasSpecialDiscount() {
        return specialDiscount > 0;
    }

    public boolean hasGiftEvent() {
        return giftPrice > 0;
    }
}
