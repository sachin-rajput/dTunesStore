package dTunesStore.dataStore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MusicStore {
	
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
		Iterator itr = arrayList.iterator();
		while(itr.hasNext()){
		System.out.println(itr.next());
		}
	}
}
