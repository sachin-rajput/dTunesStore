package dTunesStore.util;

public class dTunesStoreException extends Exception {
	public dTunesStoreException(String message, int exceptionLevel) {
		System.err.println(message);

		switch (exceptionLevel) {
		case 0:
			System.err.println("Missing argument dataFile");
			break;
		case 1:
			System.err.println("Missing argument number of threads for PopulateWorker");
			break;
		case 2:
			System.err.println("Missing argument searchFile");
			break;
		case 3:
			System.err.println("Missing argument number of threads for SearchWorker");
			break;
		case 4:
			System.err.println("Missing argument for DEBUG_VALUE");
			break;
		default:
			break;
		}
		
		if (message.equals("Arguments Exception:")) {
			System.out
					.println("Give arguments in the following format "
							+ "<DataFile> <#ofworkerThreads> <searchFile> <#ofSearchThreads><DebugValue>");
		}
	}
}
