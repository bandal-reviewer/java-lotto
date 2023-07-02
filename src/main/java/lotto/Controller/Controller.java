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

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Controller {
    public void run() {
        try {
            startLotto();
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void startLotto() {
        int purchaseLotteryCount = generatePurchaseLotteryCount();
        Output.printPurchaseLotteryCount(purchaseLotteryCount);
        Lotto[] lotteries = getLotteryTickets(purchaseLotteryCount);

        WinningNumberSet winningNumberSet = readWinningNumbers();
        setGameOver(purchaseLotteryCount, lotteries, winningNumberSet);
    }

    public static int generatePurchaseLotteryCount() {
        return readPurchasePrice() / 1000;
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
        Map<Winning, Integer> winningScoreMap = getWinningScore(lotteries, winningNumberSet);
        double rateOfReturn = getRateOfReturn(purchaseLotteryCount, winningScoreMap);
        Winning.printResult(winningScoreMap, rateOfReturn);
    }

    public static Map<Winning, Integer> getWinningScore(
            Lotto[] lotteries,
            WinningNumberSet winningNumberSet
    ) {
        Map<Winning, Integer> winningScoreMap = new EnumMap<>(Winning.class);
        for (Winning winning : Winning.values()) {
            winningScoreMap.put(winning, 0);
        }

        for (Lotto lottery : lotteries) {
            List<Integer> lotteryNumbers = lottery.getNumbers();
            boolean hasBonusNum = lotteryNumbers.contains(winningNumberSet.getBonusNumber());
            int winningCount = getWinningCount(lotteryNumbers, winningNumberSet.getNumbersList());
            Winning winning = Winning.getRightWinningScore(winningCount, hasBonusNum);
            winningScoreMap.put(winning, winningScoreMap.get(winning) + 1);
        }
        return winningScoreMap;
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

    public static double getRateOfReturn(
            int purchaseLotteryCount,
            Map<Winning, Integer> winningScoreMap
    ) {
        int totalWinningMoney = 0;
        for (Winning key : winningScoreMap.keySet()) {
            totalWinningMoney += winningScoreMap.get(key) * key.getMoneyRatio();
        }
        return (double) totalWinningMoney / (double) purchaseLotteryCount * 100.0;
    }
}