package com.xsn.chapter1;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

public class Subject {
    public static void main(String[] args) throws InterruptedException {
        // asyncSubject();

        // behaviorSubject();

        // replaySubject1();

        publishSubject();
    }

    static void asyncSubject() {
        AsyncSubject asyncSubject = AsyncSubject.create();

        asyncSubject.onNext(1);
        asyncSubject.onNext(2);

        asyncSubject.subscribe(t -> System.out.println(t));

        asyncSubject.onNext(3);
        asyncSubject.onNext(4);
        asyncSubject.onComplete();
    }

    static void behaviorSubject() {
        BehaviorSubject behaviorSubject
                = BehaviorSubject.createDefault("default");

        // behaviorSubject.onNext(1);

        behaviorSubject.subscribe(t -> System.out.println(t));

        behaviorSubject.onNext(2);
    }

    // 发射所有消息
    static void replaySubject1() {
        ReplaySubject replaySubject = ReplaySubject.create();

        replaySubject.onNext(1);
        replaySubject.onNext(2);

        replaySubject.subscribe(t -> System.out.println(t));

        replaySubject.onNext(3);
    }

    // 缓存一条消息
    static void replaySubject2() {
        ReplaySubject replaySubject = ReplaySubject.createWithSize(1);

        replaySubject.onNext(1);
        replaySubject.onNext(2);

        replaySubject.subscribe(t -> System.out.println(t));

        replaySubject.onNext(3);
    }

    // 缓存 1s
    static void replaySubject3() throws InterruptedException {
        ReplaySubject replaySubject
                = ReplaySubject.createWithTime(
                        1
                        , TimeUnit.SECONDS
                        , Schedulers.newThread()
                );

        replaySubject.onNext(1);

        Thread.sleep(2000);

        replaySubject.subscribe(t -> System.out.println(t));

        replaySubject.onNext(3);
    }

    static void publishSubject() {
        PublishSubject publishSubject = PublishSubject.create();

        publishSubject.onNext(1);
        publishSubject.onNext(2);

        publishSubject.subscribe(t -> System.out.println(t));

        publishSubject.onNext(3);
        publishSubject.onNext(4);
    }
}
