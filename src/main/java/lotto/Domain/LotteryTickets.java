package lotto.Domain;

import java.util.ArrayList;
import java.util.List;

public class LotteryTickets {

    private final List<Lotto> lotteryTickets = new ArrayList<>();

    public LotteryTickets(int purchaseLotteryCount) {
        generateLotteryTickets(purchaseLotteryCount);
    }

    public void generateLotteryTickets(int purchaseLotteryCount) {
        for (int i = 0; i < purchaseLotteryCount; i++) {
            lotteryTickets.add(new Lotto(RandomNumbers.getRandomNumberList()));
        }
    }

    public List<Lotto> getLotteryTickets() {
        return lotteryTickets;
    }
}
