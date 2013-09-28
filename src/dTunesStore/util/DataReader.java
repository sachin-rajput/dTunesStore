package dTunesStore.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dTunesStore.dataStore.MusicInfo;
import dTunesStore.dataStore.MusicStore;

public class DataReader {
	
	private String file_name;
	BufferedReader br = null;
	Helper helper = new Helper();
	
	/***
	 * Let's create a null data structure
	 * @param filename
	 * @param arrayList
	 */
	public List<MusicInfo> array_List = new ArrayList<MusicInfo>();
	public List<MusicInfo> dataStore_List = new ArrayList<MusicInfo>();
	
	private MusicInfo currentObj;
	//MusicStore musicStore = MusicStore.getUniqueInstance();
	
	public DataReader(String filename,List<MusicInfo> arrayList){
		this.file_name = filename;
		this.array_List = arrayList;
		
		this.open_file();
	}
	
	public DataReader(String filename,List<MusicInfo> arrayList, List<MusicInfo> dataStoreList){
		this.file_name = filename;
		this.array_List = arrayList;
		this.dataStore_List = dataStoreList;
		
		
		this.open_file();
	}
	
	public void open_file() {
		try {
			br = new BufferedReader(new FileReader(this.file_name));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	
	public void close_file(){
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	public void read_file(int currentThreadId,String mode) {
		
			String sCurrentLine;
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					String details[] = sCurrentLine.split(" ");
					//musicStore.createStructure(songName, albumName, leadName, duration)
					if(mode == "saveToDS")
						array_List.add(helper.createStructure(details[0],details[1],details[2],details[3]));
					else if(mode == "search"){
						//System.out.println("sdd");
						search_file(currentThreadId,sCurrentLine);
						
					}
					//System.out.println("Thread id: "+ currentThreadId + " - " +sCurrentLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}		
	}
	
	public synchronized void search_file(int currentThreadId,String Datatosearch){
		
		Iterator<MusicInfo> itr = this.dataStore_List.iterator();
		
		while(itr.hasNext()){
			currentObj = itr.next();
			String songname = currentObj.getSongName();
			String leadname = currentObj.getLeadName();
			String albumname = currentObj.getAlbumName();
			Double duration = currentObj.getDuration();
			
			if(songname.equals(Datatosearch) || leadname.equals(Datatosearch) || albumname.equals(Datatosearch)){
				System.out.println("found !!! thread id : "+currentThreadId+" - "+songname + " " + albumname + " " + 
						leadname + " " + duration);
				array_List.add(helper.createStructure(songname,albumname,leadname,duration.toString()));
			} 
		}
		
	}
}
