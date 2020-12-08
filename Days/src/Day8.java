import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
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
        Console console = new Console(lines);
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
                console = new Console(copy);
                try {
                    System.out.println(console.doStuff());
                    break;
                } catch (RuntimeException e) {
                    //lines.set(i, lines.get(i).replace("jmp", "nop"));
                }
            }
            if (lines.get(i).contains("jmp")) {
                copy.set(i, lines.get(i).replace("jmp", "nop"));
                console = new Console(copy);
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

class Console {

    int lineNumberToCheck = 0;
    boolean[] lineWasVisited;
    String[][] listPerLine;
    int[] numberPerLine;
    int accumulator = 0;

    public Console(List<String> lines) {
        listPerLine = new String[lines.size()][];
        numberPerLine = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            listPerLine[i] = lines.get(i).split(" ");
            if (listPerLine[i][1].contains("+")) {
                listPerLine[i][1] = listPerLine[i][1].replace("+", "");
            }
            numberPerLine[i] = Integer.parseInt(listPerLine[i][1]);
        }
        lineWasVisited = new boolean[lines.size()];
    }

    public int doStuff() throws Exception {
        while (lineNumberToCheck < listPerLine.length) {
            if (lineWasVisited[lineNumberToCheck]) {
                throw new RuntimeException("Infinite loop.");
            }
            lineWasVisited[lineNumberToCheck] = true;
            switch (listPerLine[lineNumberToCheck][0]) {
                case "acc":
                    accumulator += numberPerLine[lineNumberToCheck];
                    lineNumberToCheck++;
                    break;
                case "jmp":
                    lineNumberToCheck += numberPerLine[lineNumberToCheck];
                    break;
                case "nop":
                    lineNumberToCheck++;
                    break;
                default:
                    System.out.println(" GROOT ALARM " + listPerLine[lineNumberToCheck][0]);
                    throw new Exception(" blubberbrein ");
            }
        }
        return accumulator;
    }
}
