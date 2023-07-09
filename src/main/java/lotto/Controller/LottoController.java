package lotto.Controller;

import lotto.Domain.LotteryTickets;
import lotto.Domain.Lotto;
import lotto.Domain.LottoNumberVO;
import lotto.Domain.PurchasePrice;
import lotto.Domain.Winning;
import lotto.Domain.WinningLotto;
import lotto.UI.Input;
import lotto.UI.Output;

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

        LotteryTickets lotteryTickets = new LotteryTickets(purchaseLotteryCount);
        Output.printLotteryTickets(lotteryTickets);

        WinningLotto winningNumberSet = readWinningNumbers();
        calculateResult(purchaseLotteryCount, lotteryTickets, winningNumberSet);
    }

    public static int generatePurchaseLotteryCount() {
        PurchasePrice purchasePrice = readPurchasePrice();
        return Lotto.getPurchaseLotteryCount(purchasePrice.getPrice());
    }

    public static PurchasePrice readPurchasePrice() {
        Output.outputPurchasePrice();
        return new PurchasePrice(Input.inputPurchasePrice());
    }

    public static WinningLotto readWinningNumbers() {
        Output.outputWinningNumbers();
        Lotto winningNumbersList = new Lotto(Input.inputWinningNumbers());

        Output.outputBonusNumber();
        LottoNumberVO bonusNumber = LottoNumberVO.from(Input.inputBonusNumber());

        return new WinningLotto(bonusNumber, winningNumbersList);
    }

    public static void calculateResult(
            int purchaseLotteryCount,
            LotteryTickets lotteryTickets,
            WinningLotto winningNumberSet
    ) {
        Map<Winning, Integer> winningScoreMap = getWinningScoreMap(lotteryTickets, winningNumberSet);
        String rateOfReturn = getRateOfReturn(purchaseLotteryCount, winningScoreMap);
        Winning.printResult(winningScoreMap, rateOfReturn);
    }

    public static Map<Winning, Integer> getWinningScoreMap(
            LotteryTickets lotteryTickets,
            WinningLotto winningNumberSet
    ) {
        Map<Winning, Integer> winningScoreMap = new EnumMap<>(Winning.class);
        for (Winning winning : Winning.values()) {
            winningScoreMap.put(winning, 0);
        }
        saveWinningScore(lotteryTickets, winningNumberSet, winningScoreMap);
        return winningScoreMap;
    }

    public static void saveWinningScore(
            LotteryTickets lotteryTickets,
            WinningLotto winningNumberSet,
            Map<Winning, Integer> winningScoreMap
    ) {
        for (Lotto lottery : lotteryTickets.getLotteryTickets()) {
            List<LottoNumberVO> lotteryNumbers = lottery.getNumbers();

            boolean hasBonusNum = hasBonusNumber(lotteryNumbers, winningNumberSet.getBonusNumber());
            int winningCount = getWinningCount(lotteryNumbers, winningNumberSet.getWinningLotto());

            Winning winning = Winning.getRightWinningScore(winningCount, hasBonusNum);
            winningScoreMap.put(winning, winningScoreMap.get(winning) + 1);
        }
    }

    public static boolean hasBonusNumber(
            List<LottoNumberVO> lotteryNumbers,
            LottoNumberVO bonusNumber
    ) {
        for (LottoNumberVO lotteryNumber : lotteryNumbers) {
            if (bonusNumber.equals(lotteryNumber)) return true;
        }
        return false;
    }

    public static int getWinningCount(
            List<LottoNumberVO> lotteryNumbers,
            List<LottoNumberVO> winningNumbersList
    ) {
        int winningCount = 0;
        for (LottoNumberVO lotteryNum : lotteryNumbers) {
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