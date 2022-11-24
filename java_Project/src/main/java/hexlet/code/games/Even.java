package hexlet.code.games;

import hexlet.code.interfaces.Game;

import java.util.Random;

public class Even implements Game {
    private static Random random = new Random();
    private static int low = 0;
    private static int high = 100;
    private static String lastAnswer;
    private static String rightAnswer;
    private String name = "Even";
    private String question;
    private int randomNumber;
    private String rules;

    public Even() {
        name = "Even";
        rules = "Answer 'yes' if the number "
                + "is even, otherwise answer 'no'.";
    }

    public String getRules() {
        return rules;
    }

    public void startRound() {
        randomNumber = generateRandomNumber();
        question = String.valueOf(randomNumber);
        rightAnswer = isEven(randomNumber) ? "yes" : "no";
    }

    private static int generateRandomNumber() {
        return random.nextInt((high + low) + 1) + low;
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    @Override
    public String getLastAnswer() {
        return lastAnswer;
    }

    @Override
    public String getRightAnswer() {
        return rightAnswer;
    }

    @Override
    public String getGameName() {
        return name;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public void setAnswerFromUser(String answerFromUser) {
        lastAnswer = answerFromUser;
    }

    @Override
    public boolean isRightAnswer() {
        return lastAnswer.equals(rightAnswer);
    }
}
