import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day4 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day4Input.txt").toPath());

        int count = 0;
        int valid = 0;
        int passportsValid = 0;

        for (String line : lines) {
            if (!line.equals("")) {
                if (line.contains("byr")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("byr")) {
                            String stringBirthYear = s.replace("byr:", "");
                            int birthYear = Integer.parseInt(stringBirthYear);
                            if (birthYear >= 1920 & birthYear <= 2002) {
                                valid++;
                            }
                        }
                    }
                }

                if (line.contains("iyr")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("iyr")) {
                            String stringIssueYear = s.replace("iyr:", "");
                            int issueYear = Integer.parseInt(stringIssueYear);
                            if (issueYear >= 2010 & issueYear <= 2020) {
                                valid++;
                            }
                        }
                    }
                }

                if (line.contains("eyr")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("eyr")) {
                            String stringExpirationYear = s.replace("eyr:", "");
                            int expirationYear = Integer.parseInt(stringExpirationYear);
                            if (expirationYear >= 2020 & expirationYear <= 2030) {
                                valid++;
                            }
                        }
                    }
                }

                if (line.contains("hgt")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("hgt")) {
                            String stringHeight = s.replace("hgt:", "");
                            if (stringHeight.contains("cm")) {
                                String stringHeightCM = stringHeight.replace("cm", "");
                                int heightCM = Integer.parseInt(stringHeightCM);
                                if (heightCM >= 150 & heightCM <= 193) {
                                    valid++;
                                }
                            }
                            if (stringHeight.contains("in")) {
                                String stringHeightIN = stringHeight.replace("in", "");
                                int heightIN = Integer.parseInt(stringHeightIN);
                                if (heightIN >= 59 & heightIN <=  76) {
                                    valid++;
                                }
                            }
                        }
                    }
                }

                if (line.contains("hcl")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("hcl")) {
                            String stringHairColor = s.replace("hcl:", "");
                            if (stringHairColor.charAt(0) == '#') {
                                String code = stringHairColor.replace("#", "");
                                int charCountCorrect = 0;
                                if (code.length() == 6) {
                                    for (char c : code.toCharArray()) {
                                        if (    c == '0' |
                                                c == '1' |
                                                c == '2' |
                                                c == '3' |
                                                c == '4' |
                                                c == '5' |
                                                c == '6' |
                                                c == '7' |
                                                c == '8' |
                                                c == '9' |
                                                c == 'a' |
                                                c == 'b' |
                                                c == 'c' |
                                                c == 'd' |
                                                c == 'e' |
                                                c == 'f' ) {
                                            charCountCorrect++;
                                        }
                                    }
                                }
                                if (charCountCorrect == 6) {
                                    valid++;
                                }
                            }
                        }
                    }
                }

                if (line.contains("ecl")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("ecl")) {
                            String stringEyeColor = s.replace("ecl:", "");
                            int eyeColorValid = 0;
                            switch(stringEyeColor) {
                                case "amb":
                                    eyeColorValid++;
                                    break;
                                case "blu":
                                    eyeColorValid++;
                                    break;
                                case "brn":
                                    eyeColorValid++;
                                    break;
                                case "gry":
                                    eyeColorValid++;
                                    break;
                                case "grn":
                                    eyeColorValid++;
                                    break;
                                case "hzl":
                                    eyeColorValid++;
                                    break;
                                case "oth":
                                    eyeColorValid++;
                                    break;
                                default:
                                    break;
                            }
                            if (eyeColorValid == 1) {
                                valid++;
                            }
                        }
                    }
                }

                if (line.contains("pid")) {
                    count++;
                    String[] splitLine = line.split(" ");
                    for (String s : splitLine) {
                        if (s.contains("pid")) {
                            String stringPassportID = s.replace("pid:", "");
                            if (stringPassportID.length() == 9) {
                                int passportID = Integer.parseInt(stringPassportID);
                                if (passportID >= 0 & passportID <= 999999999) {
                                    valid++;
                                }
                            }
                        }
                    }
                }

            } else {
                if (count == 7 & valid == 7) {
                    passportsValid++;
                }
                count = 0;
                valid = 0;
            }
        }
        System.out.println("Passports valid: " + passportsValid);
    }
}
