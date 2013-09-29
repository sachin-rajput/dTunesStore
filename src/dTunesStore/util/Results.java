package dTunesStore.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dTunesStore.dataStore.MusicInfo;

public class Results {
	
	
	public Results(){
		Debug.print_debug(4,"Helper constructor called.");
	}
	
	//DATA STRUCTURE IMPACT ZONE
	public List<MusicInfo> arrayList = new ArrayList<MusicInfo>();
	
	
	public void displayData(){
		//DATA STRUCTURE IMPACT ZONE entire function 
		Iterator<MusicInfo> itr = arrayList.iterator();
		
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			stream_output("stdout",currentObj);
		}
		
		System.out.println("The size of array is : " + arrayList.size());
	}
	
	public void stream_output(String type,MusicInfo currentObj){
		if(type == "stdout"){
			System.out.println(currentObj.getSongName() + " " + currentObj.getAlbumName() + " " + 
					currentObj.getLeadName() + " " + currentObj.getDuration());
		}
	}
	
}
