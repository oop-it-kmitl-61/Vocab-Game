package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.screens.OrangeEx;
import com.mygdx.game.screens.RainbowExample;
import com.mygdx.game.screens.Test;

public class RainbowEx {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 500;
		config.height = 500;
		new LwjglApplication(new Test(), config);
	}
}
