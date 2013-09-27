package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.List;

import dTunesStore.util.DataReader;

public class SearchWorker implements Runnable {
	Thread t;
	private List<Thread> threads = new ArrayList<Thread>();
	private DataReader reader;
	private MusicStore musicStore;

	public SearchWorker(String file_name, int search_threads) {
		this.reader = new DataReader(file_name);
		this.musicStore = new MusicStore();

		for (int i = 0; i < search_threads; i++) {

			// new PopulateWorker(threads,file_name,from,to);
			t = new Thread(this, "Thread Created!");
			System.out.println("Child thread: " + t + " with id: " + t.getId());
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

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		/***
		 * Read the file 
		 */
		int currentThreadId = (int) Thread.currentThread().getId();
		System.out.println("Child thread: " + Thread.currentThread().getId());
	}
}
