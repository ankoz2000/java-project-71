package hexlet.code;

import hexlet.code.games.GCD;
import hexlet.code.games.Exit;
import hexlet.code.games.Even;
import hexlet.code.games.Prime;
import hexlet.code.games.Greeting;
import hexlet.code.interfaces.Game;
import hexlet.code.games.Calculator;
import hexlet.code.games.Progression;

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

    public Integer getGreetingId() {
        return games.length - 2;
    }

    public Game getExit() {
        return games[games.length - 1];
    }

    public Integer getExitId() {
        return games.length - 1;
    }

    public Game[] getGames() {
        return games;
    }
}
