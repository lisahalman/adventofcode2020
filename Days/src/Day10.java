import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day10Input.txt").toPath());
        List<Integer> numbers = new ArrayList<>();
        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }
        Collections.sort(numbers);
        System.out.println(numbers);
        int begin = 0;
        int jolt1Difference = 0;
        int jolt3Difference = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == begin + 1) {
                jolt1Difference++;
                begin = numbers.get(i);
            } else if (numbers.get(i) == begin + 2) {
                begin = numbers.get(i);
            } else if (numbers.get(i) == begin + 3) {
                jolt3Difference++;
                begin = numbers.get(i);
            }
        }
        jolt3Difference++;
        System.out.println("1 Difference: " + jolt1Difference);
        System.out.println("3 Difference: " + jolt3Difference);
        System.out.println("Multiplied: " + (jolt1Difference * jolt3Difference));


        //part 2

        numbers.add(0);
        Collections.sort(numbers);

        List<List<Integer>> newSequence = new ArrayList<>();
        List<Integer> smallSequence = new ArrayList<>();

        for (int i = 0; i < numbers.size(); ) {
            if (smallSequence.isEmpty()) {
                smallSequence.add(numbers.get(i));
                i++;
            } else if (numbers.get(i) == smallSequence.get(smallSequence.size() - 1) + 1) {
                smallSequence.add(numbers.get(i));
                i++;
            } else {
                newSequence.add(smallSequence);
                smallSequence = new ArrayList<>();
            }
        }
        System.out.println(newSequence);

        BigInteger total = BigInteger.valueOf(1);

        for (int i = 0; i < newSequence.size(); i++) {
            if (newSequence.get(i).size() == 3) {
                total = total.multiply(BigInteger.valueOf(2));
            } else if (newSequence.get(i).size() == 4) {
                total = total.multiply(BigInteger.valueOf(4));
            } else if (newSequence.get(i).size() == 5) {
                total = total.multiply(BigInteger.valueOf(7));
            }
        }
        System.out.println(total);
    }
}
