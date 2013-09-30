package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.List;

import dTunesStore.util.DataReader;
import dTunesStore.util.Debug;
import dTunesStore.util.Results;

public class SearchWorker implements Runnable {
	Thread t;
	private List<Thread> threads = new ArrayList<Thread>();
	private DataReader reader;
	private MusicStore musicStore;
	private Results results;
	
	/**
	 * Constructor for SearchWorker 
	 * @param fileName - searchFilename
	 * @param searchThreads - number of threads for searchworker
	 * @param results - object of the class where we will save the data structure 
	 * @param musicStore - object from which we will read the data
	 */
	public SearchWorker(String fileName, int searchThreads, Results results,MusicStore musicStore) {
		Debug.print_debug(4,"SearchWorker constructor called.");
		this.musicStore = musicStore;
		this.results = results;
		
		//DATA STRUCTURE IMPACT ZONE in parameter 
		this.reader = new DataReader(fileName,this.results.arrayList,this.musicStore.vector);
		

		for (int i = 0; i < searchThreads; i++) {
			t = new Thread(this, "Thread Created!");
			t.start();
			this.threads.add(t);
		}

		for (Thread th : this.threads) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/***
		 * Close file after reading
		 */
		this.reader.close_file();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int currentThreadId = (int) Thread.currentThread().getId();
		Debug.print_debug(3,"Thread - " + currentThreadId +"'s run method called from SearchWorker.");
		
		/**
		 * Read the data from datastructure and search from it and save the found data 
		 * to result datastructure
		 */
		this.reader.read_file(currentThreadId,"search");
	}
}
