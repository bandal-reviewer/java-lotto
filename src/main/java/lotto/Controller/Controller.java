package lotto.Controller;

import lotto.Domain.BonusNumber;
import lotto.Domain.Lotto;
import lotto.Domain.LottoNumbers;
import lotto.Domain.PurchasePrice;
import lotto.Domain.Winning;
import lotto.Domain.WinningNumber;
import lotto.Domain.WinningNumberSet;
import lotto.UI.Input;
import lotto.UI.Output;

import java.util.List;

public class Controller {
    public void run() {
        try {
            startLotto();
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void startLotto() {
        int purchasePrice = readPurchasePrice();

        int purchaseLotteryCount = purchasePrice / 1000;
        Output.printPurchaseLotteryCount(purchaseLotteryCount);
        Lotto[] lotteries = getLotteryTickets(purchaseLotteryCount);

        WinningNumberSet winningNumberSet = readWinningNumbers();
        setGameOver(purchaseLotteryCount, lotteries, winningNumberSet);
    }

    public static int readPurchasePrice() {
        PurchasePrice purchasePrice = new PurchasePrice(Input.inputPurchasePrice());
        return purchasePrice.getPrice();
    }

    public static Lotto[] getLotteryTickets(int purchaseLotteryCount) {
        Lotto[] lotteries = new Lotto[purchaseLotteryCount];
        for (int i = 0; i < purchaseLotteryCount; i++) {
            lotteries[i] = new Lotto(generateRandomNumberList());
            Output.printLotteryNumbers(lotteries[i].getNumbers());
        }
        return lotteries;
    }

    public static List<Integer> generateRandomNumberList() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        return lottoNumbers.getRandomNumberList();
    }

    public static WinningNumberSet readWinningNumbers() {
        WinningNumber winningNumber = new WinningNumber(Input.inputWinningNumbers());
        List<Integer> winningNumbersList = winningNumber.getNumbersList();

        BonusNumber bonusNumber = new BonusNumber(Input.inputBonusNumber());
        int integerTypeBonusNumber = bonusNumber.getBonusNumber();

        bonusNumber.validateRangeOver(integerTypeBonusNumber);
        winningNumber.validateDuplicateBonusNumber(winningNumbersList, integerTypeBonusNumber);
        winningNumbersList.add(integerTypeBonusNumber);
        return new WinningNumberSet(integerTypeBonusNumber, winningNumbersList);
    }

    public static void setGameOver(
            int purchaseLotteryCount,
            Lotto[] lotteries,
            WinningNumberSet winningNumberSet
    ) {
        int[] winningScoreArray = getWinningScore(lotteries, winningNumberSet);
        double rateOfReturn = getRateOfReturn(purchaseLotteryCount, winningScoreArray);
        printResult(winningScoreArray, rateOfReturn);
    }

    public static int[] getWinningScore(
            Lotto[] lotteries,
            WinningNumberSet winningNumberSet
    ) {
        int[] winningScoreArray = {0, 0, 0, 0, 0};

        for (Lotto lottery : lotteries) {
            List<Integer> lotteryNumbers = lottery.getNumbers();
            boolean hasBonusNum = lotteryNumbers.contains(winningNumberSet.getBonusNumber());
            int winningCount = getWinningCount(lotteryNumbers, winningNumberSet.getNumbersList());
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
        totalWinningMoney += winningScoreArray[0] * Winning.THREE_WINNING.getMoneyRatio();
        totalWinningMoney += winningScoreArray[1] * Winning.FOUR_WINNING.getMoneyRatio();
        totalWinningMoney += winningScoreArray[2] * Winning.FIVE_WINNING.getMoneyRatio();
        totalWinningMoney += winningScoreArray[3] * Winning.FIVE_AND_BONUS_WINNING.getMoneyRatio();
        totalWinningMoney += winningScoreArray[4] * Winning.SIX_WINNING.getMoneyRatio();
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
