package lotto.Domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int MINIMUM_NUMBER_RANGE = 1;
    private static final int MAXIMUM_NUMBER_RANGE = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            System.out.println("[ERROR] 로또는 6개만 작성되어야 합니다.");
            throw new IllegalArgumentException();
        }

        HashSet<Integer> validateNumbersSet = new HashSet<>(numbers);
        if (validateNumbersSet.size() != numbers.size()) {
            System.out.println("[ERROR] 로또는 중복 숫자가 없어야 합니다.");
            throw new IllegalArgumentException();
        }

        for (int number : numbers) {
            if (number < MINIMUM_NUMBER_RANGE || number > MAXIMUM_NUMBER_RANGE) {
                System.out.println("[ERROR] 로또의 범위는 1부터 45까지여야 합니다.");
                throw new IllegalArgumentException();
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
