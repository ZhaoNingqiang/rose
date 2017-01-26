package com.smile.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func4;
import rx.schedulers.Schedulers;

/**
 * rxjava 的使用演示
 *
 * @author 赵凝强
 * @version 1.0.0
 * @Time 17/1/24/下午12:10
 */

public class LearnRxJava {
    public static void main(String[] args) {
        create();
        //subscribeOn();
        //map();
        //flatMap();
        //merge();
        //concat();
        //zip();
        //filter();
        //first_1();
        //first_2();
        //last();
        //test();
        //compose();
    }

    /**
     * create方法传递Onsubscribe对象，在call方法中发送数据，这是最基本的创建方法
     */
    private static void create() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("create call  onNext subscriber = "+subscriber);
                subscriber.onCompleted();

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("create subscribe oncompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("create onerror");
            }

            @Override
            public void onNext(String s) {
                System.out.println("create onNext s = " + s+" this = "+this);
            }
        });
    }

    /**
     * just方法可以快速创建队列，每一个参数会调用onNext方法传递一次（最多10个），事件按顺序发送，just在发送完数据后会
     * 调用onCompleted方法
     */
    private static void just() {
        Observable.just(1, 2, 3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("just onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("just onError");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("just onNext integer = " + integer);
            }
        });
    }

    /**
     * from可以将数组，Interable,Feature对象转化为Observable对象。
     * from onNext s = one
     * from onNext s = two
     * from onNext s = three
     * from onCompleted
     */
    private static void from() {
        String[] s = {"one", "two", "three"};
        Subscription subscribe = Observable.from(s).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("from onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("from onError");

            }

            @Override
            public void onNext(String s) {
                System.out.println("from onNext s = " + s);
            }
        });
        boolean unsubscribed = subscribe.isUnsubscribed();
        System.out.println("from unsubscribed = " + unsubscribed);
        subscribe.unsubscribe();
    }

    /**
     * observeOn指定它之后代码执行的线程，其余代码的是在第一个subseribeOn中指定的线程执行
     */
    private static void subscribeOn() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("- 0 -  thread = " + Thread.currentThread().getName());
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onCompleted();
            }
        })
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("- 1 -  thread = " + Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.computation())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("- 2 -  thread = " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.newThread())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("- 3 -  thread = " + Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println("- 4 -  thread = " + Thread.currentThread().getName());
                    }
                });
    }

    /**
     * map：将发射的数据转化为subscribe所需要的数据。
     * 注意：map操作的数发射的数据，Observable本身并不会被转化
     */
    private static void map() {
        Observable.just(1, 2).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                if (integer == 1) {
                    return "hello";
                } else {
                    return "rxjava";
                }
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("map s = " + s);
            }
        });
    }

    /**
     * 将一个Observable转化为一个新的Observable，并且由这个新的Observable发射数据
     */
    private static void flatMap() {
        Observable.just(1, 2).flatMap(new Func1<Integer, Observable<String>>() {
            @Override
            public Observable<String> call(Integer integer) {
                if (integer == 1) {
                    return Observable.just("hello");
                } else {
                    return Observable.just("flatMap");
                }
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("flat map s = " + s);
            }
        });
    }

    /**
     * 将多个Observable合并为一个Observable发射数据。
     * 官方文档：merge可能会导致交错发送数据，即不是按照合并顺序发送数据
     */
    private static void merge() {
        Observable<String> just_1 = Observable.just("1", "6");
        Observable<String> just_2 = Observable.just("2", "5");
        Observable<String> just_3 = Observable.just("3", "6");
        Observable<String> just_4 = Observable.just("6");
        Observable.merge(just_1, just_2, just_3, just_4).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("merge s = " + s);
            }
        });
    }

    /**
     * 将多个Observable合并为一个Observable，并且按照顺序发射数据
     */
    private static void concat() {
        Observable<String> just_1 = Observable.just("1", "6");
        Observable<String> just_2 = Observable.just("2", "5");
        Observable<String> just_3 = Observable.just("3", "6");
        Observable<String> just_4 = Observable.just("6");
        Observable.merge(just_1, just_2, just_3, just_4).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("merge s = " + s);
            }
        });
    }

    /**
     * zip传入需要合并的Observable对象，以及Func4函数，与merge不同的是，zip是将所有发射的数据拿到后进行整合，
     * 最后发射整合后的数据。call中的参数是按照合并顺序所发射的数据，return的即为最终zip发射的数据
     * 注意：zip不仅可以合并发射数据，还可以对数据类型进行转化
     */
    private static void zip() {
        Observable<String> just_1 = Observable.just("1");
        Observable<String> just_2 = Observable.just("2");
        Observable<String> just_3 = Observable.just("3");
        Observable<String> just_4 = Observable.just("4");
        Observable.zip(just_1, just_2, just_3, just_4, new Func4<String, String, String, String, Integer>() {
            @Override
            public Integer call(String s, String s2, String s3, String s4) {
                return Integer.parseInt(s + s2 + s3 + s4);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("zip integer = " + integer);
            }
        });
    }

    /**
     * filter：根据规则去校验数据，只发射通过了校验的数据。
     */
    private static void filter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 5;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("filter integer = " + integer);
            }
        });
    }

    /**
     * first 只发射第一项或者满足某个条件的第一项数据，first类似于filter不同的是first致发射第一个通过验证的数据项。
     */
    private static void first_1() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).first().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("first_1 integer = " + integer);
            }
        });
    }

    /**
     * first：所有要发射的数据都会在call中按照规则进行校验，比如数据大于等于7，当满足条件时，return true，当return true的时候就会调用
     * onNext方法，而还没有发射数据的Observable将不再发射数据，如果return false，则后面的observable还会依次发射数据，如果全部数据都
     * 发射完后都没有return true，则会调用onError。
     */
    private static void first_2() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9).first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                System.out.println("first_2 call integer = " + integer);
                return integer >= 7;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("first_2 onCompleted ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("first_2 onError e = " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("first_2 onNext integer = " + integer);
            }
        });
    }

    /**
     * 只发射最后一项数据，或则满足某个条件的最后一项数据。用法同first一样。
     */
    private static void last() {
        Observable.just(4, 5, 6, 7, 8, 9).last().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("last integer = " + integer);
            }
        });
    }

    /**
     * compose方法需要传入一个Transformer对象，第一个范型为原始的Observable发射的数据类型，第二个范型转化后的数据类型
     */
    private static void compose() {
        Observable.just("shoot data").compose(new Observable.Transformer<String, String>() {
            @Override
            public Observable<String> call(Observable<String> stringObservable) {
                System.out.println("compose thread = " + Thread.currentThread().getName());
                return stringObservable.subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("compose s = " + s + " thread = " + Thread.currentThread().getName());
            }
        });
    }

    private static void test() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("create call Thread = " + Thread.currentThread().getName());
                subscriber.onNext("jack");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("subscribeOn onCompleted Thread = " + Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("subscribeOn onError Thread = " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                System.out.println("subscribeOn onNext Thread = " + Thread.currentThread().getName());
            }
        });
    }


}

