package com.maximize;

import com.maximize.model.Game;
import com.maximize.service.ConsoleService;
import com.maximize.service.GameService;
import com.maximize.service.PlayerService;
import org.apache.log4j.Logger;

import java.util.List;

public class Driver {

    private static final Logger log = Logger.getLogger(Driver.class);

    public static void main(String[] args) {
        ConsoleService consoleServ = new ConsoleService();
        GameService gameServ = new GameService();
        PlayerService playerServ = new PlayerService();

        while(true) {
            consoleServ.out("-", 70);
            consoleServ.out("-", 70);
            int optGame = 0; //Default Option (New Game)
            List<Game> games = gameServ.getAllGames();
            if (games != null && !games.isEmpty()) {
                optGame = consoleServ.getIntByList(
                        "CHOSE ONE GAME TO LOAD: ", gameServ.getAllGamesNames(games), "New Game");
            }
            //consoleServ.out(String.valueOf(optGame), 1);
            consoleServ.out("-", 70);

            Game G;
            if (optGame == 0) {
                G = gameServ.newGame();
            } else {
                G = gameServ.getGame(games.get(optGame - 1).getId());
                if (G == null)
                    log.debug("Game " + games.get(optGame - 1).getName() + " can't load, the object is null.");
            }

            while (G != null) {
                consoleServ.out("=", ConsoleService.MAX_SPACES);
                G = gameServ.beforeMove(G);
                G = gameServ.move(G);
                G = gameServ.afterMove(G);
            }

            consoleServ.out("1.PLAY AGAIN: ",1);
            consoleServ.out("2.EXIT: ",1);
            int last = consoleServ.getInt("CHOOSE YOUR OPTION: ",1,2);
            if(last==2) {
                consoleServ.out("GOOD BY!",1);
                consoleServ.out("=",70);
                break;
            }
        }
    }
}
