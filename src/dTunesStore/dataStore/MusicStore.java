package dTunesStore.dataStore;

import java.util.Iterator;
import java.util.Vector;

public class MusicStore {
	
	/**
	 * Constructor for MusicStore()
	 */
	public MusicStore(){
		//Debug.print_debug(4,"MusicStore constructor called");
	}
	
	/**
	 * DATA STRUCTURE IMPACT ZONE 
	 */
	public Vector<MusicInfo> vector = new Vector<MusicInfo>();
	
	/**
	 * Displays the data from data structure
	 */
	public void displayData(){
		/**
		 * DATA STRUCTURE IMPACT ZONE
		 */
		Iterator<MusicInfo> itr = vector.iterator();
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			streamOutput("stdout",currentObj);
		}
		
		System.out.println("The size of vector is : " + vector.size());
	}
	
	/**
	 * Prints the data according to the type 
	 * @param type - how to handle the print
	 * @param currentObj - object to print
	 */
	public void streamOutput(String type,Object currentObj){
		MusicInfo musicInfo = (MusicInfo)currentObj;
		if(type == "stdout"){
			System.out.println(musicInfo.getSongName() + " " + musicInfo.getAlbumName() + " " + 
					musicInfo.getLeadName() + " " + musicInfo.getDuration());
		}
	}
}
