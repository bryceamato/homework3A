package edu.up.homework3a;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
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
    private MyCannonAnimator myAnim;
    private AnimationCanvas myCanvas;
    private LinearLayout mainLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);

        infoText = (TextView)this.findViewById(R.id.rightTextView);
        angleSeekBar = (SeekBar)this.findViewById(R.id.angleSeekBar);
        fireButton = (Button)this.findViewById(R.id.fireButton);

        angleSeekBar.setProgress(45);

        // Create an animation canvas and place it in the main layout
        myAnim = new MyCannonAnimator();
        myCanvas = new AnimationCanvas(this, myAnim);
        mainLayout = (LinearLayout) this
                .findViewById(R.id.animationLayout);
        mainLayout.addView(myCanvas);

        myAnim.setAngle(45);
        this.angle = 45;
        myAnim.setFire(false);

        fireButton.setOnTouchListener(new Button.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                myAnim.setFire(true);
                myAnim.setBallAngle(Math.toRadians(angle));
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
    }

}
