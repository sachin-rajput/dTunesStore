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
	public void set_debug_value(int value) {
		this.DEBUG_VALUE = value;
	}

	public int get_debug_value() {
		return this.DEBUG_VALUE;
	}

	public void print_debug(int value, String Message) {
		if (this.get_debug_value() == value)
			System.out.println(Message);
	}
}
