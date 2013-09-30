package dTunesStore.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dTunesStore.dataStore.MusicInfo;

public class Results implements StoreOperationsInterface {
	
	/**
	 * Results constructor
	 */
	public Results(){
		Debug.printDebug(4,"Helper constructor called.");
	}
	
	//DATA STRUCTURE IMPACT ZONE
	public List<MusicInfo> arrayList = new ArrayList<MusicInfo>();
	
	/**
	 * Displays the search results
	 */
	public void displayData(){
		//DATA STRUCTURE IMPACT ZONE entire function 
		Iterator<MusicInfo> itr = arrayList.iterator();
		
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			streamOutput("stdout",currentObj);
		}
		
		System.out.println("The size of array is : " + arrayList.size());
	}
	
	/**
	 * Helper method for displayData
	 * @param type
	 * @param currentObj - object that should be displayed
	 */
	public void streamOutput(String type,Object currentObj){
		MusicInfo musicInfo = (MusicInfo)currentObj;
		if(type == "stdout"){
			System.out.println(musicInfo.getSongName() + " " + musicInfo.getAlbumName() + " " + 
					musicInfo.getLeadName() + " " + musicInfo.getDuration());
		}
	}
	
}
