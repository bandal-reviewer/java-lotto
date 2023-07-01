package lotto.Domain;

import static lotto.UI.Output.outputPurchasePriceDivide;
import static lotto.UI.Output.outputPurchasePriceInteger;

public class PurchasePrice {
    private final String price;

    public PurchasePrice(String price) {
        validate(price);
        this.price = price;
    }

    private void validate(String price) {
        if (!price.matches("[0-9]+")) {
            outputPurchasePriceInteger();
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(price) % 1000 != 0) {
            outputPurchasePriceDivide();
            throw new IllegalArgumentException();
        }
    }

    public int getPrice() {
        return Integer.parseInt(price);
    }
}
