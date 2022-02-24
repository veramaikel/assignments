# Maximize Game Version 1.0.0
Fully developed in Java 17 and console oriented for 1, 2 or 3 players.

## Rules

### Main goal

The objective of this game is to win the most points, while going through the squares on the board. Once all the boxes are visited, the game is over and the winner is the player with the highest score.

Initially all the squares of the board are hidden **'@'**, and they are discovered as they are visited by the players. Each player adds (or multiplies) the points that he discovers as he travels around the board. Add points, double your score and be careful of the **'X'**.

#### Board Example (8 rows X 14 columns)

   ```
   Initially all the squares of the board are hidden.
   
   y     
   1   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   2   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   3   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   4   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   5   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   6   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   7   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   8   @ @ @ @ @ @ @ @ @  @  @  @  @  @
   
       1 2 3 4 5 6 7 8 9 10 11 12 13 14 x
       
       
       
   At the end of the game, all the squares on the board are uncovered.
   
   y     
   1   . . O O O O . * .  .  +  +  O  .
   2   O + # . + . . . O  .  .  +  .  #
   3   * . * O O O * X .  .  +  .  .  O
   4   + . . * O O O . O  .  *  .  .  *
   5   O # O O . . O * .  +  .  .  .  .
   6   * . O # . X O . O  .  O  .  O  O
   7   . . . # O . . O .  O  +  O  *  +
   8   # . . . * . O + #  .  O  O  O  .
   
       1 2 3 4 5 6 7 8 9 10 11 12 13 14 x
   ```     

### Types of Moves

- **Up (&uarr;), Down (&darr;), Left (&larr;), Right (&rarr;)**.

All moves are in a straight line, either across a **column** (vertical) in an **Up (&uarr;)** or **Down (&darr;)** direction, or across a **row** (horizontal) in a **Left (&larr;)** or **Right (&rarr;)** direction. 


### Types of squares on the board

- **'@' Hidden Square:** initial state of each square on the board, the true value is hidden until it is visited by a player for the first time.
- **'#' Stop Square:** this square represents a wall, stops the path of the player who bumps into this square.
- **'O' Point Square:** it is a square that adds one point to the score of the player who visits it for the first time.
- **'*' Duplex Square:** is a space that doubles the score of the player who visits it for the first time.
- **'.' Empty Square:** is an empty square, free or neutral, without value to add.
- **'X' Zero Square:** reset the score of the player who visits this space for the first time.
- **'+' Split Square:** this square is special, when a player's path visits this square, it not only continues forward in the same direction it came from, but also splits sideways. It is a split in the road where one move arrives with a specific direction and 3 moves leave towards the remaining directions. 

 > **Example:** Taking the ``8 x 14`` board above as the base of the example. The player in turn has 10 points in his score and chooses the box 10, 1 ``(column 10, row 1)`` to start his path with direction **down (&darr;)**. The first 4 cells of the path are **empty '.'**, in square 10, 5 ``(column 10, row 5)`` meets a **split square '+'** then from this place the path splits **left (&larr;)** and **right (&rarr;)** by ``row 5 `` and continues **down (&darr;)** by the same ``column 5`` where it came from.
>
> The new path that is generated to the **right (&rarr;)** does not add points since there are 4 **empty spaces '.'**.
>
> Let's analyze the path that is generated to the **left (&larr;)**. On the ``8, 5`` square you get a **duplex square '*'**, this doubles the player's score, as it was ``10`` now it is ``20``, follow the path and add another 3 points for the 3 **point square 'O'** it encounters until it hits the **stop square '#'** square at ``2, 5``. He now has ``23`` points.
>
> Picking up the path **down (&darr;)** from the ``10, 5`` square the player scores one more point for the **point square 'O'** on ``10, 7``. Here the play ends with ``24`` points for the player (increase ``14`` points in ``20`` squares visited).
> 
>```
>  The path of the example. This is how the board looks after the move
>   
>            y                      ↓start of the move
>            1   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>            2   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>            3   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>            4   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>            5   @ # O O . . O * . +↓ .  .  .  .
>  end on the left | ←  ←  ←  ←  ←  ↓ →   →   → end on the right
>            6   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>            7   @ @ @ @ @ @ @ @ @ O↓ @  @  @  @
>            8   @ @ @ @ @ @ @ @ @ .↓ @  @  @  @
>                                   end of the move
>
>                1 2 3 4 5 6 7 8 9 10 11 12 13 14 x
>```


## Start Game

```
  0. NEW GAME (DEFAULT OPTION)
  1. Game1
  2. Game2
  CHOSE ONE GAME TO LOAD:  (0-2) |
```

#### New Game

You can start a new game by giving it a name to save it in the Database. 
```
  NAME OF GAME: Game3 |
```

The board dimensions are configured by setting the number of rows and the number of columns. In this initial version of the game we have limits on the dimensions, we can only generate boards between ``8 x 8`` and ``20 x 20``
```
  BOARD DIMENSIONS 
	NUMBER OF ROWS:  (8-20) 8
  
  NUMBER OF COLUMNS:  (8-20) 14 |
```

The number of players and their names are specified. In this initial version of the game only 3 players can play per game. If 1 player is chosen, he will be opposed by ``Maxi-bot``, a virtual player with little experience, for now.
```
  NUMBER OF PLAYERS:  (1-3) 1 
  
  NAME OF PLAYER 1: Maikel |
```

#### Load Game
You can load a game from the Database. A game started earlier but not finished to resume later.
```
  0. NEW GAME (DEFAULT OPTION)
  1. Game1
  2. Game2
  CHOSE ONE GAME TO LOAD:  (0-2) 1 |
```

#### Start moving
```
  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	GAME :Game3 	PLAYERS:   |Maikel Points=0|  Maxi-bot Points=0 
	 
	y                             
	1   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	2   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	3   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	4   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	5   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	6   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	7   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	8   @ @ @ @ @ @ @ @ @  @  @  @  @  @ 
	
	    1 2 3 4 5 6 7 8 9 10 11 12 13 14  x
```

1. Each move begins in a specific space, selected by the player in turn. The square is determined by the ``x`` and ``y`` location on the board. The ``x`` is the number of the column and the ``y`` the number of the row where it is located.
```
  Maikel's TURN:  
	Select the space on the board (x, y) from where your move starts and its direction (Up|Down|Left|Right) 
	Select x:  (1-14) 1

	Select y:  (1-8) 3 |
```

2. The player chooses the direction of his move starting from the square selected in step 1. He has 4 possible directions: **Up (&uarr;), Down (&darr;), Left (&larr;), Right (&rarr;)**.
```
  1.Up
	2.Down
	3.Left
	4.Right
	Select direction:  (1-4) 4 |
```

3. The path begins by visiting each square that is in the chosen direction. Path in that direction ends with a **Stop Square '#'** or when the board runs out.
4. Each space visited for the first time will be uncovered for the rest of the game. The tour can continue in all directions if a **Split Square '+'** is visited for the first time. 
5. After each move, the player in turn has the option to reverse it and start over from step 1, continue the game with the next player or finish the game later.
```
  EXCELLENT MOVE, YOU INCREASED 15 POINTS  WITH 28 MOVES 
	1.Continue Game
	2.Reverse the Play
	3.Finish Later
	Select Option to continue:  (1-3)  |
```

6. After each play the scoreboard is updated, the game changes are saved in the Database and the next player starts to play. This cycle is repeated until all the sqares are visited. One player per turn.
7. If it is ``Maxi-bot`` who plays, his move and the information on how his path went will be shown.
```
  Maxi-bot's TURN:  
	THE MOVE OF Maxi-bot IS: x=1, y=1, direction:RIGHT 
	EXCELLENT MOVE, YOU INCREASED 6 POINTS  WITH 16 MOVES  
```

8. At the end of the game, the winner's information will be given and you will be asked if you want to start over or quit the game. 
```
  = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
	GAME :Test1 	PLAYERS:   |Maxi-bot Points=0|  Maikel Points=20 
	 
	THE WINNER IS -------------     **Maikel** 
	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
	 
	y                 
	1   . + . + O . . . 
	2   O . O X . # . * 
	3   . O + O O * . O 
	4   * + O . + . . O 
	5   @ # O * . . . . 
	6   O + . . O * O . 
	7   . . . * O . O # 
	8   O . . O . O X O 
	
	    1 2 3 4 5 6 7 8  x

	GAME OVER 
	1.PLAY AGAIN:  
	2.EXIT:  
	CHOOSE YOUR OPTION:  (1-2) |
```

## So, Let's Play!

