package com.hencoder.practicedraw1;

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

public class Practice1Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample1_color, R.string.title_draw_color, R.layout.practice1_color));
        pageModels.add(new PageModel(R.layout.sample1_circle, R.string.title_draw_circle, R.layout.practice1_circle));
        pageModels.add(new PageModel(R.layout.sample1_rect, R.string.title_draw_rect, R.layout.practice1_rect));
        pageModels.add(new PageModel(R.layout.sample1_point, R.string.title_draw_point, R.layout.practice1_point));
        pageModels.add(new PageModel(R.layout.sample1_oval, R.string.title_draw_oval, R.layout.practice1_oval));
        pageModels.add(new PageModel(R.layout.sample1_line, R.string.title_draw_line, R.layout.practice1_line));
        pageModels.add(new PageModel(R.layout.sample1_round_rect, R.string.title_draw_round_rect, R.layout.practice1_round_rect));
        pageModels.add(new PageModel(R.layout.sample1_arc, R.string.title_draw_arc, R.layout.practice1_arc));
        pageModels.add(new PageModel(R.layout.sample1_path, R.string.title_draw_path, R.layout.practice1_path));
        pageModels.add(new PageModel(R.layout.sample1_histogram, R.string.title_draw_histogram, R.layout.practice1_histogram));
        pageModels.add(new PageModel(R.layout.sample1_pie_chart, R.string.title_draw_pie_chart, R.layout.practice1_pie_chart));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_practice1);

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
