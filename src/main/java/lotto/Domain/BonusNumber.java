package lotto.Domain;

import static lotto.UI.Output.outputBonusNumberFormat;
import static lotto.UI.Output.outputNumberRange;

public class BonusNumber {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private final String bonusNumber;
    public BonusNumber(String bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(String bonusNumber) {
        if (!bonusNumber.matches("[0-9]+")) {
            outputBonusNumberFormat();
            throw new IllegalArgumentException();
        }
    }

    public void validateRangeOver(int bonusNumber) {
        if (bonusNumber < MINIMUM_NUMBER_RANGE || bonusNumber > MAXIMUM_NUMBER_RANGE) {
            outputNumberRange();
            throw new IllegalArgumentException();
        }
    }

    public int getBonusNumber() {
        return Integer.parseInt(bonusNumber);
    }
}
