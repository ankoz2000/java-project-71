package hexlet.code;

import hexlet.code.games.*;
import hexlet.code.interfaces.Game;

public class GameStorage {

    private final Game[] games = {
        new Prime(),
        new Progression(),
        new GCD(),
        new Calculator(),
        new Even(),
        new Greeting(),
        new Exit()
    };

    public Game getGameById(int id) {
        return games[id == 0 ? 0 : games.length - 1 - id];
    }

    public Game getGreeting() {
        return games[games.length - 2];
    }

    public Game[] getGames() {
        return games;
    }
}
