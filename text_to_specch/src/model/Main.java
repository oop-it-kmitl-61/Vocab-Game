package model;

import marytts.modules.synthesis.Voice;

public class Main {
	public static void main(String[] args) {
		TextToSpeech tts = new TextToSpeech();
		Voice.getAvailableVoices().stream().forEach(System.out::println);
		tts.setVoice("dfki-poppy-hsmm"); //Or use "cmu-slt-hsmm" like siri and google translate voice
		tts.speak("Hello World", 1.5f, false, false);
	}
	
}