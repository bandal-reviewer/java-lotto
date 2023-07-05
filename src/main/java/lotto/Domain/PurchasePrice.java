package lotto.Domain;

public class PurchasePrice {
    private final int purchasePrice;

    public PurchasePrice(String purchasePrice) {
        validate(purchasePrice);
        this.purchasePrice = Integer.parseInt(purchasePrice);
    }

    private void validate(String price) {
        if (!price.matches("[0-9]+"))
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자로 입력해야 합니다.");

        if (Integer.parseInt(price) % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 구입금액은 1,000에 나누어 떨어지는 숫자여야 합니다.");
    }

    public int getPrice() {
        return purchasePrice;
    }
}
