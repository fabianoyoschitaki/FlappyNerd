package br.com.fabiano.flappynerd.gameobjects;

/**
 * Created by F0113091 on 20/01/2017.
 */

public class Grass extends Scrollable {
    // When Grass's constructor is invoked, invoke the super (Scrollable)
    // constructor
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
}
