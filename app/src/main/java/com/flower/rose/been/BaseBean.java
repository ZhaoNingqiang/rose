package com.flower.rose.been;

import org.jsoup.nodes.Document;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 下午4:12
 */

public abstract class BaseBean<T extends BaseBean> {
    public abstract T parser( Document document);
}
