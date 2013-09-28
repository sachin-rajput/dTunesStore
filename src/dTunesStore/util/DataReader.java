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
	private Iterator<MusicInfo> itr;
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
		this.itr = this.dataStore_List.iterator();
		
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
	
	public void read_file(int currentThreadId) {
		
			String sCurrentLine;
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					String details[] = sCurrentLine.split(" ");
					//musicStore.createStructure(songName, albumName, leadName, duration)
					array_List.add(helper.createStructure(details[0],details[1],details[2],details[3]));
					
					System.out.println("Thread id: "+ currentThreadId + " - " +sCurrentLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}		
	}
	
	public void search_file(int currentThreadId){
		
		
		while(itr.hasNext()){
			currentObj = itr.next();
			System.out.println("thread id : "+currentThreadId+" - "+currentObj.getSongName() + " " + currentObj.getAlbumName() + " " + 
					currentObj.getLeadName() + " " + currentObj.getDuration());
		}
		
		System.out.println("The size of array is : " + dataStore_List.size());
	}
}
