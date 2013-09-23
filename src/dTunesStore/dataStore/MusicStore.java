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
	
	private List<MusicInfo> arrayList = new ArrayList<MusicInfo>();
	
	public void createStructure(String songName,String albumName,String leadName,String duration){
		MusicInfo record = new MusicInfo();
		record.setSongName(songName);
		record.setLeadName(leadName);
		record.setAlbumName(albumName);
		record.setDuration(Double.parseDouble(duration));
		arrayList.add(record);
	}
	
	public void displayData(){
		Iterator<MusicInfo> itr = arrayList.iterator();
		while(itr.hasNext()){
		System.out.println(itr.next().getSongName() + " " + itr.next().getAlbumName() + " " + 
				itr.next().getLeadName() + " " + itr.next().getDuration());
		}
	}
}
