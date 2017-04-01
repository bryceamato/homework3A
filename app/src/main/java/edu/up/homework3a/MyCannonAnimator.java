package edu.up.homework3a;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by bryce on 3/31/2017.
 */

public class MyCannonAnimator implements Animator
{

    @Override
    public int interval() {
        return 0;
    }

    @Override
    public int backgroundColor()
    {
        return 0;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
