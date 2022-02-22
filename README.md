# Maximize Game
Fully developed in Java 17 and console oriented for 1, 2 or 3 players.

## Rules

### Main goal

The objective of this game is to win the most points, while going through the squares on the board. Once all the boxes are visited, the game is over and the winner is the player with the highest score.

Initially all the squares of the board are hidden **'@'**, and they are discovered as they are visited by the players. Each player adds (or multiplies) the points that he discovers as he travels around the board. Add points, double your score and be careful of the **'X'**.

### Types of squares on the board

- **'@' Hidden Square:** initial state of each square on the board, the true value is hidden until it is visited by a player for the first time.
- **'#' Stop Square:** this square represents a wall, stops the path of the player who bumps into this square.
- **'O' Point Square:** it is a square that adds a point to the score of the player who visits it for the first time.
- **'*' Duplex Square:** is a space that doubles the score of the player who visits it for the first time.
- **'.' Empty Square:** is an empty square, free or neutral, without value to add.
- **'X' Zero Square:** reset the score of the player who visits this space for the first time.
- **'+' Plus Square:** this square is special, when a player's path visits this square, it not only continues forward in the same direction it had but also splits to the perpendicular direction it came from. It is a bifurcation of the path where a moves arrives with a specific direction and 3 moves leave towards the remaining directions.

### Types of Moves

- **Up (&uarr;), Down (&darr;), Left (&larr;), Right (&rarr;)**.

All moves are in a straight line, either across a **column** (vertical) in an **Up (&uarr;)** or **Down (&darr;)** direction, or across a **row** (horizontal) in a **Left (&larr;)** or **Right (&rarr;)** direction. 

## Start Game

#### New Game
You can start a new game by giving it a name to save it in the Database. The board dimensions are configured by setting the number of rows and the number of columns. The number of players and their names are specified

#### Load Game
You can load a game from the Database. A game started earlier but not finished to resume later.

#### Start moving

1. Each move begins in a specific space, selected by the player in turn. The square is determined by the x and y location on the board. The x is the number of the column and the y the number of the row where it is located.
2. The player chooses the direction of his move starting from the square selected in step 1. He has 4 possible directions: **Up (&uarr;), Down (&darr;), Left (&larr;), Right (&rarr;)**.
3. The path begins by visiting each square that is in the chosen direction. Path in that direction ends with a **Stop Square '#'** or when the board runs out.
4. Each space visited for the first time will be uncovered for the rest of the game. The tour can continue in all directions if a **Plus Square '+'** is visited for the first time. 
5. After each move, the player in turn has the option to reverse it and start over from step 1 or continue the game with the next player.



