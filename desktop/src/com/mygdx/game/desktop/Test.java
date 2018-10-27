package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Font;

public class Test{

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.title = "Learn LibGDX #2 - Hello World ";
        config.width = 500;
        config.height = 500;
        new LwjglApplication(new Font(), config);
    }

}
