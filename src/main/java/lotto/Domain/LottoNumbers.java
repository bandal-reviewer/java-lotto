package lotto.Domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoNumbers {
    public List<Integer> getRandomNumberList() {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6);
    }
}
