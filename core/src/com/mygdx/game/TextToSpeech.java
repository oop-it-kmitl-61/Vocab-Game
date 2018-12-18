package com.mygdx.game;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech implements Runnable{
	//String voiceName = "kevin16";
	private Voice voice;
	private String word;
	private boolean end, callSpeak;
	public TextToSpeech() {
	    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	    voice = VoiceManager.getInstance().getVoice("kevin16");
	    if (voice != null) {
	        voice.allocate();// Allocating Voice
	        try {
	            voice.setRate(190);// Setting the rate of the voice
	            voice.setPitch(150);// Setting the Pitch of the voice
	            voice.setVolume(3);// Setting the volume of the voice
	
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	
	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	public void SpeakText(String words) {
		word = words;
		callSpeak = true;
	    
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	@Override
	public void run() {
		
		for(;;) {
			if(end) {
				break;
			}
			if(callSpeak) {
				voice.speak(word);
				callSpeak = false;
			}
			else {
				Thread.yield();
			}
		}
		
	}
}
