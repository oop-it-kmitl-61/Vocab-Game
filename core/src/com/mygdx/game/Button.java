package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Button extends ApplicationAdapter {
    private Stage stage;
    Skin mySkin;
    TextButton startButton, exitButton, optionButton;
    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin  = new Skin(Gdx.files.internal("glassy/skin/glassy-ui.json"));

        startButton = new TextButton("START",mySkin,"small");
        startButton.setSize(150,80);
        startButton.setPosition(170,350);

        optionButton = new TextButton("OPTION",mySkin,"small");
        optionButton.setSize(150,80);
        optionButton.setPosition(170,250);

        exitButton = new TextButton("EXIT",mySkin,"small");
        exitButton.setSize(150,80);
        exitButton.setPosition(170,150);

        stage.addActor(startButton);
        stage.addActor(exitButton);
        stage.addActor(optionButton);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}

