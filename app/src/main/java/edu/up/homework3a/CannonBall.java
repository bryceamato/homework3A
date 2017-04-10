package edu.up.homework3a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static edu.up.homework3a.R.id.time;

/**
 * Created by bryce on 3/31/2017.
 */

public class CannonBall {
    private int speed;
    private int posX;
    private int posY;
    private double angle;
    private int wind;

    private int negOne = -1;

    private Paint cannonBallPaint = new Paint();

    public CannonBall(int initSpeed, int initPosX, int initPosY, double initAngle, int initWind)
    {
        this.posX = initPosX;
        this.posY = initPosY;
        this.angle = initAngle;
        this.speed = initSpeed;
        this.wind = initWind;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public void drawMe(Canvas g, int time) {
        this.cannonBallPaint.setColor(0xFF655E5E);

        this.posX = (int) (this.posX + ((this.speed * Math.cos(this.angle) * (time)) - (this.wind * time)));
        this.posY = (int) (this.posY + ((this.speed * Math.sin(this.angle) * negOne) * (time)) +
                (0.5 * 9.81 * (time) * (time)));

        g.drawCircle(this.posX, this.posY, 40, this.cannonBallPaint);
    }

}
