package lotto.UI;

import lotto.Domain.Winning;

import java.util.List;

public class Output {
    private static final String PURCHASE_LOTTERY = "개를 구매했습니다.";
    private static final String RESULT_VIEW = "당첨 통계\n---";
    private static final String RESULT_BONUS_CORRECT = ", 보너스 볼 일치 ";
    private static final String RESULT_BONUS_NOT_CORRECT = " ";

    public static void printExceptionMessage(String exceptionMessage) {
        System.out.println(exceptionMessage);
    }

    public static void printPurchaseLotteryCount(int lotteryCount) {
        System.out.println(lotteryCount + PURCHASE_LOTTERY);
    }

    public static void printResultView() {
        System.out.println(RESULT_VIEW);
    }

    public static void printWinningScore(int correctCount, String winningPrize, int winningScore, Winning winning) {
        System.out.println(getCorrectCount(correctCount, winning) + "개 일치" + getBonusCorrectString(winning) + "(" + winningPrize  + "원) - " + winningScore + "개");
    }

    public static int getCorrectCount(int correctCount, Winning winning) {
        if (winning == Winning.SECOND) return correctCount - 1;
        return correctCount;
    }

    public static String getBonusCorrectString(Winning winning) {
        if (winning == Winning.SECOND) return RESULT_BONUS_CORRECT;
        return RESULT_BONUS_NOT_CORRECT;
    }

    public static void printRateOfReturn(String rateOfReturn) {
        System.out.println("총 수익률은 " + rateOfReturn + "%입니다.");
    }

    public static void printLotteryNumbers(List<Integer> lotteryNumbers) {
        System.out.println(lotteryNumbers);
    }
}
