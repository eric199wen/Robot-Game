# Robot-Game
It's a simple robot game. You can move a robot on a 8x8 board or find all the actions for a robot to go from original location to a designated destination within an assigned maximum actions allowed.

## Compile and run
`make clean` will delete all .class files.<br />
`make` will compile all .java files.<br />
`make run` will run the robot game.<br />

## Version 1
In this version, you can place a robot on a 8x8 board in the Cartesian coordinate system and tell it how you want it to move within the board. <br /><br />
An action that a robot can play including move(M), turn left(L) and turn right(R).<br /><br />

### Input
- Location: [x,y] (0 < x,y < 9) (ex: [2,3])<br />
- Direction faced: N, E, S or W (ex: N)<br />
- Actions: a combination of M, L or R (ex: M,L,R,R,M,M)

### Output
- Location: [x,y] (0 < x,y < 9)<br />
- Direction faced: N, E, S or W<br />

## Version 2
In this version, you can place a robot on a 8x8 board in the Cartesian coordinate systemand and tell it its destination, the robot will tell you all the possible combinations of actions it can move from where it is to the target location within an assigned maximum actions allowed.<br /><br />
An action that a robot can play includes
- M: move a step forward
- L: turn left
- R: turn right

### Input
- Original position: [x,y] (0 < x,y < 9) (ex: [2,3])<br />
- Original direction faced: N, E, S or W (ex: N)<br />
- Target position: [x,y] (0 < x,y < 9) (ex: [3,4])<br />
- Target direction faced: N, E, S or W (ex: N)<br />
- Maximum actions allowes: a positive integer (ex: 4)

### Output
The all possible combinations of actions that a robot can move from an original position to the destination.
