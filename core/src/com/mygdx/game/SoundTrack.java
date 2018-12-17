package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundTrack implements Runnable{
	private static Music music;
	
	
	public SoundTrack() {
		try{
			music = Gdx.audio.newMusic(Gdx.files.internal("Play Time Happy Instrumental Music for Kids.mp3"));
			music.setVolume(0.04f);
			music.setLooping(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		music.play();
		
	}
	
	public static void setVolume(float v) {
		music.setVolume(v);
	}
}
