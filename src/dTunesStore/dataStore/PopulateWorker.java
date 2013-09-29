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

	// Helper helper = Helper.getUniqueInstance();

	public PopulateWorker(String file_name, int worker_threads) {
		Debug.print_debug(4,"PopulateWorker constructor called.");
		// DataReader reader = new DataReader(this.filename);
		this.worker_threads = worker_threads;
		this.musicStore = new MusicStore();
		//DATA STRUCTURE IMPACT ZONE in parameter
		this.reader = new DataReader(file_name, this.musicStore.vector);
	}

	public MusicStore createThreads() {
		for (int i = 0; i < worker_threads; i++) {

			// new PopulateWorker(threads,file_name,from,to);
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
		//System.out.println("---------------------------");
		//musicStore.displayData();
		
		return musicStore;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		/***
		 * Read the file
		 */
		int currentThreadId = (int) Thread.currentThread().getId();
		Debug.print_debug(3,"Thread - " + currentThreadId +"'s run method called from PopulateWorker.");
		//System.out.println("Child thread: " + Thread.currentThread().getId());
		
		this.reader.read_file(currentThreadId,"saveToDS");

		//System.out.println("Exiting Child thread.");

	}
}
