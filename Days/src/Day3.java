import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day3Input.txt").toPath());
        int open = 0;
        int tree = 0;
        //tellen hoeveel herhalingen
        int repeat = lines.size() * 7 / lines.get(0).length() + 1;
        System.out.println(repeat);
        //hier maak ik het volledige grid
        List<String> fullGrid = new ArrayList<String>();
        for (int i = 0; i < lines.size() ; i++) {
            fullGrid.add(i, lines.get(i).repeat(repeat));
        }
        //even een visuele representatie maken van mijn volledige grid
        for (String line : fullGrid) {
            System.out.println(line);
        }
        //hier tel ik alle . en #
        int index = 0;
        for (String line : fullGrid) {
            if (line.charAt(index) == '.') {
                open++;
            } else if (line.charAt(index) == '#'){
                tree++;
            } else {
                System.out.println("Error");
            }
            index += 3;
        }
        System.out.println("Right 3 Down 1 - Open: " + open + " Trees: " + tree);

        //part two
        //right 1 down 1
        int openr1d1 = 0;
        int treer1d1 = 0;
        int indexr1d1 = 0;
        for (String line : fullGrid) {
            if (line.charAt(indexr1d1) == '.') {
                openr1d1++;
            } else if (line.charAt(indexr1d1) == '#'){
                treer1d1++;
            } else {
                System.out.println("Error");
            }
            indexr1d1 += 1;
        }
        System.out.println("Right 1 Down 1 - Open: " + openr1d1 + " Trees: " + treer1d1);

        //right 5 down 1
        int openr5d1 = 0;
        int treer5d1 = 0;
        int indexr5d1 = 0;
        for (String line : fullGrid) {
            if (line.charAt(indexr5d1) == '.') {
                openr5d1++;
            } else if (line.charAt(indexr5d1) == '#'){
                treer5d1++;
            } else {
                System.out.println("Error");
            }
            indexr5d1 += 5;
        }
        System.out.println("Right 5 Down 1 - Open: " + openr5d1 + " Trees: " + treer5d1);

        //right 7 down 1
        int openr7d1 = 0;
        int treer7d1 = 0;
        int indexr7d1 = 0;
        for (String line : fullGrid) {
            if (line.charAt(indexr7d1) == '.') {
                openr7d1++;
            } else if (line.charAt(indexr7d1) == '#'){
                treer7d1++;
            } else {
                System.out.println("Error");
            }
            indexr7d1 += 7;
        }
        System.out.println("Right 7 Down 1 - Open: " + openr7d1 + " Trees: " + treer7d1);

        //right 1 down 2
        int openr1d2 = 0;
        int treer1d2 = 0;
        int indexr1d2 = 0;
        for (int j = 0; j < fullGrid.size(); j++) {
            if (j % 2 == 1) {
                continue;
            }
            if (fullGrid.get(j).charAt(indexr1d2) == '.') {
                openr1d2++;
            } else if (fullGrid.get(j).charAt(indexr1d2) == '#'){
                treer1d2++;
            } else {
                System.out.println("Error");
            }
            indexr1d2 += 1;
        }

        System.out.println("Right 1 Down 2 - Open: " + openr1d2 + " Trees: " + treer1d2);
        long trees = (long)tree * (long)treer1d1 * (long)treer1d2 * (long)treer5d1 * (long)treer7d1;
        System.out.println("Trees multiplied: " + trees);
    }
}
