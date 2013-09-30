package dTunesStore.dataStore;

public class MusicInfo {

	private String songName;
	
	private String albumName;
	
	private String leadName;
	
	private double duration;

	/**
	 * Accessor for songName 
	 * @return songName
	 */
	public String getSongName() {
		return songName;
	}
	
	/**
	 * Mutator for songName
	 * @param songName
	 */
	public void setSongName(String songName) {
		this.songName = songName;
	}

	/**
	 * Accessor for albumName
	 * @return albumName
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * Mutator for albumName
	 * @param albumName
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * Accessor for leadName
	 * @return leadName
	 */
	public String getLeadName() {
		return leadName;
	}

	/**
	 * Mutator for leadName
	 * @param leadName
	 */
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	/**
	 * Accessor for duration
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Mutator for duration
	 * @param duration
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	
}
