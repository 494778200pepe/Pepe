package com.pepe.anim.property;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.pepe.view.R;


/**
 * Created by pepe on 2016/8/21 0021.
 */
public class PropertyValuesHolderAct extends AppCompatActivity {

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_anim_prop_holder);
        image = (ImageView) findViewById(R.id.image_xml);
    }
    @Override
    /**
     * 点击menu按钮时
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 0, "PropertyValuesHolder");    //添加选项
        return true;
    }

    @Override
    /**
     * 点击menu菜单中某一个选项时
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                PropertyValuesHolder pvh1= PropertyValuesHolder.ofFloat("translationX",300f);
                PropertyValuesHolder pvh2= PropertyValuesHolder.ofFloat("scaleX",1f,0.5f);
                PropertyValuesHolder pvh3= PropertyValuesHolder.ofFloat("scaleY",1f,0.5f);
                ObjectAnimator.ofPropertyValuesHolder(image,pvh1,pvh2,pvh3).setDuration(1000).start();

                break;

            default:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
