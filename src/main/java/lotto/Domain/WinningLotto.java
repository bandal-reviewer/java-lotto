package lotto.Domain;

import lotto.UI.Output;

import java.util.List;

public class WinningLotto {
    private final int bonusNumber;
    private final Lotto winningLotto;

    public WinningLotto(int bonusNumber, Lotto winningLotto) {
        validate(bonusNumber, winningLotto);
        winningLotto.getNumbers().add(bonusNumber);
        this.bonusNumber = bonusNumber;
        this.winningLotto = winningLotto;
    }

    private void validate(int bonusNumber, Lotto winningLotto) {
        if (winningLotto.getNumbers().contains(bonusNumber)) {
            Output.outputNumberDuplicate();
            throw new IllegalArgumentException();
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getWinningLotto() {
        return winningLotto.getNumbers();
    }
}
