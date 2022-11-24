package hexlet.code;

import hexlet.code.interfaces.Game;

import java.util.Scanner;

public class Cli {
    static int selectedGame;
    static Scanner userInteraction;

    public static void init() {
        userInteraction = new Scanner(System.in);
    }

    public static void showInitMessage() {
        init();
        System.out.println("Please enter the game number and press Enter.");
    }

    public static void showAvailableChoices(Game[] games) {
        int i = games.length - 1;
        for (Game game : games) {
            System.out.println(i + " - " + game.getGameName());
            i--;
        }
    }

    public static int selectGame() {
        System.out.print("Your choice: ");
        selectedGame = userInteraction.nextInt();
        return selectedGame;
    }

    public static void showWelcomeLetter() {
        System.out.println("Welcome to the Brain Games!");
    }

//    public static void startSelectedGame() {
//        switch (selectedGame) {
////            case 1 -> Greeting.start();
////            case 2 -> playRound();
//        }
//    }

    public static void close() {
        userInteraction.close();
    }

    public static void showMessageWithoutNewLine(String message) {
        System.out.print(message);
    }

    public static void showPreInteractionMessageToUser(String message) {
        System.out.println(message);
    }

    public static void showMessageToUser(String message) {
        System.out.println(message);
    }

    public static void askQuestion(String question) {
        System.out.println("Question: " + question);
        showMessageWithoutNewLine("Your answer: ");
    }

    public static String getAnswer() {
        return userInteraction.next();
    }

    public static int getNumericAnswer() {
        return userInteraction.nextInt();
    }

    public static void showEndMessage(String username) {
        System.out.println("Congratulations, " + username);
        close();
    }

    public static void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showSuccessMessage(String successMessage) {
        System.out.println(successMessage);
    }
}
