package christmas.controller;

import christmas.domain.menu.Order;
import christmas.domain.promotion.*;
import christmas.global.exception.ChristmasException;
import christmas.global.parser.OrderParser;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;
import java.util.function.Supplier;

public class ChristmasController {

    private static final int NO_DISCOUNT = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        int visitDate = getVisitDate();
        Order order = getOrder(visitDate);
        int totalBeforeDiscount = getTotalBeforeDiscount(order);
        GiftEvent giftEvent = applyGiftEvent(totalBeforeDiscount);
        BenefitResult benefitResult = calculateDiscount(visitDate, order, giftEvent);
        printBenefitDetails(benefitResult);
        printBenefitAmount(totalBeforeDiscount, benefitResult);
        printEventBadge(benefitResult);
    }

    private int getVisitDate() {
        outputView.printWelcomeMessage();
        return retryOnException(inputView::readVisitDate);
    }

    private Order getOrder(int visitDate) {
        Order order = retryOnException(this::createOrderFromInput);
        outputView.printEventPreviewMessage(visitDate);
        displayOrderDetails(order);
        return order;
    }

    private int getTotalBeforeDiscount(Order order) {
        int total = order.calculateTotal();
        outputView.printTotalBeforeDiscount(total);
        return total;
    }

    private Order createOrderFromInput() {
        String menuInput = inputView.readMenuAndQuantity();
        return OrderParser.createOrder(menuInput);
    }

    private void displayOrderDetails(Order order) {
        outputView.printOrderDetailsPrefix();
        Map<String, Integer> orderItems = order.getOrderItems();
        orderItems.forEach(outputView::printOrderDetails);
    }

    private GiftEvent applyGiftEvent(int totalBeforeDiscount) {
        outputView.printGiftMenuPrefix();
        if (!GiftEvent.isQualified(totalBeforeDiscount)) {
            outputView.printNonePromotion();
            return null;
        }
        return createGiftEvent();
    }

    private GiftEvent createGiftEvent() {
        GiftEvent giftEvent = new GiftEvent();
        outputView.printGiftMenu(giftEvent.getGiftName(), giftEvent.getGiftQuantity());
        return giftEvent;
    }

    private void printBenefitDetails(BenefitResult benefitResult) {
        outputView.printBenefitsDetailsPrefix();
        if (!benefitResult.hasDiscount()) {
            outputView.printNonePromotion();
            return;
        }
        printAllBenefits(benefitResult);
    }

    private void printAllBenefits(BenefitResult benefitResult) {
        printIfHasDiscount(benefitResult.hasChristmasDiscount(),
                BenefitResult.CHRISTMAS_DISCOUNT, benefitResult.christmasDiscount());
        printIfHasDiscount(benefitResult.hasWeekdayDiscount(),
                BenefitResult.WEEKDAY_DISCOUNT, benefitResult.weekdayDiscount());
        printIfHasDiscount(benefitResult.hasWeekendDiscount(),
                BenefitResult.WEEKEND_DISCOUNT, benefitResult.weekendDiscount());
        printIfHasDiscount(benefitResult.hasSpecialDiscount(),
                BenefitResult.SPECIAL_DISCOUNT, benefitResult.specialDiscount());
        printIfHasDiscount(benefitResult.hasGiftEvent(),
                BenefitResult.GIFT_EVENT, benefitResult.giftPrice());
    }

    private void printIfHasDiscount(boolean hasDiscount, String discountName, int amount) {
        if (hasDiscount) {
            outputView.printBenefitDetails(discountName, amount);
        }
    }

    private void printBenefitAmount(int totalBeforeDiscount, BenefitResult benefitResult) {
        printTotalBenefits(benefitResult);
        printFinalAmount(totalBeforeDiscount, benefitResult);
    }

    private void printTotalBenefits(BenefitResult benefitResult) {
        outputView.printTotalBenefitsPrefix();
        int totalBenefits = benefitResult.getTotalBenefitAmount();
        if (totalBenefits > NO_DISCOUNT) {
            outputView.printTotalBenefits(totalBenefits);
            return;
        }
        outputView.printNonePromotion();
    }

    private void printFinalAmount(int totalBeforeDiscount, BenefitResult benefitResult) {
        outputView.printTotalAfterDiscountPrefix();
        int finalAmount = totalBeforeDiscount - benefitResult.getTotalDiscountAmount();
        outputView.printTotalAfterDiscount(finalAmount);
    }

    private BenefitResult calculateDiscount(int visitDate, Order order, GiftEvent giftEvent) {
        return BenefitResult.from(
                calculateChristmasSale(visitDate),
                calculateWeekdaySale(visitDate, order),
                calculateWeekendSale(visitDate, order),
                calculateSpecialSale(visitDate),
                calculateGiftAmount(giftEvent)
        );
    }

    private int calculateGiftAmount(GiftEvent giftEvent) {
        if (giftEvent == null) {
            return NO_DISCOUNT;
        }
        return giftEvent.getGiftPrice();
    }

    private int calculateChristmasSale(int visitDate) {
        if (!ChristmasSale.isQualified(visitDate)) {
            return NO_DISCOUNT;
        }
        return new ChristmasSale().getDiscountAmount(visitDate);
    }

    private int calculateWeekdaySale(int visitDate, Order order) {
        if (!WeekdaySale.isQualified(visitDate)) {
            return NO_DISCOUNT;
        }
        WeekdaySale weekdaySale = new WeekdaySale();
        return weekdaySale.getDiscountAmount(weekdaySale.calculateWeekdaysEventQuantity(order));
    }

    private int calculateWeekendSale(int visitDate, Order order) {
        if (!WeekendSale.isQualified(visitDate)) {
            return NO_DISCOUNT;
        }
        WeekendSale weekendSale = new WeekendSale();
        return weekendSale.getDiscountAmount(weekendSale.calculateWeekendEventQuantity(order));
    }

    private int calculateSpecialSale(int visitDate) {
        if (!SpecialSale.isQualified(visitDate)) {
            return NO_DISCOUNT;
        }
        return new SpecialSale().getDiscountAmount();
    }

    private void printEventBadge(BenefitResult benefitResult) {
        outputView.printDecemberEventBadgePrefix();
        EventBadge badge = EventBadge.from(benefitResult.getTotalBenefitAmount());
        outputView.printDecemberEventBadge(badge.getName());
    }

    private <T> T retryOnException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (ChristmasException e) {
            System.out.println(e.getMessage());
            return retryOnException(supplier);
        }
    }
}