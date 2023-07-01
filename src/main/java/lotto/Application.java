package lotto;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        int purchasePrice = inputPurchasePrice();
    }

    public static int inputPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(readLine());
        if (price % 1000 != 0) throw new IllegalArgumentException();

        return price;
    }
}
