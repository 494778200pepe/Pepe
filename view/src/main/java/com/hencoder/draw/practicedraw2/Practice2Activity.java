package com.hencoder.draw.practicedraw2;

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

public class Practice2Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.sample2_linear_gradient, R.string.title_linear_gradient, R.layout.practice2_linear_gradient));
        pageModels.add(new PageModel(R.layout.sample2_radial_gradient, R.string.title_radial_gradient, R.layout.practice2_radial_gradient));
        pageModels.add(new PageModel(R.layout.sample2_sweep_gradient, R.string.title_sweep_gradient, R.layout.practice2_sweep_gradient));
        pageModels.add(new PageModel(R.layout.sample2_bitmap_shader, R.string.title_bitmap_shader, R.layout.practice2_bitmap_shader));
        pageModels.add(new PageModel(R.layout.sample2_compose_shader, R.string.title_compose_shader, R.layout.practice2_compose_shader));
        pageModels.add(new PageModel(R.layout.sample2_lighting_color_filter, R.string.title_lighting_color_filter, R.layout.practice2_lighting_color_filter));
        pageModels.add(new PageModel(R.layout.sample2_color_mask_color_filter, R.string.title_color_matrix_color_filter, R.layout.practice2_color_matrix_color_filter));
        pageModels.add(new PageModel(R.layout.sample2_xfermode, R.string.title_xfermode, R.layout.practice2_xfermode));
        pageModels.add(new PageModel(R.layout.sample2_stroke_cap, R.string.title_stroke_cap, R.layout.practice2_stroke_cap));
        pageModels.add(new PageModel(R.layout.sample2_stroke_join, R.string.title_stroke_join, R.layout.practice2_stroke_join));
        pageModels.add(new PageModel(R.layout.sample2_stroke_miter, R.string.title_stroke_miter, R.layout.practice2_stroke_miter));
        pageModels.add(new PageModel(R.layout.sample2_path_effect, R.string.title_path_effect, R.layout.practice2_path_effect));
        pageModels.add(new PageModel(R.layout.sample2_shadow_layer, R.string.title_shader_layer, R.layout.practice2_shadow_layer));
        pageModels.add(new PageModel(R.layout.sample2_mask_filter, R.string.title_mask_filter, R.layout.practice2_mask_filter));
        pageModels.add(new PageModel(R.layout.sample2_fill_path, R.string.title_fill_path, R.layout.practice2_fill_path));
        pageModels.add(new PageModel(R.layout.sample2_text_path, R.string.title_text_path, R.layout.practice2_text_path));
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
