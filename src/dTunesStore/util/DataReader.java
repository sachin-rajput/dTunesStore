package dTunesStore.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	//MusicStore musicStore = MusicStore.getUniqueInstance();
	
	public DataReader(String filename,List<MusicInfo> arrayList){
		this.file_name = filename;
		this.array_List = arrayList;
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
}
