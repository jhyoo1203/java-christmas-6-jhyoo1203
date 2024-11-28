package christmas.domain.promotion;

import christmas.domain.menu.Menu;

public class GiftEvent {

    private static final int MINIMUM_ORDER_FOR_GIFT = 120_000;
    private static final String CHAMPAIGN = "샴페인";
    private static final int GIFT_AMOUNT = 1;

    private final Menu menu = Menu.findByName(CHAMPAIGN);

    public static boolean isQualified(int total) {
        return total >= MINIMUM_ORDER_FOR_GIFT;
    }

    public String getGiftName() {
        return menu.getName();
    }

    public int getGiftQuantity() {
        return GIFT_AMOUNT;
    }
}
