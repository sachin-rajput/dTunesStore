package dTunesStore.util;

import java.io.*;

import dTunesStore.dataStore.MusicInfo;

public class Helper {
	
	private int file_lines_count;
	private static Helper instance;
	
	/***
	 * 
	 * @return Helper instance 
	 */
	public static Helper getUniqueInstance() {
		if(instance==null)
			instance = new Helper();
		return instance;
	}
	
	public Helper(){
		Debug.print_debug(4,"Helper constructor called.");
		
	}
	
	public void set_file_lines_count(int lines) {
		this.file_lines_count = lines;
	}
	
	public int get_file_lines_count() {
		return this.file_lines_count;
	}
	
	public MusicInfo createStructure(String songName,String albumName,String leadName,String duration){
		MusicInfo record = new MusicInfo();
		record.setSongName(songName);
		record.setLeadName(leadName);
		record.setAlbumName(albumName);
		record.setDuration(Double.parseDouble(duration));
		//arrayList.add(record);
		return record;
	}
	
	public int countLines(String filename) throws IOException {
	    LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
	int cnt = 0;
	String lineRead = "";
	while ((lineRead = reader.readLine()) != null) {}

	cnt = reader.getLineNumber(); 
	reader.close();
	return cnt;
	}
	
}
