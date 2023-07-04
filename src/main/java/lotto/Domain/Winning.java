package lotto.Domain;

import lotto.UI.Input;
import lotto.UI.Output;

import java.util.Map;

public enum Winning {
    NO_WINNING(-1, 0),
    FIFTH(3, 5),
    FOURTH(4, 50),
    THIRD(5, 1_500),
    SECOND(6, 30_000),
    FIRST(6, 2_000_000);

    private final int correctCount, moneyRatio;

    Winning(int correctCount, int moneyRatio) {
        this.correctCount = correctCount;
        this.moneyRatio = moneyRatio;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getMoneyRatio() {
        return moneyRatio;
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
            Output.printWinningScore(winning.name(), winningScoreMap.get(winning));
        }
        Output.printRateOfReturn(rateOfReturn);
    }
}