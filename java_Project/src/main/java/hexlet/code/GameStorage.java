package hexlet.code;

import hexlet.code.games.Even;
import hexlet.code.games.Exit;
import hexlet.code.games.Greeting;
import hexlet.code.interfaces.Game;

public class GameStorage {

    private final Game[] games = {
        new Even(),
        new Greeting(),
        new Exit()
    };

    public Game getGameById(int id) {
        return games[id == 0 ? 0 : games.length - 1 - id];
    }

    public Game getGreeting() {
        return games[1];
    }

    public Game[] getGames() {
        return games;
    }
}
