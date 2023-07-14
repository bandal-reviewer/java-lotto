package lotto.UI;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Input {
    public static int inputPurchasePrice() {
        return inputOneNumber();
    }

    public static List<Integer> inputWinningNumbers() {
        return inputNumberList();
    }

    public static int inputBonusNumber() {
        return inputOneNumber();
    }

    public static int inputOneNumber() {
        try {
            return Integer.parseInt(Console.readLine());
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("[ERROR] 정수로 입력하셔야 합니다.");
        }
    }

    public static List<Integer> inputNumberList() {
        String inputString = Console.readLine();
        validateInputStringFormat(inputString);
        return convertStringToNumList(inputString);
    }

    public static void validateInputStringFormat(String inputString) {
        if (!inputString.matches("[0-9,]+"))
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 띄어쓰기 없이 ,로 구분하여 작성해야 합니다.");
    }

    public static List<Integer> convertStringToNumList(String inputString) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : inputString.split(",")) {
            int num = Integer.parseInt(number);
            numbersList.add(num);
        }
        return numbersList;
    }
}
