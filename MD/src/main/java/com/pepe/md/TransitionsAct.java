package com.pepe.md;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.pepe.md.transtions.AutoTransitionSample;
import com.pepe.md.transtions.ChangeTextSample;
import com.pepe.md.transtions.CustomTransitionSample;
import com.pepe.md.transtions.ExplodeAndEpicenterExample;
import com.pepe.md.transtions.ImageTransformSample;
import com.pepe.md.transtions.InterpolatorDurationStartDelaySample;
import com.pepe.md.transtions.ListFragment;
import com.pepe.md.transtions.PathMotionSample;
import com.pepe.md.transtions.RecolorSample;
import com.pepe.md.transtions.RotateSample;
import com.pepe.md.transtions.ScaleSample;
import com.pepe.md.transtions.ScenesSample;
import com.pepe.md.transtions.SlideSample;
import com.pepe.md.transtions.TransitionNameSample;


/**
 * Created by pepe on 2016/9/15 0015.
 * andkulikov/Transitions-Everywhere: Backport of Android Transitions API for animations. Animations backported to Android 4.0+. API compatible with Android 2.2+
 * https://github.com/andkulikov/transitions-everywhere
 */
public class TransitionsAct extends AppCompatActivity implements ListFragment.SampleListProvider {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_md_transitions);
        ListFragment listFragment = new ListFragment();
        listFragment.setSampleListListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, listFragment)
                .commit();
    }

    @Override
    public void onSampleSelected(int index) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.md_fade_in, R.anim.md_fade_out,
                        R.anim.md_fade_in, R.anim.md_fade_out)
                .replace(R.id.container, createFragmentForPosition(index))
                .addToBackStack(String.valueOf(index))
                .commit();
    }

    @Override
    public int getSampleCount() {
        return 13;
    }

    @Override
    public String getTitleForPosition(int index) {
        switch (index) {
            case 0: return "Simple animations with AutoTransition";
            case 1: return "Interpolator, duration, start delay";
            case 2: return "Path motion";
            case 3: return "Slide transition";
            case 4: return "Scale transition";
            case 5: return "Explode transition and epicenter";
            case 6: return "Transition names";
            case 7: return "ChangeImageTransform transition";
            case 8: return "Recolor transition";
            case 9: return "Rotate transition";
            case 10: return "Change include_md_text transition";
            case 11: return "Custom transition";
            case 12: return "Scene to scene transitions";
        }
        return null;
    }

    private Fragment createFragmentForPosition(int index) {
        switch (index) {
            case 0: return new AutoTransitionSample();
            case 1: return new InterpolatorDurationStartDelaySample();
            case 2: return new PathMotionSample();
            case 3: return new SlideSample();
            case 4: return new ScaleSample();
            case 5: return new ExplodeAndEpicenterExample();
            case 6: return new TransitionNameSample();
            case 7: return new ImageTransformSample();
            case 8: return new RecolorSample();
            case 9: return new RotateSample();
            case 10: return new ChangeTextSample();
            case 11: return new CustomTransitionSample();
            case 12: return new ScenesSample();
        }
        return null;
    }

}
