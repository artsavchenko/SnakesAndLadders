package com.snakesladders.snakesladders.service;

import com.snakesladders.snakesladders.enums.Messages;
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

    public String initializeGame(String playerName, int level) {
        if (level < 1 || level > 10) {
            return String.format(Messages.WRONG_LEVEL_RANGE.getMessage(), level, Messages.START_INSTRUCTION.getMessage());
        }
        if (game == null) {
            Board board = boardService.initializeBoard(level);
            Player player = new Player(playerName);
            game = new Game(player, board);
        } else {
            return String.format(Messages.GAME_ALREADY_INITIALIZED.getMessage(), game.getPlayer().getPlayerName(), game.getPlayer().getPosition(), game.getBoard().toString());
        }
        return String.format(Messages.INITIALIZE.getMessage(), game.getPlayer().getPlayerName(), game.getBoard().toString(), game.getPlayer().getPosition());
    }

    public String rollDice() {
        if (game == null) {
            return String.format(Messages.NOT_INITIALIZED.getMessage(), Messages.START_INSTRUCTION.getMessage());
        }
        int step = diceService.rollDice();
        return makeMove(step);
    }

    public String clear() {
        if (game == null) {
            return String.format(Messages.NOT_INITIALIZED.getMessage(), Messages.START_INSTRUCTION.getMessage());
        }
        game = null;
        return String.format(Messages.GAME_CLEAR.getMessage(), Messages.START_INSTRUCTION.getMessage());
    }

    private String makeMove (int step) {
        int newPosition = game.getPlayer().getPosition() + step;
        if (newPosition > 100) {
            return String.format("%s %s", String.format(Messages.DICE_RESULT.getMessage(), step), String.format(Messages.OUT_OF_BOUND.getMessage(), newPosition, game.getPlayer().getPlayerName(), game.getPlayer().getPosition()));
        }
        String snakeOrLadder = "";
        if (game.getBoard().getLadders().containsKey(newPosition)) {
            snakeOrLadder = String.format(Messages.STEP_LADDER.getMessage(), newPosition, game.getBoard().getLadders().get(newPosition));
            newPosition = game.getBoard().getLadders().get(newPosition);
        }
        if (game.getBoard().getSnakes().containsKey(newPosition)) {
            snakeOrLadder = String.format(Messages.STEP_SNAKE.getMessage(), newPosition, game.getBoard().getSnakes().get(newPosition));
            newPosition = game.getBoard().getSnakes().get(newPosition);
        }
        game.getPlayer().setPosition(newPosition);

        if (game.getPlayer().getPosition() == 100) {
            String winner = String.format("%s %s %s %s", String.format(Messages.DICE_RESULT.getMessage(), step),
                    String.format(Messages.NEW_POSITION.getMessage(), game.getPlayer().getPlayerName(), game.getPlayer().getPosition()),
                    String.format(Messages.WINNER.getMessage(),game.getPlayer().getPlayerName()),
                    String.format(Messages.GAME_FINISH.getMessage(), Messages.START_INSTRUCTION.getMessage()));
            game = null;
            return winner;
        } else {
            return String.format("%s %s %s", String.format(Messages.DICE_RESULT.getMessage(), step), snakeOrLadder, String.format(Messages.NEW_POSITION.getMessage(), game.getPlayer().getPlayerName(), game.getPlayer().getPosition()));
        }
    }
}
