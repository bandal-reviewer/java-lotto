package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        int purchasePrice = inputPurchasePrice();
        Lotto[] lotteries = getLotteryTickets(purchasePrice);
        List<Integer> prizeNumbersList = inputPrizeNumbers();
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

    public static List<Integer> inputPrizeNumbers() {
        System.out.println("당첨 번호를 입력해주세요.");
        String prizeNumbers = readLine().trim();
        //if (!isRightInputForm(prizeNumbers)) throw new IllegalArgumentException();

        List<Integer> prizeNumbersList = new ArrayList<>();
        for (String numbers : prizeNumbers.split(",")) {
            int number = Integer.parseInt(numbers);
            //if (prizeNumbersList.contains(number)) throw new IllegalArgumentException();
            prizeNumbersList.add(number);
        }
        return prizeNumbersList;
    }

//    public static boolean isRightInputForm(String prizeNumbers) {
//        if ()
//
//        return true;
//    }
}
