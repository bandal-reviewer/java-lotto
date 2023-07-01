package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private static final int THREE_WINNING_MONEY_RATIO = 5;
    private static final int FOUR_WINNING_MONEY_RATIO = 50;
    private static final int FIVE_WINNING_MONEY_RATIO = 1_500;
    private static final int FIVE_AND_BONUS_WINNING_MONEY_RATIO = 30_000;
    private static final int SIX_WINNING_MONEY_RATIO = 2_000_000;
    public static void main(String[] args) {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int purchasePrice = inputPurchasePrice();

            int purchaseLotteryCount = purchasePrice / 1000;
            System.out.println(purchaseLotteryCount + "개를 구매했습니다.");
            Lotto[] lotteries = getLotteryTickets(purchaseLotteryCount);

            System.out.println("당첨 번호를 입력해주세요.");
            List<Integer> winningNumbersList = inputWinningNumbers();

            System.out.println("보너스 번호를 입력해 주세요.");
            int bonusNumber = inputBonusNumber(winningNumbersList);

            setGameOver(purchaseLotteryCount, lotteries, winningNumbersList, bonusNumber);
        } catch (IllegalArgumentException ignored) {
        }
    }

    public static int inputPurchasePrice() {
        String purchasePrice = readLine();
        validatePurchasePrice(purchasePrice);
        return Integer.parseInt(purchasePrice);
    }

    public static void validatePurchasePrice(String purchasePrice) {
        if (!purchasePrice.matches("[0-9]+")) {
            System.out.println("[ERROR] 구입금액은 숫자로 입력해야 합니다.");
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(purchasePrice) % 1000 != 0) {
            System.out.println("[ERROR] 구입금액은 1,000에 나누어 떨어지는 숫자여야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static Lotto[] getLotteryTickets(int purchaseLotteryCount) {
        Lotto[] lotteries = new Lotto[purchaseLotteryCount];
        for (int i = 0; i < purchaseLotteryCount; i++) {
            lotteries[i] = new Lotto(getRandomNumberList());
            System.out.println(lotteries[i].getNumbers());
        }
        return lotteries;
    }

    public static List<Integer> getRandomNumberList() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }

    public static List<Integer> inputWinningNumbers() {
        String winningNumbers = readLine();
        validateWinningNumbersFormat(winningNumbers);

        List<Integer> winningNumbersList = saveWinningNumbersList(winningNumbers);
        validateWinningNumbersSize(winningNumbersList.size());
        return winningNumbersList;
    }

    public static void validateWinningNumbersFormat(String winningNumbers) {
        if (!winningNumbers.matches("[0-9,]+")) {
            System.out.println("[ERROR] 당첨 번호는 띄어쓰기 없이 ,로 구분하여 작성해야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static List<Integer> saveWinningNumbersList(String winningNumbers) {
        List<Integer> winningNumbersList = new ArrayList<>();
        for (String numbers : winningNumbers.split(",")) {
            int number = Integer.parseInt(numbers);

            validateDuplicateNumber(winningNumbersList, number);
            validateNumberRange(number);

            winningNumbersList.add(number);
        }
        return winningNumbersList;
    }

    public static void validateDuplicateNumber(
            List<Integer> winningNumbersList,
            int number
    ) {
        if (winningNumbersList.contains(number)) {
            System.out.println("[ERROR] 당첨 번호는 중복 숫자가 없어야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static void validateNumberRange(int number) {
        if (number < MINIMUM_NUMBER_RANGE || number > MAXIMUM_NUMBER_RANGE) {
            System.out.println("[ERROR] 번호의 범위는 1부터 45까지여야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static void validateWinningNumbersSize(int winningNumbersListSize) {
        if (winningNumbersListSize != 6) {
            System.out.println("[ERROR] 당첨 번호는 6개만 작성되어야 합니다.");
            throw new IllegalArgumentException();
        }
    }

    public static int inputBonusNumber(List<Integer> winningNumbersList) {
        String bonusNumber = readLine();
        if (!bonusNumber.matches("[0-9]+")) {
            System.out.println("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");
            throw new IllegalArgumentException();
        }
        int integerTypeBonusNumber = Integer.parseInt(bonusNumber);
        validateDuplicateNumber(winningNumbersList, integerTypeBonusNumber);
        validateNumberRange(integerTypeBonusNumber);
        winningNumbersList.add(integerTypeBonusNumber);
        return integerTypeBonusNumber;
    }

    public static void setGameOver(
            int purchaseLotteryCount,
            Lotto[] lotteries,
            List<Integer> winningNumbersList,
            int bonusNumber
    ) {
        int[] winningScoreArray = getWinningScore(lotteries, winningNumbersList, bonusNumber);
        double rateOfReturn = getRateOfReturn(purchaseLotteryCount, winningScoreArray);
        printResult(winningScoreArray, rateOfReturn);
    }

    public static int[] getWinningScore(
            Lotto[] lotteries,
            List<Integer> winningNumbersList,
            int bonusNumber
    ) {
        int[] winningScoreArray = {0, 0, 0, 0, 0};

        for (Lotto lottery : lotteries) {
            List<Integer> lotteryNumbers = lottery.getNumbers();
            boolean hasBonusNum = lotteryNumbers.contains(bonusNumber);
            int winningCount = getWinningCount(lotteryNumbers, winningNumbersList);
            saveWinningScore(winningCount, winningScoreArray, hasBonusNum);
        }
        return winningScoreArray;
    }

    public static int getWinningCount(
            List<Integer> lotteryNumbers,
            List<Integer> winningNumbersList
    ) {
        int winningCount = 0;
        for (int num : lotteryNumbers) {
            if (winningNumbersList.contains(num)) winningCount++;
        }
        return winningCount;
    }

    public static void saveWinningScore(
            int winningCount,
            int[] winningScoreArray,
            boolean hasBonusNum
    ) {
        if (winningCount == 3) winningScoreArray[0]++;
        else if (winningCount == 4) winningScoreArray[1]++;
        else if (winningCount == 5) winningScoreArray[2]++;
        else if (winningCount == 6 && hasBonusNum) winningScoreArray[3]++;
        else if (winningCount == 6) winningScoreArray[4]++;
    }

    public static double getRateOfReturn(
            int purchaseLotteryCount,
            int[] winningScoreArray
    ) {
        int totalWinningMoney = 0;
        totalWinningMoney += winningScoreArray[0] * THREE_WINNING_MONEY_RATIO;
        totalWinningMoney += winningScoreArray[1] * FOUR_WINNING_MONEY_RATIO;
        totalWinningMoney += winningScoreArray[2] * FIVE_WINNING_MONEY_RATIO;
        totalWinningMoney += winningScoreArray[3] * FIVE_AND_BONUS_WINNING_MONEY_RATIO;
        totalWinningMoney += winningScoreArray[4] * SIX_WINNING_MONEY_RATIO;
        System.out.println(totalWinningMoney);
        return (double) totalWinningMoney / (double) purchaseLotteryCount * 100.0;
    }

    public static void printResult(
            int[] winningScoreArray,
            double rateOfReturn
    ) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + winningScoreArray[0] + "개");
        System.out.println("4개 일치 (50,000원) - " + winningScoreArray[1] + "개");
        System.out.println("5개 일치 (1,500,000원) - " + winningScoreArray[2] + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + winningScoreArray[3] + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + winningScoreArray[4] + "개");
        System.out.println("총 수익률은 " + String.format("%.1f", rateOfReturn) + "%입니다.");
    }
}
