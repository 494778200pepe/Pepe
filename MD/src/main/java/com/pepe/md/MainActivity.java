package com.pepe.md;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by pepe on 2016/9/14.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_md_main);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_btn0://
                startActivity(new Intent(this, AndroidUIAct.class));
                break;
            case R.id.main_btn1://
                startActivity(new Intent(this, MaterialMenuAct.class));
                break;
            case R.id.main_btn2://
                startActivity(new Intent(this, RippleEffectAct.class));
                break;
            case R.id.main_btn3://
                startActivity(new Intent(this, TickplusdrawableAct.class));
                break;
            case R.id.main_btn4://
                startActivity(new Intent(this, FloatingButtonAct.class));
                break;
            case R.id.main_btn5://
                startActivity(new Intent(this, AnimatedVectorAct.class));
                break;
            case R.id.main_btn6://
                startActivity(new Intent(this, MaterialDialogAct.class));
                break;
            case R.id.main_btn7://
                startActivity(new Intent(this, TransitionsAct.class));
                break;
            case R.id.main_btn8://
                startActivity(new Intent(this, MaterialEditTextAct.class));
                break;
            case R.id.main_btn9://
                startActivity(new Intent(this, MaterialToolbarAct.class));
                break;
            case R.id.main_btn10://
                startActivity(new Intent(this, MaterialTabAct.class));
                break;
            case R.id.main_btn11://
                startActivity(new Intent(this, PhoenixAct.class));
                break;
            case R.id.main_btn12://
                startActivity(new Intent(this, SideMenuAct.class));
                break;
            case R.id.main_btn13://
                startActivity(new Intent(this, ContextMenuAct.class));
                break;
            case R.id.main_btn14://
                startActivity(new Intent(this, DiscreteSeekBarAct.class));
                break;
            case R.id.main_btn15://
                break;
            case R.id.main_btn16://
                break;

        }
    }
}
