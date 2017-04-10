package edu.up.homework3a;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/*
    For homework 3 part B I have included the following additions:

     -Wind Speed
     -Ability to modify wind speed
     -Score counter that will add exactly 1 point every time

 */

/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 *
 * TODO for homework 3B make targets move back and forth--- may have to make new class to do so (8)
 *
 * TODO for 3b make orange circle "explosion" on cannon (7)
 *
 * TODO ball rolling(10)
 *
 * TODO maybe make a green bottom representing grass and have ball roll on that
 *
 * @author Andrew Nuxoll
 * @version September 2012
 *
 */
public class CannonMainActivity extends Activity {

    /**
     * creates an AnimationCanvas containing a MyCanvasAnimator.
     */

    private SeekBar angleSeekBar;
    private TextView infoText;
    private Button fireButton;
    private double angle;
    private int wind;
    private MyCannonAnimator myAnim;
    private AnimationCanvas myCanvas;
    private LinearLayout mainLayout;
    private SeekBar windSeekBar;
    private TextView scoreTextView;
    private TextView windTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        infoText = (TextView)this.findViewById(R.id.rightTextView);
        angleSeekBar = (SeekBar)this.findViewById(R.id.angleSeekBar);
        fireButton = (Button)this.findViewById(R.id.fireButton);
        windSeekBar = (SeekBar)this.findViewById(R.id.windSeekBar);
        scoreTextView = (TextView)this.findViewById(R.id.scoreTextView);
        windTextView = (TextView)this.findViewById(R.id.windTextView);

        angleSeekBar.setProgress(45);
        windSeekBar.setProgress(0);

        // Create an animation canvas and place it in the main layout
        myAnim = new MyCannonAnimator();
        myCanvas = new AnimationCanvas(this, myAnim);
        mainLayout = (LinearLayout) this
                .findViewById(R.id.animationLayout);
        mainLayout.addView(myCanvas);



        myAnim.setAngle(45);
        this.angle = 45;
        this.wind = 0;
        //myAnim.setFire(false);

        fireButton.setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                myAnim.setFire(true);
                myAnim.setWind(wind);
                myAnim.setBallAngle(Math.toRadians(angle));
                scoreTextView.setText("Score: " + myAnim.getScore());
                return true;
            }
        });

        angleSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {
                        angle = progress;
                        infoText.setText((int)angle + "\nDegrees");
                        myAnim.setAngle(angle);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                }
        );

        windSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener()
                {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {
                        wind = progress;
                        windTextView.setText(wind + "\nMeters per Second");
                        //myAnim.setWind(wind);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {

                    }
                }
        );
    }

}
