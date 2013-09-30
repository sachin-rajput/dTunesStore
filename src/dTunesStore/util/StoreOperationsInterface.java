package dTunesStore.util;

public interface StoreOperationsInterface {
	/**
	 * This method can be implemented by any class as per the requirement of the data structure
	 */
	public void displayData();
	
	/**
	 * This method takes an object type as a parameter, so any class implementing this interface
	 * can cast the object as per the need
	 * @param type
	 * @param currentObj - object that should be displayed
	 */
	public void streamOutput(String type,Object currentObj);
	

}
