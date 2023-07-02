package lotto.UI;

import lotto.Domain.Winning;

import java.util.HashMap;
import java.util.List;

public class Output {
    private static final String ERROR_PURCHASE_PRICE_INTEGER = "[ERROR] 구입금액은 숫자로 입력해야 합니다.";
    private static final String ERROR_PURCHASE_PRICE_DIVIDE = "[ERROR] 구입금액은 1,000에 나누어 떨어지는 숫자여야 합니다.";
    private static final String ERROR_WINNING_NUMBERS_FORMAT = "[ERROR] 당첨 번호는 띄어쓰기 없이 ,로 구분하여 작성해야 합니다.";
    private static final String ERROR_WINNING_NUMBERS_SIZE = "[ERROR] 당첨 번호는 6개만 작성되어야 합니다.";
    private static final String ERROR_NUMBER_DUPLICATE = "[ERROR] 번호는 중복 숫자가 없어야 합니다.";
    private static final String ERROR_NUMBER_RANGE =  "[ERROR] 번호의 범위는 1부터 45까지여야 합니다.";
    private static final String ERROR_BONUS_NUMBER_FORMAT = "[ERROR] 보너스 번호는 숫자로 입력해야 합니다.";
    private static final String PURCHASE_LOTTERY = "개를 구매했습니다.";
    private static final String RESULT_VIEW = "당첨 통계\n---";

    public static void printPurchaseLotteryCount(int lotteryCount) {
        System.out.println(lotteryCount + PURCHASE_LOTTERY);
    }

    public static void printResultView() {
        System.out.println(RESULT_VIEW);
    }

    public static void printWinningScore(String outputString, int winningScore) {
        System.out.println(outputString + winningScore + "개");
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + String.format("%.1f", rateOfReturn) + "%입니다.");
    }

    public static void printLotteryNumbers(List<Integer> lotteryNumbers) {
        System.out.println(lotteryNumbers);
    }

    public static void outputPurchasePriceInteger() {
        System.out.println(ERROR_PURCHASE_PRICE_INTEGER);
    }

    public static void outputPurchasePriceDivide() {
        System.out.println(ERROR_PURCHASE_PRICE_DIVIDE);
    }

    public static void outputWinningNumbersFormat() {
        System.out.println(ERROR_WINNING_NUMBERS_FORMAT);
    }

    public static void outputWinningNumbersSize() {
        System.out.println(ERROR_WINNING_NUMBERS_SIZE);
    }

    public static void outputBonusNumberFormat() {
        System.out.println(ERROR_BONUS_NUMBER_FORMAT);
    }

    public static void outputNumberDuplicate() {
        System.out.println(ERROR_NUMBER_DUPLICATE);
    }

    public static void outputNumberRange() {
        System.out.println(ERROR_NUMBER_RANGE);
    }
}
