package com.flower.rose.module.rxbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flower.rose.R;
import com.flower.rose.util.RxBus;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 利用rxjava实现的事件总线
 *
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/25/下午5:18
 */

public class RxBusActivity extends Activity {
    //compositeSubsription 可以把Subscription收集到一起，方便Activity销毁时取消订阅，防止内存泄露
    private CompositeSubscription allSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        Button btn_rxbus = (Button) findViewById(R.id.btn_rxbus);
        btn_rxbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getDefault().post(new ClickEvent("the first passenger"));
            }
        });
        allSubscription.add(RxBus.getDefault().toObservable(ClickEvent.class).subscribe(new Action1<ClickEvent>() {
            @Override
            public void call(ClickEvent clickEvent) {
                response(clickEvent);
            }
        }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (allSubscription != null && !allSubscription.isUnsubscribed()) {
            allSubscription.unsubscribe();
        }
    }

    public void response(ClickEvent clickEvent) {
        Toast.makeText(this, clickEvent.msg, Toast.LENGTH_LONG).show();
    }


    class ClickEvent {
        String msg;

        public ClickEvent(String msg) {
            this.msg = msg;
        }
    }
}
