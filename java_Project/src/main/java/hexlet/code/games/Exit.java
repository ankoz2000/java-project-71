package hexlet.code.games;

import hexlet.code.interfaces.Game;

public class Exit implements Game {

    private String name = "Exit";

    @Override
    public String getRules() {
        return null;
    }

    @Override
    public void startRound() {

    }

    @Override
    public String getLastAnswer() {
        return null;
    }

    @Override
    public String getRightAnswer() {
        return null;
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
