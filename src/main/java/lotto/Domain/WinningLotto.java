package lotto.Domain;

import java.util.List;

public class WinningLotto {
    private final LottoNumber bonusNumber;
    private final Lotto winningLotto;

    public WinningLotto(int bonusNumber, Lotto winningLotto) {
        validateDuplicate(bonusNumber, winningLotto);
        winningLotto.getNumbers().add(bonusNumber);
        this.bonusNumber = new LottoNumber(bonusNumber);
        this.winningLotto = winningLotto;
    }

    private void validateDuplicate(int bonusNumber, Lotto winningLotto) {
        if (winningLotto.getNumbers().contains(bonusNumber))
            throw new IllegalArgumentException("[ERROR] 번호는 중복 숫자가 없어야 합니다.");
    }

    public int getBonusNumber() {
        return bonusNumber.mapToInt();
    }

    public List<Integer> getWinningLotto() {
        return winningLotto.getNumbers();
    }
}
