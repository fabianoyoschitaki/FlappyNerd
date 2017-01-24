package br.com.fabiano.flappynerd.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by F0113091 on 20/01/2017.
 */

public class Pipe extends Scrollable {
    private Random r;

    private Rectangle skullUp, skullDown, barUp, barDown;

    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;
    private float groundY;

    private boolean isScored = false;

    // When Pipe's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        Gdx.app.debug("Pipe", "Construtor()");
        // Initialize a Random object for Random number generation
        r = new Random();

        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barUp = new Rectangle();
        barDown = new Rectangle();

        this.groundY = groundY;
    }

    @Override
    public void update(float delta) {
        Gdx.app.debug("Pipe", "update()");
        // Call the update method in the superclass (Scrollable)
        super.update(delta);

        // The set() method allows you to set the top left corner's x, y
        // coordinates,
        // along with the width and height of the rectangle
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));

        // Our skull width is 24. The bar is only 22 pixels wide. So the skull
        // must be shifted by 1 pixel to the left (so that the skull is centered
        // with respect to its bar).

        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);

    }

    @Override
    public void reset(float newX) {
        Gdx.app.debug("Pipe", "reset()");
        // Call the reset method in the superclass (Scrollable)
        super.reset(newX);
        // Change the height to a random number
        height = r.nextInt(90) + 15;

        // volta a ser "pontuável"
        isScored = false;
    }

    public boolean isScored() {
        Gdx.app.debug("Pipe", "isScored()");
        return isScored;
    }

    public void setScored(boolean b) {
        Gdx.app.debug("Pipe", "setScored()");
        isScored = b;
    }

    public Rectangle getSkullUp() {
        Gdx.app.debug("Pipe", "getSkullUp()");
        return skullUp;
    }

    public Rectangle getSkullDown() {
        Gdx.app.debug("Pipe", "getSkullDown()");
        return skullDown;
    }

    public Rectangle getBarUp() {
        Gdx.app.debug("Pipe", "getBarUp()");
        return barUp;
    }

    public Rectangle getBarDown() {
        Gdx.app.debug("Pipe", "getBarDown()");
        return barDown;
    }

    public boolean collides(Bird bird) {
        Gdx.app.debug("Pipe", "collides()");
        //we begin by checking if the position.x is less than bird.getX + bird.getWidth,
        // because otherwise, collision is impossible. This is a very cheap check (it does not take much toll on performance).
        // Most of the time, this condition will fail, and we will not have to perform the more expensive checks.
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                 || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                 || Intersector.overlaps(bird.getBoundingCircle(), skullUp)
                 || Intersector.overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }

    public void onRestart(float x, float scrollSpeed) {
        Gdx.app.debug("Pipe", "onRestart()");
        velocity.x = scrollSpeed;
        reset(x);
    }
}
