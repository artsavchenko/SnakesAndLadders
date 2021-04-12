package com.snakesladders.snakesladders.controller;

import com.snakesladders.snakesladders.exceptions.BadFormatException;
import com.snakesladders.snakesladders.exceptions.GameNotFoundException;
import com.snakesladders.snakesladders.model.Board;
import com.snakesladders.snakesladders.model.Game;
import com.snakesladders.snakesladders.model.Player;
import com.snakesladders.snakesladders.service.GameService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {
    @MockBean
    GameService gameServiceMock;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCurrentGame() throws Exception {
        Game game = new Game(new Player("testPlayer"), new Board(new HashMap<>(), new HashMap<>()));
        Mockito.when(gameServiceMock.getGame()).thenReturn(game).thenThrow(new GameNotFoundException());

        this.mockMvc.perform(get("/game")).andDo(print()).andExpect(status().isOk());
        this.mockMvc.perform(get("/game")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void initializeGame() throws Exception {
        Game game = new Game(new Player("testPlayer"), new Board(new HashMap<>(), new HashMap<>()));
        Mockito.when(gameServiceMock.initializeGame(Mockito.anyString(), Mockito.anyInt())).thenThrow(new BadFormatException()).thenReturn(game);

        this.mockMvc.perform(post("/game/initialize/testPlayer/15")).andDo(print()).andExpect(status().isBadRequest());

        this.mockMvc.perform(post("/game/initialize/testPlayer/5")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(post("/game/initialize/testPlayer/test")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    public void playGame() throws Exception {
        Game game = new Game(new Player("testPlayer"), new Board(new HashMap<>(), new HashMap<>()));
        Mockito.when(gameServiceMock.playGame()).thenReturn(game).thenThrow(new GameNotFoundException());

        this.mockMvc.perform(post("/game/play_game")).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(post("/game/play_game")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void clear() throws Exception {
        Mockito.doNothing().when(gameServiceMock).clear();
        this.mockMvc.perform(post("/game/clear")).andDo(print()).andExpect(status().isOk());

        Mockito.doThrow(new GameNotFoundException()).when(gameServiceMock).clear();
        this.mockMvc.perform(post("/game/clear")).andDo(print()).andExpect(status().isNotFound());
    }
}
