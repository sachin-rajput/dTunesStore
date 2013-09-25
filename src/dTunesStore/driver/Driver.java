/**
 * 
 */
package dTunesStore.driver;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

		Debug debug = Debug.getUniqueInstance();
		debug.set_debug_value(Integer.parseInt(args[4]));

		System.out.println("DEBUG_VALUE set to : " + debug.get_debug_value());

		/***
		 * Step 1: Open the dataFile for reading check change
		 */

		String file_name = args[0] + ".txt";

		try {
			
			
			/***
			 * Let us set number of lines in input file
			 */
			Helper helper = Helper.getUniqueInstance();
			int numberOflines = helper.countLines(file_name);
			helper.set_file_lines_count(numberOflines);
			System.out.println("The number of lines : " + numberOflines);
			
			
			
			int worker_threads = Integer.parseInt(args[1]);
			int search_threads = Integer.parseInt(args[3]);
			
			//file_name,worker_threads
			new PopulateWorker(file_name, worker_threads);

			//MusicStore musicStore = MusicStore.getUniqueInstance();
			//System.out.println("---------------------------");
			//musicStore.displayData();
			
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
