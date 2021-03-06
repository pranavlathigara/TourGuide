package tourguide.tourguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;


public class ToolTipGravityActivity extends ActionBarActivity {
    public TourGuide mTutorialHandler;
    public Activity mActivity;
    public static final String TOOLTIP_NUM = "tooltip_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Get parameters from main activity */
        Intent intent = getIntent();
        int tooltip_num = intent.getIntExtra(TOOLTIP_NUM, 1);

        super.onCreate(savedInstanceState);
        mActivity = this;
        int gravity;

        if (tooltip_num == 1) {
            setContentView(R.layout.activity_tooltip_gravity_i);
            gravity = Gravity.RIGHT | Gravity.BOTTOM;
        } else if (tooltip_num == 2) {
            setContentView(R.layout.activity_tooltip_gravity_ii);
            gravity = Gravity.LEFT | Gravity.BOTTOM;
        } else if (tooltip_num == 3) {
            setContentView(R.layout.activity_tooltip_gravity_iii);
            gravity = Gravity.LEFT | Gravity.TOP;
        } else {
            setContentView(R.layout.activity_tooltip_gravity_iv);
            gravity = Gravity.RIGHT | Gravity.TOP;
        }
        Button button = (Button)findViewById(R.id.button);

        ToolTip toolTip = new ToolTip().
                            title("Welcome!").
                            description("Click on Get Started to begin...").
                            backgroundColor(Color.parseColor("#2980b9")).
                            textColor(Color.parseColor("#FFFFFF")).
                            gravity(gravity).
                            shadow(true);

        mTutorialHandler = TourGuide.init(this).with(TourGuide.Technique.Click)
                .duration(700)
                .motionType(TourGuide.MotionType.ClickOnly)
                .toolTip(toolTip)
                .playOn(button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mTutorialHandler.cleanUp();
            }
        });
    }
}
