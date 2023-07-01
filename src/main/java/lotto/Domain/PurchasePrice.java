package lotto.Domain;

import static lotto.UI.Output.outputPurchasePriceDivide;
import static lotto.UI.Output.outputPurchasePriceInteger;

public class PurchasePrice {
    private String purchasePrice;

    public PurchasePrice(String purchasePrice) {
        validate(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void validate(String purchasePrice) {
        if (!purchasePrice.matches("[0-9]+")) {
            outputPurchasePriceInteger();
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(purchasePrice) % 1000 != 0) {
            outputPurchasePriceDivide();
            throw new IllegalArgumentException();
        }
    }

    public int getPurchasePrice() {
        return Integer.parseInt(purchasePrice);
    }
}
