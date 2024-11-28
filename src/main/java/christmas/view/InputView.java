package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.global.parser.VisitDateParser;

import static christmas.view.messages.InputMessages.*;

public class InputView {

    public int readVisitDate() {
        System.out.println(ASK_VISIT_DATE);
        String visitDateInput = Console.readLine();
        return VisitDateParser.parseVisitDate(visitDateInput);
    }

    public String readMenuAndQuantity() {
        System.out.println(ASK_MENU_AND_QUANTITY);
        return Console.readLine();
    }
}
