package dTunesStore.util;

public class Debug {

	private static int DEBUG_VALUE;
	private static Debug instance;

	public static Debug getUniqueInstance() {
		if (instance == null)
			instance = new Debug();
		return instance;
	}

	// accessor and mutator for DEBUG_VALUE
	public static void set_debug_value(int value) {
		DEBUG_VALUE = value;
	}

	public static int get_debug_value() {
		return DEBUG_VALUE;
	}

	public static void print_debug(int value, String Message) {
		if (get_debug_value() == value)
			System.out.println(Message);
	}
}
