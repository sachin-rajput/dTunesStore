package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.List;

import dTunesStore.util.DataReader;
import dTunesStore.util.Debug;

public class PopulateWorker implements Runnable {
	Thread t;
	private long worker_threads;
	private List<Thread> threads = new ArrayList<Thread>();
	private DataReader reader;
	private MusicStore musicStore;

	/**
	 * Constructor for PopulateWorker
	 * @param file_name - dataStoreFilename
	 * @param worker_threads - number of threads for populateworker
	 */
	public PopulateWorker(String file_name, int worker_threads) {
		Debug.print_debug(4,"PopulateWorker constructor called.");
		// DataReader reader = new DataReader(this.filename);
		this.worker_threads = worker_threads;
		this.musicStore = new MusicStore();
		//DATA STRUCTURE IMPACT ZONE in parameter
		this.reader = new DataReader(file_name, this.musicStore.vector);
	}
	
	/**
	 * Creates threads for storing data from file to data structure
	 * @return MusicStore object filled with data from dataStorefile
	 */
	public MusicStore createThreads() {
		for (int i = 0; i < worker_threads; i++) {
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
		
		return musicStore;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		int currentThreadId = (int) Thread.currentThread().getId();
		Debug.print_debug(3,"Thread - " + currentThreadId +"'s run method called from PopulateWorker.");
		
		/**
		 * Read the file from dataStorefile and save it to datastructure
		 */
		this.reader.read_file(currentThreadId,"saveToDS");
	}
}
