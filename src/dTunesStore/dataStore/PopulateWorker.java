package dTunesStore.dataStore;

public class PopulateWorker implements Runnable{
	Thread t;
	public PopulateWorker(){
		t = new Thread(this, "Thread Created!");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
