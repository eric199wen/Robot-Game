import java.io.*;

public class GameHelper {

	private static final int MINIMUM_LENGTH = 0; 	// minimum length of board
	private static final int MAXIMUM_LENGTH = 9;	// maximum length of board

	// a buffer reads input from user
	private static BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));

	/**
     * Ask user to type input.
     *
     * @param  prompt the prompt to ask user for input
     * @return <code>String</code> is the user input
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
     * Check if location was out of boundary
     *
     * @param  x the x position to be checked
     * @param  y the y position to be checked
     * @return <code>true</code> if boundary is valid, <code>fasle</code>
     *		   if out of boundary
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
     *						action
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