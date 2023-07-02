package lotto.Domain;

import lotto.UI.Output;

import java.util.List;

public class WinningNumberSet {
    private final int bonusNumber;
    private final Lotto numbersList;

    public WinningNumberSet(int bonusNumber, Lotto numbersList) {
        validate(bonusNumber, numbersList);
        numbersList.getNumbers().add(bonusNumber);
        this.bonusNumber = bonusNumber;
        this.numbersList = numbersList;
    }

    private void validate(int bonusNumber, Lotto numbersList) {
        if (numbersList.getNumbers().contains(bonusNumber)) {
            Output.outputNumberDuplicate();
            throw new IllegalArgumentException();
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getNumbersList() {
        return numbersList.getNumbers();
    }
}
