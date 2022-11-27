package hexlet.code.games;

import hexlet.code.interfaces.Game;
import hexlet.code.utils.RandomGenerator;

public class Progression implements Game {
    private String name;
    private String rules;
    private String question;
    private String replacement = "..";
    private String whiteSpace = " ";
    private static String lastAnswer;
    private static String rightAnswer;

    public Progression() {
        this.name = "Progression";
        this.rules = "What number is missing in the progression?";
    }


    @Override
    public String getRules() {
        return rules;
    }

    @Override
    public void startRound() {
        int step = RandomGenerator.getRandomNumberWithInterval(0, 10);
        int firstNumber = RandomGenerator.getRandomNumberWithInterval(0, 10);
        int numberCount = RandomGenerator.getRandomNumberWithInterval(5, 10);
        numberCount = numberCount > 10 ? numberCount - numberCount % 10 : numberCount;
        int hiddenNumberPosition = RandomGenerator.getRandomNumberWithInterval(0, numberCount);

        rightAnswer = String.valueOf(getProgressionNumber(firstNumber, step, hiddenNumberPosition));

        StringBuilder questionBuilder = new StringBuilder("");
        for (int i = 0; i < numberCount; i++) {
            if (i == hiddenNumberPosition) {
                questionBuilder.append(replacement);
            } else {
                questionBuilder.append(getProgressionNumber(firstNumber, step, i));
            }
            questionBuilder.append(whiteSpace);
        }

        question = questionBuilder.toString().trim();
    }

    public int getProgressionNumber(int firstNumber, int step, int position) {
        return firstNumber + step * (position - 1);
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
