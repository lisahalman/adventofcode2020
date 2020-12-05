import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day2Input.txt").toPath());
        int count = 0;
        int newCount = 0;
        for (String line : lines) {
            String[] splittedOnSpace = line.split(" ");

            //numbers
            String numbers = splittedOnSpace[0];
            String[] numbersSplittedOnDash = numbers.split("-");
            int lowest = Integer.parseInt(numbersSplittedOnDash[0]);
            int highest = Integer.parseInt(numbersSplittedOnDash[1]);

            //letter
            char letter = splittedOnSpace[1].charAt(0);

            //password
            String password = splittedOnSpace[2];

            //logics
            int letterCount = 0;
            for (char l : password.toCharArray()) {
                if (l == letter) {
                    letterCount++;
                }
            }
            if (letterCount >= lowest & letterCount <= highest) {
                count++;
            }

            //logics part two
            char firstLetter = password.charAt(lowest - 1);
            char lastLetter = password.charAt(highest - 1);
            if (letter == firstLetter ^ letter == lastLetter) {
                newCount++;
            }
        }
        System.out.println("Count part 1: " + count);
        System.out.println("Count part 2: " + newCount);
    }

}
