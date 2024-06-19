package es.cesguiro;

import java.util.List;

public class IntegerCollectionFunctional {

    private List<Integer> integers = List.of(34, 2, 6, 33, 98, 2, 43, 87, 5, 23, 43, 33);

    public int sum() {
        return integers
                .stream()
                .reduce(Integer::sum)
                .orElse(0);

        /*return integers
                .stream()
                .reduce(0, (a, b) -> a + b);*/

        /*return integers
                .stream()
                .reduce(0, Integer::sum);*/
    }

    public int sumEvenValues() {
        return integers
                .stream()
                .filter(i -> i % 2 == 0)
                .reduce(0, Integer::sum);
    }

    public int max() {
        return integers
                .stream()
                .reduce(0, Integer::max);
    }

    public List<Integer> deleteRepeated() {
        return integers
                .stream()
                .distinct()
                .toList();
    }

    public List<Integer> order() {
        return integers
                .stream()
                .sorted()
                .toList();
    }

    public List<Integer> squareList() {
        return integers
                .stream()
                .map(i -> i * i)
                .toList();
    }
}
