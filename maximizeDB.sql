DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS playing;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS game;

CREATE TABLE game(
   id int IDENTITY(1,1) PRIMARY KEY,
   name varchar(40) NOT NULL,
   start_date DATETIME NOT NULL,
   update_date DATETIME NOT NULL,
   gameover BIT DEFAULT 0 NOT NULL
);
CREATE UNIQUE INDEX gameName ON game (name);

CREATE TABLE board(
   id_game int PRIMARY KEY,
   matrix varchar(8000) NOT NULL,
   FOREIGN KEY (id_game) REFERENCES game(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE player(
   id int IDENTITY(1,1) PRIMARY KEY,
   name varchar(40) NOT NULL,
   human BIT DEFAULT 1 NOT NULL
);
CREATE UNIQUE INDEX playerName ON player (name);

CREATE TABLE playing(
   id_game int NOT NULL,
   id_player int NOT NULL,
   turn BIT DEFAULT 0 NOT NULL,
   points int DEFAULT 0 NOT NULL,
   CONSTRAINT PK_Playing PRIMARY KEY (id_game, id_player),
   CONSTRAINT FK_Playing_Game FOREIGN KEY (id_game) REFERENCES game(id) ON DELETE NO ACTION ON UPDATE CASCADE,
   CONSTRAINT FK_Playing_Player FOREIGN KEY (id_player) REFERENCES player(id) ON DELETE NO ACTION ON UPDATE CASCADE
);