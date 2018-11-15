package com.mygdx.game;

import javax.swing.JOptionPane;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.Screens.GuessingWordsScreen;

public class CountTime extends Label implements Runnable{
	private int second =30,mill=0;
	private String output;
	private boolean pause, end;
	public CountTime(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	@Override
	public void run() {
		long i = 0;
		while(true) {
			if(end)
				break;
			else if(pause) {
				try {
					System.out.println("sleep");
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				if(i%60==0) {
					if(mill==0) {
						if(second>0)
							second--;
						else {
							JOptionPane.showMessageDialog(null, "Time Out!!", "Wannig",JOptionPane.WARNING_MESSAGE);
							GuessingWordsScreen.setTimeOut(true);
						}
					}
					if(mill>0) {
						mill--;
					}else {
						mill=9;
					}		
					output = String.format("%02d:%d",second, mill);
					setText(output);
					System.out.println(output); //30:00
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			i++;
		}
	}
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	public void setEnd(boolean end) {
		this.end = end;
	}
	public  void setTime(int second, int mill) {
		this.second = second;
		this.mill = mill;
	}
	
	
	
}