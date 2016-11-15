package com.flower.rose.module.home;

import android.os.Bundle;

import com.flower.rose.base.BasePagerFragment;
import com.flower.rose.model.HomeModel;

/**
 * @author: ningqiang
 * @time: 16/11/13
 * @description:
 */

public class HomePagerFragment extends BasePagerFragment {
    private static final String[] PAGE_TITLE = new String[]{"最新","最热"};

    @Override
    protected Class[] getPageClasses() {
        return new Class[]{HomeFragment.class, HomeFragment.class};
    }

    @Override
    protected String[] getPageTitles() {
        return PAGE_TITLE;
    }

    @Override
    protected Bundle[] getPageBundles() {
        Bundle[] bundles = new Bundle[2];
        bundles[0] = new Bundle(1);
        bundles[0].putInt(HomeFragment.EXTRA_SHOW_MODE,HomeModel.SHOW_NEW);
        bundles[1] = new Bundle(1);
        bundles[1].putInt(HomeFragment.EXTRA_SHOW_MODE,HomeModel.SHOW_HOT);
        return bundles;
    }
}
