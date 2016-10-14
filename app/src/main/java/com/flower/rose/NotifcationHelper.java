package com.flower.rose;

import android.app.Notification;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: ZhaoNingqianggit
 * @Time 2016/10/13 下午6:02
 */

public class NotifcationHelper {
    /**
     * 展示通知时显示带彩色的小图标，隐藏掉通知展开后大图标旁边的小图标，处理之前需要调用指定通知的大图标
     * builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))
     * @param notification
     */
    public static void processNotification(Notification notification){

        if (Build.VERSION.SDK_INT >= 23) {
            //小icon设置颜色
            Icon smallIcon = notification.getSmallIcon();
            smallIcon.setTintMode(PorterDuff.Mode.DST_IN);//DST  DST_IN MULTIPLY

            //隐藏小icon
            try {
                Field contentView = Notification.class.getDeclaredField("contentView");
                contentView.setAccessible(true);
                RemoteViews remoteViews = (RemoteViews) contentView.get(notification);

                Method hideRightIcon = Notification.MediaStyle.class.getDeclaredMethod("hideRightIcon", RemoteViews.class);
                hideRightIcon.setAccessible(true);
                hideRightIcon.invoke(new Notification.MediaStyle(), remoteViews);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
