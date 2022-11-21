package hexlet.code;

import java.util.Scanner;

public class Cli {
    static String name;
    static Scanner userInteraction;

    public static void acquaintance() {
        init();
        welcomeLetter();
        askName();
        greetUser();
        close();
    }

    public static void init() {
        userInteraction = new Scanner(System.in);
    }

    private static void welcomeLetter() {
        System.out.println("Welcome to the Brain Games!");
    }

    private static void askName() {
        System.out.print("May I have your name? ");
        name = getAnswer();
    }

    private static String getAnswer() {
        return userInteraction.next();
    }

    private static void greetUser() {
        System.out.print("Hello, " + name + "!");
    }

    public static void close() {
        userInteraction.close();
    }
}
