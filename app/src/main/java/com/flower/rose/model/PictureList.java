package com.flower.rose.model;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/09/29 上午11:33
 */

public class PictureList extends BaseModel<PictureList>{
    ArrayList<Picture> picture_list;

    @Override
    public PictureList parser( Document document) {
        Elements picture_lists_e = document.getElementsByClass("picture-list");

        for (Element picture_list_e : picture_lists_e){
            if (picture_list_e.attributes().size() == 1){
                picture_list = new ArrayList<Picture>();
                Elements pictures_e = picture_list_e.getElementsByClass("picture");
                Picture picture ;
                for (Element picture_e : pictures_e){
                    picture = new Picture();
                    picture.href = picture_e.attr("href");
                    picture.alt = picture_e.attr("alt");
                    picture.title = picture_e.attr("title");
                    Element thumbnail_e = picture_e.getElementsByClass("thumbnail").get(0);
                    picture.thumbnail= thumbnail_e.attr("src");
                    picture_list.add(picture);

                }
            }
        }
        return this;
    }
}

