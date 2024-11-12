import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgrammingExercises {

    public static void main(String[] args) {
        countFrequencies();
    }

    // 1. list of integers find duplicates
    private static void findOutDuplicates() {
        List<Integer> list = List.of(1, 1, 2, 3, 4, 5, 5, 55, 55);
        Set<Integer> set = new HashSet<>();
        List<Integer> duplicates = list.stream().filter(n -> !set.add(n)).collect(Collectors.toList());
        System.out.println("duplicates: " + duplicates);
    }

    // 2. given a string find the first non-repeated character
    private static void nonRepeatedCharacter() {
        String input = "hello world, this is the best java course on youtube";

        Optional<Character> optional = input.chars().mapToObj(c -> Character.valueOf((char) c))
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c))
                .findFirst();
        System.out.println("first non-repeated character: " + optional.get());
    }

    // 3. list of strings, find the longest string
    private static void longestString() {
        List<String> list = List.of("hello", "ritesh", "india", "north america");
        Optional<String> optional = list.stream().max(Comparator.comparingInt(String::length));
        System.out.println("longest string: " + optional.get());
    }

    // 4. given a list of persons, calculate average age
    private static void averageAge() {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "ritesh", 10));
        list.add(new Person(2, "mohit", 11));
        list.add(new Person(3, "ayush", 33));
        list.add(new Person(4, "karan", 22));
        list.add(new Person(5, "uday", 45));

        OptionalDouble optionalDouble = list.stream().mapToInt(p -> p.age()).average();
        System.out.println("average age: " + optionalDouble.getAsDouble());
    }

    // 5. list of integers, find out all prime numbers
    private static void findOutPrimeNumbers() {
        List<Integer> list = List.of(2, 3, 5, 7, 77, 11, 21, 33, 47);
        List<Integer> primes = list.stream().filter(ProgrammingExercises::isPrime)
                .collect(Collectors.toList());

        System.out.println("prime numbers: " + primes);
    }

    // check if n is prime or not
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 6. given two list, combine them and print in sorted order
    private static void combineList() {
        List<Integer> list1 = List.of(1, 2, 3, 33, 44, 4, 44, 56);
        List<Integer> list2 = List.of(11, 1, 3, 23, 15, 17, 19);

        List<Integer> combined = Stream.concat(list1.stream(), list2.stream()).sorted().collect(Collectors.toList());
        System.out.println("Combined list: " + combined);
    }

    // 7. given transactions with date and amount, calculate total amount for a day
    private static void groupTransactionAmounts() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("01-01-2024", 200));
        transactions.add(new Transaction("01-01-2024", 200));
        transactions.add(new Transaction("02-01-2024", 500));
        transactions.add(new Transaction("04-01-2024", 600));
        transactions.add(new Transaction("07-01-2024", 500));
        transactions.add(new Transaction("07-01-2024", 800));

        Map<String, Integer> group = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::date, Collectors.summingInt(Transaction::amount)));

        System.out.println("transactions: " + group);
    }

    // 8. calculate frequency of words
    private static void countFrequencies() {
        String input = "hello world, this is best java course on youtube, follow the channel and know more";
        String[] words = input.split(" ");
        Map<String, Long> group = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("frequency of words: " + group);
    }
}

record Transaction(String date, int amount) {
}

record Person(int id, String name, int age) {
}
