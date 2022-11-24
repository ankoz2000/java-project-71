package hexlet.code;

import hexlet.code.games.Greeting;
import hexlet.code.interfaces.Game;
import hexlet.code.interfaces.GamePreprocessor;

public class App {
    public static void main(String[] args) {
        int roundsCount = 3;
        GameStorage gameStorage = new GameStorage();

        Cli.showInitMessage();
        Cli.showAvailableChoices(gameStorage.getGames());

        int selectedGameId = Cli.selectGame();

        Cli.showWelcomeLetter();

        ((GamePreprocessor) gameStorage.getGreeting()).greet();
        Game game = gameStorage.getGameById(selectedGameId);
        Cli.showPreInteractionMessageToUser(game.getRules());
        while (roundsCount > 0) {
            game.startRound();
            Cli.askQuestion(game.getQuestion());
            game.setAnswerFromUser(Cli.getAnswer());
            if (game.isRightAnswer()) {
                Cli.showSuccessMessage("Correct!");
                roundsCount--;
            } else {
                Cli.showErrorMessage("\"" + game.getLastAnswer() + "\""
                        + " is wrong answer. Right answer is \"" + game.getRightAnswer() + "\"");
            }
        }

        Cli.showEndMessage(Greeting.congratulation());
    }
}
