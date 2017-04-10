package edu.up.homework3a;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by bryce on 4/10/2017.
 */

public class Target
{
    private float xPos;
    private float yPos;
    private float radius;
    private Paint myPaint;
    private int factor = -5;
    boolean movingRight;
    private int delta = -5;


    public Target(float initX, float initY, float initRad, Paint initPaint)
    {
        this.xPos = initX;
        this.yPos = initY;
        this.radius = initRad;
        this.myPaint = initPaint;
    }

    public void setPaint(Paint newPaint)
    {
        this.myPaint = newPaint;
    }

    public void setRight(boolean direction)
    {
        this.movingRight = direction;
    }

    public boolean containsPoint(int ballX, int ballY)
    {
        float xDist = Math.abs(ballX - this.xPos);
        float yDist = Math.abs(ballY - this.yPos);
        int dist = (int)Math.sqrt(xDist*xDist + yDist*yDist);

        //give a little buffer
        return (dist < this.radius + 20);
    }

    public void drawMe(Canvas g, int count)
    {
        this.xPos = this.xPos + delta * count;
        if(this.xPos < 1700)
        {
            this.delta *= -1;
            this.xPos = 1700;
        }else if(this.xPos > 1810)
        {
            this.delta *= -1;
            this.xPos = 1810;
        }

        g.drawCircle(this.xPos, this.yPos, this.radius, this.myPaint);

    }
}
