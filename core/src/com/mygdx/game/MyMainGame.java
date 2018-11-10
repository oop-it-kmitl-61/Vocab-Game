package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.Screens.TitleScreen;

public class MyMainGame extends Game {

    static public Skin gameSkin;

    public void create () {
        gameSkin = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));
        this.setScreen(new TitleScreen(this));
//        BitmapFont myFont = new BitmapFont(Gdx.files.internal("Font/supermarketFont.fnt"));
    }

    public void render () {
        super.render();
    }


    public void dispose () {
    }
}
