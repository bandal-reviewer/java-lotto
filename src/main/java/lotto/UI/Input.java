package lotto.UI;

import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static int inputPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return inputOneNumber();
    }

    public static String inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해주세요.");
        return Console.readLine();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
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
