import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;

/**
 * It's a simple robot game. You can move a robot on a 8x8 board or find all
 * the actions for a robot to move from original location to a designated
 * destination within an assigned maximum actions allowed.
 *
 * @author       Wei-Yuan Wen (2017)
 * @version      1.0
 */
public class Robot {

    public enum Direction {
        N, E, S, W
    }

    private Direction direction;    // the direction robot is facing
    private int x;                  // the position of robot on axis x
    private int y;                  // the position of robot on axis y
    // to store all the possible paths
    ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();

    /**
     * Create a <code>Robot</code> with initial position on board and the
     * direction it faced.
     *
     * @param  x         position on x axis.
     * @param  y         position on y axis.
     * @param  direction the direction robot face to.
     */
    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Make a Robot object move a step toward the direction it is facing.
     * If a move make robot go out of board boundary, that move is invalid
     * and were skipped.
     *
     * @return <code>true</code> if move successfully, othewise return
     *         <code>false</code>
     */
    private boolean move() {
        int updatedx = this.x;
        int updatedy = this.y;
        switch (this.direction) {
            case N:
                // Move one step toward north
                updatedy = this.y+1;
                break;
            case E:
                // Move one step toward east
                updatedx = this.x+1;
                break;
            case S:
                // Move one step toward south
                updatedy = this.y-1;
                break;
            case W:
                // Move one step toward west
                updatedx = this.x-1;
                break;
        }

        // Update position if still in board boundary
        if (GameHelper.checkBoundary(updatedx, updatedy)) {
            this.x = updatedx;
            this.y = updatedy;
            return true;
        } else {
            // Move out of boundary, do nothing
            return false;
        }
    }

    /**
     * Make a Robot object turn left.
     */
    private void turnLeft() {
        switch (this.direction) {
            case N:
                // Turn to face west if faced toward north
                this.direction = Direction.W;
                break;
            case E:
                // Turn to face north if faced toward east
                this.direction = Direction.N;
                break;
            case S:
                // Turn to face east if faced toward south
                this.direction = Direction.E;
                break;
            case W:
                // Turn to face south if faced toward west
                this.direction = Direction.S;
                break;
        }
    }

    /**
     * Make a Robot object turn right.
     */
    private void turnRight() {
        switch (this.direction) {
            case N:
                // Turn to face east if faced toward north
                this.direction = Direction.E;
                break;
            case E:
                // Turn to face south if faced toward east
                this.direction = Direction.S;
                break;
            case S:
                // Turn to face west if faced toward south
                this.direction = Direction.W;
                break;
            case W:
                // Turn to face north if faced toward west
                this.direction = Direction.N;
                break;
        }
    }

    /**
     * Make a Robot object turn right.
     *
     * @param  path                  the current path that robot object
     *                               go through
     * @param  targetX               the target of x position
     * @param  targetY               the target of y position
     * @param  targetD               the target of direction
     * @param  currentStep           the number of current step taken
     * @param  maximumActionsAllowed the number of maximum action allowed
     */
    private void DFS(ArrayList<String> path, int targetX, int targetY,
        Direction targetD, int currentStep, int maximumActionsAllowed) {

        // Check if reached target of position and direction
        if (this.x == targetX && this.y == targetY &&
            this.direction == targetD)
            this.paths.add(new ArrayList<String>(path));

        // Check if reached maximum steps
        if (currentStep == maximumActionsAllowed)
            return;

        // Turn left and record path, do DFS, then backtrack the state
        // after DFS is done.
        turnLeft();
        path.add("L");
        DFS(path, targetX, targetY, targetD, currentStep+1,
            maximumActionsAllowed);
        path.remove(path.size()-1);
        turnRight();

        // Turn right and record path, do DFS, then backtrack the state
        // after DFS is done.
        turnRight();
        path.add("R");
        DFS(path, targetX, targetY, targetD, currentStep+1,
            maximumActionsAllowed);
        path.remove(path.size()-1);
        turnLeft();

        // if move successfully, record path, do DFS, and backtrack the
        // state after DFS id done.
        if (move()) {
            path.add("M");
            DFS(path, targetX, targetY, targetD, currentStep+1,
                maximumActionsAllowed);
            path.remove(path.size()-1);
            // move backward
            turnLeft();
            turnLeft();
            move();
            turnLeft();
            turnLeft();
        }
    }

    /**
     * Print all the possible paths.
     */
    private void printPaths() {

        StringBuilder sb = null;    // to construct printing message

        // Check if no possible path exists
        if (this.paths.size() == 0) {
            System.out.println("No sequence of actions can reach"
                + " the target!");
            return;
        }

        // Print each path in possible paths
        for (int i = 0; i < this.paths.size(); i++) {
            System.out.print("Actions - " + (i+1) + ": ");
            sb = new StringBuilder();

            ArrayList<String> path = this.paths.get(i);
            if (path.size() == 0)
                sb.append("No action!");
            if (path.size() >= 1)
                sb.append(path.get(0));
            for (int j = 1; j < path.size(); j++) {
                sb.append(",");
                sb.append(path.get(j));
            }
            System.out.println(sb.toString());
        }
        System.out.println("No more possible actions!");
    }

    public static void main(String[] args) {

        int originalX = 0, originalY = 0;   // store original posiiton
        int targetX = 0, targetY = 0;       // store target position
        int maximumActionsAllowed = 0;      // store maximum actions allowed
        Direction originalD, targetD;       // store input directions
        Robot robot = null;                 // main object to perform actions
        // store input strings
        String locationInput = null, directionInput = null;
        String maximumActionsAllowedInput = null;   // store input strings

        // Getting valid location input from user
        while (true) {
            locationInput = GameHelper.getUserInput("Original position: ");

            // Check if location is null
            if (locationInput == null) {
                System.out.println("Invalid input, please enter again!"
                    + " (ex: [x,y] (0<x,y<9))");
                continue;
            }

            // Check if location is typed in right pattern, i.e. [x,y]
            Pattern p = Pattern.compile("\\[\\d,\\d\\]");
            Matcher m1 = p.matcher(locationInput);
            if (m1.matches()) {
                originalX
                    = Character.getNumericValue(locationInput.charAt(1));
                originalY
                    = Character.getNumericValue(locationInput.charAt(3));
            } else {
                // Invalid input, ask user to enter again
                System.out.println("Invalid input, please enter again!"
                    + " (ex: [x,y] (0<x,y<9)");
                continue;
            }

            // Check if location is valid
            if (!GameHelper.checkBoundary(originalX, originalY)) {
                // Invalid input, ask user to enter again
                System.out.println("Input out of range, please enter"
                    + " again! (ex: [x,y] (0<x,y<9))");
                continue;
            }

            break;
        }

        // Get original direction that robot object faced from user
        directionInput = GameHelper.getDirection("Original direction faced: ");
        // Convert string to direction
        originalD = Direction.valueOf(directionInput);

        // Instantiate a Robot object with valid input
        robot = new Robot(originalX, originalY, originalD);

        while (true) {
            locationInput = GameHelper.getUserInput("Target position: ");

            // Check if location is null
            if (locationInput == null) {
                System.out.println("Invalid input, please enter again!"
                    + " (ex: [x,y] (0<x,y<9))");
                continue;
            }

            // Check if location is typed in right pattern, i.e. [x,y]
            Pattern p = Pattern.compile("\\[\\d,\\d\\]");
            Matcher m1 = p.matcher(locationInput);
            if (m1.matches()) {
                targetX
                    = Character.getNumericValue(locationInput.charAt(1));
                targetY
                    = Character.getNumericValue(locationInput.charAt(3));
            } else {
                // Invalid input, ask user to enter again
                System.out.println("Invalid input, please enter again!"
                    + " (ex: [x,y] (0<x,y<9))");
                continue;
            }

            // Check if location is valid
            if (!GameHelper.checkBoundary(targetX, targetY)) {
                // Invalid input, ask user to enter again
                System.out.println("Input out of range, please enter"
                    + " again! (ex: [x,y] (0<x,y<9))");
                continue;
            }

            break;
        }

        // Get target direction that robot object faced from user
        directionInput = GameHelper.getDirection("Target direction faced: ");
        // Convert string to direction
        targetD = Direction.valueOf(directionInput);

        // Get the maximum actions allowed from user
        maximumActionsAllowed = GameHelper.getMaximumActionsAllowed();

        // Make robot to do the Depth-First Search
        robot.DFS(new ArrayList<String>(), targetX, targetY, targetD, 0,
            maximumActionsAllowed);

        // Print all the possible paths
        robot.printPaths();

        return;
    }
}
