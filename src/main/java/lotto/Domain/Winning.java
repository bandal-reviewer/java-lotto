package lotto.Domain;

public enum Winning {
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
}
