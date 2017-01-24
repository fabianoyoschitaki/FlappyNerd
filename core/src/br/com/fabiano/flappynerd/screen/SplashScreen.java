package br.com.fabiano.flappynerd.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import br.com.fabiano.flappynerd.FlappyNerdGame;
import br.com.fabiano.flappynerd.TweenAccessors.SpriteAccessor;
import br.com.fabiano.flappynerd.helpers.AssetLoader;

/**
 * Created by Fabia on 24/01/2017.
 */

public class SplashScreen implements Screen {
    private TweenManager manager;
    private SpriteBatch batcher;
    private Sprite sprite;
    private FlappyNerdGame game;

    public SplashScreen(FlappyNerdGame game) {
        Gdx.app.debug("SplashScreen", "Construtor()");
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.debug("SplashScreen", "show()");
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1, 1, 1, 0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .7f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
                - (sprite.getHeight() / 2));
        setupTween();
        batcher = new SpriteBatch();
    }

    private void setupTween() {
        Gdx.app.debug("SplashScreen", "setupTween()");
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new GameScreen());
            }
        };

        Tween.to(sprite, SpriteAccessor.ALPHA, .8f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(manager);
    }

    @Override
    public void render(float delta) {
        Gdx.app.debug("SplashScreen", "render()");
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("SplashScreen", "resize()");
    }

    @Override
    public void hide() {
        Gdx.app.debug("SplashScreen", "hide()");
    }

    @Override
    public void pause() {
        Gdx.app.debug("SplashScreen", "pause()");
    }

    @Override
    public void resume() {
        Gdx.app.debug("SplashScreen", "resume()");
    }

    @Override
    public void dispose() {
        Gdx.app.debug("SplashScreen", "dispose()");
    }

}
