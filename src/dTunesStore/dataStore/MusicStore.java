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
	
	
	
	public void displayData(){
		Iterator<MusicInfo> itr = this.arrayList.iterator();
		MusicInfo currentObj;
		
		while(itr.hasNext()){
			currentObj = itr.next();
			System.out.println(currentObj.getSongName() + " " + currentObj.getAlbumName() + " " + 
					currentObj.getLeadName() + " " + currentObj.getDuration());
		}
		
		System.out.println("The size of array is : " + arrayList.size());
	}
}
