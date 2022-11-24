package hexlet.code.interfaces;

public interface Game {
    String getRules();
    void startRound();
    String getLastAnswer();
    String getRightAnswer();
    String getGameName();
    String getQuestion();
    void setAnswerFromUser(String answerFromUser);
    boolean isRightAnswer();
}
