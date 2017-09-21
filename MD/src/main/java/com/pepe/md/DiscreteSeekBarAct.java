package com.pepe.md;

import android.app.Activity;
import android.os.Bundle;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by pepe on 2016/9/19.
 * AnderWeb/discreteSeekBar
 * https://github.com/AnderWeb/discreteSeekBar
 */
public class DiscreteSeekBarAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_md_discrete_seekbar);
        DiscreteSeekBar discreteSeekBar1 = (DiscreteSeekBar) findViewById(R.id.discrete1);
        discreteSeekBar1.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value * 100;
            }
        });
    }
}
