/**
 * 
 */
package dTunesStore.driver;

import java.io.*;

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

		Debug debug = new Debug();
		debug.set_debug_value(Integer.parseInt(args[4]));

		System.out.println("DEBUG_VALUE set to : " + debug.get_debug_value());

		/***
		 * Step 1: Open the dataFile for reading check change
		 */

		String file_name = args[0] + ".txt";

		/***
		 * Create threads
		 */

		try {
			
			
			/***
			 * Get number of lines in input file
			 */
			Helper helper = new Helper();
			int numberOflines = helper.countLines(file_name);
			System.out.println("The number of lines : " + numberOflines);

			/***
			 * Divide the chunks
			 */
			int worker_threads = Integer.parseInt(args[1]);
			int search_threads = Integer.parseInt(args[3]);
			int chunk_size = numberOflines / worker_threads;
			
			int from = 0;
			int to = chunk_size-1;
			
			for (int i = 0; i < worker_threads; i++) {
				new PopulateWorker(file_name,from,to);
				from = to + 1;
				to = from + chunk_size - 1;
			}

			MusicStore musicStore = new MusicStore();
			musicStore.displayData();
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
