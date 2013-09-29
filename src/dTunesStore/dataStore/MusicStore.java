package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import dTunesStore.util.Debug;

public class MusicStore {
	
	private static MusicStore instance;
	
	public static MusicStore getUniqueInstance(){
		if(instance == null)
			instance = new MusicStore();
		return instance;
	}
	
	/*public MusicStore(){
		Debug.print_debug(4,"MusicStore constructor called");
	}*/
	
	//DATA STRUCTURE IMPACT ZONE 
	public Vector<MusicInfo> vector = new Vector<MusicInfo>();
	
//	public MusicInfo createStructure(String songName,String albumName,String leadName,String duration){
//		MusicInfo record = new MusicInfo();
//		record.setSongName(songName);
//		record.setLeadName(leadName);
//		record.setAlbumName(albumName);
//		record.setDuration(Double.parseDouble(duration));
//		//arrayList.add(record);
//		return record;
//	}
	
	public void displayData(){
		//DATA STRUCTURE IMPACT ZONE entire function
		Iterator<MusicInfo> itr = vector.iterator();
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			System.out.println(currentObj.getSongName() + " " + currentObj.getAlbumName() + " " + 
					currentObj.getLeadName() + " " + currentObj.getDuration());
		}
		
		System.out.println("The size of vector is : " + vector.size());
	}
}
