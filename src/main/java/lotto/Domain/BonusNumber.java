package lotto.Domain;

import lotto.UI.Output;

public class BonusNumber {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private final int bonusNumber;
    public BonusNumber(String bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    private void validate(String bonusNumber) {
        if (!bonusNumber.matches("[0-9]+")) {
            Output.outputBonusNumberFormat();
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(bonusNumber) < MINIMUM_NUMBER_RANGE
                || Integer.parseInt(bonusNumber) > MAXIMUM_NUMBER_RANGE) {
            Output.outputNumberRange();
            throw new IllegalArgumentException();
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
