package com.pepe.view.canvas;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.pepe.ItemFragment;
import com.pepe.view.R;

import java.util.ArrayList;
import java.util.List;

import static com.pepe.view.canvas.CanvasView.CONTENTS;

/**
 * @author wang
 * @date 2017/11/13.
 */

public class CanvasAct extends AppCompatActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.item_view_canvas_canvas, R.string.str_view_canvas_canvas));
        pageModels.add(new PageModel(R.layout.item_view_canvas_loadtext, R.string.str_view_canvas_load_text));
        pageModels.add(new PageModel(R.layout.item_view_canvas_circle_image, R.string.str_view_canvas_circle_image));
        pageModels.add(new PageModel(R.layout.item_view_canvas_porter_duff_xfermode, R.string.str_view_canvas_porter_duff_xfer_mode));
        pageModels.add(new PageModel(R.layout.item_view_canvas_reflection_image, R.string.str_view_canvas_reflection_image));
        pageModels.add(new PageModel(R.layout.item_view_canvas_shimmer_text, R.string.str_view_canvas_shimmer_text));
        pageModels.add(new PageModel(R.layout.item_view_canvas_wave, R.string.str_view_canvas_wave));
        pageModels.add(new PageModel(R.layout.item_view_canvas_inverted, R.string.str_view_canvas_inverted_image));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_practice);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return ItemFragment.newInstance(pageModel.sampleLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_canvas:
                CanvasView canvasView = ((CanvasView) findViewById(R.id.canvas_view));
                int mode = canvasView.getDrawmode();
                if (mode == 18) {
                    mode = -1;
                }
                canvasView.setDrawMode(++mode);
                Button btn_canvas = ((Button) findViewById(R.id.btn_canvas));
                btn_canvas.setText(CONTENTS[mode]);
                break;
            default:
                break;
        }
    }

    private class PageModel {
        @LayoutRes
        int sampleLayoutRes;
        @StringRes
        int titleRes;

        PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
        }
    }

}
