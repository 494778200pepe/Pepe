package com.pepe.animation;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class Demo1Act extends Activity implements OnClickListener {

    private static final String TAG = "Demo1Act";

    private static final long DURATION = 2000;

    private CheckBox cbFromXml;
    private Animation animation1, animation2, animation3, animation4;
    private AnimationSet animationSet;
    private Interpolator interpolator;
    private int selectedInterpolator = 0;
    private AlertDialog interpolationDialog;
    private TextView textView;

    private RadioButton rbSequence, rbSimultaneous;

    private long startOffset = 0;

    private String[] interpolators = { "Accelerate", "AccelerateDecelerator",
            "Anticipate", "OverShoot", "AnticipateOvershoot" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo1);
        initButtons();
        cbFromXml = (CheckBox) findViewById(R.id.checkBox1);
        cbFromXml.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setupAnimation();
            }
        });
        interpolator = new AccelerateInterpolator();
        textView = (TextView) findViewById(R.id.textView);
        rbSequence = (RadioButton) findViewById(R.id.radioButton1);
        rbSequence.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    rbSimultaneous.setChecked(false);
                    startOffset = DURATION;
                }
            }
        });

        rbSimultaneous = (RadioButton) findViewById(R.id.radioButton2);
        rbSimultaneous.setChecked(true);
        rbSimultaneous
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if (isChecked) {
                            rbSequence.setChecked(false);
                            startOffset = 0;
                        }
                    }
                });
        setupAnimation();
    }

    private void initButtons() {
        int[] btnIds = { R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6 };
        for (int btnId : btnIds) {
            Button button = (Button) findViewById(btnId);
            button.setOnClickListener(this);
        }
    }


    private void setupAnimation() {
        if (cbFromXml.isChecked()) {
            animation1 = AnimationUtils.loadAnimation(Demo1Act.this,
                    R.anim.transform1);
            animation2 = AnimationUtils.loadAnimation(Demo1Act.this,
                    R.anim.transform2);
            animation3 = AnimationUtils.loadAnimation(Demo1Act.this,
                    R.anim.transform3);
            animation4 = AnimationUtils.loadAnimation(Demo1Act.this,
                    R.anim.transform4);
        } else {
            animation1 = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,50,50);
            animation1.setDuration(DURATION);
            animation1.setRepeatCount(1);
            animation1.setInterpolator(interpolator);
            animation1.setRepeatMode(Animation.REVERSE);

            animation2 = new RotateAnimation(0, 180, 50, 50);
            animation2.setDuration(DURATION);
            animation2.setRepeatCount(1);
            animation2.setInterpolator(interpolator);
            animation2.setRepeatMode(Animation.REVERSE);

            animation3 = new TranslateAnimation(0, 100, 0, 0);
            animation3.setDuration(DURATION);
            animation3.setRepeatCount(1);
            animation3.setInterpolator(interpolator);
            animation3.setRepeatMode(Animation.REVERSE);

            animation4 = new AlphaAnimation(1.0f, 0f);
            animation4.setDuration(DURATION);
            animation4.setRepeatCount(1);
            animation4.setInterpolator(interpolator);
            animation4.setRepeatMode(Animation.REVERSE);

            animationSet = new AnimationSet(false);
            animationSet.addAnimation(animation1);
            animationSet.addAnimation(animation2);
            animationSet.addAnimation(animation3);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                animation2.setStartOffset(0);
                v.startAnimation(animation1);
                break;
            case R.id.button2:
                animation2.setStartOffset(0);
                v.startAnimation(animation2);
                break;
            case R.id.button3:
                animation2.setStartOffset(0);
                v.startAnimation(animation3);
                break;
            case R.id.button4:
                animation2.setStartOffset(0);
                v.startAnimation(animation4);
                break;
            case R.id.button5:
                animation2.setStartOffset(startOffset);
                animation3.setStartOffset(startOffset * 2);
                v.startAnimation(animationSet);
                break;
            case R.id.button6:
                interpolationDialog = new AlertDialog.Builder(this)
                        .setTitle("Interpolators")
                        .setSingleChoiceItems(interpolators, selectedInterpolator,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        selectedInterpolator = which;
                                    }
                                })
                        .setPositiveButton("Sure",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        Log.d(TAG, "Select Which : " + which);
                                        switch (selectedInterpolator) {
                                            case 0:
                                                interpolator = new AccelerateInterpolator();
                                                break;
                                            case 1:
                                                interpolator = new AccelerateDecelerateInterpolator();
                                                break;
                                            case 2:
                                                interpolator = new AnticipateInterpolator();
                                                break;
                                            case 3:
                                                interpolator = new OvershootInterpolator();
                                                break;
                                            case 4:
                                                interpolator = new AnticipateOvershootInterpolator();
                                                break;
                                        }
                                        ;
                                        textView.setText(interpolators[selectedInterpolator]);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        interpolationDialog.dismiss();
                                    }
                                }).create();
                interpolationDialog.show();
                break;
        }
    }
}
