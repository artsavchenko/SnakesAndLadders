package com.snakesladders.snakesladders.controller;

import com.snakesladders.snakesladders.exceptions.BadFormatException;
import com.snakesladders.snakesladders.exceptions.GameNotFoundException;
import com.snakesladders.snakesladders.model.Game;
import com.snakesladders.snakesladders.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping
    public ResponseEntity<Game> getCurrentGame() {
        try {
            Game game = gameService.getGame();
            return new ResponseEntity<>(game, HttpStatus.OK);
        } catch (GameNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/initialize/{playerName}/{level}")
    public ResponseEntity<Game> initializeGame(@PathVariable String playerName,
                                               @PathVariable String level) {
        try {
            return new ResponseEntity<>(gameService.initializeGame(playerName, Integer.parseInt(level)), HttpStatus.OK);
        } catch (NumberFormatException | BadFormatException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/play_game")
    public ResponseEntity<Game> playGame() {
        try {
            return new ResponseEntity<>(gameService.playGame(), HttpStatus.OK);
        } catch (GameNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/clear")
    public ResponseEntity<HttpStatus> clear() {
        try {
            gameService.clear();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
