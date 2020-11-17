package com.xsn.chapter1;

import io.reactivex.rxjava3.core.Observable;

public class Do {
    public static void main(String[] args) {
        Observable.just("test")
                // 没发射一条数据就会被调用一次，在 subscribe 发生
                .doOnNext(t -> System.out.println("doOnNtext " + t))
                // 在 Consumer#onNext 之后调用
                .doAfterNext(t -> System.out.println("doAfterNext " + t))
                // 在 Observable 正常终止时调用
                .doOnComplete(() -> System.out.println("doOnComplete"))
                // 一旦被订阅，就会被调用，返回 Disposable
                .doOnSubscribe(d -> System.out.println("doOnSubscribe " + d.isDisposed()))
                // 当 Observable 调用 onComplete 或 onError 会调用，在 doFinally 之后
                .doAfterTerminate(() -> System.out.println("doAfterTerminate"))
                // 当 Observable 调用 onComplete 或 onError 会调用
                .doFinally(() -> System.out.println("doFinally"))
                // 当 Observable 每发射一项数据，都会调用一次，返回 Notification
                .doOnEach(n -> System.out.println("doOnEach "
                        + (n.isOnNext() ? "onNext" : n.isOnComplete() ? "onComplete" : "onError")))
                // 在订阅之后，返回 Disposable，可以设置是否取消订阅
                .doOnLifecycle(
                        d -> System.out.println("doOnLifecycle " + d.isDisposed())
                        , () -> System.out.println("doOnLifecycle action"))
                .subscribe(t -> System.out.println(t));
    }
}
