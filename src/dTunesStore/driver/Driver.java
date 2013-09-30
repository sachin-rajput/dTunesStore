/**
 * dTunesStore - the best music app! 
 */
package dTunesStore.driver;

import dTunesStore.util.Results;
import dTunesStore.util.Debug;
import dTunesStore.util.Helper;
import dTunesStore.util.dTunesStoreException;
import dTunesStore.dataStore.MusicStore;
import dTunesStore.dataStore.PopulateWorker;
import dTunesStore.dataStore.SearchWorker;

/**
 * @author1 Sachin Rajput
 * @author2 Amol Mankar
 * 
 */
public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws dTunesStoreException {
		// TODO Auto-generated method stub

		/***
		 * Set DEBUG_VALUE
		 */

		

		try {
			if(args.length != 5){
				throw new dTunesStoreException("Arguments Exception:", args.length);
			}
			String fileName = args[0] + ".txt";
			int worker_threads = Integer.parseInt(args[1]);
			String search_fileName = args[2] + ".txt";
			int search_threads = Integer.parseInt(args[3]);
			Debug.set_debug_value(Integer.parseInt(args[4]));
			
			Helper helper = Helper.getUniqueInstance();
			int noOfEntries = 100000;
			int noOfSearchTerms = 50;
			int outOfBoundEntries = noOfEntries + 50000;

			helper.createEntriesScript(fileName, noOfEntries);
			helper.createSearchScript(search_fileName, noOfEntries,
					outOfBoundEntries, noOfSearchTerms);
			int numberOflines = helper.countLines(fileName);
			helper.set_file_lines_count(numberOflines);

			PopulateWorker populateWorker = new PopulateWorker(fileName,
					worker_threads);
			MusicStore musicStore = populateWorker.createThreads();

			/***
			 * After storing the data from text file to data structure let us
			 * try to open the search file and try to find the entries if entry
			 * found then add to results data structure
			 */
			Results results = new Results();
			new SearchWorker(search_fileName, search_threads, results,
					musicStore);

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
