package br.com.fabiano.flappynerd.TweenAccessors;

import com.badlogic.gdx.Gdx;

/**
 * Created by Fabia on 24/01/2017.
 */

public class Value {
    private float val = 1;

    public float getValue() {
        Gdx.app.debug("Value", "getValue()");
        return val;
    }

    public void setValue(float newVal) {
        Gdx.app.debug("Value", "setValue()");
        val = newVal;
    }
}
