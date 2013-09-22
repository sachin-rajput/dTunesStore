package dTunesStore.dataStore;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class PopulateWorker implements Runnable {
	Thread t;
	private String filename;
	private int from;
	private int to;
	private long thread_no;

	public PopulateWorker(String file_name, int from, int to) {

		this.filename = file_name;
		this.from = from;
		this.to = to;

		t = new Thread(this, "Thread Created!");
		System.out.println("Child thread: " + t);
		this.thread_no = t.getId();
		
		t.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(this.filename);
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int cnt = 0;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				if(cnt>=from && cnt<=to){
					System.out.println(cnt +": "+strLine+" - Thread no : "+this.thread_no);
				}
				cnt++;
			}
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println("Exiting child thread.");
	}
}
