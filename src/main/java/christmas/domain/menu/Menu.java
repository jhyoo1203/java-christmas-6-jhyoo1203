package christmas.domain.menu;

import christmas.global.exception.ChristmasException;
import christmas.global.exception.ErrorMessage;

import java.util.Arrays;

import static christmas.domain.menu.Category.*;

public enum Menu {

    // 에피타이저
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    // 메인
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(MAIN, "바비큐립", 54_000),
    SEA_FOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),

    // 디저트
    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    // 음료
    ZERO_COLA(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000)
    ;

    private final Category category;
    private final String name;
    private final int price;

    Menu(
            Category category,
            String name,
            int price
    ) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu findByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> ChristmasException.from(ErrorMessage.INVALID_ORDER_FORMAT));
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
