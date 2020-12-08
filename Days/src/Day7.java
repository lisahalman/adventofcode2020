import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day7Input.txt").toPath());
        HashSet<String> bagsWhichMayContainShinyGold = listOfBagsWhichContainGivenColor(lines, "shiny gold");

        int bagCountWhereOtherBagsFitIn = bagsWhichMayContainShinyGold.size();

        System.out.println(bagsWhichMayContainShinyGold);
        System.out.println("Number of bags which can contain shiny gold bag: " + bagCountWhereOtherBagsFitIn);

        do {
            bagCountWhereOtherBagsFitIn = bagsWhichMayContainShinyGold.size();
            HashSet<String> copy = new HashSet<>(bagsWhichMayContainShinyGold);
            for (String bag : copy) {
                bagsWhichMayContainShinyGold.addAll(listOfBagsWhichContainGivenColor(lines, bag));

            }
        } while (bagCountWhereOtherBagsFitIn != bagsWhichMayContainShinyGold.size());

        System.out.println(bagCountWhereOtherBagsFitIn);

        //part 2
        Map<String, Map<String, Integer>> mapOfMaps = new HashMap<>();

        for (String line : lines) {
            line = line.replace(".", "");
            String[] getFirstColor = line.split(" bags");
            String keyForMap = getFirstColor[0];
            //System.out.println(keyForMap);
            String[] getFirstRequirement = line.split(" contain ");
            String allTheBags = getFirstRequirement[1];
            String[] bagsWithNumbers = allTheBags.split(", ");

            //System.out.println(Arrays.toString(bagsWithNumbers));

            List<String> numberInString = new ArrayList<>();
            List<String> colorNames = new ArrayList<>();

            for (String criteria : bagsWithNumbers) {
                String[] numColorBags = criteria.split(" ");
                String number = numColorBags[0];
                numberInString.add(number);

                colorNames.add(numColorBags[1] + " " + numColorBags[2]);
            }
            //System.out.println(numberInString);
            for (String number : numberInString) {
                if (number.equals("no")) {
                    numberInString.remove("no");
                    numberInString.add("0");
                }
            }
            List<Integer> num = new ArrayList<>();
            for (String number : numberInString) {
                num.add(Integer.parseInt(number));
            }
            //System.out.println(numberInString);
            //System.out.println(num);
            //System.out.println(colorNames);

            Map<String, Integer> map = new HashMap<>();
            int howManyPairs = num.size();
            for (int i = 0; i < howManyPairs; i++) {
                map.put(colorNames.get(i), num.get(i));
            }
            //System.out.println(map);
            mapOfMaps.put(keyForMap, map);
        }
        System.out.println(mapOfMaps);

        System.out.println(recursion(mapOfMaps, "shiny gold"));

    }

    public static Integer recursion(Map<String, Map<String, Integer>> map, String color) {
        if (!map.containsKey(color)) {
            return 0;
        } else {
            int count = 0;
            for (Map.Entry<String, Integer> bagAmount : map.get(color).entrySet()) {
                count += bagAmount.getValue() * (1 + recursion(map, bagAmount.getKey()));
            }
            return count;
        }
    }
















    public static HashSet<String> listOfBagsWhichContainGivenColor(List<String> lines, String color) {
        List<String> currentListOfLines = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(color)) {
                currentListOfLines.add(line);
            }
        }
        currentListOfLines.removeIf(line -> line.contains(color + " bags contain"));

        HashSet<String> currentListOfBags = new HashSet<>();
        for (String line : currentListOfLines) {
            String[] bsg = line.split(" bags contain");
            currentListOfBags.add(bsg[0]);
        }
        return currentListOfBags;
    }

}
