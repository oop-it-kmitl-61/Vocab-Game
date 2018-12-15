package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Vocab;
import java.util.ArrayList;
import javax.swing.JOptionPane;



public class ConcludeScreen extends ApplicationAdapter implements Screen, InputProcessor {
    private Stage stage;
    private Game game;
    private InputMultiplexer inputMultiplexer;
    public ConcludeScreen(Game aGame, ArrayList<Vocab> falseAnswer) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        int row_height = Gdx.graphics.getWidth() / 12;
        //background
        Texture texture = new Texture(Gdx.files.internal("img/conconcludeScreen.jpg"));
        Image image1 = new Image(texture);
        image1.setPosition(0,0);
        image1.setSize(500,500);
        stage.addActor(image1);
        
        final Label.LabelStyle label1Style = new Label.LabelStyle();
        final BitmapFont myFont = new BitmapFont(Gdx.files.local("Font/supermarket.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLACK;
        
        Label label1 = new Label("", label1Style);
        label1.setSize(Gdx.graphics.getWidth(),row_height);
        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*2.5f);
        label1.setAlignment(Align.center);
        Label vocab = new Label("", label1Style);
        vocab.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab.setPosition(80,300);
        if(falseAnswer.size()==5) {
        	label1.setText("You Fail");
	        String text = "\n";
	        for(int i=0;i<falseAnswer.size();i++) {
	        	if(i==falseAnswer.size()-1) {
	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
	        	}else {
	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
	        	}
	        }
	       vocab.setText(text);
        }
        else if(falseAnswer.size()!=0) {
           	label1.setText("You Win");
	        String text = "\n";
	        for(int i=0;i<falseAnswer.size();i++) {
	        	if(i==falseAnswer.size()-1) {
	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
	        	}else {
	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
	        	}
	        }
	       vocab.setText(text);
        }
        else {
        	label1.setText("You Win");
        	vocab.setText("\nCongratulations\nall of your answers \nis Correct");
        }
        stage.addActor(label1);
        stage.addActor(vocab);


    }
    public void create () {


    }



    @Override
    public void show() {
        inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            Object[] options = {"Yes", "No"};
            int n= JOptionPane.showOptionDialog(null, "Would you like to exit?","Question", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);
            if(n==0){
                game.setScreen(new VocabScreen(game));

            }
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}