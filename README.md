# Robot-Game
It's a simple robot game. You can move a robot on a 8x8 board or find all the actions for a robot to move from original location to a designated destination within an assigned maximum actions allowed.

## Compile and run
`make clean` will delete all .class files.<br />
`make` will compile all .java files.<br />
`make run` will run the robot game.<br />
`make debug` is for version 1, this command will print the debug messages when executing.<br />

## Version 1
In this version, you can place a robot on a 8x8 square board in the Cartesian coordinate system and tell it how you want it to move within the board. Notice that if a move led the robot to go out of the board boundary, that move will simply be ignored. At a boundary, it can only turn left/right.<br /><br />
An action that a robot can play includes
- M: move a square forward
- L: turn left
- R: turn right

### Input
- Location: [x,y] (0 < x,y < 9) (ex: [2,3])<br />
- Direction faced: N, E, S or W (ex: N)<br />
- Actions: a sequence of of actions (ex: M,L,R,R,M,M)

### Output
- Location: [x,y] (0 < x,y < 9)<br />
- Direction faced: N, E, S or W<br />

## Version 2
In this version, you can place a robot on a 8x8 square board in the Cartesian coordinate systemand and tell it its destination, the robot will tell you all the possible sequences of actions it can move from where it is to the target location within an assigned maximum actions allowed.<br /><br />

An action that a robot will play includes
- M: move a square forward
- L: turn left
- R: turn right

### Input
- Original position: [x,y] (0 < x,y < 9) (ex: [2,3])<br />
- Original direction faced: N, E, S or W (ex: N)<br />
- Target position: [x,y] (0 < x,y < 9) (ex: [3,4])<br />
- Target direction faced: N, E, S or W (ex: N)<br />
- Maximum actions allowes: a positive integer (ex: 4)

### Output
The all possible sequences of actions that a robot can move from an original position to the destination.
