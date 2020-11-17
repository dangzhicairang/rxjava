package com.xsn.chapter1;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class HandC {
    public static void main(String[] args) throws InterruptedException {
        // coldObservable();

        // c2h();

        // h2c();

        h2c2();
    }

    static void coldObservable() throws InterruptedException {
        Observable o = Observable.create(e -> {
            Observable.interval(
                    10
                    , TimeUnit.MILLISECONDS
                    , Schedulers.computation()
            )
                    .take(Integer.MAX_VALUE)
                    .subscribe(e::onNext);
        })
                .observeOn(Schedulers.newThread());

        o.subscribe(t -> System.out.println("c1: " + t));
        o.subscribe(t -> System.out.println("   c2: " + t));

        Thread.sleep(100);
    }

    static void c2h() throws InterruptedException {
        Observable o = Observable.create(e -> {
            Observable.interval(
                    10
                    , TimeUnit.MILLISECONDS
                    , Schedulers.computation()
            )
                    .take(Integer.MAX_VALUE)
                    .subscribe(e::onNext);
        })
                .observeOn(Schedulers.newThread());

        // publish 操作符，将 Cold Observable 转化为 Hot Observable
        ConnectableObservable connectableObservable
                = o.publish();
        // 需要调用 connect 才开始执行
        connectableObservable.connect();

        connectableObservable.subscribe(t -> System.out.println("c1: " + t));
        connectableObservable.subscribe(t -> System.out.println("   c2: " + t));

        Thread.sleep(100);

        connectableObservable.subscribe(t -> System.out.println("       c3: " + t));

        Thread.sleep(100);
    }

    static void h2c() throws InterruptedException {
        ConnectableObservable connectableObservable
                = Observable.create(e -> {
            Observable.interval(
                    10
                    , TimeUnit.MILLISECONDS
                    , Schedulers.computation()
            )
                    .take(Integer.MAX_VALUE)
                    .subscribe(e::onNext);
        })
                .observeOn(Schedulers.newThread())
                .publish();

        connectableObservable.connect();

        Observable o = connectableObservable.refCount();

        /**
         * Disposable:可以选择是否取消订阅
         */
        Disposable d1 = o.subscribe(t -> System.out.println("c1: " + t));
        Disposable d2 = o.subscribe(t -> System.out.println("   c2: " + t));

        Thread.sleep(20);

        /**
         * 取消订阅
         */
        d1.dispose();
        d2.dispose();

        System.out.println("重新发送");
        o.subscribe(t -> System.out.println("c1: " + t));
        o.subscribe(t -> System.out.println("   c2: " + t));

        Thread.sleep(20);
    }

    static void h2c2() throws InterruptedException {
        ConnectableObservable connectableObservable
                = Observable.create(e -> {
            Observable.interval(
                    10
                    , TimeUnit.MILLISECONDS
                    , Schedulers.computation()
            )
                    .take(Integer.MAX_VALUE)
                    .subscribe(e::onNext);
        })
                .observeOn(Schedulers.newThread())
                .publish();

        connectableObservable.connect();

        Observable o = connectableObservable.refCount();

        /**
         * Disposable:可以选择是否取消订阅
         */
        Disposable d1 = o.subscribe(t -> System.out.println("c1: " + t));
        Disposable d2 = o.subscribe(t -> System.out.println("   c2: " + t));

        Thread.sleep(20);

        /**
         * 仅 d1 取消订阅
         */
        d1.dispose();
        // d2.dispose();

        System.out.println("重新发送");
        o.subscribe(t -> System.out.println("c1: " + t));

        Thread.sleep(20);
    }
}
