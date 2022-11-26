package hexlet.code.games;

import hexlet.code.interfaces.Game;
import hexlet.code.utils.RandomGenerator;

public class Prime implements Game {
    private String name;
    private String rules;
    private String question;
    private static String lastAnswer;
    private static String rightAnswer;

    public Prime() {
        this.name = "Prime";
        this.rules = "Answer 'yes' if given number is prime. Otherwise answer 'no'.";
    }

    @Override
    public String getRules() {
        return rules;
    }

    @Override
    public void startRound() {
        int number = RandomGenerator.getRandomNumber();
        question = String.valueOf(number);
        rightAnswer = IsPrime(number) ? "yes" : "no";
    }

    public boolean IsPrime(int number) {
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0) return false;
        int k = (int) Math.round(Math.sqrt(number));
        for (int i = 3; i <= k; i += 2) {
            if (number % k == 0) return false;
        }
        return true;
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
