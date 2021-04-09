package com.snakesladders.snakesladders.controller;

import com.snakesladders.snakesladders.enums.Messages;
import com.snakesladders.snakesladders.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping
    public @ResponseBody ResponseEntity showInstructions() {
        return new ResponseEntity(Messages.START_INSTRUCTION.getMessage(), HttpStatus.OK) ;
    }

    @GetMapping(value = "game/initialize/{playerName}/{level}")
    public @ResponseBody ResponseEntity initializeGame (@PathVariable String playerName,
                                                @PathVariable String level) {
        try {
            return new ResponseEntity(gameService.initializeGame(playerName, Integer.parseInt(level)), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity(String.format(Messages.WRONG_LEVEL.getMessage(), Messages.START_INSTRUCTION.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "game/roll_dice")
    public @ResponseBody ResponseEntity rollDice() {
        String result = gameService.rollDice();
        if (result.contains(Messages.NOT_INITIALIZED.getMessage().split("!")[0])){
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @GetMapping(value = "game/clear")
    public @ResponseBody ResponseEntity clear() {
        String result = gameService.clear();
        if (result.contains(Messages.NOT_INITIALIZED.getMessage().split("!")[0])){
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
