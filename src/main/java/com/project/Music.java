package com.project;

public class Music {

    private String chords;
    private Specification spec;

    public Music(String chords, Specification spec){
        this.chords = chords;
        this.spec = spec;
    }

    public String getChords(){
        return chords;
    }

    public Specification getSpec(){
        return spec;
    }
}
