package com.mygdx.game.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.CountTime;
import com.mygdx.game.MyMainGame;
import com.mygdx.game.ReadVocabs;
import com.mygdx.game.TextToSpeech;
import com.mygdx.game.Vocab;
import java.util.ArrayList;


public class GuessingWordsScreen extends PrototypeScreen{
    private ShapeRenderer shapeRenderer;
    private InputMultiplexer inputMultiplexer;
    private int index, score;
    private Vocab rightVocab, allVocabs[];
    private ArrayList<Vocab> choiceVocabs = new ArrayList<Vocab>(); 
    private ArrayList<Integer> randomNumber = new ArrayList<Integer>();
    final Label vocab1, vocab2, vocab3, vocab4, label1, falseLabel;
    private ArrayList<Vocab> falseAnswer = new ArrayList<Vocab>();
    private int falseCount;
    private CountTime timeLable;
    private Thread timeThread, textToSpeechThread;
    private static boolean timeOut;
    private Skin orangeSkin;
    private TextToSpeech textToSpeech;
    private final Label.LabelStyle label1Style;
    public GuessingWordsScreen(Game aGame, String tableName) {
        game = aGame;
        stage = new Stage(new ScreenViewport());
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        orangeSkin= new Skin(Gdx.files.internal("orange/skin/extras/skin-composer-dark/dark-peel-ui.json"));
        
        //tell ReadVocab what table should be read and return vocab
        allVocabs = ReadVocabs.getData(tableName);
        
        textToSpeech = new TextToSpeech();
        
        randomChoice();
        
        label1Style = new Label.LabelStyle();
        final BitmapFont myFont = new BitmapFont(Gdx.files.local("Font/ltim.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.valueOf("#ffcd00");
        
        Label.LabelStyle label2Style = new Label.LabelStyle();
        final BitmapFont myFont2 = new BitmapFont(Gdx.files.local("rainbow/skin/font-button-export.fnt"));
        label2Style.font = myFont2;
        label2Style.fontColor = Color.valueOf("#00bfff");
       
        falseLabel = new Label(1+"/"+OptionScreen.getWordnum()+"\nFalse: "+falseCount+"/"+OptionScreen.getFalseWordnum(), MyMainGame.gameSkin);
        falseLabel.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        falseLabel.setPosition(2, 435);
        
        //time label
        timeLable = new CountTime("", MyMainGame.gameSkin);
        timeLable.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        timeLable.setPosition(420, 450);
        
        //time thread
        timeThread = new Thread(timeLable); 
        timeThread.start();
        //speak thread
        textToSpeechThread = new Thread(textToSpeech);
        textToSpeechThread.start();
        
        // words center and make Capitalize
        label1 = new Label(rightVocab.getWord().substring(0, 1).toUpperCase()+ rightVocab.getWord().substring(1).toLowerCase(), label2Style);
        label1.setSize(10,10);
        label1.setPosition(250,340);
        label1.setAlignment(Align.center);

        ImageButton speakButton = new ImageButton(orangeSkin);
        speakButton.setSize(col_width*1.2f,(float)(row_height*1.2f));
        speakButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-sound.png"))));
        speakButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("orange/raw/image-sound.png"))));
        speakButton.setPosition(5,Gdx.graphics.getHeight()-row_height*4.3f);
        
   
        vocab1 = new Label(choiceVocabs.get(randomNumber.get(0)).getMeaning(), label1Style);
        vocab1.setSize(Gdx.graphics.getWidth()/3f,row_height*1.2f);
        vocab1.setPosition(30,175);
        
        vocab2 = new Label(choiceVocabs.get(randomNumber.get(1)).getMeaning(), label1Style);
        vocab2.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab2.setPosition(260,175);

        vocab3 = new Label(choiceVocabs.get(randomNumber.get(2)).getMeaning(), label1Style);
        vocab3.setSize(Gdx.graphics.getWidth()/3f,row_height*1.2f);
        vocab3.setPosition(30,80);

        vocab4 = new Label(choiceVocabs.get(randomNumber.get(3)).getMeaning(), label1Style);
        vocab4.setSize(Gdx.graphics.getWidth()/4,row_height*1.2f);
        vocab4.setPosition(260,80);

         //add listener
        vocab1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	
            	//check answer
            	timeLable.setPause(true);
            	if(choiceVocabs.get(randomNumber.get(0)).getWord().equals(rightVocab.getWord())) {
            		score++;
            		popUp("Correct");
            	}else {
            		popUp("Wrong");
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	falseLabel.setText((index+1)+"/"+OptionScreen.getWordnum()+"\nFalse: "+falseCount+"/"+OptionScreen.getFalseWordnum());
            	isGameOver();
            	timeLable.setPause(false);
            	timeLable.setTime(OptionScreen.getTimernum(),0);
            	if(index <OptionScreen.getWordnum()) {
            		randomChoice();
            		dynamicLabel();
            	}
            	
            	
                
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
              		score++;
              		popUp("Correct");
            	}else {
            		popUp("Wrong");
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
              	falseLabel.setText((index + 1)+"/"+OptionScreen.getWordnum()+"\nFalse: "+falseCount+"/"+OptionScreen.getFalseWordnum());
              	isGameOver();
              	timeLable.setPause(false);
              	timeLable.setTime(OptionScreen.getTimernum(),0);
            	if(index <OptionScreen.getWordnum()) {
            		randomChoice();
            		dynamicLabel();
            	}
            	
            	
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
            		score++;
            		popUp("Correct");
            	}else {
            		popUp("Wrong");
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	falseLabel.setText((index + 1)+"/"+OptionScreen.getWordnum()+"\nFalse: "+falseCount+"/"+OptionScreen.getFalseWordnum());
            	isGameOver();
            	timeLable.setPause(false);
            	timeLable.setTime(OptionScreen.getTimernum(),0);
            	if(index <OptionScreen.getWordnum()) {
            		randomChoice();
            		dynamicLabel();
            	}
            	
            	
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
            		score++;
            		popUp("Correct");
            	}else {
            		popUp("Wrong");
            		falseCount++;
            		falseAnswer.add(rightVocab);
            	}
            	falseLabel.setText((index + 1) + "/" + OptionScreen.getWordnum() + "\nFalse: " + falseCount + "/"+ OptionScreen.getFalseWordnum());
                isGameOver();
                timeLable.setPause(false);
                timeLable.setTime(OptionScreen.getTimernum(), 0);
                if ((index) < OptionScreen.getWordnum()) {
                    randomChoice();
                    dynamicLabel();
                }
            	
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
        		hoverStyle.fontColor = Color.valueOf("#e87683");
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
        		hoverStyle.fontColor = Color.valueOf("#e87683");
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
        		hoverStyle.fontColor = Color.valueOf("#e87683");
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
        		hoverStyle.fontColor = Color.valueOf("#e87683");
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
        shapeRenderer.setColor(Color.valueOf("#fff3c1"));
        shapeRenderer.rect(0,0,500,500);
        
        //background button vocab
        shapeRenderer.setColor(Color.valueOf("#84ceed"));
        shapeRenderer.rect(0,0,500,250);
        shapeRenderer.end();
        stage.act();
        stage.draw();
        
        //check time out
    	if(GuessingWordsScreen.timeOut) {
    		timeLable.setPause(true);
    		GuessingWordsScreen.timeOut = false;
    		 //pop up
            Window window = new Window("Inform", MyMainGame.gameSkin);
	        window.setSize(300f, 150.0f);
	        window.setPosition(110, 220);
	        window.getTitleLabel().setAlignment(Align.center);
	        
	        Label label = new Label("Time out !!", MyMainGame.gameSkin);
	        window.add(label).padTop(20);
	  
	        window.row();
	        TextButton textButton = new TextButton("OK", MyMainGame.gameSkin, "small");
	        window.add(textButton).expandX().padTop(25.0f);
             stage.addActor(window);
             textButton.addListener(new InputListener(){
                 @Override
                 public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
             		
            		falseCount++;
            		falseAnswer.add(rightVocab);
            		timeLable.setTime(OptionScreen.getTimernum(),0);
            		falseLabel.setText((index + 1)+"/"+OptionScreen.getWordnum()+"\nFalse: "+falseCount+"/5");
            		isGameOver();
                	if(index <OptionScreen.getWordnum()) {
                		randomChoice();
                		dynamicLabel();
                	}
                	timeLable.setPause(false);
                	 window.remove();
                 }
                 @Override
                 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                     return true;
                 }
             });
    	}
        

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
        	timeLable.setPause(true);
            //pop up
            Window window = new Window("Question ?", MyMainGame.gameSkin);
	        window.setSize(350f, 150.0f);
	        window.setPosition(70, 220);
	        window.getTitleLabel().setAlignment(Align.center);
	        
	        Label label = new Label("               Would you like to exit ?", MyMainGame.gameSkin);
	        window.add(label).padTop(20);
	  
	        window.row();
	        TextButton textButton1 = new TextButton("Resume", MyMainGame.gameSkin, "small");
	        window.add(textButton1).expandX().padTop(10.0f).padRight(20);
	        
	        TextButton textButton = new TextButton("Exit", MyMainGame.gameSkin, "small");
	        window.add(textButton).expandX().padTop(10.0f).padRight(150f);
	        
             stage.addActor(window);
             textButton.addListener(new InputListener(){
                 @Override
                 public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	timeLable.setEnd(true);
                 	textToSpeech.setEnd(true);
                    game.setScreen(new VocabScreen(game));
                 }
                 @Override
                 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                     return true;
                 }
             });
             textButton1.addListener(new InputListener(){
                 @Override
                 public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	 timeLable.setPause(false);
                	 window.remove();
                 }
                 @Override
                 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                     return true;
                 }
             });
            
            
            
            
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
    
   
	
	public void randomChoice() {
    	//clear before start
    	choiceVocabs.clear();
    	randomNumber.clear();
        rightVocab = allVocabs[index];
        choiceVocabs.add(rightVocab);
        do {
        	int r = (int) (Math.random()*OptionScreen.getWordnum());
        	//prevent add the same word
        	if(!choiceVocabs.contains(allVocabs[(r)])) {
        		choiceVocabs.add(allVocabs[(r)]);        	
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
	public void isGameOver() {
        if(index==OptionScreen.getWordnum()|| falseCount==OptionScreen.getFalseWordnum() ) {
        	//update best score
        	ReadVocabs.isUpdateBestScore(score);
        	int bestScore = ReadVocabs.getBestScore();
        	// kill time thread 
        	timeLable.setEnd(true);
        	if(index ==OptionScreen.getWordnum() && falseCount==0) {
        		
        		popUpEndGame("You Win\nYour score: "+score+" Best score: "+bestScore);
        	}else if(falseCount !=0 && falseCount!=OptionScreen.getFalseWordnum()) {
        		if(OptionScreen.isShowAnswer()) {
	        		String text = "";
	    	        for(int i=0;i<falseAnswer.size();i++) {
	    	        	if(i==falseAnswer.size()-1) {
	    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
	    	        	}else {
	    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
	    	        	}
	    	        }
	    	        popUpEndGame("You Win\n"+"Your score: "+score+"\nBest score: "+bestScore+"\n"+text);
        		}
        		//in case of don't want to show the answer
        		else
        			popUpEndGame("You Win"+"\nYour score: "+score+"\nBest score: "+bestScore);
        	}
        	else {
        		if(OptionScreen.isShowAnswer()) {
	        		String text = "";
	    	        for(int i=0;i<falseAnswer.size();i++) {
	    	        	if(i==falseAnswer.size()-1) {
	    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning();
	    	        	}else {
	    	        		text += falseAnswer.get(i).getWord()+"    "+ falseAnswer.get(i).getMeaning()+"\n";
	    	        	}
	    	        }
	    	        popUpEndGame("You Fail"+"\nYour score: "+score+"\nBest score: "+bestScore+"\n"+text);
        		}//don't want to show answer 
        		else {
        			popUpEndGame("You Fail\n"+"Your score: "+score+"\nBest score: "+bestScore);
        		}
        	}
        }
	}
	
	public void popUp(String text) {
		 //pop up
        Window window = new Window("Result", MyMainGame.gameSkin);
        window.setSize(300f, 150.0f);
        window.setPosition(110, 220);
        window.getTitleLabel().setAlignment(Align.center);
        
        Label label = new Label(text+" !!", MyMainGame.gameSkin);
        window.add(label).padTop(20);
  
        window.row();
        TextButton textButton = new TextButton("OK", MyMainGame.gameSkin, "small");
        window.add(textButton).expandX().padTop(25.0f);
         stage.addActor(window);
         textButton.addListener(new InputListener(){
             @Override
             public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                window.remove();
             }
             @Override
             public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                 return true;
             }
         });
		
	}
	
	
	public void popUpEndGame(String text) {
		 //pop up end game
        Window window = new Window("Result", MyMainGame.gameSkin);
        window.setSize(500f, 490.0f);       
        window.getTitleLabel().setAlignment(Align.center);
        
        Label.LabelStyle label3Style = new Label.LabelStyle();
        final BitmapFont myFont3 = new BitmapFont(Gdx.files.local("Font/ltimSmall.fnt"));
        label3Style.font = myFont3;
        label3Style.fontColor = Color.BLACK;
        
        Label label = new Label(text, label3Style);
        label.setAlignment(java.awt.Label.CENTER);
    
        window.add(label);
        
  
        window.row();
        TextButton textButton = new TextButton("OK", MyMainGame.gameSkin, "small");
        window.add(textButton).expandX().padTop(25.0f);
        stage.addActor(window);
         textButton.addListener(new InputListener(){
             @Override
             public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
             	
            	textToSpeech.setEnd(true);
            	game.setScreen(new VocabScreen(game));
             }
             @Override
             public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                 return true;
             }
         });
		
	}

}