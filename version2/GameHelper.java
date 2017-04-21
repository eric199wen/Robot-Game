import java.io.*;

/**
 * A GameHelper is willing to facilitate the gaming process, it can ask user
 * for input, check if robot is going out of board boundary, check if input
 * of direction is valid and check if all actions in input are valid.
 *
 * @author       Wei-Yuan Wen (2017)
 * @version      1.0
 */
public class GameHelper {

    private static final int MINIMUM_LENGTH = 0;    // minimum length of board
    private static final int MAXIMUM_LENGTH = 9;    // maximum length of board

    // a buffer reads input from user
    private static BufferedReader is = new BufferedReader(
        new InputStreamReader(System.in));

    /**
     * Ask user to type input.
     *
     * @param  prompt the prompt to ask user for input
     * @return <code>String</code> of the user input
     */
    public static String getUserInput (String prompt) {
        String inputLine = null;
        System.out.print(prompt);
        try {
            inputLine = is.readLine();
            if (inputLine.length() == 0) return null;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return inputLine;
    }

    /**
     * Get sequence of actions from user input
     *
     * @return <code>String</code> of sequence of actions that robot going
     *         to take
     */
    public static String getActions() {
        String actions = null;

        actions = getUserInput("Actions: ");

        // Check if actions is valid
        while (!checkActions(actions)) {
            // Invalid input, ask user to enter again
            System.out.println("Invalid input, please enter again!"
                + " (ex: M,L,R,R,M)");
            actions = getUserInput("Actions: ");
        }

        return actions;
    }

    /**
     * Get direction that Robot object faced from user input
     *
     * @return <code>String</code> of direction that Robot object faced
     */
    public static String getDirection(String prompt) {
        String direction = null;

        direction = getUserInput(prompt);

        // Check if direction is valid
        while (!GameHelper.checkDirection(direction)) {
            // Invalid input, ask user to enter again
            System.out.println("Invalid direction, just enter"
                + " W, E, S or N, please enter again!");
            direction = getUserInput(prompt);
        }

        return direction;
    }

    /**
     * Get maximum actions allowed from user input
     *
     * @return <code>int</code> maximum actions allowed
     */
    public static int getMaximumActionsAllowed() {
        String maximumActionsAllowedInput = null;
        int maximumActionsAllowed = 0;

        outer:
        while (true) {
            maximumActionsAllowedInput = getUserInput("Maximum"
                + " actions allowed: ");

            // Check if the number of maximum actions allowed is null
            if (maximumActionsAllowedInput == null) {
                // Invalid input, ask user to enter again
                System.out.println("Invalid Input, maximum actions allowed"
                    + " should be greater than zero, please enter again!");
                continue;
            }

            for (int i = 0; i < maximumActionsAllowedInput.length(); i++) {
                if (maximumActionsAllowedInput.charAt(i) < '0'
                    || maximumActionsAllowedInput.charAt(i) > '9') {
                    // Invalid input, ask user to enter again
                    System.out.println("Invalid Input, maximum actions allowed"
                        + " should be greater than zero, please enter again!");
                    continue outer;
                }
            }

            maximumActionsAllowed
                = Integer.parseInt(maximumActionsAllowedInput);

            if (maximumActionsAllowed <= 0) {
                System.out.println("Invalid Input, maximum actions allowed"
                    + " should be greater than zero, please enter again!");
                continue;
            }

            break;
        }

        return maximumActionsAllowed;
    }

    /**
     * Check if location was out of boundary
     *
     * @param  x the x position to be checked
     * @param  y the y position to be checked
     * @return <code>true</code> if boundary is valid, <code>fasle</code>
     *        if out of boundary
     */
    public static boolean checkBoundary(int x, int y) {
        if (x > MINIMUM_LENGTH && x < MAXIMUM_LENGTH &&
            y > MINIMUM_LENGTH && y < MAXIMUM_LENGTH)
            return true;

        return false;
    }

    /**
     * Check if direction in input is valid
     *
     * @param  directionInput a string to be checked
     * @return <code>true</code> if valid, otherwise return
     *         <code>false</code>
     */
    public static boolean checkDirection(String directionInput) {
        return (directionInput != null) && (directionInput.equals("E") ||
                directionInput.equals("S") || directionInput.equals("N") ||
                directionInput.equals("W"));
    }

    /**
     * Check if all actions in input are valid
     *
     * @param  actionsInput a string to be checked if contained all valid
     *                      action
     * @return <code>true</code> if valid, otherwise return <code>false</code>
     */
    public static boolean checkActions(String actionsInput) {
        // Check if input is null
        if (actionsInput == null)
            return false;

        // Split input strings into array and check if all elements were
        // valid
        String[] actions = actionsInput.split(",");
        for (String action: actions)
            if (!action.equals("M") && !action.equals("L") &&
                !action.equals("R"))
                return false;
        return true;
    }
}
