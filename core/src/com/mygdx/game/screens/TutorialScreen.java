package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class TutorialScreen extends PrototypeScreen {

    public TutorialScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        //background
        Texture texture = new Texture(Gdx.files.internal("img/tutor.jpg"));
        Image image1 = new Image(texture);
        image1.setPosition(0,0);
        image1.setSize(500,200);
        stage.addActor(image1);
    }

	@Override
	public boolean keyDown(int keycode) {
		 if(keycode == Input.Keys.ESCAPE){
			 game.setScreen(new TitleScreen(game));	
		 }
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return super.keyUp(keycode);
	}
}
