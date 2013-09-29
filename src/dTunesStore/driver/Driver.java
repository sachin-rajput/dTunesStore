/**
 * 
 */
package dTunesStore.driver;


import dTunesStore.util.Results;
import dTunesStore.util.Debug;
import dTunesStore.util.Helper;
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

		/***
		 * Set DEBUG_VALUE
		 */

		//Debug debug = Debug.getUniqueInstance();
		Debug.set_debug_value(Integer.parseInt(args[4]));

		//System.out.println("DEBUG_VALUE set to : " + Debug.get_debug_value());

		/***
		 * Step 1: Open the dataFile for reading check change
		 */

		

		try {
			
			
			/***
			 * Let us set number of lines in input file
			 */
			Helper helper = Helper.getUniqueInstance();
			int noOfEntries=100000;
			int noOfSearchTerms=50;
			int outOfBoundEntries = noOfEntries + 50000;
			String search_fileName = args[2] + ".txt";
			String fileName = args[0] + ".txt";
			helper.createEntriesScript(fileName,noOfEntries);
			helper.createSearchScript(search_fileName, noOfEntries,outOfBoundEntries ,noOfSearchTerms);
			int numberOflines = helper.countLines(fileName);
			helper.set_file_lines_count(numberOflines);
			//System.out.println("The number of lines : " + numberOflines);
			
			
			
			int worker_threads = Integer.parseInt(args[1]);
			int search_threads = Integer.parseInt(args[3]);
			
			//fileName,worker_threads
			PopulateWorker populateWorker = new PopulateWorker(fileName, worker_threads);
			MusicStore musicStore = populateWorker.createThreads();
			
			//MusicStore musicStore = MusicStore.getUniqueInstance();
			//System.out.println("---------------------------");
			//musicStore.displayData();
			
			/***
			 * After storing the data from text file to data structure 
			 * let us try to open the search file and try to find the entries
			 * if entry found then add to results data structure
			 */
			
			
			
			Results results = new Results();
			new SearchWorker(search_fileName,search_threads,results,musicStore);
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
