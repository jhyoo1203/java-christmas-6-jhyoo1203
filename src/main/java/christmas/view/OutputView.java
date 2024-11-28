package christmas.view;

import static christmas.view.messages.OutputMessages.*;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printEventPreviewMessage(int visitDate) {
        System.out.printf(EVENT_PREVIEW_MESSAGE + LINE_SEPARATOR, visitDate);
    }

    public void printOrderDetailsPrefix() {
        System.out.println(LINE_SEPARATOR + ORDER_DETAILS_PREFIX);
    }

    public void printOrderDetails(String menuName, int quantity) {
        System.out.printf(ORDER_DETAILS_FORMAT + LINE_SEPARATOR, menuName, quantity);
    }

    public void printTotalBeforeDiscount(int totalBeforeDiscount) {
        System.out.println(LINE_SEPARATOR + TOTAL_BEFORE_DISCOUNT_PREFIX);
        System.out.printf(TOTAL_BEFORE_DISCOUNT_FORMAT + LINE_SEPARATOR, totalBeforeDiscount);
    }

    public void printGiftMenuPrefix() {
        System.out.println(LINE_SEPARATOR + GIFT_MENU_PREFIX);
    }

    public void printGiftMenu(String menuName, int quantity) {
        System.out.printf(GIFT_MENU_FORMAT + LINE_SEPARATOR, menuName, quantity);
    }

    public void printBenefitsDetailsPrefix() {
        System.out.println(LINE_SEPARATOR + BENEFIT_DETAILS_PREFIX);
    }

    public void printBenefitDetails(String benefitName, int amount) {
        System.out.printf(BENEFIT_DETAILS_FORMAT + LINE_SEPARATOR, benefitName, amount);
    }

    public void printTotalBenefitsPrefix() {
        System.out.println(LINE_SEPARATOR + TOTAL_BENEFITS_PREFIX);
    }

    public void printTotalBenefits(int totalBenefits) {
        System.out.printf(TOTAL_BENEFITS_FORMAT + LINE_SEPARATOR, totalBenefits);
    }

    public void printTotalAfterDiscountPrefix() {
        System.out.println(LINE_SEPARATOR + TOTAL_AFTER_DISCOUNT_PREFIX);
    }

    public void printTotalAfterDiscount(int totalAfterDiscount) {
        System.out.printf(TOTAL_AFTER_DISCOUNT_FORMAT + LINE_SEPARATOR, totalAfterDiscount);
    }

    public void printDecemberEventBadgePrefix() {
        System.out.println(LINE_SEPARATOR + DECEMBER_EVENT_BADGE_PREFIX);
    }

    public void printDecemberEventBadge(String eventBadge) {
        System.out.printf(DECEMBER_EVENT_BADGE_FORMAT, eventBadge);
    }

    public void printNonePromotion() {
        System.out.println(NONE);
    }
}
