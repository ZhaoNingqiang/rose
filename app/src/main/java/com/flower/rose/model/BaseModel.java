package com.flower.rose.model;

import org.jsoup.nodes.Document;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 下午4:12
 */

public abstract class BaseModel<T extends BaseModel> {
    public abstract T parser( Document document);
}
