package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.Random;



public class MyGame extends ApplicationAdapter  {
    ShapeRenderer shapeRenderer;
    SpriteBatch batch;
    BitmapFont font;
    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        shapeRenderer = new ShapeRenderer();

    }


    @Override
    public void resize(int width, int height) {

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //background vocab
        shapeRenderer.setColor(Color.ROYAL);
        shapeRenderer.rect(0,0,500,250);

        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(170,350,150,50);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(170,150,150,50);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch, "Play", 230, 380);
        font.draw(batch, "Exit", 230, 180);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(2, 2);
        batch.end();
    }

    @Override
    public void dispose() {
        // TODO: Dispose of our ShapeRenderer
        batch.dispose();
        font.dispose();
        super.dispose();
        shapeRenderer.dispose();
    }
}