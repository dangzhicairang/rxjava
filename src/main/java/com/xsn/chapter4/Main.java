package com.xsn.chapter4;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // all();

        // cai();

        // amb();

        // defaultIfEmpty();

        // sequenceEqual();

        // skipUntil();

        // skipWhile();

        // takeUntil();

        // takeWhile();

        // merge();

        // zip();

        startWith();
    }

    static void all() {
        Observable.just(1, 2, 3)
                .all(n -> n > 0)
                .subscribe(t -> System.out.println(t));
    }

    static void cai() {
        Observable.just(1, 2, 3)
                .contains(1)
                .subscribe(t -> System.out.println(t));

        Observable.just(1, 2, 3)
                .isEmpty()
                .subscribe(t -> System.out.println(t));
    }

    static void amb() throws InterruptedException {
        Observable.amb(
                new Iterable<ObservableSource<?>>() {
                    @Override
                    public Iterator<ObservableSource<?>> iterator() {
                        List list = new ArrayList();
                        list.add(Observable.just(1, 2).delay(1, TimeUnit.SECONDS));
                        list.add(Observable.just(3, 4));
                        return list.iterator();
                    }
                }
        ).subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(2);
    }

    static void defaultIfEmpty() {
        Observable.empty()
                .defaultIfEmpty("default")
                .subscribe(t -> System.out.println(t));
    }

    static void sequenceEqual() {
        Observable.sequenceEqual(
                Observable.just(1, 2)
                , Observable.just(1, 2)
        ).subscribe(t -> System.out.println(t));
    }

    static void skipUntil() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(3, TimeUnit.SECONDS))
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(5);
    }

    static void skipWhile() {
        Observable.just(1, 3, 2)
                .skipWhile(n -> n < 2)
                .subscribe(t -> System.out.println(t));
    }

    static void takeUntil() throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(Observable.timer(3, TimeUnit.SECONDS))
                .subscribe(t -> System.out.println(t));

        TimeUnit.SECONDS.sleep(3);
    }

    static void takeWhile() {
        Observable.just(1, 3, 2)
                .takeWhile(n -> n < 3)
                .subscribe(t -> System.out.println(t));
    }

    static void merge() {
        Observable.merge(
                Observable.just(1, 2)
                , Observable.just(3, 4)
        ).subscribe(t -> System.out.println(t));
    }

    static void zip() {
        Observable.zip(
                Observable.just(1, 2)
                , Observable.just(3, 4, 5)
                , (m, n) -> {
                    return m + n;
                }
        ).subscribe(t -> System.out.println(t));
    }

    static void startWith() {
        Observable.just(1)
                .startWith(Observable.just(0))
                .subscribe(t -> System.out.println(t));
    }
}
