package lotto.Domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.UI.Output.outputNumberDuplicate;
import static lotto.UI.Output.outputNumberRange;
import static lotto.UI.Output.outputWinningNumbersFormat;
import static lotto.UI.Output.outputWinningNumbersSize;

public class WinningNumber {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private final List<Integer> numbersList;

    public WinningNumber(String numbers) {
        validate(numbers);
        this.numbersList = getNumbers(numbers);
    }

    public void validate(String numbers) {
        if (!numbers.matches("[0-9,]+")) {
            outputWinningNumbersFormat();
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getNumbers(String numbers) {
        List<Integer> numbersList = new ArrayList<>();
        for (String number : numbers.split(",")) {
            int num = Integer.parseInt(number);
            validateList(numbersList, num);

            numbersList.add(num);
        }
        validateWinningNumbersSize(numbersList.size());
        return numbersList;
    }

    private void validateList(
            List<Integer> numbersList,
            int number
    ) {
        if (numbersList.contains(number)) {
            outputNumberDuplicate();
            throw new IllegalArgumentException();
        }

        if (number < MINIMUM_NUMBER_RANGE || number > MAXIMUM_NUMBER_RANGE) {
            outputNumberRange();
            throw new IllegalArgumentException();
        }
    }

    private void validateWinningNumbersSize(int winningNumbersListSize) {
        if (winningNumbersListSize != 6) {
            outputWinningNumbersSize();
            throw new IllegalArgumentException();
        }
    }

    public List<Integer> getNumbersList() {
        return numbersList;
    }
}
