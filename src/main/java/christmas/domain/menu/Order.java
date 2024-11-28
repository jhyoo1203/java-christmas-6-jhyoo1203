package christmas.domain.menu;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {

    private final Map<String, Integer> orderMap;

    public Order() {
        this.orderMap = new LinkedHashMap<>();
    }

    public void addOrder(String menuItem, int quantity) {
        orderMap.put(menuItem, quantity);
    }

    public int calculateTotal() {
        return orderMap.entrySet().stream()
                .mapToInt(entry -> {
                    Menu menu = Menu.findByName(entry.getKey());
                    return menu.getPrice() * entry.getValue();
                })
                .sum();
    }

    public Map<String, Integer> getOrderItems() {
        return Collections.unmodifiableMap(orderMap);
    }
}
