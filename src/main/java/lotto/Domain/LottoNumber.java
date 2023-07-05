package lotto.Domain;

public class LottoNumber {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;

    private final int lottoNumber;

    public LottoNumber(int lottoNumber) {
        validateRange(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

    private void validateRange(int lottoNumber) {
        if (lottoNumber < MINIMUM_NUMBER_RANGE
                || lottoNumber > MAXIMUM_NUMBER_RANGE)
            throw new IllegalArgumentException("[ERROR] 번호의 범위는 1부터 45까지여야 합니다.");
    }

    public int mapToInt() {
        return lottoNumber;
    }
}
