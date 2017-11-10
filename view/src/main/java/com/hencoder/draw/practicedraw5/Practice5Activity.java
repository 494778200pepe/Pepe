package com.hencoder.draw.practicedraw5;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.pepe.view.R;

import java.util.ArrayList;
import java.util.List;

public class Practice5Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample5_after_on_draw, R.string.title_after_on_draw, R.layout.practice5_after_on_draw));
        pageModels.add(new PageModel(R.layout.sample5_before_on_draw, R.string.title_before_on_draw, R.layout.practice5_before_on_draw));
        pageModels.add(new PageModel(R.layout.sample5_on_draw_layout, R.string.title_on_draw_layout, R.layout.practice5_on_draw_layout));
        pageModels.add(new PageModel(R.layout.sample5_dispatch_draw, R.string.title_dispatch_draw, R.layout.practice5_dispatch_draw));
        pageModels.add(new PageModel(R.layout.sample5_after_on_draw_foreground, R.string.title_after_on_draw_foreground, R.layout.practice5_after_on_draw_foreground));
        pageModels.add(new PageModel(R.layout.sample5_before_on_draw_foreground, R.string.title_before_on_draw_foreground, R.layout.practice5_before_on_draw_foreground));
        pageModels.add(new PageModel(R.layout.sample5_after_draw, R.string.title_after_draw, R.layout.practice5_after_draw));
        pageModels.add(new PageModel(R.layout.sample5_before_draw, R.string.title_before_draw, R.layout.practice5_before_draw));
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
                return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private class PageModel {
        @LayoutRes int sampleLayoutRes;
        @StringRes int titleRes;
        @LayoutRes int practiceLayoutRes;

        PageModel(@LayoutRes int sampleLayoutRes, @StringRes int titleRes, @LayoutRes int practiceLayoutRes) {
            this.sampleLayoutRes = sampleLayoutRes;
            this.titleRes = titleRes;
            this.practiceLayoutRes = practiceLayoutRes;
        }
    }
}
