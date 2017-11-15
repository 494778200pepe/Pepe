package com.pepe.view.path;

import android.app.Activity;
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

import com.pepe.view.ItemFragment;
import com.pepe.view.R;
import com.pepe.view.canvas.CanvasAct;

import java.util.ArrayList;
import java.util.List;

import static com.pepe.view.path.PathView.CONTENTS;


/**
 * @author wang
 * @date 2017/11/13.
 */

public class PathAct extends AppCompatActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.item_view_path_path, R.string.str_view_canvas_canvas));
        pageModels.add(new PageModel(R.layout.item_view_path_clip_path, R.string.str_view_path_clip_path));
        pageModels.add(new PageModel(R.layout.item_view_path_clip_rect, R.string.str_view_path_clip_rect));
        pageModels.add(new PageModel(R.layout.item_view_path_show_hide, R.string.str_view_path_show_hide));
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
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_path:
                PathView pathView = ((PathView) findViewById(R.id.path_view));
                int mode = pathView.getDrawMode();
                if (mode == 7) {
                    mode = -1;
                }
                pathView.setDrawMode(++mode);
                Button btn_path = ((Button) findViewById(R.id.btn_path));
                btn_path.setText(CONTENTS[mode]);
                break;
            default:
                break;
        }
    }
}
