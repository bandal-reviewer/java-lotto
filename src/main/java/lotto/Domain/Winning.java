package lotto.Domain;

import lotto.UI.Input;
import lotto.UI.Output;

import java.util.Map;

public enum Winning {
    NO_WINNING(-1, 0, ""),
    THREE_WINNING(3, 5, "3개 일치 (5,000원) - "),
    FOUR_WINNING(4, 50, "4개 일치 (50,000원) - "),
    FIVE_WINNING(5, 1_500, "5개 일치 (1,500,000원) - "),
    FIVE_AND_BONUS_WINNING(6, 30_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    SIX_WINNING(6, 2_000_000, "6개 일치 (2,000,000,000원) - ");

    private int correctCount, moneyRatio;
    private String outputString;

    Winning(int correctCount, int moneyRatio, String outputString) {
        this.correctCount = correctCount;
        this.moneyRatio = moneyRatio;
        this.outputString = outputString;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getMoneyRatio() {
        return moneyRatio;
    }

    public String getOutputString() {
        return outputString;
    }

    public static Winning getRightWinningScore(int winningCount, boolean hasBonusNum) {
        if (winningCount == FIVE_AND_BONUS_WINNING.correctCount
                && hasBonusNum) return FIVE_AND_BONUS_WINNING;

        for (Winning winning : values()) {
            if (winningCount == winning.getCorrectCount()
                    && winning != FIVE_AND_BONUS_WINNING) return winning;
        }

        return NO_WINNING;
    }

    public static void printResult(Map<Winning, Integer> winningScoreMap, double rateOfReturn) {
        Output.printResultView();
        for (Winning winning : values()) {
            if (winning == NO_WINNING) continue;
            Output.printWinningScore(winning.getOutputString(), winningScoreMap.get(winning));
        }
        Output.printRateOfReturn(rateOfReturn);
    }
}