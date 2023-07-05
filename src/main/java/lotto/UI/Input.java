package lotto.UI;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static int inputPurchasePrice() {
        return inputOneNumber();
    }

    public static String inputWinningNumbers() {
        return Console.readLine();
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
}
