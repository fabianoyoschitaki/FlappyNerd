package br.com.fabiano.flappynerd;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.fabiano.flappynerd.helpers.AssetLoader;
import br.com.fabiano.flappynerd.screen.GameScreen;
import br.com.fabiano.flappynerd.screen.SplashScreen;

public class FlappyNerdGame extends Game {

	@Override
	public void create() {
		Gdx.app.debug("FlappyNerdGame", "create()");
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		Gdx.app.debug("FlappyNerdGame", "dispose()");
		super.dispose();
		AssetLoader.dispose();
	}
}
