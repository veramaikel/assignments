package com.maximize.service;

import com.maximize.dao.GameDao;
import com.maximize.model.*;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameService {
    private static final Logger log = Logger.getLogger(GameService.class);

    private final GameDao dao;
    private final ConsoleService consoleServ;
    private final PlayerService playerServ;
    private final MaxibotService maxibotServ;

    public GameService(){
        dao = new GameDao();
        consoleServ = new ConsoleService();
        playerServ = new PlayerService();
        maxibotServ = new MaxibotService();
    }

    public List<Game> getAllGames() {
        List<Game> games;
        try {
            games = dao.getAllGames();
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
        return games;
    }

    public List<String> getAllGamesNames(List<Game> list) {
        List<String> names = new ArrayList<>();
        for (Game g: list) {
            if(g.isOver()) names.add(g.getName()+" (GAME OVER)");
            else names.add(g.getName());
        }

        return names;
    }

    public Game getGame(Integer id) {
        Game G;
        try {
            G = dao.get(id);
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
            return null;
        }
        return G;
    }

    public Game addOrUpdateGame(Game G){
        try {
            //Add Game
            if (G.getId() == null) {
                G = dao.add(G);
            } else { //Update Game
                Date date = new Date(System.currentTimeMillis());
                G.setUpdate(date);
                dao.update(G);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e.getCause());
        }

        return G;
    }

    public Game newGame(){
        Game G = new Game(consoleServ.getString("NAME OF GAME: "));
        consoleServ.out("BOARD DIMENSIONS",1);
        int rows = consoleServ.getInt("NUMBER OF ROWS: ", 8, 20);
        int columns = consoleServ.getInt("NUMBER OF COLUMNS: ", 8, 20);
        consoleServ.out("-", ConsoleService.MAX_SPACES);
        int inPlayers = consoleServ.getInt("NUMBER OF PLAYERS: ", 1, Game.MAX_PLAYERS);
        for (int i = 1; i <= inPlayers; i++) {
            String pName = consoleServ.getString("NAME OF PLAYER "+i+": ");
            Player P = playerServ.getPlayer(pName);
            if(P!=null) G.addPlayer(P);
            else {
                P = new Player(pName);
                P = playerServ.addPlayer(P);
                G.addPlayer(P);
            }
        }
        if(inPlayers == 1) {
            inPlayers++;
            String pName = "Maxi-bot";
            Player P = playerServ.getPlayer(pName);
            if(P==null) {
                P = new Player(pName);
                P.setHuman(false);
                P = playerServ.addPlayer(P);
            }
            G.addPlayer(P);
        }
        G.setBoard(rows, columns, inPlayers);
        return G;
    }

    public Game beforeMove(Game G) {

        G = addOrUpdateGame(G);
        consoleServ.out("GAME :" + G.getName() + " \tPLAYERS: ", 1);
        for (Player p : G.getPlayers()) {
            if (G.isHead(p)) consoleServ.outInLine(p.getName() + " Points=" + p.getPoints(), "|");
            else consoleServ.outInLine(p.getName() + " Points=" + p.getPoints(), "");
        }
        consoleServ.out("", 1);
        if (!G.isOver()) G.getBoard().print(false);

        return G;
    }

    public Game move(Game G){
        if(!G.isOver()) {
            Player P = G.getPlayer();
            int moves = P.getActualMoves();
            int points = P.getPoints();
            Move move;

            consoleServ.out(P.getName() + "'s TURN: ", 1);
            if (P.isHuman()) {
                int x, y, dir;
                consoleServ.out(
                        "Select the space on the board (x, y) from where your move starts and its direction (Up|Down|Left|Right)", 1);
                x = consoleServ.getInt("Select x: ", 1, G.getBoard().getColumns());
                y = consoleServ.getInt("Select y: ", 1, G.getBoard().getRows());
                List<String> list = new ArrayList<>(4);
                list.add("Up");
                list.add("Down");
                list.add("Left");
                list.add("Right");
                dir = consoleServ.getIntByList("Select direction: ", list, null);
                DirectionMove direction;
                if (dir == 1) direction = DirectionMove.UP;
                else if (dir == 2) direction = DirectionMove.DOWN;
                else if (dir == 3) direction = DirectionMove.LEFT;
                else direction = DirectionMove.RIGHT;
                move = new Move(y - 1, x - 1, P, direction, true);
            } else { //Maxi-bot
                move = maxibotServ.generateMove(G);
                consoleServ.out("THE MOVE OF " + P.getName() + " IS: x=" + (move.getColumn() + 1) +
                        ", y=" + (move.getRow() + 1) + ", direction:" + move.getDirection(), 1);
            }

            G.getBoard().play(move);
            G.setPlayer(P);
            int diffPoints = P.getPoints() - points;
            if (diffPoints > 0) {
                consoleServ.out("EXCELLENT MOVE, YOU INCREASED " + diffPoints + " POINTS", 1);
            } else if (diffPoints < 0) {
                consoleServ.out("SORRY, YOU LOST " + diffPoints + " POINTS", 1);
            } else consoleServ.out("YOU HAVE THE SAME AMOUNT OF POINTS", 1);
            consoleServ.outInLine("WITH " + (P.getActualMoves() - moves) + " MOVES", "");
        }
        return G;
    }

    public Game afterMove(Game G){
        if((G.getBoard().getPositiveCells()>0 || G.getBoard().getZeroCells()>0) && !G.isOver()){
            List<String> list = new ArrayList<>(2);
            list.add("Continue Game");
            list.add("Reverse the Play");
            int opt = consoleServ.getIntByList("Select Option to continue: ", list, null);
            if (opt == 2){
                G.reversePlay();
            }
            else {
                G.nextPlayer();
            }
            return G;
        }
        else {
            G.setOver(true);
            G = addOrUpdateGame(G);
            Player win = G.getPlayer();
            int max = -1;
            for (Player p : G.getPlayers()) {
                if(p.getPoints() > max) {
                    max = p.getPoints();
                    win = p;
                }
            }
            consoleServ.out("THE WINNER IS -------------   ",1);
            consoleServ.outInLine(win.getName(),"**");
            consoleServ.out("=",70);
            consoleServ.out("",1);
            G.getBoard().print(false);
            consoleServ.out("GAME OVER", 1);

            return null;
        }
    }
}
