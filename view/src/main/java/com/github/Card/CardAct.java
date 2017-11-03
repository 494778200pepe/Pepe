package com.github.Card;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pepe.view.R;


/**
 * Created by wang on 2017/10/12.
 */

public class CardAct extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * LinearLayout linearLayout =new LinearLayout(CardAct.this);
         * linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
         * CardView cardView = new CardView(CardAct.this);
         * cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
         * linearLayout.addView(cardView);
         * setContentView(linearLayout);
         */
        setContentView(R.layout.act_github_card);
    }
}
