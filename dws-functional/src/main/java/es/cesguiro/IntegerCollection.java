package es.cesguiro;

import java.util.ArrayList;
import java.util.List;

public class IntegerCollection {

    private List<Integer> integers = List.of(34, 2, 6, 33, 98, 2, 43, 87, 5, 23, 43, 33);

    public int sum() {
        Integer sum = 0;
        for (Integer integer : integers) {
             sum += integer;
        }
        return sum;
    }

    public int sumEvenValues() {
        int sum = 0;
        for (Integer integer : integers) {
            if (integer % 2 == 0) {
                sum += integer;
            }
        }
        return sum;
    }

    public int max() {
        Integer max = 0;
        for (Integer integer : integers) {
            if (integer > max) {
                max = integer;
            }
        }
        return max;
    }

    public List<Integer> deleteRepeated() {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : integers) {
            if (!result.contains(integer)) {
                result.add(integer);
            }
        }
        return result;
    }

    public List<Integer> order() {
        List<Integer> result = new ArrayList<>(integers);
        result.sort(Integer::compareTo);
        return result;
    }

    public List<Integer> squareList() {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : integers) {
            result.add(integer * integer);
        }
        return result;
    }

}
