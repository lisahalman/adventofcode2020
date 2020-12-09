import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    static Long firstNumber = 0L;
    static Long lastNumber = 0L;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day9Input.txt").toPath());
        List<Long> numbers = new ArrayList<>();
        for (String line : lines) {
            numbers.add(Long.parseLong(line));
        }
        System.out.println(numbers);

        long invalidNumber = 0L;

//        List<Long> numbers = new ArrayList<>();
//        numbers.add(35L);
//        numbers.add(20L);
//        numbers.add(15L);
//        numbers.add(25L);
//        numbers.add(47L);
//        numbers.add(40L);
//        numbers.add(62L);
//        numbers.add(55L);
//        numbers.add(65L);
//        numbers.add(95L);
//        numbers.add(102L);
//        numbers.add(117L);
//        numbers.add(150L);
//        numbers.add(182L);
//        numbers.add(127L);
//        numbers.add(219L);
//        numbers.add(299L);
//        numbers.add(277L);
//        numbers.add(309L);
//        numbers.add(576L);

        for (int i = 25; i < numbers.size(); i++) {
            List<Long> list25Numbers = new ArrayList<>();
            for (int j = i - 25; j < i; j++) {
                list25Numbers.add(numbers.get(j));
            }
            if (!add(list25Numbers, numbers.get(i))) {
                //System.out.println(numbers.get(i));
                invalidNumber = numbers.get(i);
            }
        }
        System.out.println(invalidNumber);

        //part 2




        for (int i = 0; i < numbers.size(); i++) {
            if (contiguous(numbers, i, invalidNumber)) {
                System.out.println("First number: " + numbers.get(i));
//                firstNumber = numbers.get(i);
            }

        }




    }

    public static boolean contiguous(List<Long> numbers, int startNumber, Long invalidNumber) {
        long counter = 0;
        int howManyEncounters = 0;
        for (int i = startNumber; i < numbers.size(); i++) {
            counter += numbers.get(i);
            howManyEncounters++;
            if (counter > invalidNumber) {
                return false;
            } else if (counter == invalidNumber) {
                System.out.println("Last number: " + numbers.get(i));
//                lastNumber = numbers.get(i);
//
//                for (int j = startNumber; j < howManyEncounters; )

                return true;
            }
        }
        return true;
    }




    public static boolean add(List<Long> list25Numbers, Long numberToAddUpTo) {
        for (int i = 0; i < list25Numbers.size(); i++) {
            for (int j = 1; j < list25Numbers.size(); j++) {
                if (list25Numbers.get(i).equals(list25Numbers.get(j))) {
                    continue;
                }
                long answer = list25Numbers.get(i) + list25Numbers.get(j);
                if (answer == numberToAddUpTo) {
                    return true;
                }
            }
        }
        return false;
    }
}
