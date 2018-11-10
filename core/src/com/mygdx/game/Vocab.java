package com.mygdx.game;

public class Vocab {
    private int id;
    private String word;
    private String meaning;
    
    public Vocab(){

    }
    public Vocab(int id, String word, String meaning) {
    	 this.id = id;
         this.word = word;
         this.meaning = meaning;
    }
    public void setId(int id){
         this.id = id;
    }
    public void setWord(String word){
         this.word = word;
    }
    public void setMeaning(String meaning){
         this.meaning = meaning;
    }
    public void setVocab(int id, String word, String meaning){
         this.id = id;
         this.word = word;
         this.meaning = meaning;
    }
    public int  getId(){
         return id;
    }
    public String getWord(){
         return word;
    }
    public String getMeaning(){
         return meaning;
    }
    public String toString() {
    	return id+" "+word+" "+ meaning;
    }
}
