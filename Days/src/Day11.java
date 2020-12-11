import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day11 {

    public static final char FILLED_SEAT = '#';
    public static final char EMPTY_SEAT = 'L';
    public static int rowSize;
    public static int howManyRows;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(new File("Day11Input.txt").toPath());
        rowSize = lines.get(0).length();
        howManyRows = lines.size();
        char[][] pattern = new char[howManyRows][rowSize];
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                pattern[i][j] = lines.get(i).charAt(j);
            }
        }
        char[][] finalSeatings = fillSeats(pattern);
        int occupiedSeats = 0;
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (finalSeatings[i][j] == '#') {
                    occupiedSeats++;
                }
            }
        }
        System.out.println("Number of occupied seats: " + occupiedSeats);
        //part 2
        char[][] finalSeatingsSecondTime = fillSeatsSecondTime(pattern);
        int occupiedSeatsSecondTime = 0;
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (finalSeatingsSecondTime[i][j] == '#') {
                    occupiedSeatsSecondTime++;
                }
            }
        }
        System.out.println("Number of occupied seats second time: " + occupiedSeatsSecondTime);
    }

    private static int getNAdjacentSeats(char[][] pattern, int i, int j) {
        int adjacentFilledSeats = 0;
        if (i > 0 && j > 0 && pattern[i - 1][j - 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (i > 0 && pattern[i - 1][j] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (i > 0 && j < rowSize - 1 && pattern[i - 1][j + 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (j > 0 && pattern[i][j - 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (j < rowSize - 1 && pattern[i][j + 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (i < howManyRows - 1 && j > 0 && pattern[i + 1][j - 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (i < howManyRows - 1 && pattern[i + 1][j] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        if (i < howManyRows - 1 && j < rowSize - 1 && pattern[i + 1][j + 1] == FILLED_SEAT) {
            adjacentFilledSeats++;
        }
        return adjacentFilledSeats;
    }

    public static char[][] fillSeats(char[][] pattern) {
        boolean fillChange = false;
        char[][] filledPattern = new char[howManyRows][rowSize];
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (pattern[i][j] == EMPTY_SEAT && getNAdjacentSeats(pattern, i, j) == 0) {
                    filledPattern[i][j] = FILLED_SEAT;
                    fillChange = true;
                } else {
                    filledPattern[i][j] = pattern[i][j];
                }
            }
        }
        if (!fillChange) {
            return filledPattern;
        }
        return emptySeats(filledPattern);
    }

    public static char[][] emptySeats(char[][] pattern) {
        boolean fillChange = false;
        char[][] filledPattern = new char[howManyRows][rowSize];
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (pattern[i][j] == FILLED_SEAT && getNAdjacentSeats(pattern, i, j) >= 4) {
                    filledPattern[i][j] = EMPTY_SEAT;
                    fillChange = true;
                } else {
                    filledPattern[i][j] = pattern[i][j];
                }
            }
        }
        if (!fillChange) {
            return filledPattern;
        }
        return fillSeats(filledPattern);
    }

    //--------------------------------------part 2-------------------------------------------------------------------------
    private static int helper(char[][] pattern, int x, int y, int d_x, int d_y) {
        do {
            x += d_x;
            y += d_y;
            if (x >= 0 && y >= 0 && x < pattern.length && y < pattern[x].length) {
                if (pattern[x][y] == FILLED_SEAT) {
                    return 1;
                } else if (pattern[x][y] == EMPTY_SEAT) {
                    return 0;
                }
            }
        } while (x >= 0 && y >= 0 && x < pattern.length && y < pattern[x].length);
        return 0;
    }

    private static int getNVisibleSeats(char[][] pattern, int k, int l) {
        int visibleSeats = 0;
        visibleSeats += helper(pattern, k, l, -1, -1);
        visibleSeats += helper(pattern, k, l, -1, 0);
        visibleSeats += helper(pattern, k, l, -1, 1);
        visibleSeats += helper(pattern, k, l, 0, -1);
        visibleSeats += helper(pattern, k, l, 0, 1);
        visibleSeats += helper(pattern, k, l, 1, -1);
        visibleSeats += helper(pattern, k, l, 1, 0);
        visibleSeats += helper(pattern, k, l, 1, 1);
        return visibleSeats;
    }

    public static char[][] fillSeatsSecondTime(char[][] pattern) {
        boolean fillChange = false;
        char[][] filledPattern = new char[howManyRows][rowSize];
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (pattern[i][j] == EMPTY_SEAT && getNVisibleSeats(pattern, i, j) == 0) {
                    filledPattern[i][j] = FILLED_SEAT;
                    fillChange = true;
                } else {
                    filledPattern[i][j] = pattern[i][j];
                }
            }
        }
        if (!fillChange) {
            return filledPattern;
        }
        return emptySeatsSecondTime(filledPattern);
    }

    public static char[][] emptySeatsSecondTime(char[][] pattern) {
        boolean fillChange = false;
        char[][] filledPattern = new char[howManyRows][rowSize];
        for (int i = 0; i < howManyRows; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (pattern[i][j] == FILLED_SEAT && getNVisibleSeats(pattern, i, j) >= 5) {
                    filledPattern[i][j] = EMPTY_SEAT;
                    fillChange = true;
                } else {
                    filledPattern[i][j] = pattern[i][j];
                }
            }
        }
        if (!fillChange) {
            return filledPattern;
        }
        return fillSeatsSecondTime(filledPattern);
    }
}
