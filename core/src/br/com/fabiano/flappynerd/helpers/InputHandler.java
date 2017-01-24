package br.com.fabiano.flappynerd.helpers;

import com.badlogic.gdx.InputProcessor;

import br.com.fabiano.flappynerd.gameobjects.Bird;
import br.com.fabiano.flappynerd.gameworld.GameWorld;

/**
 * Created by F0113091 on 20/01/2017.
 */

public class InputHandler implements InputProcessor {
    private Bird myBird;

    //Our InputHandler needs a reference to our GameWorld object,
    // so that we can determine what the current GameState is and handle touches correctly.
    private GameWorld myWorld;

    // Ask for a reference to the Bird when InputHandler is created.
    public InputHandler(GameWorld myWorld) {
        // myBird now represents the gameWorld's bird.
        this.myWorld = myWorld;
        myBird = myWorld.getBird();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (myWorld.isReady()) {
            myWorld.start();
        }

        myBird.onClick();

        if (myWorld.isGameOver() || myWorld.isHighScore()) {
            // Reset all variables, go to GameState.READY
            myWorld.restart();
        }
        return true; // Return true to say we handled the touch.
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
