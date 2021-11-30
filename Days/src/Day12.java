import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Day12 {

    public static StringBuilder direction = new StringBuilder("E");

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day12Input.txt").toPath());
        List<String> actions = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (String line : lines) {
            actions.add(line.substring(0, 1));
            values.add(Integer.parseInt(line.substring(1)));
        }
        System.out.println(actions);
        System.out.println(values);

        Map<String, Integer> map = new HashMap<>();
        map.put("N/S", 0);
        map.put("E/W", 0);

        for (int i = 0; i < lines.size(); i++) {
            if (actions.get(i).equals("L") || actions.get(i).equals("R")) {
                setDirection(actions, values, i);
            } else if (actions.get(i).equals("F")) {
                actions.set(i, direction.toString());
            }
            switch (actions.get(i)) {
                case "N": {
                    int oldCoordination = map.get("N/S");
                    int newCoordination = oldCoordination + values.get(i);
                    map.replace("N/S", newCoordination);
                    break;
                }
                case "E": {
                    int oldCoordination = map.get("E/W");
                    int newCoordination = oldCoordination + values.get(i);
                    map.replace("E/W", newCoordination);
                    break;
                }
                case "S": {
                    int oldCoordination = map.get("N/S");
                    int newCoordination = oldCoordination - values.get(i);
                    map.replace("N/S", newCoordination);
                    break;
                }
                case "W": {
                    int oldCoordination = map.get("E/W");
                    int newCoordination = oldCoordination - values.get(i);
                    map.replace("E/W", newCoordination);
                    break;
                }
            }
            System.out.println(map.keySet() + " " + map.values());
        }
        System.out.println("Manhattan distance: " + ((+(map.get("N/S"))) + (+(map.get("E/W")))));

    }



    public static void setDirection(List<String> action, List<Integer> degree, int i) {
        List<String> compass = new ArrayList<>(Arrays.asList("N", "E", "S", "W"));
        List<Integer> compassIndex = new ArrayList<>(Arrays.asList(0, 90, 180, 270));
        int currentIndex = compass.indexOf(direction.toString());
        int newDegree = 0;
        if (action.get(i).equals("R")) {
            newDegree = degree.get(i) + compassIndex.get(currentIndex);
        } else if (action.get(i).equals("L")) {
            newDegree = compassIndex.get(currentIndex) - degree.get(i);
        }
        if (newDegree == 360) {
            newDegree = 0;
        } else if (newDegree == 450) {
            newDegree = 90;
        } else if (newDegree == 540) {
            newDegree = 180;
        } else if (newDegree == -90) {
            newDegree = 270;
        } else if (newDegree == -180) {
            newDegree = 180;
        } else if (newDegree == -270) {
            newDegree = 90;
        }
        int newCompassIndex = compassIndex.indexOf(newDegree);
        System.out.println((i + 1) + " " + newDegree + " " + compass.get(newCompassIndex));
        direction = new StringBuilder(compass.get(newCompassIndex));
    }


}
