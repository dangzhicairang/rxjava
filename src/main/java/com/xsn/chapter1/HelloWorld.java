package com.xsn.chapter1;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class HelloWorld {

    public static void main(String[] args) {

        Observable.just("hello world")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        System.out.println(s);
                    }
                });

        // Java 8
        // Observable.just("hello world").subscribe(t -> System.out.println(t));

        /**
         * subscribe 的重载
         * Observable#subscribe(Consumer onNext, Consumer onError, Action onComplete)
         */
        Observable.just("hello world 2")
                .subscribe(
                        // onNext
                        new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Throwable {
                                System.out.println(s);
                            }
                        }
                        // onError
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                System.out.println("error");
                            }
                        }
                        // onComplete
                        , new Action() {
                            @Override
                            public void run() throws Throwable {
                                System.out.println("onComplete");
                            }
                        }
                );

        // Java8
        /*Observable.just("test2")
                .subscribe(
                        t -> System.out.println(t)
                        , e -> System.out.println("error")
                        , () -> System.out.println("complete")
                );*/
    }
}
