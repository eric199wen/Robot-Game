import java.io.*;

public class GameHelper {

	private static final int MINIMUM_LENGTH = 0;
	private static final int MAXIMUM_LENGTH = 9;

	private static BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));

	public static String getUserInput (String prompt) {
		String inputLine = null;
		System.out.print(prompt + " ");
		try {
			inputLine = is.readLine();
			if (inputLine.length() == 0) return null;
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return inputLine;
	}

	public static boolean checkBoundary(int x, int y) {
		if (x > MINIMUM_LENGTH && x < MAXIMUM_LENGTH &&
			y > MINIMUM_LENGTH && y < MAXIMUM_LENGTH)
			return true;

		return false;
	}

	public static boolean checkDirection(String directionInput) {
		return (directionInput != null) && (directionInput.equals("E") ||
				directionInput.equals("S") || directionInput.equals("N") ||
				directionInput.equals("W"));
	}

	public static boolean checkActions(String actionsInput) {
		if (actionsInput == null)
			return false;

		String[] actions = actionsInput.split(",");
		for (String action: actions)
			if (!action.equals("M") && !action.equals("L") && !action.equals("R"))
				return false;
		return true;
	}
}