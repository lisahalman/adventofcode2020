import java.util.Arrays;

public class Day1 {

    public static void main(String[] args) {
        InputDay1 input = new InputDay1();
        String[] split = input.input.split("\\s+".trim());
        System.out.println(Arrays.toString(split));
        int length = split.length;
        Integer[] intArray = new Integer[length];
        int i = 0;
        for (String str : split) {
            intArray[i] = Integer.parseInt(str);
            i++;
        }
        System.out.println(Arrays.toString(intArray));

        for (int j = 0; j < intArray.length; j++) {
            for (int k = 1; k < intArray.length; k++) {
                int answer = intArray[j] + intArray[k];
                //System.out.println(answer);
                if (answer == 2020) {
//                    System.out.println(intArray[j] * intArray[k]);
//                    System.out.println(intArray[j]);
//                    System.out.println(intArray[k]);
                } else {
                    //System.out.println("Geen 2020");
                }
            }
        }

        for (int a = 0; a < intArray.length; a++) {
            for (int b = 1; b < intArray.length; b++) {
                for (int c = 2; c < intArray.length; c++) {
                    int answer = intArray[a] + intArray[b] + intArray[c];
                    //System.out.println(answer);
                    if (answer == 2020) {
                        System.out.println(intArray[a] * intArray[b] * intArray[c]);
                        System.out.println(intArray[a]);
                        System.out.println(intArray[b]);
                        System.out.println(intArray[c]);
                    } else {
                        //System.out.println("Geen 2020");
                    }
                }

            }
        }



    }
}
