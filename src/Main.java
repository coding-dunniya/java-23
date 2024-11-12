import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// main class - contains main method
public class Main {

    // main method - jvm calls this method
    public static void main(String[] args) {
        List<Integer> list = List.of(1, -1, 2, -2, 33, 44, -11);
        Stream<Integer> stream = list.stream();

        // filter +ve numbers
        Stream<Integer> filteredStream = stream.filter(n -> n >= 0);
        // map integers
        Stream<String> mapStream = filteredStream.map(n -> String.valueOf(n));

        List<String> collected = mapStream.collect(Collectors.toList());

        System.out.println("list: " + collected);

        // list of integers --> filter +ve numbers, find minimum value

        List<Integer> intList = List.of(1, 2, -2, 33, -3, 55);

        Optional<Integer> minOptional = intList.stream()
                .filter(n -> n >= 0).min(Integer::compareTo);

        System.out.println("minimum value: " + minOptional.get());
        normalCode();
    }

    // write the same examples using normal code
    private static void normalCode() {
        List<Integer> list = List.of(1, -1, 2, -2, 33, 44, -11);
        List<String> finalList = new ArrayList<>();
        for (int n : list) {
            if (n >= 0) {
                String strValue = String.valueOf(n);
                finalList.add(strValue);
            }
        }

        System.out.println("list: " + finalList);

        // remove -ve values, find minimum
        List<Integer> intList = List.of(1, -1, 11, -11, -2, -3, 33);
        int currentMin = Integer.MAX_VALUE;
        for (int n : intList) {
            if (n >= 0) {
                currentMin = Math.min(n, currentMin);
            }
        }
        System.out.println("minimum value: " + currentMin);
    }
}
