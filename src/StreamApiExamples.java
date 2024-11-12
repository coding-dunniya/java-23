import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiExamples {

    public static void main(String[] args) {
        findUniqueStudentIds();
    }

    // array of email ids, find all emails end with @gmail.com
    // sort in ascending order, print all
    private static void sortEmailIds() {
        String[] emails = new String[]{"hello@gmail.com", "example@hello.com", "ritesh@gmail.com", "ritesh@yahoo.com", "hello@hello.com"};

        Stream<String> stream = Arrays.stream(emails);

        Stream<String> filteredStream = stream.filter(s -> s.endsWith("@gmail.com"));
        Stream<String> sortedStream = filteredStream.sorted();

        List<String> collected = sortedStream.collect(Collectors.toList());

        System.out.println("list: " + collected);

        System.out.println("Print using forEach: ");
        // if we have to print using for each we have to create another stream, previous stream is consumed
        Arrays.stream(emails).filter(s -> s.endsWith("@gmail.com"))
                .sorted()
                .forEach(s -> System.out.println(s));
    }

    // list of integers, filter only +ve values, multiply with random value
    // finally calculate maximum value
    private static void findMaximumValue() {
        List<Integer> list = List.of(1, -1, 2, -2, 22, -22, 3, 4, 5, 6);
        Optional<Double> optional = list.stream().filter(n -> n >= 0)
                .map(n -> n * Math.random())
                .max(Double::compareTo);

        System.out.println("max value: " + optional.get());
    }

    // list of integers, multiply all with each other
    private static void multiplyUsingReduce() {
        List<Integer> list = List.of(1, 2, 3);
        Optional<Integer> optional = list.stream().reduce((a, b) -> a * b);

        System.out.println("Multiplied value: " + optional.get());

        int result = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println("multiplied using identity value: " + result);
    }

    // list of students, find students name starts with a
    private static void findStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "ritesh"));
        students.add(new Student(2, "ayush"));
        students.add(new Student(3, "arvind"));
        students.add(new Student(4, "akash"));
        students.add(new Student(5, "mohit"));

        List<Student> collected = students.stream().filter(s -> s.name().startsWith("a"))
                .collect(Collectors.toList());

        System.out.println("Students: " + collected);
    }

    // list of students, find unique ids
    private static void findUniqueStudentIds() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "ritesh"));
        students.add(new Student(1, "ritesh"));
        students.add(new Student(2, "ayush"));
        students.add(new Student(3, "arvind"));
        students.add(new Student(4, "akash"));
        students.add(new Student(5, "mohit"));

        Set<Integer> ids = students.stream().map(s -> s.id()).collect(Collectors.toSet());
        System.out.println("ids: " + ids);
    }
}

record Student(int id, String name) {
}
