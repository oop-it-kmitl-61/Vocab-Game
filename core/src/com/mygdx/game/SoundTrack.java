package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundTrack implements Runnable{
	private Music music;
	
	
	public SoundTrack() {
		music = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
		music.setVolume(0.1f);
	}

	@Override
	public void run() {
		music.play();
		
	}
	
	
}
