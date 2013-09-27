package dTunesStore.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dTunesStore.dataStore.MusicStore;

public class DataReader {
	
	private String file_name;
	BufferedReader br = null;
	//MusicStore musicStore = MusicStore.getUniqueInstance();
	
	public DataReader(String filename){
		this.file_name = filename;
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
	
	public void read_file(int currentThreadId, MusicStore musicStore) {
		
			String sCurrentLine;
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					String details[] = sCurrentLine.split(" ");
					//musicStore.createStructure(songName, albumName, leadName, duration)
					musicStore.arrayList.add(musicStore.createStructure(details[0],details[1],details[2],details[3]));
					
					System.out.println("Thread id: "+ currentThreadId + " - " +sCurrentLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}		
	}
}
