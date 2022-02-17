package com.maximize;

import com.maximize.dao.gameDAO;
import com.maximize.dao.playerDAO;
import com.maximize.model.Board;
import com.maximize.model.Game;
import com.maximize.model.Player;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    private static final Logger log = Logger.getLogger(Driver.class);

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        /*
        A. Hacer la presentacion
        B. Elegir Opciones
           1. Load Game
              Se cargan todos los datos de la Base de Datos y se crean todos los objetos (Game, Board and Player)
           2. New Game
              Se toman los datos del juego:
              Nombre del Juego
              Dimensiones del Tablero para generar el nuevo tablero
              Cantidad de Jugadores y datos de los jugadores

        C. Comienza el juego

        */

        /* 2  */
        gameDAO gDAO = new gameDAO();
        playerDAO pDAO = new playerDAO();
        int optGame = 0;
        try {
            if(!gDAO.getAllGames().isEmpty()){
                optGame = getInt(myScanner,
                        "CHOSE AN OPTION(1.LOAD GAME | 2.NEW GAME): ",1,2);
            }
            else optGame = 2;
        } catch (SQLException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
        separator("-");
        Game G = new Game(getString(myScanner, "NAME OF GAME: "));
        if(optGame==1){
            try {
                G = gDAO.get(G.getName());
            } catch (SQLException e) {
                e.printStackTrace();
                log.debug(e.getMessage());
            }
        }
        else { //optGame == 2
            int rows = 0, columns = 0, inPlayers = 0;
            System.out.println("\tBOARD DIMENSIONS");
            rows = getInt(myScanner, "NUMBER OF ROWS (rows>8 & rows<50): ", 8, 50);
            columns = getInt(myScanner, "NUMBER OF COLUMNS (columns>8 & columns<50): ", 8, 50);
            G.setBoard(rows, columns);
            separator("-");
            inPlayers = getInt(myScanner, "NUMBER OF PLAYERS (1|2|3): ", 1, 3);
            for (int i = 1; i <= inPlayers; i++) {
                String pName = getString(myScanner, "NAME OF PLAYER "+i+": ");
                G.addPlayer(new Player(pName));
            }
            if(inPlayers == 1) {
                Player robot = new Player("ROBOT-MAX");
                robot.setHuman(false);
                G.addPlayer(robot);
            }

        }

        /*while (true) {

            header(G, gDAO, pDAO, myScanner);

        }*/
        G.getBoard().print(true);
    }

    private static void header(Game G, gameDAO gDAO, playerDAO pDAO, Scanner myScanner){
        separator("=");
        Player P;
        System.out.print("\n\tGAME :"+G.getName()+" \tPLAYERS: ");
        for (int i = 0; i < G.getPlayers().size(); i++) {
            P = G.getPlayer();
            try {
                if(P.getId()==null){
                    G.setPlayer(pDAO.add(P));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.debug(e.getMessage());
            }
            if(i==0) System.out.print(" |");
            else System.out.print("\t");
            System.out.print(P.getName()+" Points="+P.getPoints());
            if(i==0) System.out.print("| ");
            G.nextPlayer();
        }
        try {
            if(G.getId()==null){
                gDAO.add(G);
            }
            else{
                G.setUpdate(new Date(System.currentTimeMillis()));
                gDAO.update(G);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
        System.out.println();
        G.getBoard().print(true);

        P = G.getPlayer();
        System.out.println("\n\t"+P.getName()+"'s TURN: ");
        System.out.print("\tSelect the space on the board (x, y) from where your move starts and its direction (Up|Down|Left|Right)");
        int x = -1, y = -1, dir = -1;
        x = getInt(myScanner, "Select x: ",1,G.getBoard().getColumns() );
        y = getInt(myScanner, "Select y: ",1,G.getBoard().getRows() );
        dir = getInt(myScanner, "Select direction (1.Up | 2.Down | 3.Left | 4.Right): ",1,4 );
        System.out.println("\tRow:"+(y-1)+", Col:"+(x-1)+", Dir:"+dir);
        myScanner.nextLine();
    }

    private static void separator(String msg){
        System.out.print("\n\t");
        for (int i = 0; i < 70; i++) {
            System.out.print(msg+" ");
        }
    }

    private static int getInt(Scanner myScanner, String msg, int min, int max){
        int r = 0;
        while (r<min || r>max) {
            System.out.print("\n\t"+msg);
            try {
                r = Integer.parseInt(myScanner.nextLine());
                if(r<min || r>max) System.out.println("\tINVALID OPTION");
            } catch (Exception e) {
                log.debug(e.getMessage());
                System.out.println("\tINVALID OPTION");
            }
        }
        return r;
    }

    private static String getString(Scanner myScanner, String msg){
        System.out.print("\n\t"+msg);
        return myScanner.nextLine();
    }
}
