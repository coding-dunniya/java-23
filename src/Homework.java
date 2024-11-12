import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// class contains homework questions code
public class Homework {

    // main method - run this method to run examples
    public static void main(String[] args) {
        maximumSalesForEachDay();
    }

    // 1. given a list of integers partition the list into two separate lists
    // of even and odd numbers
    private static void partitionList() {
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partitions = ints.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        System.out.println("even numbers: " + partitions.get(true));
        System.out.println("odd numbers: " + partitions.get(false));
    }

    // 2. find the intersection of two lists using stream api
    // intersection means common elements
    private static void listsIntersection() {
        List<Integer> one = List.of(1, 3, 4, 5, 6, 7, 88, 9, 99, 98);
        List<Integer> two = List.of(1, 3, 44, 55, 66, 77);
        List<Integer> common = one.stream().filter(two::contains).collect(Collectors.toList());

        System.out.println("intersection (common elements) of two list: " + common);
    }

    // 3. given a string find the first repeated character
    private static void firstRepeatedCharacter() {
        String input = "hello world, my name is ritesh. I am running a youtube channel to teach coding to future software developers";
        // first count the frequency of each character
        Map<Character, Long> frequencies = input.chars()
                .mapToObj(c -> Character.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        // now get the first character with count > 1
        // entry set is a set of map entries
        // stream on that set and then filter with values > 1 and finally get the key which is the actual character
        // and print using sys out
        Optional<Character> optional = frequencies.entrySet().stream().filter(e -> e.getValue() > 1)
                .map(e -> e.getKey()).findFirst();
        if (optional.isPresent()) {
            System.out.println("first repeated character in input string: " + optional.get());
        }
    }

    // 4. given a list of employees group based on their departments
    private static void groupEmployeesBasedOnDepartment() {
        List<Employee> employees = new ArrayList<>();
        // add some employees
        employees.add(new Employee(1, "ritesh", "it", 10));
        employees.add(new Employee(2, "ayush", "sales", 11));
        employees.add(new Employee(3, "mohit", "it", 12));
        employees.add(new Employee(4, "john", "sales", 13));
        employees.add(new Employee(5, "shubham", "marketing", 14));
        employees.add(new Employee(6, "gagan", "ui", 15));
        employees.add(new Employee(7, "shobhit", "sales", 16));
        employees.add(new Employee(8, "elon", "marketing", 17));

        Map<String, List<Employee>> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::department));

        System.out.println("Employees grouped based on department: " + group);
    }

    // 5. given a list of employees count employees in each department
    private static void countEmployeesBasedOnDepartment() {
        List<Employee> employees = new ArrayList<>();
        // add some employees
        employees.add(new Employee(1, "ritesh", "it", 10));
        employees.add(new Employee(2, "ayush", "sales", 11));
        employees.add(new Employee(3, "mohit", "it", 12));
        employees.add(new Employee(4, "john", "sales", 13));
        employees.add(new Employee(5, "shubham", "marketing", 14));
        employees.add(new Employee(6, "gagan", "ui", 15));
        employees.add(new Employee(7, "shobhit", "sales", 16));
        employees.add(new Employee(8, "elon", "marketing", 17));

        Map<String, Long> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.counting()));

        System.out.println("Count of employees in each department: " + group);
    }

    // 6. given a list of employees find the department who has maximum employees
    private static void findDepartmentWithMaxEmployees() {
        List<Employee> employees = new ArrayList<>();
        // add some employees
        employees.add(new Employee(1, "ritesh", "it", 10));
        employees.add(new Employee(2, "ayush", "sales", 11));
        employees.add(new Employee(3, "mohit", "it", 12));
        employees.add(new Employee(4, "john", "sales", 13));
        employees.add(new Employee(5, "shubham", "marketing", 14));
        employees.add(new Employee(6, "gagan", "ui", 15));
        employees.add(new Employee(7, "shobhit", "sales", 16));
        employees.add(new Employee(8, "elon", "marketing", 17));

        Map<String, Long> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.counting()));

        // this we get a key value pair with department and no of employees
        // something like
        // marketing --> 2
        // ui --> 1
        // it --> 2
        // sales --> 3
        // now we have to find from this map the department with maximum employee
        // department is the map key and no of employees is map value
        // so we have to find out the key which has maximum value

        Optional<Map.Entry<String, Long>> optional = group.entrySet().stream().max(Map.Entry.comparingByValue());
        if (optional.isPresent()) {
            Map.Entry<String, Long> department = optional.get();
            System.out.println("department with maximum employees: " + department.getKey());
        }
    }

    // 7. given a list of employees find average salary in each department
    private static void averageSalaryBasedOnDepartment() {
        List<Employee> employees = new ArrayList<>();
        // add some employees
        employees.add(new Employee(1, "ritesh", "it", 10));
        employees.add(new Employee(2, "ayush", "sales", 11));
        employees.add(new Employee(3, "mohit", "it", 12));
        employees.add(new Employee(4, "john", "sales", 13));
        employees.add(new Employee(5, "shubham", "marketing", 14));
        employees.add(new Employee(6, "gagan", "ui", 15));
        employees.add(new Employee(7, "shobhit", "sales", 16));
        employees.add(new Employee(8, "elon", "marketing", 17));

        Map<String, Double> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));

        System.out.println("average salary in each department: " + group);
    }

    // 8. given a list of employees, find the highest salary in each department
    private static void highestSalaryBasedOnDepartment() {
        List<Employee> employees = new ArrayList<>();
        // add some employees
        employees.add(new Employee(1, "ritesh", "it", 10));
        employees.add(new Employee(2, "ayush", "sales", 11));
        employees.add(new Employee(3, "mohit", "it", 12));
        employees.add(new Employee(4, "john", "sales", 13));
        employees.add(new Employee(5, "shubham", "marketing", 14));
        employees.add(new Employee(6, "gagan", "ui", 15));
        employees.add(new Employee(7, "shobhit", "sales", 16));
        employees.add(new Employee(8, "elon", "marketing", 17));

        // grouping by is used extensively
        // grouping by is a function which group items in stream
        // based on a key and calculate the value based on our lambda function
        Map<String, Optional<Employee>> group = employees.stream()
                .collect(Collectors.groupingBy(Employee::department,
                        Collectors.maxBy(Comparator.comparing(Employee::salary))));

        System.out.println("average salary in each department: " + group);
    }

    // 9. given a list of orders, find total sales for each day
    // this question is similar to what we did in programming exercises
    // when we calculated total transaction amount for each day
    private static void totalSalesForEachDay() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "01-01-2024", 200));
        orders.add(new Order(2, "01-01-2024", 200));
        orders.add(new Order(3, "01-01-2024", 200));
        orders.add(new Order(4, "02-01-2024", 200));
        orders.add(new Order(5, "03-01-2024", 200));
        orders.add(new Order(6, "04-01-2024", 200));

        Map<String, Integer> group = orders.stream()
                .collect(Collectors.groupingBy(Order::date, Collectors.summingInt(Order::amount)));

        System.out.println("total sales for each day: " + group);
    }

    // 10. given a list of orders, find the day on which maximum sales occurred
    private static void maximumSalesForEachDay() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1, "01-01-2024", 200));
        orders.add(new Order(2, "01-01-2024", 200));
        orders.add(new Order(3, "01-01-2024", 200));
        orders.add(new Order(4, "02-01-2024", 200));
        orders.add(new Order(5, "03-01-2024", 200));
        orders.add(new Order(6, "04-01-2024", 200));

        // first we group sales for eaach day
        Map<String, Integer> group = orders.stream()
                .collect(Collectors.groupingBy(Order::date, Collectors.summingInt(Order::amount)));

        // next we find the day from this map which has maximum value
        Optional<Map.Entry<String, Integer>> optional = group.entrySet().stream().max(Map.Entry.comparingByValue());
        if (optional.isPresent()) {
            Map.Entry<String, Integer> day = optional.get();
            System.out.println("maximum sales on this day: " + day.getKey());
        }
    }
}

// create employee record to represent an employee
record Employee(int id, String name, String department, int salary) {
}

// create order record to store orders
record Order(int id, String date, int amount) {
}