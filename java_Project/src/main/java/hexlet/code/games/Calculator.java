package hexlet.code.games;

import hexlet.code.interfaces.Game;
import hexlet.code.utils.RandomGenerator;

public class Calculator implements Game {
    private String name;
    private String rules;
    private String question;
    private static String lastAnswer;
    private static String rightAnswer;

    public Calculator() {
        name = "Calc";
        rules = "What is the result of the expression?";
    }

    @Override
    public String getRules() {
        return rules;
    }

    @Override
    public void startRound() {
        String operation = RandomGenerator.getRandomOperation();
        int firstOperand = RandomGenerator.getRandomNumber();
        int secondOperand = RandomGenerator.getRandomNumber();
        question = firstOperand + operation + secondOperand;
        if (operation.equals("+")) {
            rightAnswer = String.valueOf(firstOperand + secondOperand);
        } else if (operation.equals("-")) {
            rightAnswer = String.valueOf(firstOperand - secondOperand);
        } else {
            rightAnswer = String.valueOf(firstOperand * secondOperand);
        }
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
        return rightAnswer.equals(lastAnswer);
    }
}
