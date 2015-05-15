package com.project;

public class Specification {

    private String band;
    private String musicName;

    public Specification(String band, String musicName){
        this.band = band;
        this.musicName = musicName;
    }
    
    public boolean matches(Specification spec){
        if(!band.equals(spec.band)) return false;
        if(!musicName.equals(spec.musicName)) return false;
        return true;
    }

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
}
