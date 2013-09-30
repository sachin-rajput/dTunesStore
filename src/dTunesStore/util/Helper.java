package dTunesStore.util;

import java.io.*;
import java.util.Random;

import dTunesStore.dataStore.MusicInfo;

public class Helper {
	
	private int file_lines_count;
	private static Helper instance;
	
	/**
	 * Constructor for Helper class
	 */
	public Helper(){
		Debug.printDebug(4,"Helper constructor called.");
	}
	
	/**
	 * This method sets the below parameters to the MusicInfo object
	 * @param songName
	 * @param albumName
	 * @param leadName
	 * @param duration
	 * @return MusicInfo
	 */
	public MusicInfo createStructure(String songName,String albumName,String leadName,String duration){
		MusicInfo record = new MusicInfo();
		record.setSongName(songName);
		record.setLeadName(leadName);
		record.setAlbumName(albumName);
		record.setDuration(Double.parseDouble(duration));
		//arrayList.add(record);
		return record;
	}
	
	/**
	 * This method contains the script to create entries for the DataFile 
	 * @param fileName
	 * @param noOfEntries
	 */
	public void createEntriesScript(String fileName,int noOfEntries){
		try {
			 Random generator = new Random();
			 File ff = new File(fileName);
			 if(!ff.exists() || ff.length()==0){
				 ff.createNewFile();
				 BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
				 for (int i = 1; i <= noOfEntries; i++) {
	            	double r = Math.round((generator.nextDouble()*5 + 4) * 10) / 10.0;
	                out.write("name" + i +" " + "album" + i +" " + "singer" + i + " " + r);
	                out.newLine();
	            }
	            out.close();
			 }
	        } 
		catch (IOException e) {
	        	
	        }
	    }
	
	/**
	 * This method contains the script to create search terms for the Search file
	 * @param fileName
	 * @param noOfEntries
	 * @param outOfBoundEntries
	 * @param noOfSearchTerms
	 */
	public void createSearchScript(String fileName,int noOfEntries,int outOfBoundEntries,int noOfSearchTerms){
		try {
			 Random number = new Random();
			 Random picker  = new Random();
			 File ff = new File(fileName);
			 String[] searchTerms = {"name","album","singer"};
			 if(!ff.exists() || ff.length()==0){
				 ff.createNewFile();
				 BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
				 for (int i = 1; i <= noOfSearchTerms; i++) {
					 //5 + (int)(Math.random() * ((10 - 5) + 1))
					 int r = (int)(Math.random() * ((outOfBoundEntries - 1) +1));
	            	//int r = Math.round((number.nextInt()*1 + outOfBoundEntries));
	                out.write(searchTerms[picker.nextInt(searchTerms.length)]+r);
	                out.newLine();
	            }
	            out.close();
			 }
	        } 
		catch (IOException e) {
	        	
	        }
	    }

	}
	

