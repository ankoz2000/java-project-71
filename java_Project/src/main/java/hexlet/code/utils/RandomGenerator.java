package hexlet.code.utils;

import java.util.Random;

public class RandomGenerator {
    private static Random random = new Random();


    public static int getRandomNumber() {
        return generateRandomNumber(0, 100);
    }

    public static int getRandomNumberWithInterval(int low, int high) {
        return generateRandomNumber(low, high);
    }

    private static int generateRandomNumber(int low, int high) {
        return random.nextInt((high + low) + 1) + low;
    }

    public static String getRandomOperation() {
        int number = generateRandomNumber(1, 3);
        if (number == 1) {
            return "+";
        } else if (number == 2) {
            return "-";
        }
        return "*";
    }
}
