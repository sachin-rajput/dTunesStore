package dTunesStore.util;

public class Debug {

	private static int DEBUG_VALUE;
	private static Debug instance;

	public static Debug getUniqueInstance() {
		if (instance == null)
			instance = new Debug();
		return instance;
	}

	
	/**
	 * Setter method(mutator) for DEBUG_VALUE
	 * @param value - set the DEBUG_VALUE to this
	 */
	public static void setDebugValue(int value) {
		DEBUG_VALUE = value;
	}
	
	/**
	 * Getter(accessor) for DEBUG_VALUE
	 * @return - returns the DEBUG_VALUE
	 */
	public static int getDebugValue() {
		return DEBUG_VALUE;
	}
	
	/**
	 * Prints the contents as per the debug value
	 * @param value - DEBUG_VALUE
	 * @param Message - message to print
	 */
	public static void printDebug(int value, String Message) {
		if (getDebugValue() == value)
			System.out.println(Message);
	}
}
