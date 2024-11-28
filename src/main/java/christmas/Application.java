package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        ChristmasController christmasController = new ChristmasController();
        christmasController.run();
        Console.close();
    }
}
