package com.mygdx.game.screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyMainGame;
import com.mygdx.game.SoundTrack;
public class OptionScreen extends PrototypeScreen{
	private static int wordnum=20, timernum=30, falseWordnum=5;
	private static boolean showAnswer=true;
	
	
    public OptionScreen(Game aGame) {
            game = aGame;
            stage = new Stage(new ScreenViewport());
            
            final Label.LabelStyle label1Style = new Label.LabelStyle();
            final BitmapFont myFont = new BitmapFont(Gdx.files.local("rainbow/skin/font-button-export.fnt"));
            label1Style.font = myFont;
            label1Style.fontColor = Color.WHITE;
            
            if(MyMainGame.isOptionState()) {
            	wordnum = MyMainGame.getWordnum();
            	timernum = MyMainGame.getTimernum();
            	falseWordnum = MyMainGame.getFalseWordnum();
            	showAnswer = MyMainGame.isOptionState();
            }
            
            
            //background
            Texture texture = new Texture(Gdx.files.internal("img/optioncover.jpg"));
            Image image1 = new Image(texture);
            image1.setPosition(0,0);
            image1.setSize(500,500);
            stage.addActor(image1);
            
            
            Label wordlabel = new Label("Words.", label1Style);
            wordlabel.setPosition(30, 420);
            
            Label timelabel = new Label("Timer(sec).", label1Style);
            timelabel.setPosition(30, 360);
            
            Label falselabel = new Label("False \nWords.", label1Style);
            falselabel.setPosition(30, 240);
            
            Label answerlabel = new Label("Show the\nAnswers.", label1Style);
            answerlabel.setPosition(30, 135);
            
            Label soundlabel = new Label("Volume.", label1Style);
            soundlabel.setPosition(30, 70);
            
//            wordlabel.setFontScale(1.5f, 1.5f);
//            ImageTextButton wordTextButton = new ImageTextButton("Number of \n Words", MyMainGame.gameSkin, "");
            SelectBox<Integer> wordselectBox = new SelectBox<>(MyMainGame.gameSkin);
            wordselectBox.setItems(10,15, 20,25, 30,35, 40);
            wordselectBox.setSelected(wordnum);;
            wordselectBox.setSize(150, 80);
            wordselectBox.setPosition(320,420);
	        
	        SelectBox<Integer> timerselectBox = new SelectBox<>(MyMainGame.gameSkin);
	        timerselectBox.setItems(10,15, 20, 25, 30);
	        timerselectBox.setSelected(timernum);;
	        timerselectBox.setSize(150, 80);
	        timerselectBox.setPosition(320,330);
	        
	        SelectBox<Integer> falseselectBox = new SelectBox<>(MyMainGame.gameSkin);
	        falseselectBox.setItems(1,2, 3, 4, 5);
	        falseselectBox.setSelected(falseWordnum);;
	        falseselectBox.setSize(150, 80);
	        falseselectBox.setPosition(320,240);
	        
	        SelectBox<String> answerselectBox = new SelectBox<>(MyMainGame.gameSkin);
	        answerselectBox.setItems("Yes", "No");
	        if(showAnswer)
	        	answerselectBox.setSelectedIndex(0);
	        else
	        	answerselectBox.setSelectedIndex(1);
	        answerselectBox.setSize(170, 80);
	        answerselectBox.setPosition(310,150);
//	        Label timerlabel = new Label("Timer", MyMainGame.gameSkin);
//            wordlabel.setPosition(70, 310);
//            wordlabel.setFontScale(1.5f, 1.5f);
//	        
	        
	        //setting volume
	        final Slider slider = new Slider(0, 1, 0.01f, false, MyMainGame.gameSkin);
	        slider.setValue(0.04f);
	        slider.setPosition(320, 60);
	        slider.addListener(new ChangeListener() {
	            @Override
	            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
	               SoundTrack.setVolume(slider.getValue());
	            }
	        });
	        
            TextButton backButton, applyButton, resetButton;
            backButton = new TextButton("BACK", MyMainGame.gameSkin,"small");
            backButton.setSize(100,50);
            backButton.setPosition(340,10);
            
            resetButton = new TextButton("RESET", MyMainGame.gameSkin,"small");
            resetButton.setSize(100,50);
            resetButton.setPosition(200,10);
            
            applyButton = new TextButton("APPLY", MyMainGame.gameSkin,"small");
            applyButton.setSize(100,50);
            applyButton.setPosition(70,10);
            
            
            
            applyButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    wordnum = wordselectBox.getSelected();
                    timernum = timerselectBox.getSelected();
                    falseWordnum = falseselectBox.getSelected();
                    if(answerselectBox.getSelected().equals("Yes")) {
                    	showAnswer = true;
                    }else {
                    	showAnswer = false;
                    }
                    System.out.printf("%d %d %d\n", wordnum, timernum, falseWordnum);
                    System.out.println(showAnswer);
                    
                    SoundTrack.setVolume(slider.getValue());
                   	MyMainGame.setOption(wordnum, timernum, falseWordnum, showAnswer);
                   	MyMainGame.setOptionState(true);
                    
                    //popup
                    Window window = new Window("Inform", MyMainGame.gameSkin);
        	        window.setSize(300f, 150.0f);
        	        window.setPosition(110, 220);
        	        window.getTitleLabel().setAlignment(Align.center);
        	        
        	        Label label = new Label("Apply successfully !!", MyMainGame.gameSkin);
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
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            
            resetButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	wordnum=20; timernum=30; falseWordnum=5;showAnswer = true;
                	wordselectBox.setSelected(20);
                	timerselectBox.setSelected(30);
                	falseselectBox.setSelected(5);
                	answerselectBox.setSelectedIndex(0);
                	SoundTrack.setVolume(0.04f);
                	slider.setValue(0.04f);
                	
                	//pop up
                	 Window window = new Window("Inform", MyMainGame.gameSkin);
         	        window.setSize(300f, 150.0f);
         	        window.setPosition(110, 220);
         	        window.getTitleLabel().setAlignment(Align.center);
         	        
         	        Label label = new Label("Reset successfully !!", MyMainGame.gameSkin);
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
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            
            
            backButton.addListener(new InputListener(){
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new TitleScreen(game));
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            
            stage.addActor(soundlabel);
            stage.addActor(slider);
            stage.addActor(resetButton);
            stage.addActor(applyButton);
            stage.addActor(backButton);
            stage.addActor(answerselectBox);
            stage.addActor(falseselectBox);
            stage.addActor(timerselectBox);
            stage.addActor(wordlabel);
            stage.addActor(timelabel);
            stage.addActor(falselabel);
            stage.addActor(answerlabel);
            stage.addActor(wordselectBox);
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

	public static int getWordnum() {
		return wordnum;
	}

	public static int getTimernum() {
		return timernum;
	}

	public static int getFalseWordnum() {
		return falseWordnum;
	}

	public static boolean isShowAnswer() {
		return showAnswer;
	}

}
