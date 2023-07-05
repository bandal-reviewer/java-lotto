package lotto.Domain;

public class PurchasePrice {
    private static final int PURCHASE_PRICE = 1000;

    private final int purchasePrice;

    public PurchasePrice(int purchasePrice) {
        validateRange(purchasePrice);
        validateFormat(purchasePrice);
        this.purchasePrice = purchasePrice;
    }

    private void validateRange(int price) {
        if (price < PURCHASE_PRICE)
            throw new IllegalArgumentException("[ERROR] 구입금액은 1,000원 이상으로 작성하셔야 합니다.");
    }

    private void validateFormat(int price) {
        if (price % PURCHASE_PRICE != 0)
            throw new IllegalArgumentException("[ERROR] 구입금액은 1,000에 나누어 떨어지는 숫자여야 합니다.");
    }

    public int getPrice() {
        return purchasePrice;
    }
}
