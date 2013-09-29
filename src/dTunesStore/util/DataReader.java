package dTunesStore.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import dTunesStore.dataStore.MusicInfo;
import dTunesStore.dataStore.MusicStore;

public class DataReader {
	
	private String fileName;
	BufferedReader br = null;
	Helper helper = new Helper();
	
	/***
	 * Let's create a null DATA STRUCTURE
	 *  DATA STRUCTURE IMPACT ZONE
	 * @param fileName
	 * @param arrayList
	 */
	Vector<MusicInfo> vector = new Vector<MusicInfo>();
	//public List<MusicInfo> vector = new ArrayList<MusicInfo>();
	public List<MusicInfo> resultList = new ArrayList<MusicInfo>();
	
	private MusicInfo currentObj;
	//MusicStore musicStore = MusicStore.getUniqueInstance();
	
	/***
	 * DATA STRUCTURE IMPACT ZONE in parameter 
	 * 
	 * @param fileName
	 * @param arrayList
	 */
	public DataReader(String fileName,Vector<MusicInfo> vector){
		Debug.print_debug(4,"DataReader constructor(String fileName,Vector<MusicInfo> vector) called.");
		this.fileName = fileName;
		this.vector = vector;
		
		this.open_file();
	}
	
	/***
	 * DATA STRUCTURE IMPACT ZONE in parameter 
	 * 
	 * @param fileName
	 * @param arrayList
	 * @param dataStoreList
	 */
	public DataReader(String fileName, List<MusicInfo> resultList,Vector<MusicInfo> vector){
		Debug.print_debug(4,"DataReader constructor(String fileName, List<MusicInfo> resultList,Vector<MusicInfo>) vector called.");
		this.fileName = fileName;
		this.vector = vector;
		this.resultList = resultList;
		
		this.open_file();
	}
	
	public void open_file() {
		try {
			br = new BufferedReader(new FileReader(this.fileName));
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
					
					//DATA STRUCTURE IMPACT ZONE variable vector 
					
					if(mode == "saveToDS")
						vector.add(helper.createStructure(details[0],details[1],details[2],details[3]));
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
	
	public synchronized void search_file(int currentThreadId,String DataTosearch){
		
		//DATA STRUCTURE IMPACT ZONE variable dataStore_List
		Iterator<MusicInfo> itr = this.vector.iterator();
		
		while(itr.hasNext()){
			currentObj = itr.next();
			String songname = currentObj.getSongName();
			String leadname = currentObj.getLeadName();
			String albumname = currentObj.getAlbumName();
			Double duration = currentObj.getDuration();
			
			if(songname.equals(DataTosearch) || leadname.equals(DataTosearch) || albumname.equals(DataTosearch)){
				if(Debug.get_debug_value()==1){
				System.out.println("Entry found by thread  : "+currentThreadId+" - "+songname + " " + albumname + " " + 
						leadname + " " + duration);
				}
				Debug.print_debug(2,"New entry added to the result structure");
				resultList.add(helper.createStructure(songname,albumname,leadname,duration.toString()));
			} 
		}
		
	}
}
