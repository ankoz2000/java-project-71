package hexlet.code.games;

import hexlet.code.Cli;
import hexlet.code.interfaces.GamePreprocessor;

public class Greeting implements GamePreprocessor {
    private static String userName;

    private String name;

    public Greeting() {
        name = "Greet";
    }

    @Override
    public void greet() {
        askName();
        greetUser();
    }

    private static void askName() {
        Cli.showMessageWithoutNewLine("May I have your name? ");
        userName = Cli.getAnswer();
    }

    private static void greetUser() {
        Cli.showMessageToUser("Hello, " + userName + "!");
    }

    public static String getUsername() {
        return userName;
    }

    public static String congratulation() {
        return "Congratulations, " + userName + "!";
    }

    @Override
    public String getRules() {
        return null;
    }

    @Override
    public void startRound() {

    }

    @Override
    public String getLastAnswer() {
        return userName;
    }

    @Override
    public String getRightAnswer() {
        return userName;
    }

    @Override
    public String getGameName() {
        return name;
    }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public void setAnswerFromUser(String answerFromUser) {

    }

    @Override
    public boolean isRightAnswer() {
        return false;
    }
}
