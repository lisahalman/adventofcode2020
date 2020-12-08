import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day8Input.txt").toPath());
        String[][] listPerLine = new String[1000][];
        int[][] numbers = new int[1000][1];
        for (int i = 0; i < lines.size(); i++) {
            listPerLine[i] = lines.get(i).split(" ");
            if (listPerLine[i][1].contains("+")) {
                listPerLine[i][1] = listPerLine[i][1].replace("+", "");
            }
            numbers[i][0] = Integer.parseInt(listPerLine[i][1]);
        }
        int[] countIfDouble = new int[1000];
        System.out.println(calculateDouble(listPerLine, numbers, countIfDouble, 0));
    }

    public static int calculateDouble(String[][] listPerLine, int[][] accNumber, int[] countForLine, int lineNumberToCheck) {
        if (countForLine[lineNumberToCheck] == 1) {
            return 0;
        } else {
            countForLine[lineNumberToCheck] = 1;
            int accCount = 0;
            if (listPerLine[lineNumberToCheck][0].equals("acc")) {
                accCount += accNumber[lineNumberToCheck][0];
                lineNumberToCheck++;
            }
            if (listPerLine[lineNumberToCheck][0].equals("jmp")) {
                lineNumberToCheck += (accNumber[lineNumberToCheck][0]);
            }
            if (listPerLine[lineNumberToCheck][0].equals("nop")) {
                lineNumberToCheck++;
            }
            return accCount + calculateDouble(listPerLine, accNumber, countForLine, lineNumberToCheck);
        }
    }
}
