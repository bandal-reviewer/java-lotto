package lotto.Domain;

public class WinningNumber {

    private final String numbers;

    public WinningNumber(String numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public void validate(String numbers) {
        if (!numbers.matches("[0-9,]+"))
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 띄어쓰기 없이 ,로 구분하여 작성해야 합니다.");
    }

    public String getNumbers() {
        return numbers;
    }
}
