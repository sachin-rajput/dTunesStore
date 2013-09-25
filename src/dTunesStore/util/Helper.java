package dTunesStore.util;

import java.io.*;

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
	
	public void set_file_lines_count(int lines) {
		this.file_lines_count = lines;
	}
	
	public int get_file_lines_count() {
		return this.file_lines_count;
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
