import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) throws Exception {
//        List<String> lines = Files.readAllLines(new File("Day8Input.txt").toPath());
//        String[][] listPerLine = new String[lines.size()][];
//        int[] numbers = new int[lines.size()];
//        for (int i = 0; i < lines.size(); i++) {
//            listPerLine[i] = lines.get(i).split(" ");
//            if (listPerLine[i][1].contains("+")) {
//                listPerLine[i][1] = listPerLine[i][1].replace("+", "");
//            }
//            numbers[i] = Integer.parseInt(listPerLine[i][1]);
//        }
//        int[] countIfDouble = new int[lines.size()];
//        System.out.println(calculateDouble(listPerLine, numbers, countIfDouble, 0));


        List<String> lines = Files.readAllLines(new File("Day8Input.txt").toPath());
        Day8Console console = new Day8Console(lines);
        try {
            console.doStuff();
        } catch (RuntimeException e) {
            System.out.println(console.accumulator);
        }

        //part 2

        for (int i = 0; i < lines.size(); i++) {
            List<String> copy = new ArrayList<>(lines);
            if (lines.get(i).contains("nop")) {
                copy.set(i, lines.get(i).replace("nop", "jmp"));
                console = new Day8Console(copy);
                try {
                    System.out.println(console.doStuff());
                    break;
                } catch (RuntimeException e) {
                    //lines.set(i, lines.get(i).replace("jmp", "nop"));
                }
            }
            if (lines.get(i).contains("jmp")) {
                copy.set(i, lines.get(i).replace("jmp", "nop"));
                console = new Day8Console(copy);
                try {
                    System.out.println(console.doStuff());
                    break;
                } catch (RuntimeException e) {
                    //lines.set(i, lines.get(i).replace("nop", "jmp"));
                }
            }
        }
    }

//    public static int calculateDouble(String[][] listPerLine, int[] accNumber, int[] countForLine, int lineNumberToCheck) {
//        if (countForLine[lineNumberToCheck] == 1) {
//            return 0;
//        } else {
//            countForLine[lineNumberToCheck] = 1;
//            int accCount = 0;
//            if (listPerLine[lineNumberToCheck][0].equals("acc")) {
//                accCount += accNumber[lineNumberToCheck];
//                lineNumberToCheck++;
//            }
//            if (listPerLine[lineNumberToCheck][0].equals("jmp")) {
//                lineNumberToCheck += (accNumber[lineNumberToCheck]);
//            }
//            if (listPerLine[lineNumberToCheck][0].equals("nop")) {
//                lineNumberToCheck++;
//            }
//            return accCount + calculateDouble(listPerLine, accNumber, countForLine, lineNumberToCheck);
//        }
//    }
}
