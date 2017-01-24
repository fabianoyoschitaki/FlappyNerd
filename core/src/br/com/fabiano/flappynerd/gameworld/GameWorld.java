package br.com.fabiano.flappynerd.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import br.com.fabiano.flappynerd.gameobjects.Bird;
import br.com.fabiano.flappynerd.gameobjects.ScrollHandler;
import br.com.fabiano.flappynerd.helpers.AssetLoader;

/**
 * Created by F0113091 on 17/01/2017.
 */

public class GameWorld {
    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private float runTime = 0;
    public int midPointY;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY) {
        Gdx.app.debug("GameWorld", "Construtor()");
        currentState = GameState.MENU;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 136, 11);
    }

    public void update(float delta) {
        Gdx.app.debug("GameWorld", "update()");
        runTime += delta;
        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;

            case RUNNING:
                updateRunning(delta);
                break;

            default:
                break;
        }
    }

    private void updateReady(float delta) {
        Gdx.app.debug("GameWorld", "updateReady()");
        bird.updateReady(runTime);
        scroller.updateReady(delta);
    }

    public void updateRunning(float delta) {
        Gdx.app.debug("GameWorld", "updateRunning()");

        // Add a delta cap so that if our game takes too long
        // to update, we will not break our collision detection.
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            //se morreu, verifica se foi record.
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public Bird getBird() {
        Gdx.app.debug("GameWorld", "getBird()");
        return bird;
    }

    public int getMidPointY() {
        Gdx.app.debug("GameWorld", "getMidPointY()");
        return midPointY;
    }

    public ScrollHandler getScroller() {
        Gdx.app.debug("GameWorld", "getScroller()");
        return scroller;
    }

    public int getScore() {
        Gdx.app.debug("GameWorld", "getScore()");
        return score;
    }

    public void addScore(int increment) {
        Gdx.app.debug("GameWorld", "addScore()");
        score += increment;
    }

    public void start() {
        Gdx.app.debug("GameWorld", "start()");
        currentState = GameState.RUNNING;
    }

    public void ready() {
        Gdx.app.debug("GameWorld", "ready()");
        currentState = GameState.READY;
    }

    public void restart() {
        Gdx.app.debug("GameWorld", "restart()");
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isReady() {
        Gdx.app.debug("GameWorld", "isReady()");
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        Gdx.app.debug("GameWorld", "isGameOver()");
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        Gdx.app.debug("GameWorld", "isHighScore()");
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        Gdx.app.debug("GameWorld", "isMenu()");
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        Gdx.app.debug("GameWorld", "isRunning()");
        return currentState == GameState.RUNNING;
    }
}
