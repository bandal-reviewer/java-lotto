package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    private static final int THREE_PRIZE_MONEY = 5000;
    private static final int FOUR_PRIZE_MONEY = 50000;
    private static final int FIVE_PRIZE_MONEY = 1_500_000;
    private static final int FIVE_AND_BONUS_PRIZE_MONEY = 30_000_000;
    private static final int SIX_PRIZE_MONEY = 2_000_000_000;
    public static void main(String[] args) {
        try {
            int purchasePrice = inputPurchasePrice();
            Lotto[] lotteries = getLotteryTickets(purchasePrice);
            List<Integer> prizeNumbersList = inputPrizeNumbers();
            int bonusNumber = inputBonusNumber(prizeNumbersList);
            setGameOver(purchasePrice, lotteries, prizeNumbersList, bonusNumber);
        } catch (IllegalArgumentException ignored) {
        }
    }

    public static int inputPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchasePrice = readLine();
        if (!isRightInputForPurchasePrice(purchasePrice)) {
            System.out.println("[ERROR] 구입금액은 숫자 형식으로 1,000에 나누어 떨어지는 숫자여야 합니다.");
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(purchasePrice);
    }

    public static boolean isRightInputForPurchasePrice(String purchasePrice) {
        if (!purchasePrice.matches("[0-9]*")) return false;
        return Integer.parseInt(purchasePrice) % 1000 == 0;
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

    public static void setGameOver(
            int purchasePrice,
            Lotto[] lotteries,
            List<Integer> prizeNumbersList,
            int bonusNumber
    ) {
        int[] prizeScoreArray = getPrizeScore(lotteries, prizeNumbersList, bonusNumber);
        double rateOfReturn = getRateOfReturn(purchasePrice, prizeScoreArray);
        printResult(prizeScoreArray, rateOfReturn);
    }

    public static int[] getPrizeScore(
            Lotto[] lotteries,
            List<Integer> prizeNumbersList,
            int bonusNumber
    ) {
        int[] prizeScoreArray = {0, 0, 0, 0, 0};

        for (Lotto lottery : lotteries) {
            List<Integer> lotteryNumbers = lottery.getNumbers();
            boolean hasBonusNum = lotteryNumbers.contains(bonusNumber);
            int prizeCount = getPrizeCount(lotteryNumbers, prizeNumbersList);

            savePrizeScore(prizeCount, prizeScoreArray, hasBonusNum);
        }
        return prizeScoreArray;
    }

    public static int getPrizeCount(
            List<Integer> lotteryNumbers,
            List<Integer> prizeNumbersList
    ) {
        int prizeCount = 0;
        for (int num : lotteryNumbers) {
            if (prizeNumbersList.contains(num)) prizeCount++;
        }

        return prizeCount;
    }

    public static void savePrizeScore(
            int prizeCount,
            int[] prizeScoreArray,
            boolean hasBonusNum
    ) {
        if (prizeCount == 3) prizeScoreArray[0]++;
        else if (prizeCount == 4) prizeScoreArray[1]++;
        else if (prizeCount == 5) prizeScoreArray[2]++;
        else if (prizeCount == 6 && hasBonusNum) prizeScoreArray[3]++;
        else if (prizeCount == 6) prizeScoreArray[4]++;
    }

    public static double getRateOfReturn(
            int purchasePrice,
            int[] scoreArray
    ) {
        int totalPrizeMoney = 0;
        totalPrizeMoney += scoreArray[0] * THREE_PRIZE_MONEY;
        totalPrizeMoney += scoreArray[1] * FOUR_PRIZE_MONEY;
        totalPrizeMoney += scoreArray[2] * FIVE_PRIZE_MONEY;
        totalPrizeMoney += scoreArray[3] * FIVE_AND_BONUS_PRIZE_MONEY;
        totalPrizeMoney += scoreArray[4] * SIX_PRIZE_MONEY;
        System.out.println(totalPrizeMoney);
        return (double) totalPrizeMoney / (double) purchasePrice * 100.0;
    }

    public static void printResult(
            int[] scoreArray,
            double rateOfReturn
    ) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + scoreArray[0] + "개");
        System.out.println("4개 일치 (50,000원) - " + scoreArray[1] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + scoreArray[2] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + scoreArray[3] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + scoreArray[4] + "개");
        System.out.println("총 수익률은 " + String.format("%.1f", rateOfReturn) + "%입니다.");
    }
}
