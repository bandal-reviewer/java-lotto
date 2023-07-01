package lotto.Controller;

import lotto.Domain.BonusNumber;
import lotto.Domain.Lotto;
import lotto.Domain.LottoNumbers;
import lotto.Domain.PurchasePrice;
import lotto.Domain.WinningNumber;
import lotto.Domain.WinningNumberSet;
import lotto.UI.Input;
import lotto.UI.Output;

import java.util.List;

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

        WinningNumberSet winningNumberSet = readWinningNumbers();
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

    public static WinningNumberSet readWinningNumbers() {
        WinningNumber winningNumber = new WinningNumber(Input.inputWinningNumbers());
        List<Integer> winningNumbersList = winningNumber.getNumbersList();

        BonusNumber bonusNumber = new BonusNumber(Input.inputBonusNumber());
        int integerTypeBonusNumber = bonusNumber.getBonusNumber();

        bonusNumber.validateRangeOver(integerTypeBonusNumber);
        winningNumber.validateDuplicateBonusNumber(winningNumbersList, integerTypeBonusNumber);
        winningNumbersList.add(integerTypeBonusNumber);
        return new WinningNumberSet(integerTypeBonusNumber, winningNumbersList);
    }
}
