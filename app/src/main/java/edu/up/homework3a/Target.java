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


    public Target(float initX, float initY, float initRad, Paint initPaint)
    {
        this.xPos = initX;
        this.yPos = initY;
        this.radius = initRad;
        this.myPaint = initPaint;
    }

    public float getXPos()
    {
        return this.xPos;
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
        this.xPos = xPos - (count * 5);
        g.drawCircle(this.xPos, this.yPos, this.radius, this.myPaint);

    }
}
