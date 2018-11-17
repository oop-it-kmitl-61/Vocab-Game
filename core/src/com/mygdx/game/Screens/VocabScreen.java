package com.mygdx.game.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

        bodyButton = new TextButton("Body", MyMainGame.gameSkin,"small");
        bodyButton.setSize(150,65);
        bodyButton.setPosition(90,370);

        animalButton = new TextButton("Animal", MyMainGame.gameSkin,"small");
        animalButton.setSize(150,65);
        animalButton.setPosition(90,295);

        careerButton = new TextButton("Career", MyMainGame.gameSkin,"small");
        careerButton.setSize(150,65);
        careerButton.setPosition(90,220);

        foodAndBeverageButton = new TextButton("Food", MyMainGame.gameSkin,"small");
        foodAndBeverageButton.setSize(150,65);
        foodAndBeverageButton.setPosition(90,145);


        clothesButton = new TextButton("Clothes", MyMainGame.gameSkin,"small");
        clothesButton.setSize(150,65);
        clothesButton.setPosition(90,70);
        
        dailyButton = new TextButton("Routine", MyMainGame.gameSkin,"small");
        dailyButton.setSize(150,65);
        dailyButton.setPosition(280,370);
        
        placeButton = new TextButton("Place", MyMainGame.gameSkin,"small");
        placeButton.setSize(150,65);
        placeButton.setPosition(280,295);
        
        sportButton = new TextButton("Sport", MyMainGame.gameSkin,"small");
        sportButton.setSize(150,65);
        sportButton.setPosition(280,220);
        
        thingButton = new TextButton("Thing", MyMainGame.gameSkin,"small");
        thingButton.setSize(150,65);
        thingButton.setPosition(280,145);

        toeicButton = new TextButton("TOEIC", MyMainGame.gameSkin,"small");
        toeicButton.setSize(150,65);
        toeicButton.setPosition(280,70);
        
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
		game.setScreen(new TitleScreen(game));
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return super.keyUp(keycode);
	}

}
