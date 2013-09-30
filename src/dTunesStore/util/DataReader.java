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
	Vector<MusicInfo> vector = new Vector<MusicInfo>();
	public List<MusicInfo> resultList = new ArrayList<MusicInfo>();
	private MusicInfo currentObj;
	
	/***
	 * DATA STRUCTURE IMPACT ZONE in parameter 
	 * Constructor for DataReader
	 * @param fileName - name of the data file
	 * @param vector - data structure to store the data
	 */
	public DataReader(String fileName,Vector<MusicInfo> vector){
		Debug.printDebug(4,"DataReader constructor(String fileName,Vector<MusicInfo> vector) called.");
		this.fileName = fileName;
		this.vector = vector;
		
		this.openFile();
	}
	
	/***
	 * DATA STRUCTURE IMPACT ZONE in parameter 
	 * Constructor for DataReader(for searching)
	 * @param fileName - name of the file to be searched
	 * @param resultList - data structure to store the search results
	 * @param vector - data structure with the data
	 */
	public DataReader(String fileName, List<MusicInfo> resultList,Vector<MusicInfo> vector){
		Debug.printDebug(4,"DataReader constructor(String fileName, List<MusicInfo> resultList,Vector<MusicInfo>) vector called.");
		this.fileName = fileName;
		this.vector = vector;
		this.resultList = resultList;
		
		this.openFile();
	}
	
	/**
	 * This method opens the file
	 */
	public void openFile() {
		try {
			br = new BufferedReader(new FileReader(this.fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * This method will close the file
	 */
	public void closeFile(){
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			
		}
	}
	
	/**
	 * This method will read the file and according the value of mode will either save the content
	 * of the file to the data structure or will call the searchFile for searching.
	 * @param currentThreadId - current thread Id
	 * @param mode - depending on this value, it will either populate the data structure of search for a term
	 */
	public void readFile(int currentThreadId,String mode) {
		
			String sCurrentLine;
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					String details[] = sCurrentLine.split(" ");
					//musicStore.createStructure(songName, albumName, leadName, duration)
					
					//DATA STRUCTURE IMPACT ZONE variable vector 
					
					if(mode == "saveToDS"){
						vector.add(helper.createStructure(details[0],details[1],details[2],details[3]));
					}
						
					else if(mode == "search"){
						//System.out.println("sdd");
						searchFile(currentThreadId,sCurrentLine);
						
					}
					//System.out.println("Thread id: "+ currentThreadId + " - " +sCurrentLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}		
	}
	
	/**
	 * This method will search for the search term in the data structure and if present will display the result
	 * @param currentThreadId - current thread Id
	 * @param DataTosearch - search term to be searched
	 */
	public synchronized void searchFile(int currentThreadId,String DataTosearch){
		
		//DATA STRUCTURE IMPACT ZONE variable dataStore_List
		Iterator<MusicInfo> itr = this.vector.iterator();
		
		while(itr.hasNext()){
			currentObj = itr.next();
			String songname = currentObj.getSongName();
			String leadname = currentObj.getLeadName();
			String albumname = currentObj.getAlbumName();
			Double duration = currentObj.getDuration();
			
			if(songname.equals(DataTosearch) || leadname.equals(DataTosearch) || albumname.equals(DataTosearch)){
				if(Debug.getDebugValue()==1){
				System.out.println("Entry found by thread  : "+currentThreadId+" - "+songname + " " + albumname + " " + 
						leadname + " " + duration);
				}
				Debug.printDebug(2,"New entry added to the result structure");
				resultList.add(helper.createStructure(songname,albumname,leadname,duration.toString()));
			} 
		}
		
	}
}
