package edu.up.homework3a;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by bryce on 3/31/2017.
 */

public class MyCannonAnimator implements Animator
{
    private int count = 0;
    private double ballAngle;
    private double angle;
    private boolean go = false;
    private CannonBall ball;
    private Canvas canvas;
    private Rect cannon = new Rect(75, 1100, 125, 1300);
    private Paint target1ColorsRed = new Paint();
    private Paint targetColorsWhite = new Paint();
    private Paint target2ColorsRed = new Paint();
    private Paint wheelColor = new Paint();


    @Override
    public int interval() {
        return 70;
    }

    @Override
    public int backgroundColor()
    {
        return 0xFF000000;
    }

    @Override
    public boolean doPause()
    {
        if(go)
        {
            return false;
        }
        else{   return true;    }
    }

    @Override
    public boolean doQuit()
    {
       return false;
    }

    /*
    This method sets the angle for the cannon to be drawn- important note is the cannon will be
    redrawn whenever this angle is changed
     */
    public void setAngle(double initAngle)
    {
        this.angle = initAngle;
    }

    /*
    This method sets the angle for the ball to be drawn- I made this a second class so it is only called
    on startup and whenever the fire button is pressed. This makes it so the ball will not change angles
    in the middle of the air after being shot.
     */
    public void setBallAngle(double initAngle)
    {
        this.ballAngle = initAngle;
    }

    /*
    Tick method called every time the animation ticks so it will redraw the ball to its current x and y
     */
    @Override
    public void tick(Canvas canvas)
    {
        this.canvas = canvas;
        ++count;

        //Draw all of the elements
        ball = new CannonBall(175, 100, 1200, ballAngle);
        ball.drawMe(canvas, count);
        this.drawCannon();
        this.drawTargets();

        //Did the ball strike a target?
        if ((ball.getX() >= 1700) && (ball.getX() <= 1900) && (ball.getY() >= 50) && (ball.getY() <= 250))
        {
            //The top target was hit
            target1ColorsRed.setColor(Color.GREEN);
        } else
        {
            target1ColorsRed.setColor(Color.RED);
        }
        if ((ball.getX() >= 1700) && (ball.getX() <= 1900) && (ball.getY() >= 900) && (ball.getY() <= 1100))
        {
            //The bottom target was hit
            target2ColorsRed.setColor(Color.GREEN);
        } else
        {
            target2ColorsRed.setColor(Color.RED);
        }
    }

    public void drawTargets()
    {
        //Draws the 2 targets in a bullseye design

        canvas.drawCircle(1800f, 150f, 100f, target1ColorsRed);
        canvas.drawCircle(1800f, 1000f, 100f, target2ColorsRed);

        targetColorsWhite.setColor(Color.WHITE);
        canvas.drawCircle(1800f, 150f, 75f, targetColorsWhite);
        canvas.drawCircle(1800f, 1000f, 75f, targetColorsWhite);

        canvas.drawCircle(1800f, 150f, 50f, target1ColorsRed);
        canvas.drawCircle(1800f, 1000f, 50f, target2ColorsRed);

        canvas.drawCircle(1800f, 150f, 25f, targetColorsWhite);
        canvas.drawCircle(1800f, 1000f, 25f, targetColorsWhite);

        wheelColor.setColor(0xFFA0522D);
        canvas.drawCircle(100f, 1250f, 25f, wheelColor);
    }

    public void drawCannon()
    {
        //draws and rotates the cannon to correspond with the current angle
        canvas.save();
        float degreeAngle = (float)(90 - angle);
        canvas.rotate(degreeAngle, 100, 1200);
        Paint myCannonPaint = new Paint();
        myCannonPaint.setColor(0xFF655E5E);
        canvas.drawRect(cannon, myCannonPaint);
        canvas.restore();
    }
    public void setFire(boolean fire)
    {
        //this is called when the fire button is pressed, starts count at 0
        count = 0;
        this.go = fire;
    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
