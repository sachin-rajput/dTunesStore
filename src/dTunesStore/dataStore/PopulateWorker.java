package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.List;

import dTunesStore.util.DataReader;

public class PopulateWorker implements Runnable {
	Thread t;
	private long worker_threads;
	private List<Thread> threads = new ArrayList<Thread>();
	private DataReader reader;
	private MusicStore musicStore;

	// Helper helper = Helper.getUniqueInstance();

	public PopulateWorker(String file_name, int worker_threads) {
		// DataReader reader = new DataReader(this.filename);
		this.worker_threads = worker_threads;
		this.musicStore = new MusicStore();
		this.reader = new DataReader(file_name, this.musicStore.arrayList);
	}

	public MusicStore createThreads() {
		for (int i = 0; i < worker_threads; i++) {

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

		/***
		 * Lets print the entire DataStructure which we saved
		 */
		System.out.println("---------------------------");
		musicStore.displayData();
		
		return musicStore;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		/***
		 * Read the file
		 */
		int currentThreadId = (int) Thread.currentThread().getId();
		System.out.println("Child thread: " + Thread.currentThread().getId());

		this.reader.read_file(currentThreadId);

		System.out.println("Exiting Child thread.");

		/*
		 * Thread spawned_t;
		 * 
		 * 
		 * for(int i=0;i<this.workerthreads;i++){ //System.out.println("dd");
		 * //Runnable runn = (Runnable) new DataReader(); spawned_t = new
		 * Thread("Spawned!"); System.out.println("Child thread: " + t);
		 * spawned_t.start(); this.threads.add(spawned_t); try {
		 * Thread.sleep(200); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 * 
		 * 
		 * for(Thread th: this.threads){ try { th.join(); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

	}
}
