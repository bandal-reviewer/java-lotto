package lotto.Domain;

import lotto.UI.Output;

import java.text.DecimalFormat;
import java.util.Map;

public enum Winning {
    NO_WINNING(-1, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(6, 30_000_000),
    FIRST(6, 2_000_000_000);

    private final int correctCount, winningPrize;
    private static final int LOTTERY_PRICE = 1000;

    Winning(int correctCount, int winningPrize) {
        this.correctCount = correctCount;
        this.winningPrize = winningPrize;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getMoneyRatio() {
        return winningPrize / LOTTERY_PRICE;
    }

    public int getWinningPrize() {
        return winningPrize;
    }

    public String getWinningPrizeStringFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(getWinningPrize());
    }

    public static Winning getRightWinningScore(int winningCount, boolean hasBonusNum) {
        if (winningCount == SECOND.correctCount
                && hasBonusNum) return SECOND;

        for (Winning winning : values()) {
            if (winningCount == winning.getCorrectCount()
                    && winning != SECOND) return winning;
        }

        return NO_WINNING;
    }

    public static void printResult(Map<Winning, Integer> winningScoreMap, String rateOfReturn) {
        Output.printResultView();
        for (Winning winning : values()) {
            if (winning == NO_WINNING) continue;
            Output.printWinningScore(winning.getCorrectCount(), winning.getWinningPrizeStringFormat(), winningScoreMap.get(winning), winning);
        }
        Output.printRateOfReturn(rateOfReturn);
    }
}