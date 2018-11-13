package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    final Label vocab1, vocab2, vocab3, vocab4, label1;
    public GuessingWordsScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        
        allVocabs = ReadVocabs.getData();
        randomChoice();
        
        final Label.LabelStyle label1Style = new Label.LabelStyle();
        final BitmapFont myFont = new BitmapFont(Gdx.files.local("Font/supermarket.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLACK;
        
        // words center and make Capitalize
        label1 = new Label(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase(), label1Style);
        label1.setSize(Gdx.graphics.getWidth(),row_height);
        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*4);
        label1.setAlignment(Align.center);

        vocab1 = new Label(choiceVocabs.get(randomNumber.get(0)).getMeaning(), label1Style);
        vocab1.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab1.setPosition(65,175);
       
        vocab2 = new Label(choiceVocabs.get(randomNumber.get(1)).getMeaning(), label1Style);
        vocab2.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab2.setPosition(315,175);

        vocab3 = new Label(choiceVocabs.get(randomNumber.get(2)).getMeaning(), label1Style);
        vocab3.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab3.setPosition(65,80);

        vocab4 = new Label(choiceVocabs.get(randomNumber.get(3)).getMeaning(), label1Style);
        vocab4.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab4.setPosition(315,80);

         //add listener
        vocab1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	randomChoice();
            	dynamicLabel();
                
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
            	dynamicLabel();
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
            	dynamicLabel();
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
            	dynamicLabel();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        
        
        //hover
        vocab1.addListener(new ClickListener() {    
        	@Override
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		Label.LabelStyle hoverStyle = new Label.LabelStyle();
        		hoverStyle.fontColor = Color.valueOf("#00bfff");
        		hoverStyle.font = myFont;
        		vocab1.setStyle(hoverStyle);
        	}
        	 @Override
        	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        		 vocab1.setStyle(label1Style);
        	}
        });
        vocab2.addListener(new ClickListener() {    
        	@Override
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		Label.LabelStyle hoverStyle = new Label.LabelStyle();
        		hoverStyle.fontColor = Color.valueOf("#00bfff");
        		hoverStyle.font = myFont;
        		vocab2.setStyle(hoverStyle);
        	}
        	 @Override
        	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        		 vocab2.setStyle(label1Style);
        	}
        });
        vocab3.addListener(new ClickListener() {    
        	@Override
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		Label.LabelStyle hoverStyle = new Label.LabelStyle();
        		hoverStyle.fontColor = Color.valueOf("#00bfff");
        		hoverStyle.font = myFont;
        		vocab3.setStyle(hoverStyle);
        	}
        	 @Override
        	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        		 vocab3.setStyle(label1Style);
        	}
        });
        vocab4.addListener(new ClickListener() {    
        	@Override
        	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        		Label.LabelStyle hoverStyle = new Label.LabelStyle();
        		hoverStyle.fontColor = Color.valueOf("#00bfff");
        		hoverStyle.font = myFont;
        		vocab4.setStyle(hoverStyle);
        	}
        	 @Override
        	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        		 vocab4.setStyle(label1Style);
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
    	//clear before start
    	choiceVocabs.clear();
    	randomNumber.clear();
        rightVocab = allVocabs[index];
        choiceVocabs.add(rightVocab);
        do {
        	int r = (int) (Math.random()*20);
        	//prevent add the same word
        	if(!choiceVocabs.contains(allVocabs[(r)%19])) {
        		choiceVocabs.add(allVocabs[(r)%19]);        	
        		System.out.println("choic "+allVocabs[(r)%19]);
        	}
        }while(choiceVocabs.size()<4);
        System.out.println("===============================");
       System.out.println("riht "+rightVocab);
        do {
        	//random 0-3
        	int n = (int) (Math.random()*4);
        	if(!randomNumber.contains(n)) {
        		randomNumber.add(n);
        		System.out.println("randomnum "+ n);
        	}
        }while(randomNumber.size()<4);
        index++;

    }
    public void dynamicLabel() {
    	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
        vocab1.setText(choiceVocabs.get(randomNumber.get(0)).getMeaning());
        vocab2.setText(choiceVocabs.get(randomNumber.get(1)).getMeaning());
        vocab3.setText(choiceVocabs.get(randomNumber.get(2)).getMeaning());
        vocab4.setText(choiceVocabs.get(randomNumber.get(3)).getMeaning());
    }
}