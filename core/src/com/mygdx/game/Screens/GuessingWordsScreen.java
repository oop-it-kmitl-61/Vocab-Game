package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.CountTime;
import com.mygdx.game.ReadVocabs;
import com.mygdx.game.TextToSpeech;
import com.mygdx.game.Vocab;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GuessingWordsScreen extends ApplicationAdapter implements Screen, InputProcessor{
    private Stage stage;
    private Game game;
    private ShapeRenderer shapeRenderer;
    private InputMultiplexer inputMultiplexer;
    private int index;
    private Vocab rightVocab, allVocabs[];
    private ArrayList<Vocab> choiceVocabs = new ArrayList<Vocab>(); 
    private ArrayList<Integer> randomNumber = new ArrayList<Integer>();
    final Label vocab1, vocab2, vocab3, vocab4, label1, falseLabel;
    private ArrayList<Vocab> falseAnswer = new ArrayList<Vocab>();
    private int falseCount;
    private CountTime timeLable;
    private Thread timeThread, textToSpeechThread;
    private static boolean timeOut;
    private Skin speakSkin;
    private TextToSpeech textToSpeech;
    public GuessingWordsScreen(Game aGame, String tableName) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        speakSkin= new Skin(Gdx.files.internal("orange/skin/extras/skin-composer-dark/dark-peel-ui.json"));
        
        //tell ReadVocab what table should be read and return vocab
        allVocabs = ReadVocabs.getData(tableName);
        
        textToSpeech = new TextToSpeech();
        
        randomChoice();
        
        final Label.LabelStyle label1Style = new Label.LabelStyle();
        final BitmapFont myFont = new BitmapFont(Gdx.files.local("Font/supermarket.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.BLACK;
       
        falseLabel = new Label(index+"/20\nFalse: "+falseCount+"/5", label1Style);
        falseLabel.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        falseLabel.setPosition(2, 435);
        
        //time lable
        timeLable = new CountTime("", label1Style);
        timeLable.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        timeLable.setPosition(420, 450);
        
        //time thread
        timeThread = new Thread(timeLable); 
        timeThread.start();
        //speak thread
        textToSpeechThread = new Thread(textToSpeech);
        textToSpeechThread.start();
        
        // words center and make Capitalize
        label1 = new Label(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase(), label1Style);
        label1.setSize(Gdx.graphics.getWidth(),row_height);
        label1.setPosition(0,Gdx.graphics.getHeight()-row_height*4);
        label1.setAlignment(Align.center);

        //speak button
        ImageButton speakButton = new ImageButton(speakSkin);
        speakButton.setSize(col_width*1.7f,(float)(row_height*1.5f));
        speakButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-sound.png"))));
        speakButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-sound.png"))));
        speakButton.setPosition(50,Gdx.graphics.getHeight()-row_height*4.3f);
        
        vocab1 = new Label(choiceVocabs.get(randomNumber.get(0)).getMeaning(), label1Style);
        vocab1.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab1.setPosition(55,175);
       
        vocab2 = new Label(choiceVocabs.get(randomNumber.get(1)).getMeaning(), label1Style);
        vocab2.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab2.setPosition(305,175);

        vocab3 = new Label(choiceVocabs.get(randomNumber.get(2)).getMeaning(), label1Style);
        vocab3.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab3.setPosition(55,80);

        vocab4 = new Label(choiceVocabs.get(randomNumber.get(3)).getMeaning(), label1Style);
        vocab4.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab4.setPosition(305,80);

         //add listener
        vocab1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	//check answer
            	timeLable.setPause(true);
            	if(choiceVocabs.get(randomNumber.get(0)).getWord().equals(rightVocab.getWord())) {
            		JOptionPane.showMessageDialog(null, "Correct!!", "Result",  JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		JOptionPane.showMessageDialog(null, "Wrong!!", "Result",JOptionPane.WARNING_MESSAGE);
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	timeLable.setPause(false);
            	timeLable.setTime(30,0);
            	randomChoice();
            	dynamicLabel();
            	falseLabel.setText(index+"/20\nFalse: "+falseCount+"/5");
                
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        vocab2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	//pause time
            	timeLable.setPause(true);
            	//check answer
              	if(choiceVocabs.get(randomNumber.get(1)).getWord().equals(rightVocab.getWord())) {
            		JOptionPane.showMessageDialog(null, "Correct!!", "Result",  JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		JOptionPane.showMessageDialog(null, "Wrong!!", "Result",JOptionPane.WARNING_MESSAGE);
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
              	timeLable.setPause(false);
              	timeLable.setTime(30,0);
            	randomChoice();
            	dynamicLabel();
            	falseLabel.setText(index+"/20\nFalse: "+falseCount+"/5");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
       
        vocab3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	timeLable.setPause(true);
            	//check answer
            	if(choiceVocabs.get(randomNumber.get(2)).getWord().equals(rightVocab.getWord())) {
            		JOptionPane.showMessageDialog(null, "Correct!!", "Result",  JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		JOptionPane.showMessageDialog(null, "Wrong!!", "Result",JOptionPane.WARNING_MESSAGE);
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	timeLable.setPause(false);
            	timeLable.setTime(30,0);
            	randomChoice();
            	dynamicLabel();
            	falseLabel.setText(index+"/20\nFalse: "+falseCount+"/5");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        vocab4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	timeLable.setPause(true);
            	//check answer
            	if(choiceVocabs.get(randomNumber.get(3)).getWord().equals(rightVocab.getWord())) {
            		JOptionPane.showMessageDialog(null, "Correct!!", "Result",  JOptionPane.INFORMATION_MESSAGE);
            	}else {
            		JOptionPane.showMessageDialog(null, "Wrong!!", "Result",JOptionPane.WARNING_MESSAGE);
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	timeLable.setPause(false);
            	timeLable.setTime(30, 0);
            	randomChoice();
            	dynamicLabel();
            	falseLabel.setText(index+"/20\nFalse: "+falseCount+"/5");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        //text to speech
        speakButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	textToSpeech.SpeakText(rightVocab.getWord());
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
        stage.addActor(speakButton);
        stage.addActor(timeLable);
        stage.addActor(falseLabel);
        stage.addActor(label1);
        stage.addActor(vocab1);
        stage.addActor(vocab2);
        stage.addActor(vocab3);
        stage.addActor(vocab4);
        stage.addListener(new ClickListener() {
        	
        });
        shapeRenderer = new ShapeRenderer();


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
            Object[] options = {"Resume", "Exit"};
        	timeLable.setPause(true);
            int n= JOptionPane.showOptionDialog(null, "Would you like to resume or exit ?","Question", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]);
            if(n==1){
            	timeLable.setEnd(true);
            	textToSpeech.setEnd(true);
                game.setScreen(new VocabScreen(game));

            }
            timeLable.setPause(false);
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
    	if(GuessingWordsScreen.timeOut) {
    		GuessingWordsScreen.timeOut = false;
    		falseCount++;
    		falseAnswer.add(rightVocab);
    		timeLable.setTime(30,0);
    		randomChoice();
    		dynamicLabel();
    		falseLabel.setText(index+"/20\nFalse: "+falseCount+"/5");
    	}
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
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
        	if(!choiceVocabs.contains(allVocabs[(r)])) {
        		choiceVocabs.add(allVocabs[(r)%19]);        	
        	}
        }while(choiceVocabs.size()<4);
        do {
        	//random 0-3
        	int n = (int) (Math.random()*4);
        	if(!randomNumber.contains(n)) {
        		randomNumber.add(n);
        	}
        }while(randomNumber.size()<4);
        index++;

        if(index==20|| falseCount==5) {
        	if(index ==20 && falseCount==0) {
        		timeLable.setPause(true);
        		JOptionPane.showMessageDialog(null, "            You win!!", "Result",  JOptionPane.INFORMATION_MESSAGE);
        	}else if(falseCount !=0 && falseCount!=5) {
        		String text = "";
    	        for(int i=0;i<falseAnswer.size();i++) {
    	        	if(i==falseAnswer.size()-1) {
    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
    	        	}else {
    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
    	        	}
    	        }
    	        timeLable.setPause(true);
        		JOptionPane.showMessageDialog(null, "            You Win!!\n"+text, "Result", JOptionPane.WARNING_MESSAGE);
        	}
        	else {
        		String text = "";
    	        for(int i=0;i<falseAnswer.size();i++) {
    	        	if(i==falseAnswer.size()-1) {
    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
    	        	}else {
    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
    	        	}
    	        }
    	        timeLable.setPause(true);
        		JOptionPane.showMessageDialog(null, "            You fail!!\n"+text, "Result", JOptionPane.WARNING_MESSAGE);
        	}
        	timeLable.setEnd(true);
        	textToSpeech.setEnd(true);
        	game.setScreen(new VocabScreen(game));
        }

    }
    public void dynamicLabel() {
    	label1.setText(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase());
        vocab1.setText(choiceVocabs.get(randomNumber.get(0)).getMeaning());
        vocab2.setText(choiceVocabs.get(randomNumber.get(1)).getMeaning());
        vocab3.setText(choiceVocabs.get(randomNumber.get(2)).getMeaning());
        vocab4.setText(choiceVocabs.get(randomNumber.get(3)).getMeaning());
    }
	public static void setTimeOut(boolean t) {
		timeOut = t;
	}

}