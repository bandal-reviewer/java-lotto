package lotto.Controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Domain.Lotto;
import lotto.Domain.LottoNumbers;
import lotto.Domain.PurchasePrice;
import lotto.Domain.WinningNumber;
import lotto.UI.Input;
import lotto.UI.Output;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lotto.UI.Output.outputNumberDuplicate;
import static lotto.UI.Output.outputNumberRange;
import static lotto.UI.Output.outputWinningNumbersSize;

public class Controller {
    public void run() {
        try {
            startLotto();
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void startLotto() {
        int purchasePrice = readPurchasePrice();

        int purchaseLotteryCount = purchasePrice / 1000;
        Output.printPurchaseLotteryCount(purchaseLotteryCount);
        Lotto[] lotteries = getLotteryTickets(purchaseLotteryCount);

        List<Integer> winningNumbersList = readWinningNumbers();
    }

    public static int readPurchasePrice() {
        PurchasePrice purchasePrice = new PurchasePrice(Input.inputPurchasePrice());
        return purchasePrice.getPrice();
    }

    public static Lotto[] getLotteryTickets(int purchaseLotteryCount) {
        Lotto[] lotteries = new Lotto[purchaseLotteryCount];
        for (int i = 0; i < purchaseLotteryCount; i++) {
            lotteries[i] = new Lotto(generateRandomNumberList());
            Output.printLotteryNumbers(lotteries[i].getNumbers());
        }
        return lotteries;
    }

    public static List<Integer> generateRandomNumberList() {
        LottoNumbers lottoNumbers = new LottoNumbers();
        return lottoNumbers.getRandomNumberList();
    }

    public static List<Integer> readWinningNumbers() {
        String winningNumbers = Input.inputWinningNumbers();
        WinningNumber winningNumber = new WinningNumber(winningNumbers);
        return winningNumber.getNumbersList();
    }
}
