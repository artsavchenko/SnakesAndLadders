package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Statuses;
import com.snakesladders.snakesladders.exceptions.BadFormatException;
import com.snakesladders.snakesladders.exceptions.GameNotFoundException;
import com.snakesladders.snakesladders.model.Board;
import com.snakesladders.snakesladders.model.Game;
import com.snakesladders.snakesladders.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {
    @Autowired
    BoardService boardService;

    @Autowired
    DiceService diceService;

    private Game game = null;

    public Game getGame() throws GameNotFoundException {
        if (game == null) {
            throw new GameNotFoundException();
        }
        return game;
    }

    public Game initializeGame(String playerName, int level) throws BadFormatException {
        if (level < 1 || level > 10) {
            throw new BadFormatException();
        }
        if (game == null) {
            Board board = boardService.initializeBoard(level);
            Player player = new Player(playerName);
            game = new Game(player, board);
        }
        return game;
    }

    public Game playGame() throws GameNotFoundException {
        if (game == null) {
            throw new GameNotFoundException();
        }
        game.setDice(diceService.rollDice());
        Game game = makeMove();
        if (game.getStatus().equals(Statuses.WON)) {
            clear();
        }
        return game;
    }

    public void clear() throws GameNotFoundException {
        if (game == null) {
            throw new GameNotFoundException();
        }
        game = null;
    }

    private Game makeMove() {
        int newPosition = game.getPlayer().getPosition() + game.getDice();
        if (newPosition > 100) {
            game.setStatus(Statuses.ON_PLAY);
            return game;
        }
        if (game.getBoard().getLadders().containsKey(newPosition)) {
            newPosition = game.getBoard().getLadders().get(newPosition);
        }
        if (game.getBoard().getSnakes().containsKey(newPosition)) {
            newPosition = game.getBoard().getSnakes().get(newPosition);
        }
        game.getPlayer().setPosition(newPosition);
        if (game.getPlayer().getPosition() == 100) {
            game.setStatus(Statuses.WON);
        } else {
            game.setStatus(Statuses.ON_PLAY);
        }
        return game;
    }
}
