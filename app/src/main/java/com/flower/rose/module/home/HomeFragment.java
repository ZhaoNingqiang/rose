package com.flower.rose.module.home;

import android.os.Bundle;

import com.flower.rose.base.BasePagerFragment;
import com.flower.rose.module.home.news.NewFragment;

/**
 * @author: ningqiang
 * @time: 16/11/13
 * @description:
 */

public class HomeFragment extends BasePagerFragment {
    @Override
    protected Class[] getPageClasses() {
        return new Class[]{NewFragment.class, NewFragment.class};
    }

    @Override
    protected String[] getPageTitles() {
        return new String[]{"最新","最热"};
    }

    @Override
    protected Bundle[] getPageBundles() {
        return new Bundle[2];
    }
}
