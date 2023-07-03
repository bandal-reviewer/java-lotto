package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.Domain.BonusNumber;
import lotto.Domain.PurchasePrice;
import lotto.Domain.WinningNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static lotto.Controller.Controller.getWinningCount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @DisplayName("구입금액을 형식에 맞지 않게 입력하면 [ERROR] 메시지가 출력된다.")
    @ValueSource(strings = {"1000h", "1001"})
    @ParameterizedTest
    void outputErrorWhenPurchasePriceTypedIncorrectly(String testPurchasePrice) {
        assertSimpleTest(() -> {
            runException(testPurchasePrice);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("구입금액을 형식에 맞지 않게 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"1000h", "1001"})
    @ParameterizedTest
    void occurredErrorWhenPurchasePriceTypedIncorrectly(String testPurchasePrice) {
        assertThrows(IllegalArgumentException.class,
                () -> new PurchasePrice(testPurchasePrice));
    }

    @DisplayName("당첨 번호를 형식에 맞지 않게 입력하면 [ERROR] 메시지가 출력된다.")
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "d,d,d,d,d,d", "1.2.3.4.5.6"})
    @ParameterizedTest
    void outputErrorWhenWinningNumbersTypedIncorrectly(String testWinningNumbers) {
        assertSimpleTest(() -> {
            runException(testWinningNumbers);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("당첨 번호를 형식에 맞지 않게 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "d,d,d,d,d,d", "1.2.3.4.5.6"})
    @ParameterizedTest
    void occurredErrorWhenWinningNumbersTypedIncorrectly(String testWinningNumbers) {
        assertThrows(IllegalArgumentException.class,
                () -> new WinningNumber(testWinningNumbers));
    }

    @DisplayName("보너스 번호를 형식에 맞지 않게 입력하면 [ERROR] 메시지가 출력된다.")
    @ValueSource(strings = {"0", "46", "h", "0h"})
    @ParameterizedTest
    void createNumberByOverRange(String testNumber) {
        assertSimpleTest(() -> {
            runException("1000", "1,2,3,4,5,6", testNumber);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @DisplayName("보너스 번호를 형식에 맞지 않게 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"0", "46", "h", "0h"})
    @ParameterizedTest
    void createNumberByOverRange2(String testNumber) {
        assertThrows(IllegalArgumentException.class,
                () -> new BonusNumber(testNumber));
    }

//    @DisplayName("숫자가 이미 리스트에 포함되어 있다면 예외가 발생한다.")
//    @Test
//    void validateDuplicateNumberTest() {
//        List<Integer> testWinningNumbersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
//        assertThrows(IllegalArgumentException.class,
//                () -> validateDuplicateNumber(testWinningNumbersList, 6));
//    }
//

    @DisplayName("당첨 숫자와 로또를 비교하여 일치하는 숫자를 제대로 찾는지 확인")
    @Test
    void getWinningCountTest() {
        assertAll(
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(7, 8, 9, 10, 11, 12, 13)), 0),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 7, 8, 9, 10, 11, 12)), 1),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 7, 8, 9, 10, 11)), 2),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 7, 8, 9, 10)), 3),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 7, 8, 9)), 4),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 7, 8)), 5),
                () -> assertEquals(getWinningCount(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6, 7)), 6)
        );
    }

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
