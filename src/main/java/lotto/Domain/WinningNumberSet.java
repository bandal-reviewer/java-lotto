package lotto.Domain;

import java.util.List;

public class WinningNumberSet {
    private int bonusNumber;
    private List<Integer> numbersList;

    public WinningNumberSet(int bonusNumber, List<Integer> numbersList) {
        this.bonusNumber = bonusNumber;
        this.numbersList = numbersList;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getNumbersList() {
        return numbersList;
    }
}
