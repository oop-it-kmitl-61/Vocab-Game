package com.mygdx.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.SoundTrack;
public class Test implements ApplicationListener{
	  private Stage stage;
	    private static Skin skin;
	    @Override
	    public void create() {
	        skin = new Skin(Gdx.files.internal("orange/skin/uiskin.json"));
	        stage = new Stage(new ScreenViewport());
	        Gdx.input.setInputProcessor(stage);
	        SoundTrack soundTrack = new SoundTrack();
	        Thread thread = new Thread(soundTrack);
	        thread.start();
	        Table rootTable = new Table(skin);
	        rootTable.setFillParent(true);
	        stage.addActor(rootTable);
	        rootTable.row();
//	        Texture texture = new Texture("orange/skin/orange-logo.png");
//	        TextureRegion region = new TextureRegion(texture);
//	        Drawable drawable = new TextureRegionDrawable(region);
	        Table table = new Table();
//	        table.setBackground(drawable);
	        
	        rootTable.row();
	        table = new Table();
	        ImageButtonStyle style ;
	        style =new ImageButtonStyle(skin.get("menu-left", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-help");
//	        style.imageDown = skin.getDrawable("image-help-down");
	        ImageButton imageButton = new ImageButton(style);
//	        table.add(imageButton);
	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-info");
//	        style.imageDown = skin.getDrawable("image-info-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-left");
//	        style.imageDown = skin.getDrawable("image-left-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-right");
//	        style.imageDown = skin.getDrawable("image-right-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-plus");
//	        style.imageDown = skin.getDrawable("image-plus-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-minus");
//	        style.imageDown = skin.getDrawable("image-minus-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-delete");
//	        style.imageDown = skin.getDrawable("image-delete-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-duplicate");
//	        style.imageDown = skin.getDrawable("image-duplicate-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
//	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-settings");
//	        style.imageDown = skin.getDrawable("image-settings-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        
	        style = new ImageButtonStyle(skin.get("menu-center", ImageButtonStyle.class));
	        style.imageUp = skin.getDrawable("image-sound");
	        style.imageChecked = skin.getDrawable("image-sound");
	        style.imageDown = skin.getDrawable("image-sound-down");
	        imageButton = new ImageButton(style);
	        rootTable.add(imageButton).padRight(300f);
	        
//	        style = new ImageButtonStyle(skin.get("menu-right", ImageButtonStyle.class));
//	        style.imageUp = skin.getDrawable("image-music-off");
//	        style.imageChecked = skin.getDrawable("image-music");
//	        style.imageDown = skin.getDrawable("image-music-down");
//	        imageButton = new ImageButton(style);
//	        table.add(imageButton);
//	        rootTable.add(table).padTop(10.0f).colspan(10).growX();
	    
	    }
	    @Override
	    public void render() {
	        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        System.out.println(3);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();
	    }

	    @Override
	    public void resize(int width, int height) {
	        stage.getViewport().update(width, height, true);
	    }

	    @Override
	    public void dispose() {
	        stage.dispose();
	        skin.dispose();
	    }

		@Override
		public void pause() {
			// TODO Auto-generated method stub
			System.out.println(1);
			
		}
		@Override
		public void resume() {
			// TODO Auto-generated method stub
			System.out.println(2);
		}
	
	    
}
