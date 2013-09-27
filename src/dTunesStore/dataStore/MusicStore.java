package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicStore {
	
	private static MusicStore instance;
	
	public static MusicStore getUniqueInstance(){
		if(instance == null)
			instance = new MusicStore();
		return instance;
	}
	
	public MusicStore(){
		
	}
	
	public List<MusicInfo> arrayList = new ArrayList<MusicInfo>();
	
	public MusicInfo createStructure(String songName,String albumName,String leadName,String duration){
		MusicInfo record = new MusicInfo();
		record.setSongName(songName);
		record.setLeadName(leadName);
		record.setAlbumName(albumName);
		record.setDuration(Double.parseDouble(duration));
		//arrayList.add(record);
		return record;
	}
	
	public void displayData(){
		Iterator<MusicInfo> itr = arrayList.iterator();
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			System.out.println(currentObj.getSongName() + " " + currentObj.getAlbumName() + " " + 
					currentObj.getLeadName() + " " + currentObj.getDuration());
		}
		
		System.out.println("The size of array is : " + arrayList.size());
	}
}
