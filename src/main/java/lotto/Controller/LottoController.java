package lotto.Controller;

import lotto.Domain.BonusNumber;
import lotto.Domain.Lotto;
import lotto.Domain.RandomNumbers;
import lotto.Domain.PurchasePrice;
import lotto.Domain.Winning;
import lotto.Domain.WinningNumber;
import lotto.Domain.WinningLotto;
import lotto.UI.Input;
import lotto.UI.Output;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    public void run() {
        try {
            startLotto();
        } catch (IllegalArgumentException exception) {
            Output.printExceptionMessage(exception.getMessage());
        }
    }

    public void startLotto() {
        int purchaseLotteryCount = generatePurchaseLotteryCount();
        Output.printPurchaseLotteryCount(purchaseLotteryCount);
        Lotto[] lotteries = getLotteryTickets(purchaseLotteryCount);

        WinningLotto winningNumberSet = readWinningNumbers();
        calculateResult(purchaseLotteryCount, lotteries, winningNumberSet);
    }

    public static int generatePurchaseLotteryCount() {
        PurchasePrice purchasePrice = readPurchasePrice();
        return purchasePrice.getPurchaseLotteryCount();
    }

    public static PurchasePrice readPurchasePrice() {
        return new PurchasePrice(Input.inputPurchasePrice());
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
        RandomNumbers lottoNumbers = new RandomNumbers();
        return lottoNumbers.getRandomNumberList();
    }

    public static WinningLotto readWinningNumbers() {
        WinningNumber winningNumber = new WinningNumber(Input.inputWinningNumbers());
        Lotto winningNumbersList
                = new Lotto(convertWinningNumberStringToList(winningNumber.getNumbers()));

        BonusNumber bonusNumber = new BonusNumber(Input.inputBonusNumber());
        int integerTypeBonusNumber = bonusNumber.getBonusNumber();

        return new WinningLotto(integerTypeBonusNumber, winningNumbersList);
    }

    public static List<Integer> convertWinningNumberStringToList(String numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers.split(",")) {
            int num = Integer.parseInt(number);
            numbersList.add(num);
        }
        return numbersList;
    }

    public static void calculateResult(
            int purchaseLotteryCount,
            Lotto[] lotteries,
            WinningLotto winningNumberSet
    ) {
        Map<Winning, Integer> winningScoreMap = getWinningScoreMap(lotteries, winningNumberSet);
        String rateOfReturn = getRateOfReturn(purchaseLotteryCount, winningScoreMap);
        Winning.printResult(winningScoreMap, rateOfReturn);
    }

    public static Map<Winning, Integer> getWinningScoreMap(
            Lotto[] lotteries,
            WinningLotto winningNumberSet
    ) {
        Map<Winning, Integer> winningScoreMap = new EnumMap<>(Winning.class);
        for (Winning winning : Winning.values()) {
            winningScoreMap.put(winning, 0);
        }
        saveWinningScore(lotteries, winningNumberSet, winningScoreMap);
        return winningScoreMap;
    }

    public static void saveWinningScore(
            Lotto[] lotteries,
            WinningLotto winningNumberSet,
            Map<Winning, Integer> winningScoreMap
    ) {
        for (Lotto lottery : lotteries) {
            List<Integer> lotteryNumbers = lottery.getNumbers();

            boolean hasBonusNum = lotteryNumbers.contains(winningNumberSet.getBonusNumber());
            int winningCount = getWinningCount(lotteryNumbers, winningNumberSet.getWinningLotto());

            Winning winning = Winning.getRightWinningScore(winningCount, hasBonusNum);
            winningScoreMap.put(winning, winningScoreMap.get(winning) + 1);
        }
    }

    public static int getWinningCount(
            List<Integer> lotteryNumbers,
            List<Integer> winningNumbersList
    ) {
        int winningCount = 0;
        for (int lotteryNum : lotteryNumbers) {
            if (winningNumbersList.contains(lotteryNum)) winningCount++;
        }
        return winningCount;
    }

    public static String getRateOfReturn(
            int purchaseLotteryCount,
            Map<Winning, Integer> winningScoreMap
    ) {
        int totalWinningMoney = 0;
        for (Winning key : winningScoreMap.keySet()) {
            totalWinningMoney += winningScoreMap.get(key) * key.getMoneyRatio();
        }
        return String.format("%.1f", (double) totalWinningMoney / (double) purchaseLotteryCount * 100.0);
    }
}