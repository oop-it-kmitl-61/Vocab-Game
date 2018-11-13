package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyMainGame;
import com.mygdx.game.ReadVocabs;
import com.mygdx.game.Vocab;
import com.mygdx.game.readVocab;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;

import java.awt.event.InputMethodListener;
import java.awt.event.KeyEvent;


public class GuessingWordsScreen extends ApplicationAdapter implements Screen, InputProcessor {
    private Stage stage;
    private Game game;
    private ShapeRenderer shapeRenderer;
    private InputMultiplexer inputMultiplexer;
    private int index;
    private Vocab rightVocab, allVocabs[];
    private ArrayList<Vocab> choiceVocabs = new ArrayList<Vocab>(); 
    private ArrayList<Integer> randomNumber = new ArrayList<Integer>();
    public GuessingWordsScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        
        allVocabs = ReadVocabs.getData();
        randomChoice();
        
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.local("Font/supermarket.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLACK;
        
        // words center and make Capitalize
        final Label label1 = new Label(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase(), label1Style);
        label1.setSize(Gdx.graphics.getWidth(),row_height);
        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*4);
        label1.setAlignment(Align.center);

        final Label vocab1 = new Label(choiceVocabs.get(randomNumber.get(0)).getMeaning(), label1Style);
        vocab1.setSize(Gdx.graphics.getWidth(),row_height);
        vocab1.setPosition(70,175);
       
        final Label vocab2 = new Label(choiceVocabs.get(randomNumber.get(1)).getMeaning(), label1Style);
        vocab2.setSize(Gdx.graphics.getWidth(),row_height);
        vocab2.setPosition(330,175);

        final Label vocab3 = new Label(choiceVocabs.get(randomNumber.get(2)).getMeaning(), label1Style);
        vocab3.setSize(Gdx.graphics.getWidth(),row_height);
        vocab3.setPosition(70,80);

        final Label vocab4 = new Label(choiceVocabs.get(randomNumber.get(3)).getMeaning(), label1Style);
        vocab4.setSize(Gdx.graphics.getWidth(),row_height);
        vocab4.setPosition(330,80);

         //add listener
        vocab1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	randomChoice();
            	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
            	vocab1.setText(choiceVocabs.get(randomNumber.get(0)).getMeaning());
                
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        vocab2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	randomChoice();
            	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
                vocab2.setText(choiceVocabs.get(randomNumber.get(1)).getMeaning());
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
       
        vocab3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	randomChoice();
            	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
                vocab3.setText(choiceVocabs.get(randomNumber.get(2)).getMeaning());
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        vocab4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	randomChoice();
            	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
                vocab4.setText(choiceVocabs.get(randomNumber.get(3)).getMeaning());
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        stage.addActor(label1);
        stage.addActor(vocab1);
        stage.addActor(vocab2);
        stage.addActor(vocab3);
        stage.addActor(vocab4);

        shapeRenderer = new ShapeRenderer();


        TextButton backButton;
        backButton = new TextButton("BACK", MyMainGame.gameSkin,"small");
        backButton.setSize(150,80);
        backButton.setPosition(350,420);

        backButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new VocabScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(backButton);

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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //background vocab
        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.rect(0,0,500,250);
        //div1
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(40,170,170,60);
        //div2
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(290,170,170,60);
        //div3
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(40,70,170,60);
        //div4
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(290,70,170,60);
        shapeRenderer.end();
        

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        shapeRenderer = new ShapeRenderer();
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
        shapeRenderer.dispose();
        stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.ESCAPE){
            Object[] options = {"resume", "exit"};
            int n= JOptionPane.showOptionDialog(null, "Would you like to resume or exit","Question", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);
            if(n==1){
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
    public static void main(String[] args) {

    }
    public void randomChoice() {
        rightVocab = allVocabs[index];
        choiceVocabs.add(rightVocab);
        for(int i =1 ;i<=3;i++) {
        	choiceVocabs.add(allVocabs[(i*index)%19]);
        }
 
        do {
        	//random 0-3
        	int n = (int) (Math.random()*4);
        	if(!randomNumber.contains(n)) {
        		randomNumber.add(n);
        	}
        }while(randomNumber.size()<4);
        index++;
    }
}