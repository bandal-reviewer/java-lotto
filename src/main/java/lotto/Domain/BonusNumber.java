package lotto.Domain;

public class BonusNumber {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private final int bonusNumber;
    public BonusNumber(String bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = Integer.parseInt(bonusNumber);
    }

    private void validate(String bonusNumber) {
        if (!bonusNumber.matches("[0-9]+"))
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자로 입력해야 합니다.");

        if (Integer.parseInt(bonusNumber) < MINIMUM_NUMBER_RANGE
                || Integer.parseInt(bonusNumber) > MAXIMUM_NUMBER_RANGE)
            throw new IllegalArgumentException("[ERROR] 번호의 범위는 1부터 45까지여야 합니다.");
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
