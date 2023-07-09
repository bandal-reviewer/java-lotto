package lotto.Domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LottoNumberVO {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;

    private static final Map<Integer, LottoNumberVO> lottoNumberVOCache = new HashMap<>();

    private final int lottoNumber;

    public LottoNumberVO(int lottoNumber) {
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

    public static LottoNumberVO from (int lottoNumber) {
        if (lottoNumberVOCache.containsKey(lottoNumber))
            return lottoNumberVOCache.get(lottoNumber);
        else {
            LottoNumberVO lottoNumberVO = new LottoNumberVO(lottoNumber);
            lottoNumberVOCache.put(lottoNumber, lottoNumberVO);
            return lottoNumberVO;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumberVO lottoNumber = (LottoNumberVO) o;
        return Objects.equals(this.lottoNumber, lottoNumber.lottoNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }
}
