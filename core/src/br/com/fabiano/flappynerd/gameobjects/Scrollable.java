package br.com.fabiano.flappynerd.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by F0113091 on 20/01/2017.
 */

public class Scrollable {
    // Protected is similar to private, but allows inheritance by subclasses.
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        Gdx.app.debug("Scrollable", "Construtor()");
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    public void update(float delta) {
        Gdx.app.debug("Scrollable", "update()");
        position.add(velocity.cpy().scl(delta));

        // If the Scrollable object is no longer visible:
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        Gdx.app.debug("Scrollable", "reset()");
        position.x = newX;
        isScrolledLeft = false;
    }

    public void stop() {
        Gdx.app.debug("Scrollable", "stop()");
        velocity.x = 0;
    }

    // Getters for instance variables
    public boolean isScrolledLeft() {
        Gdx.app.debug("Scrollable", "isScrolledLeft()");
        return isScrolledLeft;
    }

    public float getTailX() {
        Gdx.app.debug("Scrollable", "getTailX()");
        return position.x + width;
    }

    public float getX() {
        Gdx.app.debug("Scrollable", "getX()");
        return position.x;
    }

    public float getY() {
        Gdx.app.debug("Scrollable", "getY()");
        return position.y;
    }

    public int getWidth() {
        Gdx.app.debug("Scrollable", "getWidth()");
        return width;
    }

    public int getHeight() {
        Gdx.app.debug("Scrollable", "getHeight()");
        return height;
    }
}
