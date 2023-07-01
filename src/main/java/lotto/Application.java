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
        int bonusNumber = inputBonusNumber(prizeNumbersList);
        printResult(purchasePrice, lotteries, prizeNumbersList, bonusNumber);
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

    public static int inputBonusNumber(List<Integer> prizeNumbersList) {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonusNumber = Integer.parseInt(readLine());
        prizeNumbersList.add(bonusNumber);

        return bonusNumber;
    }

    public static void printResult(
            int purchasePrice,
            Lotto[] lotteries,
            List<Integer> prizeNumbersList,
            int bonusNumber
    ) {
        // three, four, five, fiveBonus, six
        int[] scoreArray = {0, 0, 0, 0, 0};
        double rateOfReturn = 0.0;

        for (Lotto lottery : lotteries) {
            boolean hasBonusNum = false;
            List<Integer> lotteryNumbers = lottery.getNumbers();
            int count = 0;

            if (lotteryNumbers.contains(bonusNumber)) hasBonusNum = true;
            for (int num : lotteryNumbers) {
                if (prizeNumbersList.contains(num)) count++;
            }

            if (count == 3) scoreArray[0]++;
            else if (count == 4) scoreArray[1]++;
            else if (count == 5) scoreArray[2]++;
            else if (count == 6 && hasBonusNum) scoreArray[3]++;
            else if (count == 6) scoreArray[4]++;
        }
    }
}
