/**
 * 
 */
package dTunesStore.driver;

import java.io.*;

import com.sun.corba.se.spi.orb.ParserData;
import com.sun.xml.internal.rngom.parse.Parseable;

import dTunesStore.util.Results;
import dTunesStore.util.Debug;
import dTunesStore.dataStore.PopulateWorker;
import dTunesStore.dataStore.SearchWorker;


/**
 * @author1 Sachin Rajput
 * @author1 Amol Mankar
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
		
		System.out.println("DEBUG_VALUE set to : "+ debug.get_debug_value());
		
		/***
		 * Step 1: Open the dataFile for reading check change 
		 */
		
		String file_name = args[0]+".txt";
		
		new PopulateWorker(); // create a new thread
		new PopulateWorker(); 
		
		/***
		 * Create threads 
		 */
		
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(file_name);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

}
