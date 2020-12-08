import java.util.List;

class Day8Console {

    int lineNumberToCheck = 0;
    boolean[] lineWasVisited;
    String[][] listPerLine;
    int[] numberPerLine;
    int accumulator = 0;

    public Day8Console(List<String> lines) {
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
