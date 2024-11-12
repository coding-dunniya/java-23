import java.util.List;

public class ParallelStream {

    public static void main(String[] args) {
        // imagine you have a list of integers
        // you have to calculate product of square roots of all integers
        List<Double> list = List.of(1.0, 2.0, 4.0, 5.0, 7.0);
        double product = 1;
        for (double n : list) {
            double sqrt = Math.sqrt(n);
            product = product * sqrt;
        }
        System.out.println("product of square roots using normal code: " + product);

        // do using stream
        double sqrt = list.stream().reduce(1.0, (a, b) -> a * Math.sqrt(b));
        System.out.println("product of square roots using sequential stream: " + sqrt);

        // using parallel stream
        double parallelSqrt = list.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b));
        System.out.println("product of square roots using parallel stream: " + parallelSqrt);

        // while using parallel stream, we have to be careful about how to combine partial results.
        // partial results means the individual calculation results from different threads.
        // above we were not providing any combiner function and accumulator function was given
        // accumulator function additionally do Math.sqrt which is why we get incorrect answer
        // below we are giving combiner function as well
        // this combiner function will multiply the results from individual threads
        // what's the difference - above we were multiplying sqrt of partial results
        // below we are multiplying partial results
        double parallelSqrt1 = list.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b), (a, b) -> a * b);
        System.out.println("product of square roots using parallel stream: " + parallelSqrt1);
    }
}
