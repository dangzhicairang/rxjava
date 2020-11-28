package com.xsn.chapter3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // map();

        // flatmap();

        // groupBy();

        // buffer();

        // window();

        // fal();

        // take();

        // takeLast();

        // skipLast();

        // elementAt();

        // ignoreElements();

        // distinct();

        // distinctUntilChanged();

        // filter();

        debounce();
    }

    static void map() {
        Observable.just("a", "b")
                .map(s -> s.toUpperCase())
                .subscribe(t -> System.out.println(t));
    }

    static void flatmap() {
        Observable.just("a", "b")
                .flatMap(s -> Observable.just(s.toUpperCase()))
                .subscribe(t -> System.out.println(t));
    }

    static void groupBy() {
        Observable.range(0, 10)
                .groupBy(t -> t >= 5 ? "大" : "小")
                //.subscribeOn(Schedulers.trampoline())
                .subscribe(g -> {
                    System.out.println();
                    System.out.println(g.getKey());
                    g
                            //.subscribeOn(Schedulers.trampoline())
                            .subscribe(t -> System.out.print(t));
                });
    }

    static void buffer() {
        Observable.range(1, 10)
                .buffer(2)
                .subscribe(t -> System.out.println(t));

        /*Observable.range(1, 10)
                .buffer(5, 1)
                .subscribe(t -> System.out.println(t));*/
    }

    static void window() {
        Observable.range(1, 10)
                .window(2)
                .subscribe(o -> {
                    o.subscribe(t -> System.out.print(t));
                    System.out.println("");
                    System.out.println("===============");
                });
    }

    static void fal() {
        Observable.just(1, 2, 3)
                .first(0)
                .subscribe(t -> System.out.println(t));

        Observable.empty()
                .last("default")
                .subscribe(t -> System.out.println(t));
    }

    static void take() throws InterruptedException {
        /*Observable.just(1, 2, 3)
                .take(2)
                .subscribe(t -> System.out.println(t));*/

        Observable.interval(1, TimeUnit.SECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(6);
    }

    static void takeLast() throws InterruptedException {
        Observable.just(1, 2, 3)
                .takeLast(2)
                .subscribe(t -> System.out.println(t));

        Observable.intervalRange(0, 5, 1, 1, TimeUnit.SECONDS)
                .takeLast(3, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(6);
    }

    static void skip() throws InterruptedException {
        Observable.just(1, 2, 3)
                .skip(2)
                .subscribe(t -> System.out.println(t));

        Observable.intervalRange(0, 5, 1, 1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(5);
    }

    static void skipLast() throws InterruptedException {
        Observable.just(1, 2, 3)
                .skipLast(2)
                .subscribe(t -> System.out.println(t));

        Observable.intervalRange(0, 5, 1, 1, TimeUnit.SECONDS)
                .skipLast(3, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(5);
    }

    static void elementAt() {
        Observable.just(1, 2, 3)
                .elementAt(1)
                .subscribe(t -> System.out.println(t));

        Observable.just(1, 2, 3)
                .elementAt(3, 0)
                .subscribe(t -> System.out.println(t));
    }

    static void ignoreElements() {
        Observable.just("test")
                .ignoreElements()
                .subscribe(
                       () -> System.out.println("complete")
                        , e -> System.out.println("error")
                );
    }

    static void distinct() {
        Observable.just("a", "a", "A")
                .distinct(s -> s.toUpperCase())
                .subscribe(t -> System.out.println(t));
    }

    static void distinctUntilChanged() {
        Observable.just(1, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(t -> System.out.println(t));
    }

    static void filter() {
        Observable.just(1, 2, 3)
                .filter(n -> n > 1)
                .subscribe(t -> System.out.println(t));
    }

    static void debounce() throws InterruptedException {
        Observable.create(e -> {
            if (!e.isDisposed()) {
                for (int i = 0; i < 5; i++) {
                    e.onNext(i);
                    TimeUnit.SECONDS.sleep(i);
                }
            }
        })
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(30);
    }
}
