package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.screens.TitleScreen;

public class MyMainGame extends Game {
	private static boolean optionState;
    static public Skin gameSkin;
	private static int wordnum, timernum, falseWordnum;
	private static boolean showAnswer;
	
    public void create () {
     //   SoundTrack soundTrack = new SoundTrack();
       // Thread thread = new Thread(soundTrack);
        //thread.start();
        gameSkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
        this.setScreen(new TitleScreen(this));

    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
    
    public static void setOption(int word, int time,int falseword, boolean answer) {
    	wordnum = word;
    	timernum = time;
    	falseWordnum = falseword;
    	showAnswer = answer;
    }

	public static boolean isOptionState() {
		return optionState;
	}

	public static void setOptionState(boolean optionState) {
		MyMainGame.optionState = optionState;
	}

	public static int getWordnum() {
		return wordnum;
	}

	public static int getTimernum() {
		return timernum;
	}

	public static int getFalseWordnum() {
		return falseWordnum;
	}

	public static boolean isShowAnswer() {
		return showAnswer;
	}
}
