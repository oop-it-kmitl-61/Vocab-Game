package com.mygdx.game.Screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyMainGame;

public class VocabScreen implements Screen {
    private Stage stage;
    private Game game;

    public VocabScreen(Game aGame) {
        game = aGame;
        stage = new Stage(new ScreenViewport());

        //background
        Texture texture = new Texture(Gdx.files.internal("img/cover3.jpg"));
        Image image1 = new Image(texture);
        image1.setPosition(0,0);
        image1.setSize(500,500);
        stage.addActor(image1);

        TextButton natureAndWeatherButton, animalButton, familyButton, foodAndBeverageButton, backButton;

        natureAndWeatherButton = new TextButton("Bodies", MyMainGame.gameSkin,"small");
        natureAndWeatherButton.setSize(200,65);
        natureAndWeatherButton.setPosition(150,370);

        animalButton = new TextButton("Animals", MyMainGame.gameSkin,"small");
        animalButton.setSize(200,65);
        animalButton.setPosition(150,295);

        familyButton = new TextButton("Careers", MyMainGame.gameSkin,"small");
        familyButton.setSize(200,65);
        familyButton.setPosition(150,220);

        foodAndBeverageButton = new TextButton("Food & Beverage", MyMainGame.gameSkin,"small");
        foodAndBeverageButton.setSize(200,65);
        foodAndBeverageButton.setPosition(150,145);


        backButton = new TextButton("BACK", MyMainGame.gameSkin,"small");
        backButton.setSize(200,65);
        backButton.setPosition(150,70);

        animalButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new AnimalScreen(game));
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
        stage.addActor(natureAndWeatherButton);
        stage.addActor(animalButton);
        stage.addActor(familyButton);
        stage.addActor(foodAndBeverageButton);
        stage.addActor(backButton);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
}
