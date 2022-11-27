package hexlet.code.games;

import hexlet.code.interfaces.Game;
import hexlet.code.utils.RandomGenerator;

public class GCD implements Game {
    private String name;
    private String rules;
    private String question;
    private static String lastAnswer;
    private static String rightAnswer;

    public GCD() {
        name = "GCD";
        rules = "Find the greatest common divisor of given numbers.";
    }

    @Override
    public String getRules() {
        return rules;
    }

    @Override
    public void startRound() {
        int firstNumber = RandomGenerator.getRandomNumber();
        int secondNumber = RandomGenerator.getRandomNumber();
        question = firstNumber + " " + secondNumber;
        rightAnswer = String.valueOf(findGcd(firstNumber, secondNumber));
    }

    private int findGcd(int firstNumber, int secondNumber) {
        for (int i = Math.min(firstNumber, secondNumber); i > 1; i--) {
            if (firstNumber % i == 0 && secondNumber % i == 0) {
                return i;
            }
        }
        return 1;
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
