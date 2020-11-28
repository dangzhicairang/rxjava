package com.xsn.transformer;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Observable.just(1)
                //.subscribeOn(Schedulers.trampoline())
                .compose(transformer())
                .subscribe(t -> System.out.println("s=" + t));

        TimeUnit.SECONDS.sleep(1);
    }

    static ObservableTransformer transformer() {
        return o -> {
            return n -> String.valueOf(n);
        };
    }
}
