package christmas.global.parser;

import christmas.domain.menu.Order;
import christmas.global.exception.ChristmasException;
import christmas.global.exception.ErrorMessage;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderParser {

    private static final String ITEM_DELIMITER = ",";
    private static final Pattern ORDER_PATTERN = Pattern.compile("([가-힣a-zA-Z]+)-([1-9]\\d*)");
    public static final int MENU_NAME_INDEX = 1;
    public static final int QUANTITY_INDEX = 2;

    public static Order createOrder(String input) {
        Order order = new Order();
        validateBlankOrder(input);
        Set<String> menuNames = new HashSet<>();

        for (String item : input.split(ITEM_DELIMITER)) {
            addMenuItem(item, order, menuNames);
        }
        return order;
    }

    private static void addMenuItem(String item, Order order, Set<String> menuNames) {
        Matcher matcher = ORDER_PATTERN.matcher(item.trim());
        validateOrderFormat(matcher);

        String menuName = matcher.group(MENU_NAME_INDEX);
        validateDuplicateMenu(menuName, menuNames);
        int quantity = Integer.parseInt(matcher.group(QUANTITY_INDEX));
        order.addOrder(menuName, quantity);
        menuNames.add(menuName);
    }

    private static void validateDuplicateMenu(String menuName, Set<String> menuNames) {
        if (menuNames.contains(menuName)) {
            throw ChristmasException.from(ErrorMessage.INVALID_ORDER_FORMAT);
        }
    }

    private static void validateOrderFormat(Matcher matcher) {
        if (!matcher.matches()) {
            throw ChristmasException.from(ErrorMessage.INVALID_ORDER_FORMAT);
        }
    }

    private static void validateBlankOrder(String input) {
        if (input.isBlank()) {
            throw ChristmasException.from(ErrorMessage.INVALID_ORDER_FORMAT);
        }
    }
}