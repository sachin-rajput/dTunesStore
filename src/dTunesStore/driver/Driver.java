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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			if(args.length != 5){
				throw new dTunesStoreException("Arguments Exception:", args.length);
			}
			
			String fileName = args[0] + ".txt";
			int worker_threads = Integer.parseInt(args[1]);
			String search_fileName = args[2] + ".txt";
			int search_threads = Integer.parseInt(args[3]);
			Debug.set_debug_value(Integer.parseInt(args[4]));
			
			Helper helper = new Helper();
			
			/**
			 * Script to create the dataFile and searchFile
			 */
			int noOfEntries = 100000;
			int noOfSearchTerms = 50;
			int outOfBoundEntries = noOfEntries + 50000;
			
			/**
			 * Create dataFile with fileName
			 */
			helper.createEntriesScript(fileName, noOfEntries);
			
			/**
			 * Create searchFile with search_fileName
			 */
			helper.createSearchScript(search_fileName, noOfEntries,
					outOfBoundEntries, noOfSearchTerms);

			/**
			 * Create an instance for PopulateWorker
			 */
			PopulateWorker populateWorker = new PopulateWorker(fileName,
					worker_threads);
			/**
			 * Create threads for PopulateWorker
			 */
			MusicStore musicStore = populateWorker.createThreads();

			/***
			 * After storing the data from text file to data structure let us
			 * try to open the search file and try to find the entries if entry
			 * found then add to results data structure
			 */
			Results results = new Results();
			new SearchWorker(search_fileName, search_threads, results,
					musicStore);

		} catch (dTunesStoreException e) {
			// TODO Auto-generated catch block
			System.err.println("Error: " + e.getMessage());
		}
	}

}
