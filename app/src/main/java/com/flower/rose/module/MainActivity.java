package com.flower.rose.module;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.flower.rose.NotifcationHelper;
import com.flower.rose.R;
import com.flower.rose.base.BaseActicity;
import com.flower.rose.module.home.HomeFragment;
import com.flower.rose.module.login.LoginActivity;
import com.flower.rose.tinker.util.Utils;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class MainActivity extends BaseActicity {
    private static final String TAG = "MainActivity";
    String patchPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"patch.apk";

    @BindView(R.id.main_drawer)
    DrawerLayout main_drawer;
    FrameLayout content_frame;

    @BindView(R.id.navigation_view)
    NavigationView navigation_view;

    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.e(TAG, "i am on onCreate");

        Log.e(TAG, "i am on onCreate classloader:" + MainActivity.class.getClassLoader().toString());
//        //test resource change
        Log.e(TAG, "i am on patch onCreate");

        drawerToggle = new ActionBarDrawerToggle(this, main_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("open");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("close");
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        main_drawer.addDrawerListener(drawerToggle);

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_menu1:
                        switchFragment();
                        break;
                    case R.id.item_menu2:
                        Log.e(TAG, "begin patch");
                        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk");
                        Log.e(TAG, "end patch");
//                        File file = new File(patchPath);
//                        if (file.exists()) {
//                            Log.e(TAG,"补丁文件存在");
//                            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
//                            Log.e(TAG,"安装完成");
//                            Toast.makeText(getApplicationContext(),"安装完成",Toast.LENGTH_LONG).show();
//                        } else {
//                            Log.e(TAG,"补丁文件不存在");
//                            Toast.makeText(getApplicationContext(),"文件不存在",Toast.LENGTH_LONG).show();
//
//                        }
                     /*   ArrayList<Course> c = new ArrayList<Course>();
                        for (int i = 0; i < 3; i++) {
                            Course course = new Course();
                            course.course = "course  " + i;
                            c.add(course);
                        }

                        final ArrayList<Student> students = new ArrayList<Student>();
                        for (int i = 0; i < 5; i++) {
                            Student student = new Student();
                            student.name = "jack " + i;
                            student.courses = c;
                            students.add(student);
                        }


                        Subscriber<Course> subscriber = new Subscriber<Course>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "tt onCompleted  ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "tt onError = " + e.getMessage());
                            }

                            @Override
                            public void onNext(Course course) {
                                Log.d(TAG, "tt onNext course = " + course.course);
                            }
                        };
                        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
                            @Override
                            public Observable<Course> call(Student student) {
                                Log.d(TAG, "tt student =   " + student.name);
                                return Observable.from(student.courses);
                            }
                        }).subscribe(subscriber);*/

                        break;
                    case R.id.item_sub_menu1:

                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        intent.putExtra("content","内容 ＋ "+count);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),12,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication())
                                .setSmallIcon(R.mipmap.icon)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))
                                .setContentText("content")
                                .setContentIntent(pendingIntent)
                                .setContentTitle("title");
                        Notification notification = builder.build();

                        //
                        NotifcationHelper.processNotification(notification);



                        int mNotificationId = 001;
                        NotificationManager mNotifyMgr =
                                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        mNotifyMgr.notify(mNotificationId, notification);


                        count++;

                        break;
                    case R.id.item_sub_menu2:
                        Toast.makeText(MainActivity.this, "我是修改后的加了布丁", Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });
        switchFragment();


    }


    int count = 0;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void switchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, Fragment.instantiate(getApplicationContext(), HomeFragment.class.getName()));
        transaction.commit();

    }

    class Student {
        public String name;
        public ArrayList<Course> courses;
    }

    class Course {
        public String course;
    }


    @Override
    protected void onResume() {
        Log.e(TAG, "i am on onResume");
//        Log.e(TAG, "i am on patch onResume");

        super.onResume();
        Utils.setBackground(false);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Utils.setBackground(true);
    }

}
