package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyMainGame;
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Vocab Game";
		config.width = 500;
		config.height = 500;
		config.resizable = false;
		
		//arg[0] is path of project 
		new LwjglApplication(new MyMainGame(arg[0]), config);
	}
}
