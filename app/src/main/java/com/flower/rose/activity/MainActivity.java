package com.flower.rose.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.flower.rose.R;
import com.flower.rose.model.PictureList;
import com.flower.rose.net.RoseService;
import com.flower.rose.net.converter.HtmlConverterFactory;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_drawer)
    DrawerLayout main_drawer;
    @BindView(R.id.content_frame)
    FrameLayout content_frame;

    @BindView(R.id.navigation_view)
    NavigationView navigation_view;

    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        main_drawer.setScrimColor(0xff0000);

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
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://www.zhuangbi.info/")
//                               .addConverterFactory(GsonConverterFactory.create())
//                               .addConverterFactory(SimpleXmlConverterFactory.create())
                                .addConverterFactory(HtmlConverterFactory.create())
                                .build();
                        RoseService roseService = retrofit.create(RoseService.class);
                        Call<PictureList> listCall = roseService.groupList(1);
                        listCall.enqueue(new Callback<PictureList>() {
                            @Override
                            public void onResponse(Call<PictureList> call, Response<PictureList> response) {
                                URL url = call.request().url().url();
                                Log.d("hhhhhh", url.toString());
                            }

                            @Override
                            public void onFailure(Call<PictureList> call, Throwable t) {
                                URL url = call.request().url().url();
                                Log.d("hhhhhh", url.toString());
                            }
                        });


                        break;
                    case R.id.item_menu2:
                        Intent i = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(i);
                        break;
                    case R.id.item_sub_menu1:
                        break;
                    case R.id.item_sub_menu2:
                        break;

                }
                return true;
            }
        });

    }

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
}
