package br.com.fabiano.flappynerd.TweenAccessors;

import com.badlogic.gdx.Gdx;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by Fabia on 24/01/2017.
 */

public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        Gdx.app.debug("ValueAccessor", "getValues()");
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        Gdx.app.debug("ValueAccessor", "setValues()");
        target.setValue(newValues[0]);
    }

}