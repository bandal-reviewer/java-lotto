package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.Collections;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        int purchasePrice = inputPurchasePrice();
        Lotto[] lotteries = getLotteryTickets(purchasePrice);
    }

    public static int inputPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        int price = Integer.parseInt(readLine());
        if (price % 1000 != 0) throw new IllegalArgumentException();

        return price;
    }

    public static Lotto[] getLotteryTickets(int purchasePrice) {
        int purchaseLotteryCount = purchasePrice / 1000;
        System.out.println(purchaseLotteryCount + "개를 구매했습니다.");

        Lotto[] lotteries = new Lotto[purchaseLotteryCount];
        for (int i = 0; i < purchaseLotteryCount; i++) {
            lotteries[i] = new Lotto(getRandomNumberList());
            System.out.println(lotteries[i].getNumbers());
        }
        return lotteries;
    }

    public static List<Integer> getRandomNumberList() {
        List<Integer> list = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(list);
        return list;
    }


}
