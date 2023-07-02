package lotto.Domain;

import static lotto.UI.Output.outputWinningNumbersFormat;

public class WinningNumber {

    private final String numbers;

    public WinningNumber(String numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public void validate(String numbers) {
        if (!numbers.matches("[0-9,]+")) {
            outputWinningNumbersFormat();
            throw new IllegalArgumentException();
        }
    }

    public String getNumbers() {
        return numbers;
    }
}
