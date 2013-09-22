package dTunesStore.dataStore;

public class PopulateWorker implements Runnable{
	Thread t;
	public PopulateWorker(){
		
		t = new Thread(this, "Thread Created!");
		System.out.println("Child thread: " + t);
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
	         for(int i = 5; i > 0; i--) {
	            System.out.println("Child Thread: " + i);
	            // Let the thread sleep for a while.
	            Thread.sleep(50);
	         }
	     } catch (InterruptedException e) {
	         System.out.println("Child interrupted.");
	     }
	     System.out.println("Exiting child thread.");
	}
}
