package com.github.InfiniteCards;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pepe.view.R;

/**
 * Created by wang on 2017/10/12.
 */

public class InfiniteCardsAct extends Activity implements View.OnClickListener {


    private int[] pictures = {R.mipmap.card_pic1, R.mipmap.card_pic2, R.mipmap.card_pic3, R.mipmap.card_pic4, R.mipmap.card_pic5};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_github_infinite_cards);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre:
                break;
            case R.id.btn_change:
                break;
            case R.id.btn_next:
                break;
            default:
                break;
        }
    }
}
