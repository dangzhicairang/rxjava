package com.xsn.chapter2;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // create();

        // just();

        // fromFuture();

        // defer();

        // interval();

        // timer();

        // repeat();

        // repeatWhen();

        repeatUtil();
    }

    static void create() {
        Observable.create(e -> {
            // 检查观察者的订阅状态
            if (!e.isDisposed()) {
                IntStream.rangeClosed(0, 10)
                        .forEach(i -> e.onNext(i));
                e.onComplete();
            }
        }).subscribe(t -> System.out.println(t));
    }

    static void just() {
        Observable.just(1, 2).subscribe(t -> System.out.println(t));
    }

    static void from() {
        Observable.fromArray("1", "2").subscribe(t -> System.out.println(t));

        Observable.fromIterable(
                new ArrayList<Integer>() {
                    {
                        add(1);
                        add(2);
                    }
                }
        ).subscribe(t -> System.out.println(t));
    }

    static void fromFuture() {
        FutureTask future = new FutureTask(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "done";
        });
        new Thread(future).start();

        // 2s 超时
        Observable.fromFuture(future, 2, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));
    }

    static void defer() {
        Observable.defer(() -> {
            return Observable.just("test");
        }).subscribe(t -> System.out.println(t));
    }

    static void interval() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(5);
    }

    static void timer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));
    }

    static void repeat() {
        Observable.just("test")
                .repeat(2)
                .subscribe(t -> System.out.println(t));
    }

    static void repeatWhen() throws InterruptedException {
        Observable.just("test")
                .repeatWhen(o -> {
                    return Observable.timer(3, TimeUnit.SECONDS);
                })
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(5);
    }

    static void repeatUtil() {
        AtomicInteger i = new AtomicInteger(0);

        Observable.just("test")
                .repeatUntil(() -> {
                    return i.incrementAndGet() > 5;
                }).subscribe(t -> System.out.println(t));
    }
}
