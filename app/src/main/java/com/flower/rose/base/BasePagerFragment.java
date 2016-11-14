package com.flower.rose.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.flower.rose.R;

/**
 * @author: ningqiang
 * @time: 16/11/13
 * @description:
 */

public abstract class BasePagerFragment extends BaseFragment<BasePresenter> {
    protected TabLayout tb_title;
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
        tb_title = findView(R.id.tb_title);
        vp_container = findView(R.id.vp_container);
    }

    @Override
    protected void initListener() {
        mPageAdapter = new FragmentPageAdapter(getChildFragmentManager());
        vp_container.setAdapter(mPageAdapter);
        tb_title.setupWithViewPager(vp_container);
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
