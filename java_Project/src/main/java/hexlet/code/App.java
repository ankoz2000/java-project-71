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
        if (gameStorage.getExitId().equals(selectedGameId)
                || gameStorage.getGreetingId().equals(selectedGameId)) {
            return;
        }
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
                Cli.showErrorMessage("'" + game.getLastAnswer() + "'"
                        + " is wrong answer ;(. Correct answer was '" + game.getRightAnswer() + "'\n"
                        + "Let's try again, " + Greeting.getUsername() + "!");
            }
        }
        Cli.showEndMessage(Greeting.congratulation());
    }
}
