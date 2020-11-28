package com.xsn.backpressure;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main {

    public static void main(String[] args) {
        main1();
    }

    static void main1() {
        @NonNull Disposable subscribe = Flowable.create(e -> {
            for (int i = 0; i < 129; i++) {
                e.onNext(i);
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.newThread())
                .subscribe(t -> System.out.println(t));
    }
}
