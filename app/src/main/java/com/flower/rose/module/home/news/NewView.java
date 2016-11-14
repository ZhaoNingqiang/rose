package com.flower.rose.module.home.news;

import com.flower.rose.base.BaseView;
import com.flower.rose.been.PictureList;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/11 下午2:10
 */

public interface NewView extends BaseView {
   public void showPictures(int page, PictureList pictureList);
}
