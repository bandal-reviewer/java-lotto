package lotto.Controller;

import lotto.Domain.PurchasePrice;
import lotto.UI.Input;

public class Controller {
    public void run() {
        try {
            startLotto();
        } catch (IllegalArgumentException ignored) {
        }
    }

    public void startLotto() {
        int purchasePrice = readPurchasePrice();
        System.out.println("okay");
    }

    public static int readPurchasePrice() {
        PurchasePrice purchasePrice = new PurchasePrice(Input.inputPurchasePrice());
        return purchasePrice.getPurchasePrice();
    }
}
