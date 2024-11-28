package christmas.domain.promotion;

import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Order;
import christmas.global.constants.DateInfo;
import christmas.global.constants.DiscountAmount;
import christmas.global.constants.WeekInfo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class WeekdaySale {

    public static boolean isQualified(int visitDate) {
        DayOfWeek dayOfWeek = LocalDate.of(DateInfo.YEAR, DateInfo.MONTH, visitDate).getDayOfWeek();
        return WeekInfo.from(dayOfWeek).equals(WeekInfo.WEEKDAY);
    }

    public int getDiscountAmount(int quantity) {
        return DiscountAmount.BASIC_DISCOUNT_AMOUNT * quantity;
    }

    public int calculateWeekdaysEventQuantity(Order order) {
        return order.getOrderItems().entrySet().stream()
                .filter(entry -> {
                    Menu menu = Menu.findByName(entry.getKey());
                    return menu.getCategory().equals(Category.DESSERT);
                })
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
