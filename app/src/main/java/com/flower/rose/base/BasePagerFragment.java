package com.flower.rose.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.flower.rose.R;

/**
 * @author: ningqiang
 * @time: 16/11/13
 * @description:
 */

public abstract class BasePagerFragment extends BaseFragment<BasePresenter> {
    protected Toolbar toolbar;
    protected TabLayout tabs;
    protected ViewPager vp_container;
    protected FragmentPageAdapter mPageAdapter;
    @Override
    protected void loadDataIfNull() {


    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_base_pager;
    }

    @Override
    protected void initView() {
        toolbar = findView(R.id.toolbar);
        mActivity.setSupportActionBar(toolbar);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setHomeButtonEnabled(true);

        tabs = findView(R.id.tabs);
        vp_container = findView(R.id.vp_container);
    }

    @Override
    protected void initListener() {
        mPageAdapter = new FragmentPageAdapter(getChildFragmentManager());
        vp_container.setAdapter(mPageAdapter);
        tabs.setupWithViewPager(vp_container);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }



    protected abstract Class<?>[] getPageClasses();

    protected abstract String[] getPageTitles();

    protected abstract Bundle[] getPageBundles();

    private class FragmentPageAdapter extends FragmentStatePagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            String[] pageTitles = getPageTitles();
            return  pageTitles == null ? null : pageTitles[position];
        }

        public FragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mActivity,getPageClasses()[position].getName(),getPageBundles()[position]);

        }

        @Override
        public int getCount() {
            Class[] pageClasses = getPageClasses();
            return pageClasses == null ? 0 : pageClasses.length;
        }
    }
}
