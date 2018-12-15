package com.mygdx.game.screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyMainGame;

public class VocabScreen extends PrototypeScreen {
	
    public VocabScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        //background
        Texture texture = new Texture(Gdx.files.internal("img/cover3.jpg"));
        Image image1 = new Image(texture);
        image1.setPosition(0,0);
        image1.setSize(500,500);
        stage.addActor(image1);

        TextButton bodyButton, animalButton, careerButton, foodAndBeverageButton, clothesButton,
        dailyButton, placeButton, sportButton, thingButton, toeicButton;

        bodyButton = new TextButton("Body", MyMainGame.gameSkin);
        bodyButton.setSize(240,70);
        bodyButton.setPosition(0,370);

        animalButton = new TextButton("Animal", MyMainGame.gameSkin);
        animalButton.setSize(240,70);
        animalButton.setPosition(0,295);

        careerButton = new TextButton("Career", MyMainGame.gameSkin);
        careerButton.setSize(240,70);
        careerButton.setPosition(0,220);

        foodAndBeverageButton = new TextButton("Food", MyMainGame.gameSkin);
        foodAndBeverageButton.setSize(240,70);
        foodAndBeverageButton.setPosition(0,145);


        clothesButton = new TextButton("Clothes", MyMainGame.gameSkin);
        clothesButton.setSize(240,70);
        clothesButton.setPosition(0,70);
        
        dailyButton = new TextButton("Routine", MyMainGame.gameSkin);
        dailyButton.setSize(240,70);
        dailyButton.setPosition(240,370);
        
        placeButton = new TextButton("Place", MyMainGame.gameSkin);
        placeButton.setSize(240,70);
        placeButton.setPosition(240,295);
        
        sportButton = new TextButton("Sport", MyMainGame.gameSkin);
        sportButton.setSize(240,70);
        sportButton.setPosition(240,220);
        
        thingButton = new TextButton("Thing", MyMainGame.gameSkin);
        thingButton.setSize(240,70);
        thingButton.setPosition(240,145);

        toeicButton = new TextButton("TOEIC", MyMainGame.gameSkin);
        toeicButton.setSize(240,70);
        toeicButton.setPosition(240,70);
        
        bodyButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GuessingWordsScreen(game, "bodies"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        animalButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GuessingWordsScreen(game, "animals"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        careerButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GuessingWordsScreen(game, "careers"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        foodAndBeverageButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GuessingWordsScreen(game, "foods"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        
        clothesButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "clothes"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        dailyButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "daily"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        placeButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "Place"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        sportButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "sports"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        thingButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "Things"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        toeicButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	 game.setScreen(new GuessingWordsScreen(game, "TOEIC"));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(bodyButton);
        stage.addActor(animalButton);
        stage.addActor(careerButton);
        stage.addActor(foodAndBeverageButton);
        stage.addActor(clothesButton);
        stage.addActor(dailyButton);
        stage.addActor(placeButton);
        stage.addActor(sportButton);
        stage.addActor(thingButton);
        stage.addActor(toeicButton);
        
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
