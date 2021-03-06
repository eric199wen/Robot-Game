import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * It's a simple robot game. You can move a robot on a 8x8 board or find all
 * the actions for a robot to move from original location to a designated
 * destination within an assigned maximum actions allowed.
 *
 * @author       Wei-Yuan Wen (2017)
 * @version      1.0
 */
public class Robot {

    private static boolean debug_on = false; // toggle debug mode

    public enum Direction {
        N, E, S, W
    }

    private Direction direction;    // the direction robot is facing
    private int x;                  // the position of robot on axis x
    private int y;                  // the position of robot on axis y

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
     * Get the location of a Robot object.
     *
     * @return  <code>String</code> has pattern of [x,y], where x is x
     *          position and y is y position
     */
    public String getLocation() {
        return "[" + String.valueOf(this.x) + "," +
            String.valueOf(this.y) + "]";
    }

    /**
     * Get the direction of a Robot object is facing.
     *
     * @return  <code>String</code> of the direction.
     */
    public String getDirection() {
        return this.direction.toString();
    }

    /**
     * Make a Robot object take actions, such as move, turn left, turn right.
     *
     * @param  actions an array of actions
     */
    private void takeAction(String[] actions) {
        for (int i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "M":
                    move();
                    break;
                case "L":
                    turnLeft();
                    break;
                case "R":
                    turnRight();
                    break;
            }
        }
    }

    /**
     * Make a Robot object move a step toward the direction it is facing.
     * If a move make robot go out of board boundary, that move is invalid
     * and were skipped.
     */
    private boolean move() {
        int updatedx = this.x;
        int updatedy = this.y;
        switch (this.direction) {
            case N:
                // Move one step toward north
                if (debug_on)
                    System.err.println("[DEBUG* Move 1 square toward north.]");
                updatedy = this.y+1;
                break;
            case E:
                // Move one step toward east
                if (debug_on)
                    System.err.println("[DEBUG* Move 1 square toward east.]");
                updatedx = this.x+1;
                break;
            case S:
                // Move one step toward south
                if (debug_on)
                    System.err.println("[DEBUG* Move 1 square toward south.]");
                updatedy = this.y-1;
                break;
            case W:
                // Move one step toward west
                if (debug_on)
                    System.err.println("[DEBUG* Move 1 square toward west.]");
                updatedx = this.x-1;
                break;
        }

        // Update position if still in board boundary
        if (GameHelper.checkBoundary(updatedx, updatedy)) {
            this.x = updatedx;
            this.y = updatedy;
            if (debug_on)
                    System.err.println("[DEBUG* Current position " + this.getLocation() + "]");
            return true;
        } else {
            // Move out of boundary, do nothing
            if (debug_on)
                    System.err.println("[DEBUG* Move out of the board boundary, ignore this move.]");
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
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face west]");
                this.direction = Direction.W;
                break;
            case E:
                // Turn to face north if faced toward east
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face north]");
                this.direction = Direction.N;
                break;
            case S:
                // Turn to face east if faced toward south
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face east]");
                this.direction = Direction.E;
                break;
            case W:
                // Turn to face south if faced toward west
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face south]");
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
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face east]");
                this.direction = Direction.E;
                break;
            case E:
                // Turn to face south if faced toward east
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face south]");
                this.direction = Direction.S;
                break;
            case S:
                // Turn to face west if faced toward south
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face west]");
                this.direction = Direction.W;
                break;
            case W:
                // Turn to face north if faced toward west
                if (debug_on)
                    System.err.println("[DEBUG* Turn to face north]");
                this.direction = Direction.N;
                break;
        }
    }

    public static void main(String[] args) {

        Direction direction;    // store input direction
        int x = 0, y = 0;               // store input location
        Robot robot = null;     // main object to perform actions
        // store input strings
        String locationInput = null, directionInput = null, actionInput = null;

        // initialize debug states
        debug_on = false;

        // check command line options for debug display
        for (int index = 0; index < args.length; ++index) {
            if (args[index].equals("-x"))
                debug_on = true;
        }

        // Getting valid location input from user
        while (true) {
            locationInput = GameHelper.getUserInput("Location: ");

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
                x = Character.getNumericValue(locationInput.charAt(1));
                y = Character.getNumericValue(locationInput.charAt(3));
            } else {
                // Invalid input, ask user to enter again
                System.out.println("Invalid input, please enter again!"
                    + " (ex: [x,y] (0<x,y<9))");
                continue;
            }

            // Check if location is valid
            if (!GameHelper.checkBoundary(x, y)) {
                // Invalid input, ask user to enter again
                System.out.println("Input out of range, please enter"
                    + " again! (ex: [x,y] (0<x,y<9))");
                continue;
            }

            break;
        }

        // Get direction faced from user
        directionInput = GameHelper.getDirection("Direction faced: ");
        // Convert string to direction
        direction = Direction.valueOf(directionInput);

        // Instantiate a Robot object with valid input
        robot = new Robot(x, y, direction);

        // Get sequence of actions from user
        actionInput = GameHelper.getActions();

        // Make robot to take actions
        robot.takeAction(actionInput.split(","));

        // Print output
        System.out.println("---------------------------");
        System.out.println("Final result:");
        System.out.println("Location: " + robot.getLocation());
        System.out.println("Direction faced: " + robot.getDirection());

        return;
    }
}