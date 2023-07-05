package lotto.Domain;

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
        if (winningLotto.getNumbers().contains(bonusNumber))
            throw new IllegalArgumentException("[ERROR] 번호는 중복 숫자가 없어야 합니다.");
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getWinningLotto() {
        return winningLotto.getNumbers();
    }
}
