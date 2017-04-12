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
    private int wind;
    private boolean go = false;
    private CannonBall ball;
    private Canvas canvas;
    private Rect cannon = new Rect(75, 900, 125, 1100);
    private Paint target1ColorsRed = new Paint();
    private Paint target2ColorsRed = new Paint();
    private Paint targetColorsWhite = new Paint();
    private Paint wheelColor = new Paint();
    private int targetCount = 0;
    private int scoreCount = 0;
    private int score = 0;
    private Target target1;
    private Target target2;
    private Target target3;
    private Target target4;
    private Target target5;
    private Target target6;
    private Target target7;
    private Target target8;


    @Override
    public int interval() {
        return 70;
    }

    public int getScore()
    {
        return this.score;
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

    //sets wind speed in x direction based on the wind seekbar
    public void setWind(int initWind)
    {
        this.wind = initWind;
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
        ++targetCount;

        //Draw all of the elements
        ball = new CannonBall(175, 100, 1000, ballAngle, wind);
        ball.drawMe(canvas, count);
        this.drawCannon();
        target1 = new Target(1800f, 150f, 100f, target1ColorsRed);
        target2 = new Target(1800f, 950f, 100f, target2ColorsRed);

        targetColorsWhite.setColor(Color.WHITE);
        target3 = new Target(1800f, 150f, 75f, targetColorsWhite);
        target4 = new Target(1800f, 950f, 75f, targetColorsWhite);

        target5 = new Target(1800f, 150f, 50f, target1ColorsRed);
        target6 = new Target(1800f, 950f, 50f, target2ColorsRed);

        target7 = new Target(1800f, 150f, 25f, targetColorsWhite);
        target8 = new Target(1800f, 950f, 25f, targetColorsWhite);

        wheelColor.setColor(0xFFA0522D);
        canvas.drawCircle(100f, 1050f, 25f, wheelColor);

        //draws the targets on the animation canvas
        target1.drawMe(canvas, this.targetCount);
        target2.drawMe(canvas, this.targetCount);
        target3.drawMe(canvas, this.targetCount);
        target4.drawMe(canvas, this.targetCount);
        target5.drawMe(canvas, this.targetCount);
        target6.drawMe(canvas, this.targetCount);
        target7.drawMe(canvas, this.targetCount);
        target8.drawMe(canvas, this.targetCount);

        //When the targets get to 1000, start over
        if(target1.getXPos() == 1000)
        {
            this.targetCount = 0;
        }

        //Did the ball strike a target?
        if (target1.containsPoint(ball.getX(), ball.getY()))
        {
            //The top target was hit
            target1ColorsRed.setColor(Color.GREEN);
            ++scoreCount;
            //Keeps track of score, there is a score count so that the score isn't incremented every time
            //tick is called
            if(scoreCount == 1)
            {
                ++score;
            }
        } else
        {
            target1ColorsRed.setColor(Color.RED);
        }
        if (target2.containsPoint(ball.getX(), ball.getY()))
        {
            //The bottom target was hit
            target2ColorsRed.setColor(Color.GREEN);
            ++scoreCount;
            //Keeps track of score, there is a score count so that the score isn't incremented every time
            //tick is called
            if(scoreCount == 1)
            {
                ++score;
            }
        } else
        {
            target2ColorsRed.setColor(Color.RED);
        }
    }


    public void drawCannon()
    {
        //draws and rotates the cannon to correspond with the current angle
        canvas.save();
        float degreeAngle = (float)(90 - angle);
        canvas.rotate(degreeAngle, 100, 1000);
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
        scoreCount = 0;
    }

    @Override
    public void onTouch(MotionEvent event) {

    }
}
