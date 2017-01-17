package com.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/3/下午5:29
 */

public class HelloRxjava {
    public static void main(String[] args) {
        //hello("hell0", "nihao", "rxjava");
        //hello_1("jack");
        // hello_2();
        //hello_3();
        //hello_4();
        //createObservable();
    }

    private static void learnRX() {
        Observable.just("subscriber on")
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("doOnNext s = " + s);
                    }
                }).subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("subscribe s = " + s);
                    }
                });

    }

    private static void createObservable() {
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onStart();
                //subscriber.onCompleted();

                subscriber.onNext("just do it");
                //
                subscriber.onNext("just do it2");
                //subscriber.onCompleted();
                subscriber.onError(new IOException("error happend"));
//                subscriber.onError(new IOException("error happend 2"));


            }
        });
        stringObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onStart() {
                System.out.println("VVV onstart");
            }

            @Override
            public void onCompleted() {
                System.out.println("VVV onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("VVV onError e = " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("VVV onNext s = " + s);
            }
        });
    }


    private static void hello_4() {
        Observable<String> hello = Observable.just("hello");
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Observable<? extends Serializable> merge = Observable.merge(hello, just);
        merge.subscribe(new Action1<Serializable>() {
            @Override
            public void call(Serializable s) {
                System.out.println(s);
            }
        });

        just.reduce(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                System.out.println(integer + "  " + integer2);
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    private static void hello_3() {
        Observable<String> hello = Observable.just("hello");

        Observable<String> world = Observable.just("world");


        Observable<String> reduce = Observable.merge(hello, world).reduce(new Func2<String, String, String>() {
            @Override
            public String call(String s, String s2) {
                return "s = " + s + "  s2 = " + s2;
            }
        });

        reduce.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });


    }


    private static void hello_2() {
        Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6, 7, 8);
        Observable<String> flatmap = just.flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                return Observable.just("flat map");
            }
        });
        flatmap.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("flat map s = " + s);
            }
        });


        Observable<String> map = just.map(new Func1<Integer, String>() {

            @Override
            public String call(Integer integer) {
                return "map number is " + integer;
            }
        });
        map.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("map action s = " + s);
            }
        });

        just.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("just action integer = " + integer);
            }
        });

        System.out.println("just = " + just + " map = " + map + " flatmap = " + flatmap);

    }

    private static void hello_1(String text) {
        Observable.just(text).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private static void hello(String... text) {
        List<String> list = Arrays.asList(text);
        Observable<String> observable = Observable.from(list);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("sb-c  ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("sb-e  " + e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("sb-n  " + s);
            }
        });
    }
}
