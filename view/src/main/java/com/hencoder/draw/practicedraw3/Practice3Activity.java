package com.hencoder.draw.practicedraw3;

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

public class Practice3Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample3_draw_text, R.string.title_draw_text, R.layout.practice3_draw_text));
        pageModels.add(new PageModel(R.layout.sample3_static_layout, R.string.title_static_layout, R.layout.practice3_static_layout));
        pageModels.add(new PageModel(R.layout.sample3_set_text_size, R.string.title_set_text_size, R.layout.practice3_set_text_size));
        pageModels.add(new PageModel(R.layout.sample3_set_typeface, R.string.title_set_typeface, R.layout.practice3_set_typeface));
        pageModels.add(new PageModel(R.layout.sample3_set_fake_bold_text, R.string.title_set_fake_bold_text, R.layout.practice3_set_fake_bold_text));
        pageModels.add(new PageModel(R.layout.sample3_set_strike_thru_text, R.string.title_set_strike_thru_text, R.layout.practice3_set_strike_thru_text));
        pageModels.add(new PageModel(R.layout.sample3_set_underline_text, R.string.title_set_underline_text, R.layout.practice3_set_underline_text));
        pageModels.add(new PageModel(R.layout.sample3_set_text_skew_x, R.string.title_set_text_skew_x, R.layout.practice3_set_text_skew_x));
        pageModels.add(new PageModel(R.layout.sample3_set_text_scale_x, R.string.title_set_text_scale_x, R.layout.practice3_set_text_scale_x));
        pageModels.add(new PageModel(R.layout.sample3_set_text_align, R.string.title_set_text_align, R.layout.practice3_set_text_align));
        pageModels.add(new PageModel(R.layout.sample3_get_font_spacing, R.string.title_get_font_spacing, R.layout.practice3_get_font_spacing));
        pageModels.add(new PageModel(R.layout.sample3_measure_text, R.string.title_measure_text, R.layout.practice3_measure_text));
        pageModels.add(new PageModel(R.layout.sample3_get_text_bounds, R.string.title_get_text_bounds, R.layout.practice3_get_text_bounds));
        pageModels.add(new PageModel(R.layout.sample3_get_font_metrics, R.string.title_get_font_metrics, R.layout.practice3_get_font_metrics));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_practice3);

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
