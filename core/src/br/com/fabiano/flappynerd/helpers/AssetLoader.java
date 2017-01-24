package br.com.fabiano.flappynerd.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by F0113091 on 20/01/2017.
 */

public class AssetLoader {
    public static Preferences prefs;

    public static Texture texture, logoTexture;
    public static TextureRegion logo, zbLogo,
            bg, grass,
            bird, birdDown, birdUp,
            skullUp, skullDown, bar,
            playButtonUp, playButtonDown;

    public static Animation birdAnimation;

    public static Sound dead, flap, coin;

    public static BitmapFont font, shadow;

    public static void load() {
        Gdx.app.debug("AssetLoader", "load()");
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        //fromX, fromY, toX, toY top-left -> bottom-right
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        // sem flip aqui. Pois já carrega ao contrário
        skullUp = new TextureRegion(texture, 192, 0, 24, 14);

        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        // Caveira do cano de baixo. Inverte a imagem
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.getData().setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void dispose() {
        Gdx.app.debug("AssetLoader", "dispose()");
        // We must dispose of the texture when we are finished.
        texture.dispose();
        logoTexture.dispose();

        // sons
        dead.dispose();
        flap.dispose();
        coin.dispose();

        // fontes
        font.dispose();
        shadow.dispose();
    }

    /**
     * Recebe um int e atribui à pontuação mais alta
     * 
     * @param val
     */
    public static void setHighScore(int val) {
        Gdx.app.debug("AssetLoader", "setHighScore()");
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    /**
     * Obtém a pontuação mais alta
     *
     * @return
     */
    public static int getHighScore() {
        Gdx.app.debug("AssetLoader", "getHighScore()");
        return prefs.getInteger("highScore");
    }
}
