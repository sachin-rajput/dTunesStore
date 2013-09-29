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
	
	public SearchWorker(String fileName, int searchThreads, Results results,MusicStore musicStore) {
		Debug.print_debug(4,"SearchWorker constructor called.");
		this.musicStore = musicStore;
		this.results = results;
		
		//DATA STRUCTURE IMPACT ZONE in parameter 
		this.reader = new DataReader(fileName,this.results.arrayList,this.musicStore.vector);
		

		for (int i = 0; i < searchThreads; i++) {

			// new PopulateWorker(threads,fileName,from,to);
			t = new Thread(this, "Thread Created!");
			//System.out.println("Child thread: " + t + " with id: " + t.getId());
			// System.out.println("Thread config: from" + this.from + " to: " +
			// this.to);
			// this.thread_no = t.getId();

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
		
		/***
		 * Lets print the entire DataStructure which we saved
		 */
		//System.out.println("---------- Results Data Structure -----------------");
		//this.results.displayData();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/***
		 * Read the file 
		 */
		int currentThreadId = (int) Thread.currentThread().getId();
		Debug.print_debug(3,"Thread - " + currentThreadId +"'s run method called from SearchWorker.");
		//System.out.println("Child thread: " + Thread.currentThread().getId());
		
		this.reader.read_file(currentThreadId,"search");
		//this.reader.search_file(currentThreadId);
		
		//System.out.println("Exiting Child thread.");
	}
}
