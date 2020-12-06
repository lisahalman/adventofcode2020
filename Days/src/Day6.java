import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day6Input.txt").toPath());
        //System.out.println(lines);
        int yesCountGroup = 0;
        int yesCountTotal = 0;
        int totaltotal = 0;
        StringBuilder allLettersGroup = new StringBuilder();
        List<String> group = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals("")) {
                //part 1
                for (char letterInForm : line.toCharArray()) {
                    allLettersGroup.append(letterInForm);
                }

                //part 2

                group.add(line);


            } else {
                //System.out.println(group);
                //part 1
                char[] chars = allLettersGroup.toString().toCharArray();
                StringBuilder sb = new StringBuilder();
                boolean repeatedChar;
                for (int i = 0; i < chars.length; i++) {
                    repeatedChar = false;
                    for (int j = i + 1; j < chars.length; j++) {
                        if (chars[i] == chars[j]) {
                            repeatedChar = true;
                            break;
                        }
                    }
                    if (!repeatedChar) {
                        sb.append(chars[i]);
                    }
                }
                //System.out.println(sb);
                yesCountTotal += sb.length();
                allLettersGroup.setLength(0);

                //part 2
                System.out.println(group);

                StringBuilder groupYes = new StringBuilder();
                groupYes.append(group.get(0));
                //System.out.println(groupYes);

                boolean rememberChar = false;
                StringBuilder newGroupYes = new StringBuilder();
                StringBuilder lettersToDelete = new StringBuilder();


                    int smallestStringIndex = 0;

                    for (int i = 0; i < group.size() - 1; i++) {
                        if (group.get(smallestStringIndex).length() < group.get(i + 1).length()) {
                            //niks
                        } else {
                            smallestStringIndex = i + 1;
                        }
                    }
                    System.out.println(group.get(smallestStringIndex));
                    int total = 0;
                    for (String letter : group.get(smallestStringIndex).split("")) {
                        for (String person : group) {
                            if (person.contains(letter)) {
                                total++;
                            }
                        }
                        if (total == group.size()){
                            yesCountGroup++;
                        }
                        total = 0;
                    }
                    totaltotal += yesCountGroup;
                    yesCountGroup = 0;


                    group.clear();


            }

        }
        System.out.println(yesCountTotal);
        System.out.println(totaltotal);
    }
}
