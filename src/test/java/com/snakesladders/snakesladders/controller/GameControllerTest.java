package com.snakesladders.snakesladders.controller;

import com.snakesladders.snakesladders.enums.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GameControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void showInstructions() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.START_INSTRUCTION.getMessage())));
    }

    @Test
    public void initializeGame() throws Exception {
        this.mockMvc.perform(get("/game/initialize/testPlayer/5")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.INITIALIZE.getMessage())));

        this.mockMvc.perform(get("/game/initialize/testPlayer/test")).andDo(print()).andExpect(status().isBadRequest())
                .andExpect(content().string(containsString(String.format(Messages.WRONG_LEVEL.getMessage(), Messages.START_INSTRUCTION.getMessage()))));
    }

    @Test
    public void rollDice() throws Exception {
        this.mockMvc.perform(get("/game/roll_dice")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.DICE_RESULT.getMessage())));

        this.mockMvc.perform(get("/game/roll_dice")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.NOT_INITIALIZED.getMessage())));
    }

    @Test
    public void clear() throws Exception {
        this.mockMvc.perform(get("/game/clear")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.GAME_CLEAR.getMessage())));

        this.mockMvc.perform(get("/game/clear")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.NOT_INITIALIZED.getMessage())));
    }
}
