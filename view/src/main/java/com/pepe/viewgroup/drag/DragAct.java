package com.pepe.viewgroup.drag;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.pepe.ItemFragment;
import com.pepe.view.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wang
 * @date 2017/11/22.
 */
public class DragAct  extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.layout.item_viewgroup_drag, R.string.str_view_group_drag));
        pageModels.add(new PageModel(R.layout.item_viewgroup_drag_edge_limit, R.string.str_view_group_drag_edge_limit));
        pageModels.add(new PageModel(R.layout.item_viewgroup_drag_release, R.string.str_view_group_drag_release));
        pageModels.add(new PageModel(R.layout.item_viewgroup_drag_edge_control, R.string.str_view_group_drag_edge_control));
        pageModels.add(new PageModel(R.layout.item_viewgroup_drag_test, R.string.str_view_group_drag_test));
        pageModels.add(new PageModel(R.layout.item_viewgroup_vertical_drawer, R.string.str_view_group_drag));
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
}


