import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day5Input.txt").toPath());
        //System.out.println(lines);
        //System.out.println(127 / 2);
        int begin = 0;
        int end = 127;
        int rowLow = 0;
        int rowHigh = 7;
        int ID = 0;
        List<String> test = new ArrayList<>();
        test.add("FBFBBFFRLR");
        test.add("BFFFBBFRRR");
        test.add("FFFBBBFRRR");
        test.add("BBFFBBFRLL");
        char[][] airplane = new char[128][8];
        for (String line : lines) {
            for (char letter : line.toCharArray()) {
                if (letter == 'F') {
                    begin = begin;
                    end = ((begin + end + 1) / 2) - 1;
                }
                if (letter == 'B') {
                    begin = ((begin + end + 1) / 2);
                    end = end;
                }
                if (letter == 'L') {
                    rowLow = rowLow;
                    rowHigh = ((rowLow + rowHigh + 1) / 2) - 1;
                }
                if (letter == 'R') {
                    rowHigh = rowHigh;
                    rowLow = (rowHigh + rowLow + 1) / 2;
                }

            }
            //System.out.println("Row:" + begin + " comlumn: " + rowLow);
            //System.out.println("Seat ID: " + (begin * 8 + rowLow));

            airplane[begin][rowLow] = 'X';



            int newID = (begin * 8 + rowLow);
            if (newID > ID) {
                ID = newID;
            }
            begin = 0;
            end = 127;
            rowLow = 0;
            rowHigh = 7;
        }
        System.out.println("Max ID: " + ID);

        int rowID = 0;
        //System.out.println(Arrays.deepToString(airplane));
        for (char[] row : airplane) {
            System.out.print(rowID);
            rowID++;
            System.out.println(Arrays.toString(row));
            for (int column : row) {
                //System.out.print(column);
            }
        }
    }
}
